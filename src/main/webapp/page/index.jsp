<%--
  Created by IntelliJ IDEA.
  User: 10260
  Date: 2024/8/30
  Time: 12:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../css/index.css">
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
    <link rel="stylesheet" type="text/css" href="../css/head_new.css">
    <!-- import Vue before Element -->
    <%--    <script src="https://unpkg.com/vue@2/dist/vue.js"></script>--%>
    <%--    <!-- import JavaScript -->--%>
    <%--    <script src="https://unpkg.com/element-ui/lib/index.js"></script>--%>
    <script src="//unpkg.com/vue@2/dist/vue.js"></script>
    <script src="//unpkg.com/element-ui@2.15.14/lib/index.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/4.1.1/crypto-js.min.js"></script>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    <script src="https://unpkg.com/vue-router@4"></script>
    <title>www.webapp.Controller.pop.com</title>
</head>
<body>
<div class="all-style">
    <div class="header">
        <%@ include file="public/head_new.jsp"%>
    </div>
    <div id="index_app" class="border"  >
        <div class="index" v-if="index_show">
            <iframe id="index_iframe" src="index_body.jsp" scrolling="no" style="border: 0;width: 100%;height: 100%"></iframe>
        </div>
        <div class="work_platform_data" v-if="test_data">
            <iframe src="DataPage/TestDataTabs.jsp" scrolling="no" style="border: 0;width: 100%;height: 109%"></iframe>
        </div>
        <div v-if="userMessage">
            <iframe src="userMessage.jsp" scrolling="no" style="border: 0;width: 100%;height: 102%"></iframe>
        </div>
        <div v-if="SeleniumResult">
            <iframe src="SeleniumResult/ResultTableTabs.jsp" scrolling="no" style="border: 0;width: 100%;height: 115%"></iframe>
        </div>
        <div v-if="test_case">
            <iframe src="table/TestCaseTabs.jsp" scrolling="no" style="border: 0;width: 100%;height: 110%;"></iframe>
        </div>
    </div>
    <el-container>
        <el-footer>
            <div class="bottom" style="border-top: 20px">
                <%@ include file="public/bottom.jsp"%>
            </div>
        </el-footer>
    </el-container>
</div>
</body>
<script type="module" src="../js/indexPageShow.js"></script>
</html>

