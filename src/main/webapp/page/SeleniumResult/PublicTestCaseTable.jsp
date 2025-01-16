<%--
  Created by IntelliJ IDEA.
  User: 10260
  Date: 2024/8/29
  Time: 11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <script src="//unpkg.com/vue@2/dist/vue.js"></script>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    <script src="//unpkg.com/element-ui@2.15.14/lib/index.js"></script>
    <link rel="stylesheet" href="//unpkg.com/element-ui@2.15.14/lib/theme-chalk/index.css">
    <title>Title</title>
</head>
<body>
<div id="tab_TestCase">
    <div class="table-style" id="publicTestCase_tabs">
        <el-collapse v-model="activeNames" @change="handleChange">
            <el-collapse-item title="搜索" name="1">
                <div class="demo-input-size">
                    <el-date-picker
                            v-model="value3"
                            type="datetime"
                            placeholder="选择日期时间"
                            default-time="12:00:00">
                    </el-date-picker>
                    <el-input
                            placeholder="id"
                            suffix-icon="el-icon-date"
                            v-model="input1">
                    </el-input>
                    <el-input
                            placeholder="set_type"
                            suffix-icon="el-icon-date"
                            v-model="input2">
                    </el-input>
                </div>
            </el-collapse-item>
        </el-collapse>
        <el-table
                border
                v-loading="loading"
                :data="tableData"
                style="width: 100%"
                height="560px"
        >
            <el-table-column
                    fixed
                    prop="testCaseName"
                    label="用例集"
                    width="220">
            </el-table-column>
            <el-table-column
                    prop="caseTotal"
                    label="用例条数"
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="testCaseSuccess"
                    label="成功条数"
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="testCaseFail"
                    label="失败条数"
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="testCaseError"
                    label="错误条数"
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="startTime"
                    :formatter='dateFormat'
                    label="开始时间"
                    width="200">
            </el-table-column>
            <el-table-column
                    prop="endTime"
                    :formatter='dateFormat'
                    label="结束时间"
                    width="200">
            </el-table-column>
            <el-table-column
                    fixed="right"
                    label="操作"
                    width="120">
                <template slot-scope="scope">
                    <div class="cell_button">
                        <el-button type="text" size="small" icon="el-icon-edit" @click="ShowDetail(scope.row)">详情
                        </el-button>
                    </div>
                </template>
            </el-table-column>
        </el-table>
        <div style="height: 60px;">
            <iframe id="iframe_seleniumTestCase_pagination" src="../public/pagination.jsp" scrolling="no"
                    style="width: 11960px;border: 0;position: fixed;height: 200px"></iframe>
        </div>
        <!-- dialog-->
        <el-dialog title="测试结果详情" :visible.sync="dialogVisible">
            <iframe v-if="dialogVisible" src="../../page/SeleniumResult/PublicResultTable.jsp" scrolling="no"
                    style="width: 100%;height: 100%;border: 0"></iframe>
        </el-dialog>

    </div>
</div>
</body>
<style>
    .el-table .warning-row {
        background: red;
    }

    .el-table .success-row {
        background: green;
    }

    .el-table .fail-row {
        background: yellow;
    }

    div.cell_button {
        width: 200px;
        margin: 0;
    }

    .el-button + .el-button {
        margin-left: 0;
    }

    .el-table--border th.el-table__cell {
        background-color: #DDDDDD;
    }

    .demo-input-size {
        float: right;
    }

    .el-input.el-input--suffix {
        width: 200px;
    }

    .el-input.el-input--medium.el-input--suffix {
        width: 200px;
    }

    .el-collapse-item__content {
        height: 30px;
    }

    .el-dialog {
        margin-top: 10vh !important;
        width: 80%;
        height: 90%;
    }
</style>
<script type="module" src="../../js/utils/formatTime.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.4/moment.min.js"></script>
<script type="text/javascript" src="../../js/utils/TimePickerOption.js"></script>
<script type="module" src="../../js/utils/height_adjust.js"></script>
<script type="module" src="js/PublicTestCaseTable.js">
</script>

</html>

