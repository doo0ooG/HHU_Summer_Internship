<script setup>
import {
    Edit,
    Delete,
    Search
} from '@element-plus/icons-vue'
import { ref } from 'vue'
const input = ref('')
const activityListData = ref([
])

// 声明一个获取活动列表的异步函数
import {activityList} from '@/api/activity.js'
const getactivityList = async () => {
    let res = await activityList();
    activityListData.value = res.data;
}
getactivityList();
const resetSearch = () => {
    getactivityList();
    input.value = '';
};
//控制添加活动弹窗
const dialogVisible = ref(false)

//添加活动数据模型
const activityDataModel = ref({
    id: null,
    name: "",
    clubName: "",
    info: "",
    hostUsername: "",
    startDate:"",
    endDate:"",
    pos:"",
    cate:"",
    limitNum:"",
    photoPath:""
})
//添加活动表单校验
const rules = {
    name: [
        { required: true, message: '请输入活动名称', trigger: 'blur' },
    ]   
}

// 生命一个添加活动信息的异步函数
import { activityAdd } from '@/api/activity.js'
import { ElMessage } from 'element-plus';
const addactivity = async () => {
    let res = await activityAdd(activityDataModel.value);
    ElMessage.success("添加成功");
    dialogVisible.value = false;
    // console.log(activityListData.value);
    getactivityList();
    clearData();
}

// 定义对话框标题数据模型
const dialogTitle = ref('');
const showEditDialog = (row) => {
    console.log("row" + row)
    dialogVisible.value = true;
    dialogTitle.value = '编辑活动基本信息';
    activityDataModel.value.name = row.name;
    activityDataModel.value.clubName = row.clubName;
    activityDataModel.value.info = row.info;
    activityDataModel.value.hostUsername = row.hostUsername;
    activityDataModel.value.startDate = row.startDate;
    activityDataModel.value.endDate = row.endDate;
    activityDataModel.value.pos = row.pos;
    activityDataModel.value.cate = row.cate;
    activityDataModel.value.limitNum = row.limitNum;
    activityDataModel.value.id = row.id;
    activityDataModel.value.photoPath = row.photoPath;
    console.log("activity" + activityDataModel.value)

}

import { activityEdit } from '@/api/activity.js'
// 编辑活动信息
const editactivity = async () => {
    // console.log(activityListData.value);
    alert("未实现");
    // let res = await activityEdit(activityDataModel.value);
    // ElMessage.success("编辑成功");
    // dialogVisible.value = false;
    // // // console.log(activityListData.value);
    // getactivityList();
    // clearData();
}
// 清空数据模型
const clearData = () => {
    activityDataModel.value.name = '';
    activityDataModel.value.clubName = '';
    activityDataModel.value.info = '';
    activityDataModel.value.hostUsername = '';
    activityDataModel.value.startDate = '';
    activityDataModel.value.endDate = '';
    activityDataModel.value.pos = '';
    activityDataModel.value.cate = '';
    activityDataModel.value.limitNum = '';
    activityDataModel.value.id = '';
    activityDataModel.value.photoPath = '';
}

// 定义查询函数
const searchActivitys = () => {
    const keyword = input.value.trim().toLowerCase();
    // 定义一个函数来判断社团是否包含关键字
    const containsKeyword = (activity) => {
        return (
            activity.name.toLowerCase().includes(keyword) ||
            activity.clubName.toLowerCase().includes(keyword) ||
            activity.info.toLowerCase().includes(keyword)||
            activity.hostUsername.toLowerCase().includes(keyword) ||
            activity.startDate.toLowerCase().includes(keyword) ||
            activity.endDate.toLowerCase().includes(keyword) ||
            activity.pos.toLowerCase().includes(keyword) ||
            activity.cate.toLowerCase().includes(keyword) 
            // activity.limitNum.toLowerCase().includes(keyword) ||
            // activity.photoPath.toLowerCase().includes(keyword) 
        );
    };
    // 使用该函数来过滤社团列表
    const filteredActivitys = activityListData.value.filter(containsKeyword);
    activityListData.value = filteredActivitys;
};


</script>

<template>

    <!-- 一张存放活动信息表格的卡片 -->
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>活动信息管理</span>
                <div class="extra">
                    <el-input
                        v-model="input"
                        style="width: 240px"
                        placeholder="Please input"
                        clearable
                    />
                    &nbsp;&nbsp;
                    <el-button type="success" :icon="Search" @click="searchActivitys" circle plain/>
              
                    <el-button type="default" @click="resetSearch">重置查询</el-button>
                    
                </div>
                <div class="extra">
                    <el-button type="primary" @click="dialogVisible=true;dialogTitle='新建活动'">新建活动</el-button>
                </div>
            </div>
        </template>
        <el-table :data="activityListData" style="width: 100%">
            <el-table-column label="序号" width="100" type="index"> </el-table-column>
            <el-table-column label="活动名称" prop="name"></el-table-column>
            <el-table-column label="所属社团" prop="clubName"></el-table-column>
            <el-table-column label="负责人" prop="hostUsername"></el-table-column>
            <el-table-column label="活动简介" prop="info"></el-table-column>
            <el-table-column label="活动类型" prop="cate"></el-table-column>
            <el-table-column label="开始时间" prop="startDate"></el-table-column>
            <el-table-column label="结束时间" prop="endDate"></el-table-column>
            <el-table-column label="活动地点" prop="pos"></el-table-column>
            <el-table-column label="限制人数" prop="limitNum"></el-table-column>
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

    <!-- 新建活动的对话框 -->
     <!-- 添加活动弹窗 -->
    <el-dialog v-model="dialogVisible" :title="dialogTitle" width="30%">
        <el-form :model="activityDataModel" :rules="rules" label-width="100px" style="padding-right: 30px">
            <el-form-item label="活动名称" prop="name">
                <el-input v-model="activityDataModel.name" minlength="1" maxlength="10" :disabled="dialogTitle !== '新建活动'"></el-input>
            </el-form-item>
            <!-- 负责人先放了id但是我觉得应该是负责人姓名，有待改进 -->
             <!-- 以及可以选择下拉框展示以及有的社团负责人选项，更符合实际 -->
            <el-form-item label="负责人" prop="userid">
                <el-input v-model="activityDataModel.hostUsername" minlength="1" maxlength="10" :disabled="dialogTitle !== '新建活动'"></el-input>
            </el-form-item>
            <!-- 同上 -->
            <el-form-item label="所属社团" prop="clubid">
                <el-input v-model="activityDataModel.clubName" minlength="1" maxlength="10" :disabled="dialogTitle !== '新建活动'"></el-input>
            </el-form-item>
            <el-form-item label="活动简介">
                <el-input v-model="activityDataModel.info" minlength="1" maxlength="200"></el-input>
            </el-form-item>
            <el-form-item label="活动类型">
                <el-input v-model="activityDataModel.cate" minlength="1" maxlength="50"></el-input>
            </el-form-item>
           
            <el-form-item label="开始时间">
                <el-date-picker
                    v-model="activityDataModel.startDate"
                    type="datetime"
                    placeholder="Select date and time"
                />    
                </el-form-item>
            <el-form-item label="结束时间">
                <el-date-picker
                    v-model="activityDataModel.endDate"
                    type="datetime"
                    placeholder="Select date and time"
                /> 
            </el-form-item>
            <el-form-item label="活动地点">
                <el-input v-model="activityDataModel.pos" minlength="1" maxlength="50"></el-input>
            </el-form-item>
            <el-form-item label="限制人数">
                <el-input v-model="activityDataModel.limitNum" minlength="1" maxlength="50"></el-input>
            </el-form-item>
            <!-- 选择图片的组件 -->
        </el-form>
        <template #footer>
            <span class="dialog-footer">
                <el-button @click="dialogVisible = false">取消</el-button>
                <el-button type="primary" @click="dialogTitle == '新建活动' ? addactivity() : editactivity()"> 确认 </el-button>
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