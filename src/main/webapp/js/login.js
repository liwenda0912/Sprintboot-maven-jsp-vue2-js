import {request} from "./utils/request.js";
import {setCookie} from "./utils/Cookie.js"
import {operations} from "./utils/message.js";
import {getDecryptData, getSecretData} from "./utils/crypto.js";
import {jsonStringSwitch} from "./utils/dataHandle.js";
import router from "./utils/router.js";

// vue2中的设置方式
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
                    { pattern: /^(?! ).*(?<! )$/, message: '输入内容不得有空格', trigger: 'blur' }

                ],
                input_passwd: [
                    {required: true, message: '密码不能为空', trigger: 'blur'},
                    {min: 8, max: 16, message: '密码长度为8到16位！', trigger: 'blur'},
                    { pattern: /^(?! ).*(?<! )$/, message: '输入内容不得有空格', trigger: 'blur' }
                ]
            }
        }
    },
    methods: {
        //登录方法
        submit(formName) {
            let self_= this;
            self_.$refs[formName].validate((valid) => {
                if (valid) {
                    self_.login_();
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        login_() {
            let self_ = this;
            let data_= {
                    username: self_.$data.ruleForm.input,
                    password: self_.$data.ruleForm.input_passwd
                };
            // 向登录servlet请求登录申请
            request({
                method: 'Post',
                url: '/User/login',
                headers: {
                    'Content-Type': 'application/json',
                    "Authorization": 'Access-Control-Request-Headers'
                },
                data: getSecretData(jsonStringSwitch("String",data_))
            }).then(res => {
                let dataTokens = jsonStringSwitch("Json",getDecryptData(res.data.data))
                console.log(dataTokens)
                //获取登录servlet的登录请求响应
                if (res.data.code===200 &&dataTokens.state === true && dataTokens.state != null) {
                    //
                    setCookie(dataTokens.token, dataTokens.refresh_token)
                    operations("success",dataTokens.msg,self_)
                    // console.log(res)
                    setTimeout(r => {
                        let data = {
                            code:res.data.code,
                            message:dataTokens.msg
                        }
                        // window.location.href = ("page/public/Result.jsp?data=" + res.data.code + "&message=" + dataTokens.msg)
                        window.location.href = `page/public/Result.jsp#${encodeURIComponent(JSON.stringify(data))}`;

                    }, 2000)
                } else if(res.data.code===200) {
                    operations("error",res.data.data.msg,self_)
                }else {
                    operations("error", res.data.message,self_)
                }
            }).catch(err => {
                operations("error", err.message,self_)
            });
        }
    }
});


// 注册监听
var register = new Vue({
    el: "#app-2",
    methods: {
        register() {
            dialog_.$data.dialogFormVisible = true;
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
                    zip: '',
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
                phone: [{
                    pattern: /^((0\d{2,3}-\d{7,8})|(1[34578]\d{9}))$/, message: '手机号码不合法', trigger: 'blur'
                }],
                zip: [{min: 6, max: 6, message: '邮政编码只能填写6位', trigger: 'blur'}],
                username: [
                    {required: true, message: '账户不能为空', trigger: 'blur'},
                    { pattern: /^(?! ).*(?<! )$/, message: '输入内容不得有空格', trigger: 'blur' }
                ],
                password: [
                    {required: true, message: '密码不能为空', trigger: 'blur'},
                    {min: 8, max: 16, message: '密码长度为8到16位！', trigger: 'blur'},
                    { pattern: /^(?! ).*(?<! )$/, message: '输入内容不得有空格', trigger: 'blur' }
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
                            "Authorization": 'Access-Control-Request-Headers'
                        },
                        data: this.type_data
                    }).then(res => {
                        //获取servlet响应的信息
                        let data = res.data;
                        console.log(data)
                        if (data.code === 200) {
                            this.$message({
                                message: data.message,
                                center: true,
                                type: "success"
                            });
                        } else {
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
                    self.$refs.passwd.clear();
                    self.$refs.user.clear();
                    self.$refs.city.clear();
                    self.$refs.address.clear();
                    self.$refs.province.clear();
                    self.$refs.phone.clear();
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
