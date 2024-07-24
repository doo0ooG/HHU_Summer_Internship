<script setup>
import {
  Management,
  Promotion,
  UserFilled,
  User,
  Crop,
  EditPen,
  SwitchButton,
  CaretBottom
} from '@element-plus/icons-vue'

import { useTokenStore } from '@/stores/token';
const tokenStore = useTokenStore();
import { useRouter } from 'vue-router';
const router = useRouter();


const logout = () => {
    tokenStore.removeToken();
    router.push('/');
}


</script>

<template>
<el-dropdown-menu>
    <el-dropdown-item command="profile" :icon="User">基本资料</el-dropdown-item>
    <el-dropdown-item command="avatar" :icon="Crop">更换头像</el-dropdown-item>
    <el-dropdown-item command="password" :icon="EditPen">重置密码</el-dropdown-item>
    <el-dropdown-item command="logout" :icon="SwitchButton" @click="logout">退出登录</el-dropdown-item>
</el-dropdown-menu>
</template>

<style lang="scss" scoped>
</style>