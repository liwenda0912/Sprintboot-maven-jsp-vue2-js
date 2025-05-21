<%--
  Created by IntelliJ IDEA.
  User: 10260
  Date: 2024/7/20
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="app" style="box-shadow:grey 0 0 20px">
    <el-table
            border
            v-loading="loading"
            element-loading-text="加载中.."
            style="width: 100%"
            height="730px"
            :data="tableData"
    >
        <el-table-column
                :formatter='dateFormat'
                v-for="(column, index) in dynamicColumns"
                :key=" index"
                :label="column.label"
                :prop="column.prop?column.prop:'--'"
                :width="column.formLabelWidth"
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
    <div style="height: 80px;background-color:white">
        <iframe id="iframe_seleniumTestCase_pagination" src="../public/pagination.jsp" scrolling="no"
                style="width: 11960px;border: 0;position: fixed;height: 200px"></iframe>
    </div>
    <el-dialog title="修改信息" :visible.sync="dialogVisible" :close-on-click-modal="false">
        <el-form>
            <el-form-item v-for="(column, index) in dynamicColumns"
                          :key=" index"
                          :label="column.label+':'"
                          :label-width="formLabelWidths">
                <el-input v-model='type[column.prop]' autocomplete="off" ></el-input>
            </el-form-item>
        </el-form>
    </el-dialog>
    <el-dialog title="详情" :visible.sync="dialogFormVisible" :close-on-click-modal="false">
        <div style="font-size: 15px;margin: 5px 5px" >
            <el-descriptions v-for="(column, index) in dynamicColumns">
                <el-descriptions-item :label="column.label" :key=" index">
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
     .el-table td {
        font-size: 12px !important; /* 根据需要调整字体大小 */
    }
</style>