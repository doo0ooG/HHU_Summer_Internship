<script setup>
import {
    Edit,
    Delete,
    Search
} from '@element-plus/icons-vue'
import { ref } from 'vue'
const input = ref('')
const userListData = ref([
])

// 声明一个获取用户列表的异步函数
import {userList} from '@/api/user.js'
const getuserList = async () => {
    let res = await userList();
    userListData.value = res.data;
    // console.log(userListData.value);
}
getuserList();

//控制添加用户弹窗
const dialogVisible = ref(false)

//添加用户数据模型
const userDataModel = ref({
    id: null,
    username: "",
    password: "",
    sex: "",
    tel: "",
    per: "",//默认为普通用户
    motto: "",
    grade: "",
    clubId:"",
    photoPath:"",
})
// //添加用户表单校验
// const rules = {
//     username: [
//         { required: true, message: '请输入用户名称', trigger: 'blur' },
//     ]
// }

// 生命一个添加用户信息的异步函数
import { userAdd } from '@/api/user.js'
import { ElMessage } from 'element-plus';
const adduser = async () => {
    let res = await userAdd(userDataModel.value);
    ElMessage.success("添加成功");
    dialogVisible.value = false;
    // console.log(userListData.value);
    getuserList();
    clearData();
}

// 定义对话框标题数据模型
const dialogTitle = ref('');
const showEditDialog = (row) => {
    dialogVisible.value = true;
    dialogTitle.value = '编辑用户基本信息';
    userDataModel.value.username = row.username;
    userDataModel.value.password = row.password;
    userDataModel.value.sex = row.sex;
    userDataModel.value.tel = row.tel;
    userDataModel.value.per = row.per;
    userDataModel.value.motto = row.motto;
    userDataModel.value.grade = row.grade;
    userDataModel.value.clubId = row.club_id;
    userDataModel.value.photoPath = row.photo_path;
    userDataModel.value.id = row.id;
}

import { userEdit } from '@/api/user.js'
// 编辑用户信息
const edituser = async () => {
    alert("未实现");
    // let res = await userEdit(userDataModel.value);
    // ElMessage.success("编辑成功");
    // dialogVisible.value = false;
    // // console.log(userListData.value);
    // getuserList();
    // clearData();
}

// 清空数据模型
const clearData = () => {
  userDataModel.value.username = row.username;
    userDataModel.value.password = row.password;
    userDataModel.value.sex = row.sex;
    userDataModel.value.tel = row.tel;
    userDataModel.value.per = row.per;
    userDataModel.value.motto = row.motto;
    userDataModel.value.grade = row.grade;
    userDataModel.value.clubId = row.club_id;
    userDataModel.value.photoPath = row.photo_path;
    userDataModel.value.id = row.id;
}
</script>

<template>

    <!-- 一张存放用户信息表格的卡片 -->
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>用户信息管理</span>
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
                    <el-button type="primary" @click="dialogVisible=true;dialogTitle='新建用户'">新建用户</el-button>
                </div>
            </div>
        </template>
        <el-table :data="userListData" style="width: 100%">
            <el-table-column label="序号" width="100" type="index"> </el-table-column>
            <!-- <el-table-column label="头像" prop="photoPath"></el-table-column> -->
            <el-table-column label="用户名" prop="username"></el-table-column>
            <el-table-column label="性别" prop="sex"></el-table-column>
            <el-table-column label="联系方式" prop="tel"></el-table-column>
            <el-table-column label="年级" prop="grade"></el-table-column>
            <el-table-column label="所属社团" prop="password"></el-table-column>
            <el-table-column label="座右铭" prop="motto"></el-table-column>
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

    <!-- 新建用户的对话框 -->
     <!-- 添加用户弹窗 -->
    <el-dialog v-model="dialogVisible" :title="dialogTitle" width="30%">
        <el-form :model="userDataModel" :rules="rules" label-width="100px" style="padding-right: 30px">
            <el-form-item label="用户名" prop="username">
                <el-input v-model="userDataModel.username" minlength="1" maxlength="10" :disabled="dialogTitle !== '新建用户'"></el-input>
            </el-form-item>
            <el-form-item label="密码">
                <el-input v-model="userDataModel.password" minlength="1" maxlength="15"></el-input>
            </el-form-item>
        </el-form>
        <template #footer>
            <span class="dialog-footer">
                <el-button @click="dialogVisible = false">取消</el-button>
                <el-button type="primary" @click="dialogTitle == '新建用户' ? adduser() : edituser()"> 确认 </el-button>
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