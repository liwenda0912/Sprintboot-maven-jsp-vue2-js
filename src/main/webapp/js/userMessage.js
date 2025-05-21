//监听iframe父页面传递的数据
import {request} from "./utils/request.js";
import {adjustScrollHeight, height_adjust} from "./utils/height_adjust.js";
import {reloadLogin} from "./utils/reloadLogin.js";
import {operations} from "/js/utils/message.js";
import {getDecryptData, getSecretData} from "./utils/crypto.js";
import {jsonStringSwitch, roundUpFirst} from "./utils/dataHandle.js";
import {timestamp} from "./utils/formatTime.js";
import {debounce, scrollEventC} from "./utils/debounce.js";
import {dialog_} from "./register.js"


window.addEventListener("message", function (e) {
    if (e.data.split(":")[0] === "page") {
        User.$data.pageNum_1 = e.data.split(":")[1]
    } else {
        User.$data.pageShowNum = parseInt(e.data.split(":")[1])
    }
}, false);


var User = new Vue({
    el: "#app_tabs",
    data() {
        return {
            activeNames: [],
            value3: 0,
            input1: "",
            input2: '',
            loading: '',
            pageShowNum: 10,
            pageNum_1: 1,
            dialogVisible: false,
            provinceColumns: [],
            cityColumns: [],
            cityPage: "",
            cityPageSize: "",
            cityTotalPage: "",
            loading_top: false,
            loading_bottom: false,
            dialogFormVisible: false,
            dialogVisiblePassword:false,
            delivery: false,
            formLabelWidth: '120px',
            label_:"",
            type: [],
            form: {},
            test: [],
            index_: null,
            te_: [],
            id_: '',
            data_: {},
            dom:'',
            //输入框rules校验
            form_password: {
                NewPassword: '',
            },
            rule_password: {
                NewPassword: [
                    {required: true, message: '账户不能为空', trigger: 'blur'},
                    {min: 8, max: 16, message: '密码长度为8到16位！', trigger: 'blur'},
                    {pattern: /^(?! ).*(?<! )$/, message: '输入内容不得有空格', trigger: 'blur'}
                ],

            }
        }
    },
    mounted() {
        this.loading = true
        this.onshow();
        this.$data.dom = window.document.getElementsByClassName("el-select-dropdown__wrap el-scrollbar__wrap")
    },
    methods: {
        handle(){
            dialog_.dialogRegisterFormVisible = true;
        },
        handle_(){
            let self_ =this
            this.$data.activeNames="1";
            let val = [1]
            self_.public_(val)
        },
        // 回调函数使用
        city(cityPageNum) {
            let self_ = this
            self_.cityListInfo({
                "pageNum": cityPageNum,
                "pageSize": 10,
            })
        },
        province(PageNum) {
            let self_ = this
            self_.ProvinceListInfo({
                "pageNum": PageNum,
                "pageSize": 10,
            })
        },
        scrollEvent(event, name) {
            let self_ = this
            if (name === "province") {
                let list_ = {
                    Page: self_.$data.cityPage,
                    PageSize: self_.$data.cityPageSize,
                    TotalPage: self_.$data.cityTotalPage,
                    loading_top: self_.$data.loading_top,
                    loading_bottom: self_.$data.loading_bottom
                }
                // 回调函数
                scrollEventC(self_.province, event, self_, list_)
            } else {
                let list_ = {
                    Page: self_.$data.cityPage,
                    PageSize: self_.$data.cityPageSize,
                    TotalPage: self_.$data.cityTotalPage,
                    loading_top: self_.$data.loading_top,
                    loading_bottom: self_.$data.loading_bottom
                }
                // 回调函数
                scrollEventC(self_.city, event, self_, list_)
            }
        },
        handleScroll: function (val, name) {
            let self_ = this
            //这里注释的三个类,主要是抓的弹窗中的popover类,
            //一般情况下都可以使用,我这里使用的是 [el-scrollbar__wrap]
            // el-select-dropdown__wrap el-scrollbar__wrap el-scrollbar__wrap--hidden-default
            setTimeout(() => {
                const dom = document.querySelector(".el-scrollbar__wrap")
                // dom.style.overflowY = 'scroll';
                if (name !== "province") {
                    // 城市选择栏的滑动监听
                    if (!val) {
                        self_.$data.cityColumns = []
                        document.removeEventListener('scroll', (event) => self_.scrollEvent(event, name), true)
                    } else {
                        self_.cityListInfo({
                            "pageNum": 1,
                            "pageSize": 10,
                            "name": self_.$data.type.city
                        })
                        adjustScrollHeight(self_.$data.cityColumns,this.$data.dom)
                        document.addEventListener('scroll', debounce((event) => self_.scrollEvent(event, name), 1000), true);
                    }
                } else {
                    // 省选择栏的滑动监听
                    if (!val) {
                        self_.$data.provinceColumns = []
                        document.removeEventListener('scroll', debounce((event) => self_.scrollEvent(event, name), 1000), true)
                    } else {
                        adjustScrollHeight(self_.$data.provinceColumns,this.$data.dom)
                        self_.ProvinceListInfo({
                            "pageNum": 1,
                            "pageSize": 10,
                            "name": self_.$data.type.province
                        })
                        document.addEventListener('scroll', debounce((event) => self_.scrollEvent(event, name), 1000), true);
                    }
                }
            }, 1000)
        },
        ProvinceListInfo(data) {
            let self_ = this;
            request({
                method: "Post",
                url: "/Zone/ProvinceList",
                headers: {
                    'Content-Type': 'application/json',
                    "Authorization": 'Access-Control-Request-Headers'
                },//数据加密提交给接口
                data: data
            }).then(res => {
                let data_ = jsonStringSwitch("Json", getDecryptData(res.data.data))
                adjustScrollHeight(data_.list,this.$data.dom)
                self_.$data.provinceColumns = data_.list
                self_.$data.cityPage = data_.pageNum
                self_.$data.cityPageSize = data_.pageSize
                self_.$data.cityTotalPage = data_.total
            }).catch(e => {
                operations("error", e.message, self_)
            })
        },
        cityListInfo(data) {
            let self_ = this;
            request({
                method: "Post",
                url: "/Zone/cityList",
                headers: {
                    'Content-Type': 'application/json',
                    "Authorization": 'Access-Control-Request-Headers'
                },//数据加密提交给接口
                data: data
            }).then(res => {
                let self_ = this;
                let data_ = jsonStringSwitch("Json", getDecryptData(res.data.data))
                self_.$data.cityColumns = data_.list
                adjustScrollHeight(data_.list,this.$data.dom)
                self_.$data.cityPage = data_.pageNum
                self_.$data.cityPageSize = data_.pageSize
                self_.$data.cityTotalPage = data_.total
            }).catch(e => {
                operations("error", e.message, self_)
            })
        },
        cleartext() {
            let self_ = this
            self_.$data.value3 = 0
            self_.$data.input1 = ''
            self_.$data.input2 = ''
        },
        search() {
            let self_ = this
            request({
                method: 'Post',
                url: '/User/search',
                data: {
                    pageNum: self_.$data.pageNum_1,
                    pageSize: self_.$data.pageShowNum,
                    datetime: timestamp(self_.$data.value3 != null ? self_.$data.value3 : 0),
                    address: self_.$data.input1,
                    username: self_.$data.input2,
                },
                headers: {
                    'Content-Type': 'application/json',
                    "Authorization": 'Access-Control-Request-Headers'
                },
            }).then(res => {
                if (res.data.code === 200 && res.data.message === null) {
                    self_.loading = false;
                    operations("error", res.data.data.msg, self_)
                    // reloadLogin();
                } else if (res.data.code === 200 && res.data.message != null && res.data.data != null) {
                    // 获取后端数据解密后的数据
                    let dataDecryptData = jsonStringSwitch("Json", getDecryptData(res.data.data))
                    self_.test = dataDecryptData.list;
                    // 给分页器传数据总量值
                    self_.send(dataDecryptData.total);
                    self_.loading = false;
                } else {
                    operations("error", res.data.message, self_)
                }
            }).catch(error => {
                self_.loading = false
                operations("error", error.message, self_)
            });
        },
        //修改密码
        EditPs_(msg) {
            let self_ = this
            self_.$data.id_ = msg.id
            self_.$data.dialogVisiblePassword = true
        },
        EditPs(formName) {
            let self_ = this
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    self_.$data.data_.password = self_.$data.form_password.NewPassword
                    self_.$data.data_.id = self_.$data.id_
                    request({
                        method: 'Post',
                        url: '/User/editPassword',
                        headers: {
                            'Content-Type': 'application/json',
                            "Authorization": 'Access-Control-Request-Headers'
                        },
                        data: getSecretData(jsonStringSwitch("String", self_.$data.data_))
                    }).then(res => {
                        if (res.data.code === 200 && res.data.message === "操作成功") {
                            self_.$data.dialogVisiblePassword = false
                            operations("success", res.data.message, self_)
                            self_.$data.dialogVisiblePassword = false
                        } else if (res.data.code === 200 && res.data.message === null) {
                            operations("error", res.data.message, self_)
                            self_.$data.dialogVisiblePassword = false
                            reloadLogin()
                        } else {
                            operations("error", res.data.message, self_)
                            self_.$data.dialogVisiblePassword = false
                        }
                    }).catch(e => {
                        operations("error", e.message, self_)
                        self_.$data.dialogVisiblePassword = false
                    })
                } else {
                    return false;
                }
            });
            self_.$refs.NewPassword.clear()
        },
        //控制列表的页面样式的方法
        handleChange(val) {
            let self_ = this
            this.$data.activeNames=[]
            self_.public_(val)
        },
        public_(val){
            var name_ = window.top.document.getElementsByClassName("border");
            var name_id = window.parent.document.getElementsByClassName("app_tabs_");
            var name = window.parent.document.getElementsByClassName("el-tabs el-tabs--top el-tabs--border-card");
            height_adjust(val, name, [], name_)
        },
        //页面查看按钮的方法
        handleClick(row) {
            let self_ = this
            self_.$data.type = row;
            this.dialogFormVisible = true;
        },
        //页面编辑按钮的方法
        edit(row, index) {
            let self_ = this
            // self_.$data.type=row
            self_.$data.type = JSON.parse(JSON.stringify(row));
            self_.dialogVisible = true;
            self_.index_ = index

        },
        // iframe的父传递信息给子页面传递数据的方法
        send(data) {
            let frame_pagination = document.getElementById("iframe_pagination");
            frame_pagination.contentWindow.postMessage(data, 'http://127.0.0.1:8090/page/public/pagination.jsp');
            frame_pagination.onload = function () {
                frame_pagination.contentWindow.postMessage(data, 'http://127.0.0.1:8090/page/public/pagination.jsp\n');
            }
        },
        // 编辑保持方法
        EditTable(type) {
            let self_ = this
            self_.dialogVisible = false
            request({
                method: 'Post',
                url: '/User/edit',
                headers: {
                    'Content-Type': 'application/json',
                    "Authorization": 'Access-Control-Request-Headers'
                },//数据加密提交给接口
                data: getSecretData(jsonStringSwitch("String", type))
            }).then(res => {
                if (res.data.code === 200 && res.data.message === "操作成功") {
                    self_.dialogVisible = false;
                    self_.$message({
                        type: "success",
                        center: true,
                        message: res.data.message,
                    });
                    //修改table的数组数据并同步渲染
                    self_.$set(self_.test, self_.$data.index_, jsonStringSwitch("Json", getDecryptData(res.data.data)));
                } else if (res.data.code === 200 && res.data.message === null) {
                    self_.loading = false;
                    console.log(res.data.data.msg)
                    operations("error", res.data.data.msg, self_)
                    reloadLogin();
                } else {
                    operations("error", res.data.message, self_)
                }
            }, err => {
                operations("error", err.message, self_)
            });
        },
        //页面加载就获取数据
        onshow() {
            let self_ = this
            request({
                method: 'Post',
                url: '/User/list',
                data: {
                    pageNum: self_.$data.pageNum_1,
                    pageSize: self_.$data.pageShowNum
                },
                headers: {
                    'Content-Type': 'application/json',
                    "Authorization": 'Access-Control-Request-Headers'
                },
            }).then(res => {
                if (res.data.code === 200 && res.data.message === null) {
                    self_.loading = false;
                    operations("error", res.data.data.msg, self_)
                    reloadLogin();
                } else if (res.data.code === 200 && res.data.message != null && res.data.data != null) {
                    // 获取后端数据解密后的数据
                    let dataDecryptData = jsonStringSwitch("Json", getDecryptData(res.data.data))
                    self_.test = dataDecryptData.list;
                    console.log(dataDecryptData)

                    // 给分页器传数据总量值
                    self_.send(dataDecryptData.total);
                    self_.loading = false;
                } else {
                    operations("error", res.data.message, self_)
                }
            }).catch(error => {
                self_.loading = false
                operations("error", error.message, self_)
            });
        }
    },
    //监听函数
    watch: {
        //监听列表展示的数
        pageNum_1: function (newData, oldData) {
            let self_ = this
            if (newData !== oldData) {
                self_.loading = true
                self_.test = [];
                self_.onshow();
            }
        },
        //监听列表页数
        pageShowNum: function (newData, oldData) {
            let self_ = this
            if (newData !== oldData) {
                self_.test = [];
                self_.onshow();
            }
        }
    },
})


