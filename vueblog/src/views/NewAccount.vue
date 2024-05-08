<!--（登录页面）-->
<template>
<!--  template下面只能有一个div-->
  <el-container>
    <el-header height="380px">
      <!-- width="1500" -->
      <img class="mlogo" src="../assets/logo.png" alt="" >
    </el-header>
    <el-main class="login-main">
      <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px"
               class="demo-ruleForm">
        <el-form-item label="用户名" prop="username">
<!--          双向绑定ruleform==》username  -->
          <el-input type="text" maxlength="12" v-model="ruleForm.username"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
<!--          双向绑定ruleform==》password  -->
          <el-input type="password" v-model="ruleForm.password" autocomplete="off"></el-input>
        </el-form-item>
<!--        <el-form-item label="邮箱" prop="password">-->
<!--          &lt;!&ndash;          双向绑定ruleform==》password  &ndash;&gt;-->
<!--          <el-input type="email" v-model="ruleForm.password" autocomplete="off"></el-input>-->
<!--        </el-form-item>-->
        <el-form-item>
          <el-button @click="resetForm('ruleForm')">重置</el-button>
          <el-button type="primary" @click="newForm('ruleForm')">注册</el-button>
        </el-form-item>
      </el-form>
    </el-main>
  </el-container>
</template>

<script>
export default {
  name: "NewAccount.vue",
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        callback();
      }
    };
    return {
      ruleForm: {
        password: '',
        username: ''
      },
      rules: {
        password: [
          {validator: validatePass, trigger: 'blur'}
        ],
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
          {min: 2, max: 12, message: '长度在 2 到 12 个字符', trigger: 'blur'}
        ]
      }
    };
  },
  methods: {
    resetForm(formName) {
      this.$refs[formName].resetFields();
      this.ruleForm.username=null;
      this.ruleForm.password=null;
    },
    newForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          const _this = this  //定义一个全局整个vue项目的this
          this.$axios.post('http://localhost:8081/newaccount', this.ruleForm).then((res) => {
            _this.$router.push("/login")
          })
        }else {
          console.log('error account!!');
          return false;
        }
      });
    },
  },
  mounted() {
    this.$notify({
      title: '欢迎',
      message: '欢迎欢迎～',
      duration: 1500
    });
  }
}
</script>

<style scoped>
.el-container{
  width:100%;
  height:100%;
}
.el-header, .el-footer {
  height:280px!important;
  color: #333;
  padding:0;
}
.mlogo{
  /* margin-left: 650px; */
  margin: 0 auto;
  padding: 10px;
  display: block;
  text-align:center;
  width:960px;
  height:280px;
}
.el-aside {
  /* background-color: #D3DCE6; */
  color: #333;
  text-align: center;
  line-height: 200px;
}

.el-main {
  /*background-color: #E9EEF3; Main部分的底色 */
  color: #333;
  text-align: center;
  line-height: 160px;
}

body > .el-container {
  margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}

.demo-ruleForm{
  max-width: 500px;
  margin: auto;
}
.login-main{
  min-height:600px;
}

</style>