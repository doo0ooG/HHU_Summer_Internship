<script setup>
import {
  Edit,
  Delete,
  Search
} from '@element-plus/icons-vue'
import { ref } from 'vue'
import { enrollList, commentList } from "@/api/activity.js";
import { ElMessage } from 'element-plus';

const input = ref('')
const enrollListData = ref()


const getenrollList = async () => {
  let res = await enrollList();
  enrollListData.value = res.data;
  // console.log(enrollListData.value)
}
getenrollList();

//控制添加报名弹窗
const dialogVisible = ref(false)

const enrollDataModel = ref({
  id: null,
  userId: null,
  activityId: null,
  date: "",
  activityName: "",
  username: ""
})



const addenroll = async () => {
  alert("添加报名没写")
  // let res = await enrollAdd(enrollDataModel.value);
  // ElMessage.success("添加成功");
  // dialogVisible.value = false;
  // // console.log(enrollListData.value);
  // getenrollList();
  // clearData();
}

// 定义对话框标题数据模型
const dialogTitle = ref('');
const showEditDialog = (row) => {
  alert("回显没写")
  // dialogVisible.value = true;
  // dialogTitle.value = '编辑报名基本信息';
  // enrollDataModel.value.id = row.id;
  // enrollDataModel.value.activity_id = row.activity_id;
  // enrollDataModel.value.user_id = row.user_id;
  // enrollDataModel.date = row.date;
}

const deleteEnroll = async () => {
  alert("删除没实现")
}



// 编辑报名信息
const editenroll = async () => {
  alert("未实现");
}

// 清空数据模型
const clearData = () => {
  alert("clear没写")
}
</script>

<template>

  <!-- 一张存放报名信息表格的卡片 -->
  <el-card class="page-container">
    <template #header>
      <div class="header">
        <span>报名信息管理</span>
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
          <el-button type="primary" @click="dialogVisible=true;dialogTitle='新建报名'">新建报名</el-button>
        </div>
      </div>
    </template>
    <el-table :data="enrollListData" style="width: 100%">
      <el-table-column label="序号" width="100" type="index"> </el-table-column>
      <el-table-column label="用户名" prop="username"></el-table-column>
      <el-table-column label="活动名称" prop="activityName"></el-table-column>
      <el-table-column label="报名时间" prop="date"></el-table-column>
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

  <!-- 新建报名的对话框 -->
  <!-- 添加报名弹窗 -->
  <el-dialog v-model="dialogVisible" :title="dialogTitle" width="30%">
    <el-form :model=" enrollDataModel"  label-width="100px" style="padding-right: 30px">
      <el-form-item label="用户名" >
        <el-input v-model="enrollDataModel.userId" minlength="1" maxlength="10" :disabled="dialogTitle !== '新建报名'"></el-input>
      </el-form-item>
      <el-form-item label="活动名称" >
        <el-input v-model="enrollDataModel.activity_id" minlength="1" maxlength="10"></el-input>
      </el-form-item>
      <!-- 报名时间在添加的时候自动生成吧，比较合理 -->
      <!-- <el-form-item label="报名时间">
        <el-input v-model="enrollDataModel.activity_id" minlength="1" maxlength="15"></el-input>
      </el-form-item> -->
      <el-form-item label="报名时间">
        <el-input v-model="enrollDataModel.date" minlength="1" maxlength="15"></el-input>
      </el-form-item>
    </el-form>
    <template #footer>
            <span class="dialog-footer">
                <el-button @click="dialogVisible = false">取消</el-button>
                <el-button type="primary" @click="dialogTitle == '新建评论' ? addenroll() : editenroll()"> 确认 </el-button>
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