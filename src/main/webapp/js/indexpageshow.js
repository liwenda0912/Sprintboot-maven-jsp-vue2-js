// 回显对应的导航页面处理

import {openFullScreen2} from "./utils/reload.js";

var APPLE = new Vue({
    el: '#index_app',
    data() {
        return {
            index_show: true,
            index_code: 0,
            test_data: false,
            userMessage: false,
            SeleniumResult:false,
            test_case:false,
            loading:'',
        }
    },
    mounted() {
        // 全局页面加载中。。
        this.$loading({}).close()
    },
    created: function () {
        let self = this
// 全局页面加载中。。
        openFullScreen2(this)
    },
    methods: {
        //导航栏参数初始化
         showPage(self){
             openFullScreen2(this)
             self.$data.SeleniumResult = false
             self.$data.userMessage = false
             self.$data.index_show = false
             self.$data.test_case = false
             self.$data.test_data = false
         }
    },
    // 监听导航栏的跳转
    watch: {
        index_code: function (newData, oldData) {
            let self = this
            var name = window.document.getElementsByClassName("border");
            for (var i = 0; i < name.length; i++) {
                name[i].style.height = "100%";
            }
            // 全局页面加载中。。
            switch (newData.toString()) {
                //首页
                case "1":
                    self.showPage(self)
                    setTimeout(() => {
                        self.$data.index_show = true
                        self.$loading({}).close()
                    }, 1000);
                    break
                //测试用例
                case "2-1":
                    self.showPage(self)
                    setTimeout(() => {
                        self.$data.test_case = true
                        self.$loading({}).close()
                    }, 1000);
                    break
                //测试数据
                case "2-2":
                    self.showPage(self)
                    setTimeout(() => {
                        self.$data.test_data = true
                        self.$loading({}).close()
                    }, 1000);
                    break
                //测试报告
                case "2-3":
                    self.showPage(self)
                    setTimeout(() => {
                        self.$data.test_data = true
                        self.$loading({}).close()
                    }, 1000);
                    break
                //测试脚本-测试数据
                case "2-4-1":
                    self.showPage(self)
                    setTimeout(() => {
                        self.$data.test_data = true
                        self.$loading({}).close()
                    }, 1000);
                    break
                //测试脚本-测试脚本
                case "2-4-2":
                    self.showPage(self)
                    setTimeout(() => {
                        self.$data.test_data = true
                        self.$loading({}).close()
                    }, 1000);
                    break
                //测试脚本-测试结果
                case "2-4-3":
                    self.showPage(self)
                    setTimeout(() => {
                        self.$data.SeleniumResult = true
                        self.$loading({}).close()
                    }, 1000);
                    break
                //消息管理
                case "3":
                    self.showPage(self)
                    setTimeout(() => {
                        self.$data.test_data = true
                        self.$loading({}).close()
                    }, 1000);
                    break
                //用户管理
                case "4":
                    self.showPage(self)
                    setTimeout(() => {
                        self.$data.userMessage = true
                        self.$loading({}).close()
                    }, 1000);
                    break
            }
            APPLE.index_code = 0
        }

    }

})
// 监听子页面传递的通讯
window.addEventListener("message", function (e) {
    var frame = document.getElementById('index_iframe');
    //给iframe的id为index_iframe的子页面进行通信传参
    switch (e.data) {
        //监听导航栏的传参
        default:
            APPLE.index_code = e.data
    }
}, false);