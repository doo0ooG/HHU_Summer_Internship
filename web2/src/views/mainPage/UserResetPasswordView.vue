<script setup>
import { ref } from 'vue'

import { useUserInfoStore } from '@/stores/userinfo';
const userInfoStore = useUserInfoStore();

import { userEdit } from '@/api/user';
import { ElMessage } from 'element-plus';
import { useRouter } from 'vue-router';
const router = useRouter();

const passwordModel = ref({
    oldPassword: '',
    password: '',
    rePassword: ''
});

const subminModel = ref({
    username: '',
    oldPassword: '',
    newPassword: ''
});

const validatePassword = (rule, value, callback) => {
    if (value !== passwordModel.value.password) {
        callback(new Error('两次输入的密码不一致'));
    } else {
        callback();
    }
};

const rules = {
    oldPassword: [
        { required: true, message: '旧密码不能为空', trigger: 'blur' },
        { min: 5, max: 16, message: '旧密码长度在 5 到 16 个字符', trigger: 'blur' }
    ],
    password: [
        { required: true, message: '新密码不能为空', trigger: 'blur' },
        { min: 5, max: 16, message: '新密码长度在 5 到 16 个字符', trigger: 'blur' }
    ],
    rePassword: [
        { validator: validatePassword, trigger: 'blur' }
    ]
};

import { userResetPassword } from "@/api/user"

const updatePd = async () => {
    // alert("修改密码")
    subminModel.value.username = userInfoStore.info.username;
    subminModel.value.oldPassword = passwordModel.value.oldPassword;
    subminModel.value.newPassword = passwordModel.value.password;
    // console.log(subminModel.value);
    let res = await userResetPassword(subminModel.value);
    ElMessage.success("修改成功, 请重新登录");
    router.push('/login')
}
</script>
<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>基本资料</span>
            </div>
        </template>
        <!-- {{ passwordModel }} -->
        <el-row>
            <el-col :span="12">
                <el-form :model="passwordModel" :rules="rules" label-width="100px" size="large">
                    <el-form-item label="旧密码" prop="oldPassword">
                        <el-input type="password" v-model="passwordModel.oldPassword"></el-input>
                    </el-form-item>
                    <el-form-item label="新密码" prop="password">
                        <el-input type="password"  v-model="passwordModel.password"></el-input>
                    </el-form-item>
                    <el-form-item label="确认密码" prop="rePassword">
                        <el-input type="password"  v-model="passwordModel.rePassword"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="updatePd">提交修改</el-button>
                        <el-button type="danger" @click="router.push('/')">取消</el-button>
                    </el-form-item>
                </el-form>
            </el-col>
        </el-row>
    </el-card>
</template>