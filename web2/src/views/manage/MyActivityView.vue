<script setup>
import {
  Edit,
  Delete,
} from '@element-plus/icons-vue'
import { ref } from 'vue'
const myactivityListData = ref([
])

// 声明一个获取社团列表的异步函数
import {myactivityDelete, myactivityList} from '@/api/myactivity.js'

const getmyactivityList = async () => {
  let res = await myactivityList();
  myactivityListData.value = res.data;
  // console.log(clubListData.value);
}
getmyactivityList();

//控制添加社团弹窗
const dialogVisible = ref(false)

//添加社团数据模型
const myactivityDataModel = ref({
  id: null,
  name:"",
  club:"",
  info: "",
  pos: "",
  start_time:"",
  end_time:"",


})
//添加社团表单校验
const rules = {
  name: [
    { required: true, message: '请输入活动名称', trigger: 'blur' },
  ]
}

// 生命一个添加社团信息的异步函数

import { ElMessage } from 'element-plus';



// const myactivityDelete = async () => {
//   alert("未实现")
// };


// 清空数据模型
const clearData = () => {
  myactivityDataModel.value.id = row.id;
  myactivityDataModel.value.name = row.name;
  myactivityDataModel.value.club = row.club;
  myactivityDataModel.value.info = row.info;
  myactivityDataModel.value.pos = row.pos;
  myactivityDataModel.value.start_time = row.start_time;
  myactivityDataModel.value.end_time = row.end_time;
}

// 定义对话框标题数据模型
const dialogTitle = ref('');
const showEditDialog = (row) => {
  dialogVisible.value = true;
  dialogTitle.value = '新增评论';
  myactivityDataModel.value.info = row.info;
}



</script>

<template>

  <!-- 一张存放社团信息表格的卡片 -->
  <el-card class="page-container">
    <template #header>
      <div class="header">
        <span>个人活动</span>
      </div>
    </template>
    <el-table :data="myactivityListData" style="width: 100%">
      <el-table-column label="序号" width="100" type="index"> </el-table-column>
      <el-table-column label="活动id" prop="id"></el-table-column>
      <el-table-column label="活动名称" prop="name"></el-table-column>
      <el-table-column label="所属社团" prop="club"></el-table-column>
      <el-table-column label="活动内容" prop="info"></el-table-column>
      <el-table-column label="活动地点" prop="pos"></el-table-column>
      <el-table-column label="开始时间" prop="start_time"></el-table-column>
      <el-table-column label="结束时间" prop="end_time"></el-table-column>
      <el-table-column label="操作" width="100">
        <template #default="{ row }">
          <el-button :icon="Delete" circle plain type="primary" @click="myactivityDelete"></el-button>
          <el-button :icon="Edit" circle plain type="primary" @click="showEditDialog(row)"></el-button>

        </template>
      </el-table-column>
      <template #empty>
        <el-empty description="没有数据" />
      </template>
    </el-table>
  </el-card>
  
  <!-- 添加评论弹窗 -->
<!--  <el-dialog v-model="dialogVisible" :title="dialogTitle" width="30%">-->
<!--    <el-form :model="myactivityDataModel" :rules="rules" label-width="100px" style="padding-right: 30px">-->

<!--      <el-form-item label="评价内容">-->
<!--        <el-input v-model="myactivityDataModel.info" minlength="1" maxlength="15"></el-input>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="评价时间">-->
<!--        <el-input v-model="myactivityDataModel.date" minlength="1" maxlength="15"></el-input>-->
<!--      </el-form-item>-->
<!--    </el-form>-->

<!--    <template #footer>-->
<!--            <span class="dialog-footer">-->
<!--                <el-button @click="dialogVisible = false">取消</el-button>-->
<!--                <el-button type="primary" @click="dialogTitle == '新建评价' ? addClub() : editClub()"> 确认 </el-button>-->
<!--            </span>-->
<!--    </template>-->
<!--  </el-dialog>-->
  
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