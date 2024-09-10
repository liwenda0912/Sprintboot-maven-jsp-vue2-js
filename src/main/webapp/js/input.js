var Min = new Vue({
    el: '#APP_input',
    data() {
        return {
            //输入框rules校验
            ruleForm: {
                input: '',
                input_passwd: '',
                submit_show: '',
                code: 1
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
                    localStorage.setItem("username", this.ruleForm.input)
                    localStorage.setItem("passwd", this.ruleForm.input_passwd)
                    window.top.postMessage("login", '*');
                } else {
                    console.log(this.ruleForm)
                    localStorage.removeItem("username");
                    localStorage.removeItem("passwd");
                    window.parent.postMessage("LoginError", '*');
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        //渲染函数
        render(createElement) {
            localStorage.setItem("stataCode", 0);
            localStorage.setItem("stataCode1", 0);
        }
    }
})
// 注册监听
var register = new Vue({
    el: "#app-2",
    methods: {
        register() {
            window.top.postMessage("register", '*');
            // this.$message({
            //     message: '55555',
            //     center: true
            // });
        },
    }
})