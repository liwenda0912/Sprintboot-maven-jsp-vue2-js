<%--
  Created by IntelliJ IDEA.
  User: 10260
  Date: 2024/8/29
  Time: 11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ page isELIgnored="false" %>
<script src="//unpkg.com/vue@2/dist/vue.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script src="//unpkg.com/element-ui@2.15.14/lib/index.js"></script>
<link rel="stylesheet"  href="//unpkg.com/element-ui@2.15.14/lib/theme-chalk/index.css">
<%--<link rel="stylesheet" type="text/css" href="css/userMessage.css">--%>
<html>
<head>
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
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="caseTotal"
                    label="用例条数"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="testCaseSuccess"
                    label="成功条数"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="testCaseFail"
                    label="失败条数"
                    width="300">
            </el-table-column>
            <el-table-column
                    prop="testCaseError"
                    label="错误条数"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="startTime"
                    label="开始时间"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="endTime"
                    label="结束时间"
                    width="120">
            </el-table-column>
            <el-table-column
                    fixed="right"
                    label="操作"
                    width="200">
                <template slot-scope="scope">
                    <div class="cell_button">
                        <el-button @click="handleClick(scope.row)" type="text" size="small" icon="el-icon-search" >查看</el-button>
                        <el-button type="text" size="small" icon="el-icon-edit" @click="edit(scope.row)">详情</el-button>
                    </div>
                </template>
            </el-table-column>
        </el-table>
        <div style="height: 60px;">
            <iframe id="iframe_seleniumTestCase_pagination" src="../public/pagination.jsp" scrolling="no" style="width: 11960px;border: 0;position: fixed;height: 200px"></iframe>
        </div>

        <!-- dialog-->
        <el-dialog title="测试结果详情" :visible.sync="dialogFormVisible" >
            <el-descriptions title="垂直带边框列表" direction="vertical" :column="4" border>
                <el-descriptions-item label="用例集">{{type.testCaseName}}</el-descriptions-item>
                <el-descriptions-item label="用例条数">{{type.caseTotal}}</el-descriptions-item>
                <el-descriptions-item label="成功条数" >{{type.testCaseSuccess}}</el-descriptions-item>
                <el-descriptions-item label="失败条数" >{{type.testCaseFail}}</el-descriptions-item>
                <el-descriptions-item label="错误条数">{{type.testCaseError}}</el-descriptions-item>
                <el-descriptions-item label="开始时间">{{type.startTime}}</el-descriptions-item>
                <el-descriptions-item label="结束时间">{{type.endTime}}</el-descriptions-item>
            </el-descriptions>

            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>
            </div>
        </el-dialog>
        <el-dialog title="修改测试集信息" :visible.sync="dialogVisible">
            <el-form :model="form">
                <el-form-item label="testCaseName：" :label-width="formLabelWidth">
                    <el-input v-model="type.testCaseName" autocomplete="off" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="caseTotal：" :label-width="formLabelWidth">
                    <el-input v-model="type.caseTotal" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="TestCaseSuccess：" :label-width="formLabelWidth">
                    <el-input v-model="type.testCaseSuccess" autocomplete="off" ></el-input>
                </el-form-item>
                <el-form-item label="TestCaseFail：" :label-width="formLabelWidth">
                    <el-input v-model="type.testCaseFail" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="TestCaseError：" :label-width="formLabelWidth">
                    <el-input v-model="type.testCaseError" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="startTime：" :label-width="formLabelWidth">
                    <el-input v-model="type.startTime" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="endTime：" :label-width="formLabelWidth">
                    <el-input v-model="type.endTime" autocomplete="off"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="editButtom(type)">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</div>
<style>
    .el-table .warning-row {
        background: red;
    }

    .el-table .success-row {
        background: green;
    }
    div.cell_button{
        width:200px;
        margin:0;
    }
    .el-button+.el-button{
        margin-left: 0;
    }
    .el-table--border th.el-table__cell{
        background-color: #DDDDDD;
    }
    .demo-input-size{
        float: right;
    }
    .el-input.el-input--suffix{
        width: 200px;
    }
    .el-input.el-input--medium.el-input--suffix{
        width: 200px;
    }
    .el-collapse-item__content{
        height: 30px;
    }
</style >
<script type="text/javascript" src="../../js/utils/TimePickerOption.js"></script>
<script type="module" src="../../js/utils/height_adjust.js"></script>
<script type="module" src="js/PublicTestCaseTable.js">
</script>
</body>
</html>

