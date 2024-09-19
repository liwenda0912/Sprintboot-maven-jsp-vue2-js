// import {request} from "./utils/request.js";
//
// // let username = JSON.parse(localStorage.getItem("name"));
// // // 监听indexPageshow.js父页面的通讯
// // window.addEventListener("message", function (e) {
// //     console.log(e.data)
// //     if (e.data === 1) {
// //         login.$data.stateCode = e.data;
// //         console.log(e.data); // message
// //     } else if (e.data === 2) {
// //         register.$data.dialogCode = e.data;
// //         console.log("申请注册" + e.data); // message
// //     } else if (e.data === 3) {
// //         register.$data.dialogCode = e.data;
// //         console.log("取消注册" + e.data); // message
// //     } else if (e.data === 4) {
// //         register.$data.dialogCode = e.data;
// //         console.log("取消注册" + e.data); // message
// //     }
// // }, false);
//
//
// //登录请求服务js
// var login = new Vue({
//     data: {
//         receive_name: '',
//         receive_img: '',
//         name_show: username,
//         stateCode: 0,
//     },
//     el: '#app_login',
//     methods: {
//         // 退出登录
//         login_down() {
//             localStorage.removeItem("name");
//             console.log("退出登录成功")
//             this.$message({
//                 message: '退出登录成功！',
//                 center: true
//             });
//             setTimeout(() => {
//                 location.reload();
//             }, 1000);
//         },
//
//     }, watch: {
//         // 监听stateCode状态
//         stateCode: function (newData, oldData) {
//             let self = this
//             let name = localStorage.getItem("username");
//             let input_passwd = localStorage.getItem("passwd");
//             if (newData === 1) {
//                 // 向登录servlet请求登录申请
//                 request({
//                     method: 'Post',
//                     url: 'LoginServlet',
//                     params: {
//                         username: name,
//                         passwd: input_passwd
//                     }
//                 }).then(res => {
//                     //获取登录servlet的登录请求响应
//                     var data = res.data;
//                     console.log(res.data + "   " + data.code + data.message);
//                     window.location.href = ("Result.jsp?data=" + data.code + "&message=" + data.message)
//                 }, err => {
//                     console.log(err.message);
//                     this.$message({
//                         message: err.message,
//                         type: "error",
//                         center: true
//                     })
//                 }).catch(error => {
//                     console.error(error.message);
//                     this.$message({
//                         message: error.message,
//                         type: "error",
//                         center: true
//                     })
//                 });
//                 //清除登录获取的登录账号信息
//                 localStorage.removeItem("username");
//                 localStorage.removeItem("passwd");
//             }
//         }
//     }
//
// });
//
//
// // 注册弹窗js
// var register = new Vue({
//     data() {
//         return {
//             dialogFormVisible: false,
//             dialogCode: 0,
//             type_data: {
//                 address: "",
//                 city: "",
//                 province: "",
//                 ruleForm: {
//                     phone: "",
//                     zip:'',
//                     username: '',
//                     password: '',
//                     region: '',
//                     delivery: false,
//                     type: [],
//                     resource: '',
//                     desc: ''
//                 }
//             },
//             // 输入框校验规则
//             rules: {
//                 phone:[{
//                     pattern:/^((0\d{2,3}-\d{7,8})|(1[34578]\d{9}))$/, message: '手机号码不合法', trigger: 'blur'
//                 }],
//                 zip:[{min: 6, max: 6, message: '邮政编码只能填写6位', trigger: 'blur'}],
//                 username: [
//                     {required: true, message: '账户不能为空', trigger: 'blur'},
//                 ],
//                 password: [
//                     {required: true, message: '密码不能为空', trigger: 'blur'},
//                     {min: 8, max: 16, message: '密码长度为8到16位！', trigger: 'blur'}
//                 ]
//             },
//             formLabelWidth: '120px'
//         };
//     },
//     el: '#app-1',
//     watch: {
//         dialogCode: function (newData, oldData) {
//             let self = this
//             if (newData === 2) {
//                 self.$data.dialogFormVisible = true
//             } else {
//                 self.$data.dialogFormVisible = false
//             }
//         }
//     },
//     methods: {
//         test_() {
//             this.$router.push({path: '/Result'})
//         },
//         registered(formName) {
//             // rules校验输入框
//             this.$refs[formName].validate((valid) => {
//                 if ((valid)) {
//                     let self = this
//                     //给父页面传数据
//                     window.top.postMessage("resetState", '*');
//                     //调用servlet服务
//                     axios({
//                         method: 'Post',
//                         url: '/User/insert',
//                         headers: {
//                             'Content-Type': 'application/json',
//                             "Authorization":'Access-Control-Request-Headers'
//                         },
//                         data: this.type_data
//                     }).then(res => {
//                         //获取servlet响应的信息
//                         let data = res.data;
//                         console.log(data)
//                         if (data.code===200){
//                             this.$message({
//                                 message: data.message,
//                                 center: true,
//                                 type: "success"
//                             });
//                         }else {
//                             this.$message({
//                                 message: data.message,
//                                 center: true,
//                                 type: 'error'
//                             });
//                         }
//                     }, err => {
//                         console.log(err.message);
//                         this.$message({
//                             message: err.message,
//                             type: "error",
//                             center: true
//                         })
//                     }).catch(error => {
//                         console.error(error.message);
//                         this.$message({
//                             message: error.message,
//                             type: "error",
//                             center: true
//                         })
//                     });
//                     // 关闭注册界面并清除输入框
//                     self.$data.dialogFormVisible = false;
//                     this.$refs.passwd.clear();
//                     this.$refs.user.clear();
//                     this.$refs.city.clear();
//                     this.$refs.address.clear();
//                     this.$refs.province.clear();
//                     this.$refs.phone.clear();
//                 } else {
//                     // 清除输入框
//                     // this.$refs.passwd.clear();
//                     // this.$refs.user.clear();
//                     // this.$refs.city.clear();
//                     // this.$refs.address.clear();
//                     // this.$refs.province.clear();
//                     // this.$refs.phone.clear();
//                     return false;
//                 }
//             })
//         },
//         quit() {
//             let self = this
//             // 给父页面通讯
//             window.top.postMessage("quit", '*')
//             self.$data.dialogFormVisible = false;
//             this.$refs.passwd.clear();
//             this.$refs.user.clear();
//             this.$refs.city.clear();
//             this.$refs.address.clear();
//             this.$refs.province.clear();
//             this.$refs.phone.clear();
//             this.$message({
//                 message: '取消注册成功！',
//                 center: true
//             });
//         },
//     }
// });