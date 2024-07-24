<script setup>
import { ref } from 'vue'
// const userInfo = ref({
//     motto: '',
//     sex: '',
//     tel: '',
//     grade: '',
// })


// 把useInfoStore数据回显
import { useUserInfoStore } from '@/stores/userinfo';
const userInfoStore = useUserInfoStore();
const userInfo = ref({...userInfoStore.info})
// const getInfo = () => {
//     userInfo.value.motto = userInfoStore.info.motto;
//     userInfo.value.sex = userInfoStore.info.sex;
//     userInfo.value.tel = userInfoStore.info.tel;
//     userInfo.value.grade = userInfoStore.info.grade;
// }
// getInfo();
const rules = {
    tel: [
        { 
            pattern: /^1\d{0,10}$/, 
            message: '手机号码格式不正确', 
            trigger: 'blur' 
        }
    ]
}
import { userEdit } from '@/api/user';
import { ElMessage } from 'element-plus';

const modifyBaseInfo = async () => {
    // alert("未实现");
    let res = await userEdit(userInfo.value);
    ElMessage.success("修改成功");
    // console.log(userInfo.value);
    userInfoStore.setInfo(userInfo.value);
}

import { useRouter } from 'vue-router';
const router = useRouter();

</script>
<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>基本资料</span>
            </div>
        </template>
        <el-row>
            <el-col :span="12">
                <el-form :model="userInfo" :rules="rules" label-width="100px" size="large">
                    <el-form-item label="性别">
                        <el-input v-model="userInfo.sex"></el-input>
                    </el-form-item>
                    <el-form-item label="年级">
                        <el-input v-model="userInfo.grade"></el-input>
                    </el-form-item>
                    <el-form-item label="手机号码" prop="tel">
                        <el-input v-model="userInfo.tel"></el-input>
                    </el-form-item>
                    <el-form-item label="座右铭">
                        <el-input v-model="userInfo.motto"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="modifyBaseInfo">提交修改</el-button>
                        <el-button type="danger" @click="router.push('/')">取消</el-button>
                    </el-form-item>
                </el-form>
            </el-col>
        </el-row>
    </el-card>
</template>