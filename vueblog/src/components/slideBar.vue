<template>
  <!-- <div class="slideBar"> -->
    <el-menu
        class="el-menu-vertical-demo"
        background-color="#fdf6ec"
        text-color="#006400"
      >
        <el-menu-item index="1">
          <i class="el-icon-ship"></i>
          <a slot="title" href="https://www.bilibili.com/video/BV1Nb411G7oB?p=44&vd_source=ba52dc480d1c727b96fa0ebfb6fa2089">证从法律法规</a>
        </el-menu-item>
        <el-menu-item index="2">
          <i class="el-icon-edit"></i>
          <a slot="title" href="https://www.bilibili.com/video/BV1Ab411V7iU/?spm_id_from=333.337.search-card.all.click&vd_source=ba52dc480d1c727b96fa0ebfb6fa2089">证从金融市场</a>
        </el-menu-item>
        <el-menu-item index="3">
          <i class="el-icon-goblet-square-full"></i>
          <a slot="title" href="https://search.bilibili.com/all?keyword=初级会计实务&from_source=webtop_search&spm_id_from=333.788&search_source=2">初会实务</a>
        </el-menu-item>
        <el-menu-item index="4">
          <i class="el-icon-lollipop"></i>
          <a slot="title" href="https://search.bilibili.com/all?keyword=初级会计经济法&from_source=webtop_search&spm_id_from=333.788&search_source=2">初会经济法</a>
        </el-menu-item>
        <el-menu-item index="5">
          <i class="el-icon-star-off"></i>
          <a slot="title" href="http://kzp.mof.gov.cn/">初会报名</a>
        </el-menu-item>
        <el-menu-item index="6">
          <i class="el-icon-present"></i>
          <a slot="title" href="https://www.sac.net.cn/cyry/kspt/ksbm/">证从报名</a>
        </el-menu-item>
    </el-menu>
  <!-- </div> -->
</template>

<script>
export default {
  name:"slideBar",
}
</script>

<style scoped>
a{
  text-decoration:none;
  color: rgb(0 0 0 / 46%);
}
/* /deep/ .el-menu{ */
  /* display: flex;
  flex-direction: column;
  justify-content:space-between; */
/* } */

::v-deep .el-menu{
  height:100%;
  display: block;
}
::v-deep .el-menu-item{
  height:103px;
  line-height: 103px;
  /* height:30%; */
  font-size: 20px;
  text-align:left;
  /* flex: 1; */
  font-size: 24px;
  color: #303133;
  padding: 0px 56px;
  cursor: pointer;
  transition: border-color .3s,background-color .3s,color .3s;
  box-sizing: border-box;
}

/* /deep/ .el-menu-item{
  height:55px;
  /* flex-grow:1; */
  /* font-size: 23px; */
  /* text-align:left; */
  /* justify-content:space-between; */
/* } */ 

/* .el-menu-item, .el-submenu__title {
    height: 60px;
    line-height: 56px;
    position: relative;
    -webkit-box-sizing: border-box;
    white-space: nowrap;
    list-style: none;
} */


</style>