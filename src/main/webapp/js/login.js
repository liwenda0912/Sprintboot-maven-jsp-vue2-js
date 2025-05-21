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

