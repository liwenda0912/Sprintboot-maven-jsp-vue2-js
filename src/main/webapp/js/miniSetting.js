import {request} from "../js/utils/request.js";

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
        console.log('555')
    },
    methods: {
        editButtom(type){
            this.dialogVisible = false
            console.log(type)
            request({
                method: 'Post',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                },
                url: '/DataServlet',
                params: {
                    dbname:"setting",
                },
                data:type
            }).then(res=>{
                    console.log(res.data.code)
                    if (res.data.code==="200"){
                        this.$message({
                            type:"success",
                            center: true,
                            message:res.data.message,
                        })
                    }else {
                        this.$message({
                            type:"error",
                            center: true,
                            message:res.data.message,
                        })
                    }
                },err=>{
                    console.log(err.message);
                this.loading=false;
                let self = this
                    // this.onshow();
                    // this.req(self.$data.pageShowNum,self.$data.pageNum_1);
                    this.$message({
                        message:err.message,
                        type:"error",
                        center:true
                    })
                    setTimeout(()=>{
                        window.location.reload();
                    },3000)
                    console.log(this.$data.test)
                }
            )
            //     .catch(error=>{
            //     // console.error("sss")
            //     // this.$message({
            //     //     message:error.message,
            //     //     type:"error",
            //     //     center:true
            //     // })
            // })
            ;
        },
        handleChange(val) {
            console.log(val);
            var name_id = window.parent.document.getElementsByClassName("app_tabs_");
            var name = window.parent.document.getElementsByClassName("el-tabs el-tabs--top el-tabs--border-card");
            if (val.length === 2){
                for (var i =0;i<name.length;i++) {
                    name[i].style.height = "780px";
                }
                for (var i =0;i<name.length;i++) {
                    name_id[i].style.margin = "20px 20px 60px 20px";
                }
            } else{
                for (var i =0;i<name.length;i++) {
                    name[i].style.height = "760px";
                }
            }
        },
        handleClick(row) {
            let self = this
            self.$data.type=row;
            this.dialogFormVisible=true;
        },
        req(size,pageNum){
            request({
                method: 'Get',
                url: 'DataServlet',
                params: {
                    dbname:"setting",
                    pageNum:pageNum,
                    pageSize:size
                }
            }).then(res=>{
                //获取用户servlet的登录请求响应
                var data = res.data;
                console.log(data)
                this.send(data[data.length-1]);
                for (var i =0;i<data.length-1;i++){
                    this.test.push(data[i]);
                }
                setTimeout(() => {
                    this.loading=false;
                }, 2000);
            },err=>{
                this.loading=false;
                this.$message({
                    message:err.message,
                    type:"error",
                    center:true
                })

            });
        },
        edit(row){
            let self = this
            self.$data.type=row;
            this.dialogVisible=true;
        },
        send(data){
            let frame_pagination= document.getElementById("iframe_pagination");
            frame_pagination.contentWindow.postMessage(data,'http://localhost:8086/mavenproject_war_exploded/pagination.jsp');
            frame_pagination.onload=function (){
                frame_pagination.contentWindow.postMessage(data,'http://localhost:8086/mavenproject_war_exploded/pagination.jsp');
                // frame_pagination.contentWindow.alert("jjjjdsf")
            }
        },
        onshow(){
            this.dialogVisible=false;
            this.dialogFormVisible=false;
            let self =this
            this.req(self.$data.pageShowNum,self.$data.pageNum_1);
        }
    },watch:{
        pageNum_1: function (newData,oldData){
            if(newData!==oldData){
                this.loading=true
                this.test=[];
                this.onshow();
            }
        },
        pageShowNum:function (newData,oldData){
            if(newData!==oldData){
                this.test=[];
                this.onshow();
            }
        }
    },

})
