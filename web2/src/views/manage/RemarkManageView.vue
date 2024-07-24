<script setup>
import {
  Edit,
  Delete,
  Search
} from '@element-plus/icons-vue'
import { ref } from 'vue'
const input = ref('')
const remarkListData = ref([
])

// 声明一个获取评价列表的异步函数
import { commentList } from '@/api/activity.js'
import { ElMessage } from 'element-plus';


const getRemarkList = async () => {
  let res = await commentList();
  remarkListData.value = res.data;
  // console.log(remarkListData.value);
}
getRemarkList();

//控制添加评价弹窗
const dialogVisible = ref(false)

//添加评价数据模型
const remarkDataModel = ref({
  id: null,
  activity_id: "",
  user_id: "",
  info: "",
  date: "",
})


const addRemark = async () => {
  alert("未实现")
  // let res = await remarkAdd(remarkDataModel.value);
  // ElMessage.success("添加成功");
  // dialogVisible.value = false;
  // // console.log(remarkListData.value);
  // getRemarkList();
  // clearData();
}

// 定义对话框标题数据模型
const dialogTitle = ref('');
const showEditDialog = (row) => {
  alert("未实现")
  // dialogVisible.value = true;
  // dialogTitle.value = '编辑评价基本信息';
  // remarkDataModel.value.id = row.id;
  // remarkDataModel.value.activity_id = row.activity_id;
  // remarkDataModel.value.user_id = row.user_id;
  // remarkDataModel.value.info = row.info;
  // remarkDataModel.date = row.date;
}




// 编辑社团信息
const editRemark = async () => {
  alert("未实现");
  // let res = await clubEdit(clubDataModel.value);
  // ElMessage.success("编辑成功");
  // dialogVisible.value = false;
  // // console.log(clubListData.value);
  // getClubList();
  // clearData();
}

// 清空数据模型
const clearData = () => {
  alert("清空数据模型为实现")
  // remarkDataModel.value.id = row.id;
  // remarkDataModel.value.activity_id = row.activity_id;
  // remarkDataModel.value.user_id = row.user_id;
  // remarkDataModel.value.info = row.info;
  // remarkDataModel.date = row.date;
}
</script>

<template>

  <!-- 一张存放社团信息表格的卡片 -->
  <el-card class="page-container">
    <template #header>
      <div class="header">
        <span>评价信息管理</span>
        <div class="extra">
          <el-input
              v-model="input"
              style="width: 240px"
              placeholder="Please input"
              clearable
          />
            &nbsp;&nbsp;
            <el-button type="success" :icon="Search" circle plain/>
        </div>
        <div class="extra">
          <el-button type="primary" @click="dialogVisible=true;dialogTitle='新建评价'">新建评价</el-button>
        </div>
      </div>
    </template>
    <el-table :data="remarkListData" style="width: 100%">
      <el-table-column label="序号" width="100" type="index"> </el-table-column>
      <el-table-column label="评论id" prop="id"></el-table-column>
      <el-table-column label="活动" prop="activityName"></el-table-column>
      <el-table-column label="评价人" prop="username"></el-table-column>
      <el-table-column label="评价内容" prop="info"></el-table-column>
      <el-table-column label="评价时间" prop="date"></el-table-column>
      <el-table-column label="操作" width="100">
        <template #default="{ row }">
          <el-button :icon="Edit" circle plain type="primary" @click="showEditDialog(row)"></el-button>
          <el-button :icon="Delete" circle plain type="danger"></el-button>
        </template>
      </el-table-column>
      <template #empty>
        <el-empty description="没有数据" />
      </template>
    </el-table>
  </el-card>

  <!-- 新建社团的对话框 -->
  <!-- 添加社团弹窗 -->
  <el-dialog v-model="dialogVisible" :title="dialogTitle" width="30%">
    <el-form :model=" remarkDataModel"  label-width="100px" style="padding-right: 30px">
      <el-form-item label="评价id" >
        <el-input v-model="remarkDataModel.id" minlength="1" maxlength="10" :disabled="dialogTitle !== '新建评价'"></el-input>
      </el-form-item>
      <el-form-item label="评价人id">
        <el-input v-model="remarkDataModel.user_id" minlength="1" maxlength="10"></el-input>
      </el-form-item>
      <el-form-item label="活动id">
        <el-input v-model="remarkDataModel.activity_id" minlength="1" maxlength="15"></el-input>
      </el-form-item>
      <el-form-item label="评价内容">
        <el-input v-model="remarkDataModel.info" minlength="1" maxlength="15"></el-input>
      </el-form-item>
      <el-form-item label="评价时间">
        <el-input v-model="remarkDataModel.date" minlength="1" maxlength="15"></el-input>
      </el-form-item>
    </el-form>
    <template #footer>
            <span class="dialog-footer">
                <el-button @click="dialogVisible = false">取消</el-button>
                <el-button type="primary" @click="dialogTitle == '新建评论' ? addRemark() : editRemark()"> 确认 </el-button>
            </span>
    </template>
  </el-dialog>
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