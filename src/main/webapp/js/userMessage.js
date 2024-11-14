//监听iframe父页面传递的数据
import {request} from "./utils/request.js";
import {height_adjust} from "./utils/height_adjust.js";
import {reloadLogin} from "./utils/reloadLogin.js";
import {getCookie} from "./utils/Cookie.js";

window.addEventListener("message",function(e) {
    console.log(e.data.split(":")[1])
    if(e.data.split(":")[0]==="page"){
        User.$data.pageNum_1=e.data.split(":")[1]
    }else {
        User.$data.pageShowNum = parseInt(e.data.split(":")[1])
    }
}, false);

var User =new Vue({
    el:"#app_tabs",
    data() {
        return {
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
            index_:null,
            te_:[]
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
            let self_ = this
            self_.$data.type=row;
            this.dialogFormVisible=true;
        },
        //页面编辑按钮的方法
        edit(row,index){
            let self_ = this
            // self_.$data.type=row
            self_.$data.type=JSON.parse(JSON.stringify(row));
            self_.dialogVisible=true;
            self_.index_=index
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
            let self_ = this
            self_.dialogVisible = false
            request({
                method: 'Post',
                url: '/User/edit',
                headers: {
                        'Content-Type': 'application/json',
                        "Authorization":'Access-Control-Request-Headers'
                },
                data:type
            }).then(res=>{
                console.log(res.data.data)
                if (res.data.code===200){
                    self_.dialogVisible=false;
                    self_.$message({
                        type:"success",
                        center: true,
                        message:res.data.message,
                    });
                    //修改table的数组数据并同步渲染
                    self_.$set(self_.test, self_.$data.index_, res.data.data);
                }else {
                    self_.$message({
                        type:"error",
                        center: true,
                        message:res.data.message,
                    })
                }
            },err=>{
                console.log(err.message);
                self_.$message({
                    message:err.message,
                    type:"error",
                    center:true
                })
            });
        },
        //页面加载就获取数据
        onshow(){

            // this.dialogVisible=false;
            // this.dialogFormVisible=false;
            let self_ =this
            request({
                method: 'Post',
                url: '/User/list',
                data: {
                    pageNum:self_.$data.pageNum_1,
                    pageSize:self_.$data.pageShowNum
                },
                headers: {
                    'Content-Type': 'application/json',
                    "Authorization":'Access-Control-Request-Headers'
                },
            }).then(res=>{
                console.log(getCookie())
                if(res.data.code===200&&res.data.message!=null&&res.data.message.indexOf("过期")){
                    self_.loading=false;
                    self_.$message({
                        message:res.data.message,
                        type:"error",
                        center:true
                    })
                }else if(res.data.code===200&&res.data.message===null){
                    self_.test=res.data.data.list
                    self_.send(res.data.data.total);
                    setTimeout(() => {
                        self_.loading=false;
                    }, 2000);
                }else{
                    self_.$message({
                        message:res.data.message,
                        type:"error",
                        center:true
                    });
                    // reloadLogin();
                }
            }).catch(error=>{
                self_.loading=false
                self_.$message({
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
            let self_ =this
            console.log(newData)
            if(newData!==oldData){
                self_.loading=true
                self_.test=[];
                self_.onshow();
                console.log("new num")
            }
        },
        //监听列表页数
        pageShowNum:function (newData,oldData){
            let self_ =this
            console.log(newData)
            if(newData!==oldData){
                self_.test=[];
                self_.onshow();
            }
        }
    },
})
