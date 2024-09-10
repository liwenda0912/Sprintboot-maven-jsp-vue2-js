// 回显对应的导航页面处理
var APPLE = new Vue({
    el: '#index_app',
    data() {
        return {
            index_show: true,
            index_code: 0,
            test_data: false,
            userMessage: false
        }
    },
    methods: {
        created: function () {
            let self = this
            self.$data.index_show = "index"
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
            switch (newData.toString()) {
                case "1":
                    self.$data.index_show = false
                    self.$data.test_data = false
                    setTimeout(() => {
                        self.$data.index_show = true
                    }, 1000);
                    break
                case "2-1":
                    self.$data.index_show = false
                    self.$data.test_data = false
                    setTimeout(() => {
                        self.$data.test_data = true
                    }, 1000);
                    break
                case "2-2":
                    self.$data.index_show = false
                    self.$data.test_data = false
                    setTimeout(() => {
                        self.$data.test_data = true
                    }, 1000);
                    break
                case "2-3":
                    self.$data.test_data = false
                    self.$data.index_show = false
                    setTimeout(() => {
                        self.$data.test_data = true
                    }, 1000);
                    break
                case "2-4-1":
                    console.log(555)
                    self.$data.test_data = false
                    self.$data.index_show = false
                    self.$data.userMessage = false
                    setTimeout(() => {
                        self.$data.test_data = true
                    }, 1000);
                    break
                case "2-4-2":
                    self.$data.test_data = false
                    self.$data.index_show = false
                    setTimeout(() => {
                        self.$data.test_data = true
                    }, 1000);
                    break
                case "2-4-3":
                    self.$data.test_data = false
                    self.$data.index_show = false
                    setTimeout(() => {
                        self.$data.test_data = true
                    }, 1000);
                    break
                case "2-4-4":
                    self.$data.test_data = false
                    self.$data.index_show = false
                    setTimeout(() => {
                        self.$data.test_data = true
                    }, 1000);
                    break
                case "3":
                    self.$data.test_data = false
                    self.$data.index_show = false
                    setTimeout(() => {
                        self.$data.test_data = true
                    }, 1000);
                    break
                case "4":
                    self.$data.userMessage = false
                    self.$data.index_show = false
                    self.$data.test_data = false
                    setTimeout(() => {
                        console.log(555)
                        self.$data.userMessage = true
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
    console.log(e.data)
    switch (e.data) {
        case 'login':
            frame.contentWindow.postMessage(1);
            break;
        case 'LoginError':
            frame.contentWindow.postMessage(0);
            break;
        case "register":
            frame.contentWindow.postMessage(2);
            break;
        case  "quit" :
            frame.contentWindow.postMessage(3);
            break
        case "resetState":
            frame.contentWindow.postMessage(4);
            break;
        //监听导航栏的传参
        default:
            APPLE.index_code = e.data
            console.log(e.data)
    }
}, false);