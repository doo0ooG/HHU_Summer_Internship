<script setup>
import {
  Edit,
  Delete
} from '@element-plus/icons-vue'
import { ref } from 'vue'
const myremarkListData = ref([
])
import {myremarkEdit, myremarkDelete, myremarkList} from '@/api/myremark.js'

const getMyremarkList = async () => {
  let res = await myremarkList();
  myremarkListData.value = res.data;
  // console.log(clubListData.value);
}
getMyremarkList();

//控制添加社团弹窗
const dialogVisible = ref(false)

//添加社团数据模型
const myremarkDataModel = ref({
  id: null,
  name:"",
  info:"",
  date:""
})
//添加社团表单校验
const rules = {
  name: [
    { required: true, message: '请输入活动id', trigger: 'blur' },
  ]
}

// 生命一个添加社团信息的异步函数

import { ElMessage } from 'element-plus';



// 定义对话框标题数据模型
const dialogTitle = ref('');
const showEditDialog = (row) => {
  dialogVisible.value = true;
  dialogTitle.value = '编辑评论基本信息';
  myremarkDataModel.value.info = row.info;
}

// const myremarkEdit = async () => {
//   alert("未实现");
//   // let res = await myremarkbEdit(myremarkDataModel.value);
//   // ElMessage.success("编辑成功");
//   // dialogVisible.value = false;
//   // // console.log(clubListData.value);
//   // getClubList();
//   // clearData();
// }

// 清空数据模型
const clearData = () => {
  myremarkDataModel.value.id = row.id;
  myremarkDataModel.value.name = row.name;
  myremarkDataModel.value.info = row.info;
  myremarkDataModel.value.date = row.date
  }

// const myremarkDelete = async () => {
//   alert("未实现")
// }

</script>

<template>

  <!-- 一张存放社团信息表格的卡片 -->
  <el-card class="page-container">
    <template #header>
      <div class="header">
        <span>个人评价</span>
      </div>
    </template>
    <el-table :data="clubListData" style="width: 100%">
      <el-table-column label="序号" width="100" type="index"> </el-table-column>
      <el-table-column label="活动id" prop="id" width="100px"></el-table-column>
      <el-table-column label="活动名称" prop="name"></el-table-column>
      <el-table-column label="评价内容" prop="info"></el-table-column>
      <el-table-column label="评价时间" prop="date"></el-table-column>

      <el-table-column label="操作" width="100">

        <template #default="{ row }">
          <el-button :icon="Edit" circle plain type="primary" @click="showEditDialog(row)"></el-button>
          <el-button :icon="Delete" circle plain type="danger" @click="myremarkDelete"></el-button>
        </template>
      </el-table-column>


      <template #empty>
        <el-empty description="没有数据" />
      </template>
    </el-table>
  </el-card>

</template>

<style lang="scss" scoped>
.page-container {
  min-height: 100%;
  box-sizing: border-box;

  .header {
    display: flex;
    align-items: center;
    justify-content: space-between;
  }
}
</style>