//监听iframe父页面传递的数据
import {request} from "./utils/request.js";
import {height_adjust} from "./utils/height_adjust.js";
import {reloadLogin} from "./utils/reloadLogin.js";
import {operations} from "/js/utils/message.js";
import {getDecryptData, getSecretData} from "./utils/crypto.js";
import {jsonStringSwitch} from "./utils/dataHandle.js";
import {timestamp} from "./utils/formatTime.js";


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
            activeNames: "",
            value3:0,
            input1: "",
            input2: '',
            loading: '',
            pageShowNum: 10,
            pageNum_1: 1,
            dialogVisible: false,
            dialogVisiblePassword:false,
            dialogFormVisible: false,
            delivery: false,
            formLabelWidth: '120px',
            type: [],
            form: {},
            test: [],
            index_: null,
            te_: [],
            id_:'',
            data_:{},
            //输入框rules校验
            form_password:{
                NewPassword:'',
            },
            rule_password: {
                NewPassword: [
                    {required: true, message: '账户不能为空', trigger: 'blur'},
                    {min: 8, max: 16, message: '密码长度为8到16位！', trigger: 'blur'},
                    { pattern: /^(?! ).*(?<! )$/, message: '输入内容不得有空格', trigger: 'blur' }
                ],

            }
        }
    },
    mounted() {
        this.loading = true
        this.onshow();
    },
    methods: {
        cleartext() {
                let self_ = this
                self_.$data.value3 = 0
                self_.$data.input1 = ''
                self_.$data.input2 = ''
        },
        search(){
            let self_ = this
            request({
                method: 'Post',
                url: '/User/search',
                data: {
                    pageNum: self_.$data.pageNum_1,
                    pageSize: self_.$data.pageShowNum,
                    datetime: timestamp(self_.$data.value3!=null?self_.$data.value3:0),
                    address: self_.$data.input1,
                    username: self_.$data.input2,
                },
                headers: {
                    'Content-Type': 'application/json',
                    "Authorization": 'Access-Control-Request-Headers'
                },
            }).then(res => {
                if (res.data.code === 200 && res.data.message === null ) {
                    self_.loading = false;
                    self_.$message({
                        message: res.data.data.msg,
                        type: "error",
                        center: true
                    })
                    // reloadLogin();
                } else if (res.data.code === 200 && res.data.message != null && res.data.data!=null ) {
                    // 获取后端数据解密后的数据
                    let dataDecryptData =jsonStringSwitch("Json",getDecryptData(res.data.data))
                    self_.test = dataDecryptData.list;
                    // 给分页器传数据总量值
                    self_.send(dataDecryptData.total);
                    self_.loading=false;
                } else {
                    self_.$message({
                        message: res.data.message,
                        type: "error",
                        center: true
                    });
                }
            }).catch(error => {
                self_.loading = false
                self_.$message({
                    message: error.message,
                    type: "error",
                    center: true
                })
            });
        },
        //修改密码
        EditPs_(msg){
            let self_ = this
            self_.$data.id_ = msg.id
            self_.$data.dialogVisiblePassword=true
        },
        EditPs(formName){
            let self_ = this
            this.$refs[formName].validate((valid) => {
                if (valid) {

                    self_.$data.data_.password=self_.$data.form_password.NewPassword
                    self_.$data.data_.id=self_.$data.id_
                    request({
                        method: 'Post',
                        url: '/User/editPassword',
                        headers: {
                            'Content-Type': 'application/json',
                            "Authorization": 'Access-Control-Request-Headers'
                        },
                        data: getSecretData(jsonStringSwitch("String",self_.$data.data_))
                    }).then(res=>{
                        if (res.data.code === 200 && res.data.message==="操作成功") {
                            self_.$data.dialogVisiblePassword=false
                            operations("success",res.data.message,self_)
                            self_.$data.dialogVisiblePassword=false
                        }else if(res.data.code === 200 && res.data.message===null) {
                            operations("error",res.data.message,self_)
                            self_.$data.dialogVisiblePassword=false
                            reloadLogin()
                        }else {
                            operations("error",res.data.message,self_)
                            self_.$data.dialogVisiblePassword=false
                        }
                    }).catch(e=>{
                        operations("error",e.message,self_)
                        self_.$data.dialogVisiblePassword=false
                    })
                } else {
                    return false;
                }
            });
         self_.$refs.NewPassword.clear()
        },
        //控制列表的页面样式的方法
        handleChange(val) {
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
                data: getSecretData(jsonStringSwitch("String",type))
            }).then(res => {
                if (res.data.code === 200 && res.data.message === "操作成功") {
                    self_.dialogVisible = false;
                    self_.$message({
                        type: "success",
                        center: true,
                        message: res.data.message,
                    });
                    //修改table的数组数据并同步渲染
                    self_.$set(self_.test, self_.$data.index_, jsonStringSwitch("Json",getDecryptData(res.data.data)));
                } else if (res.data.code === 200 && res.data.message === null) {
                    self_.loading = false;
                    self_.$message({
                        message: res.data.data.msg,
                        type: "error",
                        center: true
                    });
                    reloadLogin();
                } else {
                    self_.$message({
                        type: "error",
                        center: true,
                        message: res.data.message,
                    })
                }
            }, err => {
                self_.$message({
                    message: err.message,
                    type: "error",
                    center: true
                })
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
                if (res.data.code === 200 && res.data.message === null ) {
                    self_.loading = false;
                    self_.$message({
                        message: res.data.data.msg,
                        type: "error",
                        center: true
                    })
                    reloadLogin();
                } else if (res.data.code === 200 && res.data.message != null && res.data.data!=null ) {
                    // 获取后端数据解密后的数据
                    let dataDecryptData =jsonStringSwitch("Json",getDecryptData(res.data.data))
                    self_.test = dataDecryptData.list;
                    // 给分页器传数据总量值
                    self_.send(dataDecryptData.total);
                    self_.loading=false;
                } else {
                    self_.$message({
                        message: res.data.message,
                        type: "error",
                        center: true
                    });
                }
            }).catch(error => {
                self_.loading = false
                self_.$message({
                    message: error.message,
                    type: "error",
                    center: true
                })
            });
        }
    },
    //监听函数
    watch: {
        //监听列表展示的数
        pageNum_1: function (newData, oldData) {
            let self_ = this
            console.log(newData)
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
