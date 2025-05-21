<%--
  Created by IntelliJ IDEA.
  User: 10260
  Date: 2025/2/8
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="//unpkg.com/vue@2/dist/vue.js"></script>
    <script src="//unpkg.com/element-ui@2.15.14/lib/index.js"></script>
    <link rel="stylesheet" href="//unpkg.com/element-ui@2.15.14/lib/theme-chalk/index.css">
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    <title></title></head>
<body>
<%@ include file="../public/publicTable.jsp" %>
</body>
<style>
    .el-table_1_column_1   el-table__cell cell{
        width: 30px;
    }
</style>
<script type="module">
    import {request} from "../../js/utils/request.js";
    import {operations} from "../../js/utils/message.js";
    let testCaseDir = new Vue({
        el: '#app',
        data() {
            return {
                loading:false,
                dialogVisible:false,
                dialogFormVisible:false,
                pageNum:1,
                pageShowNum:10,
                form:[],
                type:[],
                formLabelWidths: '120px',
                dynamicColumns: [
                    { label: '序号', prop: 'testCaseDrId',formLabelWidth:"100px"},
                    { label: '用例文件名', prop: 'testCaseDriName',formLabelWidth:"520px"},
                    { label: '备注', prop: 'remark',formLabelWidth:"306px"},
                ],
                tableData: []
            };
        },
        mounted() {
            this.loading=true;
            this.onshow()
            setTimeout(()=>{
                this.loading=false;
            },1000)
        },
        methods:{
            send(data) {
                let frame_pagination = document.getElementById("iframe_seleniumTestCase_pagination");
                frame_pagination.contentWindow.postMessage(data, 'http://127.0.0.1:8090/page/public/pagination.jsp');
                frame_pagination.onload = function () {
                    frame_pagination.contentWindow.postMessage(data, 'http://127.0.0.1:8090/page/public/pagination.jsp\n');
                }
            },
            dateFormat(row, column) {
                return row[column.property] === null ? "--" : row[column.property];
            },
            onshow() {
                let self_ = this;
                request({
                    method: 'Post',
                    url: '/testCase/DriList',
                    headers: {
                        'Content-Type': 'application/json',
                        "Authorization": 'Access-Control-Request-Headers'
                    },
                    data: {
                        pageNum: self_.$data.pageNum,
                        pageSize: self_.$data.pageShowNum
                    }
                }).then(res =>{
                    if(res.data.code===200){
                        this.$data.tableData = res.data.data.list
                        self_.send(res.data.data.total);
                        setTimeout(() => {
                            self_.loading = false;
                        }, 2000);
                    }else {
                        // vue的this.message方法
                        operations("error",res.data.message,self_)
                    }
                }).catch(err=>{
                    operations("error",err.message,self_)
                })

            },
            //页面编辑按钮的方法
            edit(row,index){
                let self_ = this
                // self_.$data.type=row
                self_.$data.type=JSON.parse(JSON.stringify(row));
                self_.dialogVisible=true;
                self_.index_=index
            },
            //页面查看按钮的方法
            handleClick(row) {
                let self_ = this
                self_.$data.type=row;
                this.dialogFormVisible=true;
            },
        },
        watch: {
            pageNum: function (newData, oldData) {
                if (newData !== oldData) {
                    this.loading = true
                    this.test = [];
                    this.onshow();
                }
            },
            pageShowNum: function (newData, oldData) {
                if (newData !== oldData) {
                    this.test = [];
                    this.onshow();
                }
            }
        }
    });

</script>
</html>
