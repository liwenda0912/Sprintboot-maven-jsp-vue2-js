<%--
  Created by IntelliJ IDEA.
  User: 10260
  Date: 2024/9/28
  Time: 9:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
    <link rel="stylesheet" href="https://unpkg.com/element-ui@2.15.14/lib/theme-chalk/index.css">
    <script src="https://unpkg.com/vue@2/dist/vue.js"></script>
    <script src="https://unpkg.com/element-ui@2.15.14/lib/index.js"></script>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    <title>uploadFile</title>
</head>
<style>
    .login_ {
        background-image: url('https://img.picui.cn/free/2024/09/18/66ea365e2ba3d.jpg'); /* 设置背景图片 */
        background-size: cover; /* 背景图片覆盖整个元素 */
        background-repeat: no-repeat; /* 背景图片不重复 */
        background-position: center; /* 背景图片居中 */
        image-rendering: auto;
        width: 100%; /* 元素宽度 */
        height: 100%; /* 元素高度 */
    }

    * {
        padding: 0;
        margin: 0;

    }

    .buttom_show {
        text-align: center;
    }

    button:active {
        background-color: cadetblue;
        box-shadow: 0 0 10px cadetblue;
        transform: translateY(4px);
    }

    .bottom_register {
        margin: 0;
        text-align: center;
    }

    .register_bottom {
        width: 69px;
    }

    .input_body {
        /*background: white;*/
        /*width: 350px;*/
        /*height: 400px;*/
        /*box-shadow: 0 0 2px  grey;*/
        position: absolute;
        height: 350px;
        width: 360px;
        background-color: white;
        margin-right: 20px;
        border: 2px solid white;
        border-radius: 20px;
        box-shadow: 0 0 10px grey;
        top: 30%;
        left: 40%;
    }
</style>
<body>
<div class="login_">
    <div class="input_body">
        <p style="margin:5px 5px 5px 5px;text-align:center">登录</p>
        <div id="APP_input">
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm">
                <div id="input-name">
                    <el-form-item label="UserName:" prop="input">
                        <el-input placeholder="请输入账号" v-model="ruleForm.input" clearable name="username"
                                  class="input_shadow"></el-input>
                    </el-form-item>
                </div>
                <div id="input-passwd">
                    <el-form-item label="PassWord:" prop="input_passwd">
                        <el-input placeholder="请输入密码" v-model="ruleForm.input_passwd" show-password name="passwd"
                                  class="input_shadow"></el-input>
                        <%--                              <label>--%>
                        <%--                                  <input type="password" placeholder="请输入密码" name="passwd">--%>
                        <%--                              </label>--%>
                    </el-form-item>
                </div>
                <div class="buttom_login_1">
                    <el-form-item class="buttom_show" style="margin: 0">
                        <el-button type="primary" @click="submit('ruleForm')" class="input_shadow">登录</el-button>
                        <%--      <button  ">Button</button>--%>
                    </el-form-item>
                </div>
            </el-form>
        </div>
        <div id="app-2" class="bottom_register">
            <el-button type="text" @click="register()" class="register_bottom">注 册</el-button>
        </div>
    </div>
</div>
<div id="app-1">
    <!-- Form -->
    <transition name="el-zoom-in-bottom">
        <el-dialog title="注册" :visible.sync="dialogFormVisible" style="text-align: center" :show-close="false"
                   :center="true" :close-on-click-modal="false">
            <el-form :model="type_data.ruleForm" :rules="rules" ref="type_data.ruleForm">
                <el-form-item label="账号:" :label-width="formLabelWidth" prop="username">
                    <el-input v-model.trim="type_data.ruleForm.username" autocomplete="off" clearable
                              ref="user"></el-input>
                </el-form-item>
                <el-form-item label="密码:" :label-width="formLabelWidth" prop="password">
                    <el-input v-model.trim="type_data.ruleForm.password" autocomplete="off" clearable
                              show-password ref="passwd"></el-input>
                </el-form-item>
                <el-form-item label="电话:" :label-width="formLabelWidth" prop="phone">
                    <el-input v-model="type_data.ruleForm.phone" autocomplete="off" clearable ref="phone"></el-input>
                </el-form-item>
                <el-form-item label="详细地址:" :label-width="formLabelWidth" prop="address">
                    <el-input v-model="type_data.address" autocomplete="off" clearable ref="address"></el-input>
                </el-form-item>
                <el-form-item label="城市:" :label-width="formLabelWidth" prop="city">
                    <el-input v-model="type_data.city" autocomplete="off" clearable ref="city"></el-input>
                </el-form-item>
                <el-form-item label="省份:" :label-width="formLabelWidth" prop="province">
                    <el-input v-model="type_data.province" autocomplete="off" clearable ref="province"></el-input>
                </el-form-item>
                <el-form-item label="邮政编码:" :label-width="formLabelWidth" prop="zip">
                    <el-input v-model="type_data.ruleForm.zip" autocomplete="off" clearable ref="zip"></el-input>
                </el-form-item>
                <div class="dialog-footer" style="text-align: center">
                    <el-form-item>
                        <el-button type="primary" @click="registered('type_data.ruleForm')">注册</el-button>
                        <el-button type="primary" @click="quit()">取消</el-button>
                    </el-form-item>
                </div>
            </el-form>
        </el-dialog>
    </transition>
</div>
<script src="../../js/login.js" type="module"></script>
</body>
</html>