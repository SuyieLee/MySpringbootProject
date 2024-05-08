<!--&lt;!&ndash;（编辑博客）&ndash;&gt;-->
<!--<template>-->
<!--  <div>-->
<!--    <Header></Header>-->
<!--    <div class="m-content">-->
<!--      <el-form ref="editForm" status-icon :model="editForm" :rules="rules" label-width="80px">-->
<!--        <el-form-item label="文件" prop="title">-->
<!--          <el-input v-model="editForm.title"></el-input>-->
<!--        </el-form-item>-->
<!--        <el-form-item>-->
<!--          <el-button @click="selectForm()">选择文件</el-button>-->
<!--          <el-button type="primary" @click="submitForm()">上传文件</el-button>-->
<!--          <el-button @click="resetForm()">取消</el-button>-->
<!--        </el-form-item>-->
<!--      </el-form>-->
<!--    </div>-->
<!--  </div>-->
<!--</template>-->

<!--<script>-->
<!--import Header from "@/components/Header";-->
<!--export default {-->
<!--  name: "FileAdd.vue",-->
<!--  components:{Header},-->
<!--  data(){-->
<!--    return {-->
<!--      editForm: {-->
<!--        id: null,-->
<!--        title: '',-->
<!--        description: '',-->
<!--        content: ''-->
<!--      },-->
<!--      rules: {-->
<!--        title: [-->
<!--          {required: true, message: '请输入标题', trigger: 'blur'},-->
<!--          {min: 2, max: 100, message: '长度在 2 到 100 个字符', trigger: 'blur'}-->
<!--        ],-->
<!--        description: [-->
<!--          {required: true, message: '请输入摘要', trigger: 'blur'}-->
<!--        ]-->
<!--      }-->
<!--    }-->
<!--  },-->
<!--  created() {-->
<!--    const blogId = this.$route.params.blogId-->
<!--    const _this = this-->
<!--    if(blogId) {-->
<!--      this.$axios.get('/blog/' + blogId).then((res) => {-->
<!--        const blog = res.data.data-->
<!--        _this.editForm.id = blog.id-->
<!--        _this.editForm.title = blog.title-->
<!--        _this.editForm.description = blog.description-->
<!--        _this.editForm.content = blog.content-->
<!--      });-->
<!--    }-->
<!--  },-->
<!--  methods: {-->
<!--    selectForm() {-->
<!--      this.$refs.fileRef.dispatchEvent(new MouseEvent('click'))-->
<!--    },-->
<!--    submitForm(){-->
<!--      const _this = this-->
<!--    },-->
<!--    resetForm(){-->
<!--      const _this = this-->
<!--      this.$refs.editForm.validate((valid) => {-->
<!--        if (valid) {-->
<!--          this.$axios.post('/blog/upload', this.editForm, {-->
<!--            headers: {-->
<!--              "Authorization": localStorage.getItem("token")-->
<!--            }-->
<!--          }).then((res) => {-->
<!--            _this.$alert('上传成功', '提示', {-->
<!--              confirmButtonText: '确定',-->
<!--              callback: action => {-->
<!--                _this.$router.push("/blogs")-->
<!--              }-->
<!--            });-->
<!--          });-->
<!--        } else {-->
<!--          console.log('error submit!!');-->
<!--          return false;-->
<!--        }-->
<!--      })-->
<!--    }-->
<!--  }-->
<!--}-->
<!--</script>-->
<template>
  <div class="m-content">
    <Header></Header>
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="80px" class="demo-ruleForm">
      <el-form-item label="上传文件" prop="apk">
        <el-upload ref="upload" action="/manager/appupdate/addAppUpdate" :headers="headers"
                   :http-request="httpRequest" :before-remove="beforeRemove" :before-upload="beforeUploadFile" :on-exceed="handleExceed"
                   multiple :limit="1" :auto-upload="false" :on-change="getFile" :data="ruleForm" :file-list="fileList" name="annexFile"
                   style="width: 500px;">
          <el-button size="small" type="primary">点击上传</el-button>
          <div slot="tip">{{message}}</div>
        </el-upload>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')" v-loading.fullscreen.lock="fullscreenLoading">立即创建</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import Header from "@/components/Header";
export default {
  name: 'FileAdd',
  components:{Header},
  data() {
    return {
      fullscreenLoading: false,
      //上传
      headers: {
        token: getStore('zxdAdmintoken'),
        "content-type": "multipart/form-data"
      },
      message: '请上传apk文件',
      fileList: [], //文件列表
      fd: {}, //用于放数据  FormData类型
    }
  },
  methods: {
    //上传
    getFile(file, fileList) {
      this.fileList = fileList;
      // console.log(this.fileList)
      const fd = new FormData() // FormData 对象
      this.fd = fd
    },
    //上传前
    beforeUploadFile(file) {
      let extension = file.name.substring(file.name.lastIndexOf('.') + 1);
      console.log(extension)
      if (extension !== 'apk') {
        // this.$message.warning('只能上传后缀是.zip/.rar/.apk的文件'); //控制文件类型
        this.$message.warning('文件类型不对'); //控制文件类型
        return false
      }
    },
    //超限制
    handleExceed(files, fileList) {
      this.$message.warning("目前只能上传一个包")
    },
    //移除
    beforeRemove(file, fileList) {
      let extension = file.name.substring(file.name.lastIndexOf('.') + 1);
      if (extension !== 'apk') {
        return
      }
      return this.$confirm(`确定移除 ${file.name}？`)
    },
    httpRequest(param) {
      const fileObj = param.file // 相当于input里取得的files
      this.fd.append('apk', fileObj) // 文件对象
      console.log("文件包" + this.fd.get('apk'));
    },
    //提交
    submitForm(formName) {
      // let fileArr = this.$refs.upload.uploadFiles;
      // console.log(fileArr)
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if (this.fileList.length <= 0) {
            this.$message.error("至少上传一个包！");
            return;
          }
          this.$refs.upload.submit();
          //换行自动添加为<br/>
          this.ruleForm.log= this.ruleForm.log.replace(/\n/g,"<br/>");
          // console.log(this.ruleForm.log)
          //将表单内其他内容添加进fd
          this.fd.append('type', "0")
          this.fullscreenLoading = true;
          //调用后端接口，提交即可
          addAppUpdate(this.fd).then(data => {
            console.log(data)
            if (data.code == 0) {
              this.fullscreenLoading = false;
              this.$message({
                message: '上传成功',
                type: 'success'
              });
              this.fd = {}
              this.fileList = []
              resetForm(formName)
            } else {
              this.$message.error("上传失败");
            }
          })
        } else {
          // console.log('error submit!!');
          return false;
        }
      });
    },
    //重置
    resetForm(formName) {
      this.$refs[formName].resetFields();
      this.$refs.upload.clearFiles()
    },

  }
}
</script>


<style scoped>
.m-content{
  margin: 0 auto;
  /* margin-left: 100px; */
  max-width: 960px;
  text-align: center;
}
.content{
  height: 500px;
  background: rgb(248, 241, 237);
}
</style>