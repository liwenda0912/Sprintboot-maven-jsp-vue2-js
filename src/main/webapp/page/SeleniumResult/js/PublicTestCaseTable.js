import {height_adjust} from "../../../js/utils/height_adjust.js";
import {request} from "../../../js/utils/request.js";
import {formatter} from "../../../js/utils/formatTime.js";
import {getCookie, getCookieRefresh, setCookie, tokenDetect} from "../../../js/utils/Cookie.js";
import {authDentifiy} from "../../../js/utils/authDentifiy.js";
import {reloadLogin} from "../../../js/utils/reloadLogin.js";
import {operations} from "../../../js/utils/message.js";
// import moment from "moment";
window.addEventListener("message", function (e) {
    if (e.data.split(":")[0] === "page") {
        publicTestCase_tabs.$data.pageNum = e.data.split(":")[1]
    } else {
        publicTestCase_tabs.$data.pageShowNum = parseInt(e.data.split(":")[1])
    }
}, false);

let publicTestCase_tabs = new Vue({
    el: "#publicTestCase_tabs",
    data() {
        return {
            activeNames: "",
            value3: "",
            input1: "",
            input2: '',
            loading: '',
            dialogVisible: false,
            dialogFormVisible: false,
            delivery: false,
            formLabelWidth: '120px',
            type: [],
            form: {},
            test: [],
            tableData: [],
            pageShowNum: 10,
            pageNum: 1,
        }
    },
    mounted() {
        this.loading = true
        this.onshow();
    },
    methods: {
        onshow() {
            let self = this;
            self.data_();
        },
        handleChange(val) {
            var name_ = window.top.document.getElementsByClassName("border");
            var name_id = window.parent.document.getElementsByClassName("app_tabs_");
            var name = window.parent.document.getElementsByClassName("el-tabs el-tabs--top el-tabs--border-card");
            height_adjust(val, name, name_id, name_)
        },
        send(data) {
            let frame_pagination = document.getElementById("iframe_seleniumTestCase_pagination");
            frame_pagination.contentWindow.postMessage(data, 'http://127.0.0.1:8090/page/public/pagination.jsp');
            frame_pagination.onload = function () {
                frame_pagination.contentWindow.postMessage(data, 'http://127.0.0.1:8090/page/public/pagination.jsp\n');
            }
        },
        dateFormat(row, column) {
            return formatter(row, column)
        },
        ShowDetail(row) {
            console.log(row.id)
            let self_ = this
            self_.dialogVisible = true;
        },
        data_() {
            let self_ = this;
            request({
                method: 'Post',
                url: '/testCaseTotal',
                // headers: {
                //     'Content-Type': 'application/json',
                //     "Authorization":'Access-Control-Request-Headers'
                // },
                data: {
                    pageNum: self_.$data.pageNum,
                    pageSize: self_.$data.pageShowNum
                },
            }).then(res => {
                if (res.data.code === 200 && res.data.message != null && res.data.message.indexOf("过期")) {
                    self_.loading = false;
                    operations("error", res.data.message, self_)
                    // self_.$message({
                    //     message:res.data.message,
                    //     type:"error",
                    //     center:true
                    // })
                    reloadLogin();
                } else if (res.data.code === 200 && res.data.message === null) {
                    self_.tableData = res.data.data.list
                    self_.send(res.data.data.total);
                    setTimeout(() => {
                        self_.loading = false;
                    }, 2000);
                } else {
                    operations("error", res.data.message, self_)
                    //
                    // self_.$message({
                    //     message:res.data.message,
                    //     type:"error",
                    //     center:true
                    // });
                }
            }).catch(error => {
                self_.loading = false,
                    operations("error", error.message, self_)
                //
                // self_.$message({
                //     message: error.message,
                //     type: "error",
                //     center: true
                // })
            });
        }
    },
    watch: {
        pageNum: function (newData, oldData) {
            if (newData !== oldData) {
                this.loading = true
                this.test = [];
                this.onshow();
            }
        },
        pageShowNum: function (newData, oldData) {
            if (newData !== oldData) {
                this.test = [];
                this.onshow();
            }
        }
    }
})