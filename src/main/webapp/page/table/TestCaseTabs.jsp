<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <script src="//unpkg.com/vue@2/dist/vue.js"></script>
    <script src="//unpkg.com/element-ui@2.15.14/lib/index.js"></script>
    <link rel="stylesheet" href="//unpkg.com/element-ui@2.15.14/lib/theme-chalk/index.css">
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    <title></title>
</head>
<body>
<%@ include file="../public/publicTable.jsp" %>
</body>
<script type="module">
    import {request} from "../../js/utils/request.js";
    import {operations} from "../../js/utils/message.js";
    window.addEventListener("message", function (e) {
        if (e.data.split(":")[0] === "page") {
            publicCase_tabs.$data.pageNum = e.data.split(":")[1]
        } else {
            publicCase_tabs.$data.pageShowNum = parseInt(e.data.split(":")[1])
        }
    }, false);

    let publicCase_tabs = new Vue({
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
                formLabelWidth: '120px',
                dynamicColumns: [
                    { label: '测试编号', prop: 'id' },
                    { label: '一级模块', prop: 'Module' },
                    { label: '场景', prop: 'Scene' },
                    { label: '用例标题', prop: 'CaseTitle' },
                    { label: '优先级', prop: 'Priority' },
                    { label: '操作步骤', prop: 'OperateStep' },
                    { label: '预期结果', prop: 'ExpectedResult' },
                    { label: '实际结果', prop: 'ActualResult' },
                    { label: '备注', prop: 'Remarks' },
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
                          url: '/testCase/testCaseList',
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
