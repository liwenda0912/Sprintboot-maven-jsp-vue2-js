import {request} from "./utils/request.js";

var Min = new Vue({
    el: '#APP_input',
    data() {
        return {
            //输入框rules校验
            ruleForm: {
                input: '',
                input_passwd: '',
                submit_show: '',
                code: 1,
                stateCode: 0,
            },
            rules: {
                input: [
                    {required: true, message: '账户不能为空', trigger: 'blur'},
                ],
                input_passwd: [
                    {required: true, message: '密码不能为空', trigger: 'blur'},
                    {min: 8, max: 16, message: '密码长度为8到16位！', trigger: 'blur'}
                ]
            }
        }
    },
    methods: {
        //登录方法
        submit(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    this.login_()
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        login_(){
            let self_ = this;
            window.location.href = ("../../page/public/Result.jsp?data=" + 1 + "&message=" + "登录成功")
                // 向登录servlet请求登录申请
                // request({
                //     method: 'Post',
                //     url: 'LoginServlet',
                //     params: {
                //         username: self_.username,
                //         passwd: self_.password,
                //     }
                // }).then(res => {
                //     //获取登录servlet的登录请求响应
                //     var data = res.data;
                //     window.location.href = ("Result.jsp?data=" + data.code + "&message=" + data.message)
                // }, err => {
                //     console.log(err.message);
                //     self_.$message({
                //         message: err.message,
                //         type: "error",
                //         center: true
                //     })
                // }).catch(error => {
                //     console.error(error.message);
                //     self_.$message({
                //         message: error.message,
                //         type: "error",
                //         center: true
                //     })
                // });
         }
        }
});



// 注册监听
var register = new Vue({
    el: "#app-2",
    methods: {
        register() {
            dialog_.$data.dialogFormVisible=true;
        },

    }
});

var dialog_ = new Vue({
    data() {
        return {
            dialogFormVisible: false,
            dialogCode: 0,
            type_data: {
                address: "",
                city: "",
                province: "",
                ruleForm: {
                    phone: "",
                    zip:'',
                    username: '',
                    password: '',
                    region: '',
                    delivery: false,
                    type: [],
                    resource: '',
                    desc: ''
                }
            },
            // 输入框校验规则
            rules: {
                phone:[{
                    pattern:/^((0\d{2,3}-\d{7,8})|(1[34578]\d{9}))$/, message: '手机号码不合法', trigger: 'blur'
                }],
                zip:[{min: 6, max: 6, message: '邮政编码只能填写6位', trigger: 'blur'}],
                username: [
                    {required: true, message: '账户不能为空', trigger: 'blur'},
                ],
                password: [
                    {required: true, message: '密码不能为空', trigger: 'blur'},
                    {min: 8, max: 16, message: '密码长度为8到16位！', trigger: 'blur'}
                ]
            },
            formLabelWidth: '120px'
        };
    },
    el: '#app-1',
    methods: {
        test_() {
            this.$router.push({path: '/Result'})
        },
        registered(formName) {
            // rules校验输入框
            this.$refs[formName].validate((valid) => {
                if ((valid)) {
                    let self = this
                    //调用servlet服务
                    axios({
                        method: 'Post',
                        url: '/User/insert',
                        headers: {
                            'Content-Type': 'application/json',
                            "Authorization":'Access-Control-Request-Headers'
                        },
                        data: this.type_data
                    }).then(res => {
                        //获取servlet响应的信息
                        let data = res.data;
                        console.log(data)
                        if (data.code===200){
                            this.$message({
                                message: data.message,
                                center: true,
                                type: "success"
                            });
                        }else {
                            this.$message({
                                message: data.message,
                                center: true,
                                type: 'error'
                            });
                        }
                    }, err => {
                        console.log(err.message);
                        this.$message({
                            message: err.message,
                            type: "error",
                            center: true
                        })
                    }).catch(error => {
                        console.error(error.message);
                        this.$message({
                            message: error.message,
                            type: "error",
                            center: true
                        })
                    });
                    // 关闭注册界面并清除输入框
                    self.$data.dialogFormVisible = false;
                    this.$refs.passwd.clear();
                    this.$refs.user.clear();
                    this.$refs.city.clear();
                    this.$refs.address.clear();
                    this.$refs.province.clear();
                    this.$refs.phone.clear();
                } else {
                    // 清除输入框
                    // this.$refs.passwd.clear();
                    // this.$refs.user.clear();
                    // this.$refs.city.clear();
                    // this.$refs.address.clear();
                    // this.$refs.province.clear();
                    // this.$refs.phone.clear();
                    return false;
                }
            })
        },
        quit() {
            let self = this
            self.$data.dialogFormVisible = false;
            this.$refs.passwd.clear();
            this.$refs.user.clear();
            this.$refs.city.clear();
            this.$refs.address.clear();
            this.$refs.province.clear();
            this.$refs.phone.clear();
            this.$message({
                message: '取消注册成功！',
                center: true
            });
        },


    }
});
