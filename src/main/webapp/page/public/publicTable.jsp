<%--
  Created by IntelliJ IDEA.
  User: 10260
  Date: 2024/7/20
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div id="app">
    <el-table
            border
            v-loading="loading"
            element-loading-text="加载中.."
            style="width: 100%"
<%--            height="560px"--%>
            :data="tableData">
        <el-table-column
                :formatter='dateFormat'
                v-for="(column, index) in dynamicColumns"
                :key=" index"
                :label="column.label"
                :prop="column.prop?column.prop:'--'"
        ></el-table-column>
        <el-table-column
                label="操作"
                width="300">
            <template slot-scope="scope">
                <div class="cell_button">
                    <el-button @click="handleClick(scope.row)" type="text" size="small"
                               icon="el-icon-search">查看
                    </el-button>
                    <el-button type="text" size="small" icon="el-icon-edit" @click="edit(scope.row,scope.$index)">编辑
                    </el-button>
                </div>
            </template>
        </el-table-column>
    </el-table>
    <el-dialog title="修改用户信息" :visible.sync="dialogVisible" :close-on-click-modal="false">
        <el-form>
            <el-form-item v-for="(column, index) in dynamicColumns"
                          :label="column.label"
                          :label-width="formLabelWidth">
                <el-input v-model='type[column.prop]' autocomplete="off" ></el-input>
            </el-form-item>
        </el-form>
    </el-dialog>

    <el-dialog title="用户信息" :visible.sync="dialogFormVisible" :close-on-click-modal="false">
        <div style="font-size: 15px;margin: 5px 5px" >
            <el-descriptions v-for="(column, index) in dynamicColumns">
                <el-descriptions-item  :label="column.label">
                    {{type[column.prop]?type[column.prop]:"--"}}
                </el-descriptions-item>
            </el-descriptions>
        </div>
    </el-dialog>
</div>
<%--</body>--%>

<style>
    .el-table--border th.el-table__cell {
        background-color: #DDDDDD;
    }
</style>
<%--<script>--%>
<%--    new Vue({--%>
<%--        el: '#app',--%>
<%--        data() {--%>
<%--            return {--%>
<%--                dialogVisible:false,--%>
<%--                dialogFormVisible:false,--%>
<%--                form:[],--%>
<%--                type:[],--%>
<%--                formLabelWidth: '120px',--%>
<%--                dynamicColumns: [--%>
<%--                    { label: '年龄', prop: 'age' },--%>
<%--                    { label: '城市', prop: 'city' },--%>
<%--                    { label: '职位1', prop: 'position' },--%>
<%--                    { label: '职位2', prop: 'position' },--%>
<%--                    { label: '职位3', prop: 'position' },--%>

<%--                ],--%>
<%--            tableData: [--%>
<%--			 { name: '张三', age: 28, city: '北京', position: '工程师' },--%>
<%--             { name: '李四', age: 22, city: '上海', position: '设计师' },--%>
<%--             { name: '王五', age: 30, city: '广州', position: '产品经理' }--%>
<%--        ]--%>
<%--        };--%>
<%--        },--%>
<%--        methods:{--%>
<%--            //页面编辑按钮的方法--%>
<%--            edit(row,index){--%>
<%--                let self_ = this--%>
<%--                // self_.$data.type=row--%>
<%--                self_.$data.type=JSON.parse(JSON.stringify(row));--%>
<%--                self_.dialogVisible=true;--%>
<%--                self_.index_=index--%>
<%--            },--%>
<%--            //页面查看按钮的方法--%>
<%--            handleClick(row) {--%>
<%--                let self_ = this--%>
<%--                self_.$data.type=row;--%>
<%--                this.dialogFormVisible=true;--%>
<%--            },--%>
<%--        }--%>
<%--    });--%>

<%--</script>--%>
<%--</html>--%>