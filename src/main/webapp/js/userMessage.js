//监听iframe父页面传递的数据
import {request} from "./utils/request.js";
import {reload, openFullScreen2} from "./utils/reload.js";
import {height_adjust} from "./utils/height_adjust.js";


window.addEventListener("message",function(e) {
    console.log(e.data.split(":")[1])
    if(e.data.split(":")[0]==="page"){
        // User.$data.pageNum_1 =e.data.split(":")[1]
        User.$data.pageNum_1=e.data.split(":")[1]
    }else {
        User.$data.pageShowNum = parseInt(e.data.split(":")[1])
    }
}, false);

var User =new Vue({
    el:"#app_tabs",
    data() {
        return {
            // //时间选择器的数据
            // pickerOptions: {
            //     shortcuts: [{
            //         text: '今天',
            //         onClick(picker) {
            //             picker.$emit('pick', new Date());
            //         }
            //     }, {
            //         text: '昨天',
            //         onClick(picker) {
            //             const date = new Date();
            //             date.setTime(date.getTime() - 3600 * 1000 * 24);
            //             picker.$emit('pick', date);
            //         }
            //     }, {
            //         text: '一周前',
            //         onClick(picker) {
            //             const date = new Date();
            //             date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
            //             picker.$emit('pick', date);
            //         }
            //     }]
            // },
            activeNames:"",
            value3:"",
            input1:"",
            input2:'',
            loading:'',
            pageShowNum:10,
            pageNum_1:1,
            dialogVisible: false,
            dialogFormVisible: false,
            delivery: false,
            formLabelWidth: '120px',
            type:[],
            form:{
            },
            test:[],
        }
    },
    mounted(){
        this.loading=true
        this.onshow();
    },
    methods: {

        //控制列表的页面样式的方法
        handleChange(val) {
            var name_ = window.top.document.getElementsByClassName("border");
            var name_id = window.parent.document.getElementsByClassName("app_tabs_");
            var name = window.parent.document.getElementsByClassName("el-tabs el-tabs--top el-tabs--border-card");
            height_adjust(val,name,[],[])
        },
        //页面查看按钮的方法
        handleClick(row) {
            let self = this
            console.log(row);
            self.$data.type=row;
            this.dialogFormVisible=true;
        },
        //页面编辑按钮的方法
        edit(row){
            let self = this
            console.log(row);
            self.$data.type=row;
            this.dialogVisible=true;
        },
        // iframe的父传递信息给子页面传递数据的方法
        send(data){
            let frame_pagination= document.getElementById("iframe_pagination");
            frame_pagination.contentWindow.postMessage(data,'http://127.0.0.1:8090/page/public/pagination.jsp');
            frame_pagination.onload=function (){
                frame_pagination.contentWindow.postMessage(data,'http://127.0.0.1:8090/page/public/pagination.jsp\n');
            }
        },
        // 编辑保持方法
        EditTable(type){
            this.dialogVisible = false
            request({
                method: 'Post',
                url: '/User/edit',
                headers: {
                        'Content-Type': 'application/json',
                        "Authorization":'Access-Control-Request-Headers'
                },
                data:type
            }).then(res=>{
                console.log(res.data)
                let  self = this
                if (res.data.code===200){
                    self.dialogVisible=false;
                    self.$message({
                        type:"success",
                        center: true,
                        message:res.data.message,
                    })
                }else {
                    self.$message({
                        type:"error",
                        center: true,
                        message:res.data.message,
                    })
                    reload()
                }
            },err=>{
                console.log(err.message);
                this.$message({
                    message:err.message,
                    type:"error",
                    center:true
                })
                reload()
            });
        },
        //页面加载就获取数据
        onshow(){

            // this.dialogVisible=false;
            // this.dialogFormVisible=false;
            let self =this
            request({
                method: 'Post',
                url: '/User/list',
                data: {
                    pageNum:self.$data.pageNum_1,
                    pageSize:self.$data.pageShowNum
                },
                headers: {
                    'Content-Type': 'application/json',
                    "Authorization":'Access-Control-Request-Headers'
                },
            }).then(res=>{
                //获取用户servlet的登录请求响应
                // var data = res.data;
                // for (var i =0;i<data.length-1;i++){
                //     this.test.push(data[i]);
                // }
                this.test=res.data.data.list
                this.send(res.data.data.total);
                console.log(res.data.data.total)
                setTimeout(() => {
                    this.loading=false;
                }, 2000);
            }).catch(error=>{
                this.loading=false
                this.$message({
                    message:error.message,
                    type:"error",
                    center:true
                })
            });
        }
    },
    //监听函数
    watch:{
        //监听列表展示的数
        pageNum_1: function (newData,oldData){
            console.log(newData)
            if(newData!==oldData){
                this.loading=true
                this.test=[];
                this.onshow();
                console.log("new num")
            }
        },
        //监听列表页数
        pageShowNum:function (newData,oldData){
            console.log(newData)
            if(newData!==oldData){
                this.test=[];
                this.onshow();
                console.log("new pagenum")
            }
        }
    },

})
