import {request} from "./utils/request.js";
import {operations} from "./utils/message.js";
import {reloadLogin} from "./utils/reloadLogin.js";
import {jsonStringSwitch} from "./utils/dataHandle.js";
import {getSecretData} from "./utils/crypto.js";

export  const dialog_ = new Vue({
    data() {
        return {
            dialogRegisterFormVisible: false,
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
        registered(formName) {
            // rules校验输入框
            this.$refs[formName].validate((valid) => {
                if ((valid)) {
                    let self = this
                    //调用servlet服务
                    request({
                        method: 'Post',
                        url: '/User/insert',
                        headers: {
                            'Content-Type': 'application/json',
                            "Authorization": 'Access-Control-Request-Headers'
                        },
                        data:getSecretData(jsonStringSwitch("String",this.type_data)),

                    }).then(res => {
                        //获取servlet响应的信息
                        let data = res.data;
                          if (data.code === 200 && data.message!=="") {
                            operations("success",data.message,self)
                            // this.$message({
                            //     message: data.message,
                            //     center: true,
                            //     type: "success"
                            // });
                        } else if (data.code === 200 && data.message==="") {
                              operations('error',data.data.msg,self)
                              reloadLogin();
                              //   this.$message({
                            //     message: data.message,
                            //     center: true,
                            //     type: 'error'
                            // });
                        }else {
                              operations('error',data.message,self)
                          }
                    }, err => {
                        operations('error',err.message,self)
                        //
                        // this.$message({
                        //     message: err.message,
                        //     type: "error",
                        //     center: true
                        // })
                    }).catch(error => {
                        operations('error',error.message,self)
                        //
                        // this.$message({
                        //     message: error.message,
                        //     type: "error",
                        //     center: true
                        // })
                    });
                    // 关闭注册界面并清除输入框
                    self.$data.dialogRegisterFormVisible = false;
                    self.$refs.passwd.clear();
                    self.$refs.user.clear();
                    self.$refs.city.clear();
                    self.$refs.address.clear();
                    self.$refs.province.clear();
                    self.$refs.phone.clear();
                } else {
                    return false;
                }
            })
        },
        quit() {
            let self = this
            self.$data.dialogRegisterFormVisible = false;
            this.$refs.passwd.clear();
            this.$refs.user.clear();
            this.$refs.city.clear();
            this.$refs.address.clear();
            this.$refs.province.clear();
            this.$refs.phone.clear();
            operations('','取消成功！',self)

        },
    }
});
