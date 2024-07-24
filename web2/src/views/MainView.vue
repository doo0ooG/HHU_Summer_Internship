<script setup>import {
  Management,
  Promotion,
  UserFilled,
  User,
  Crop,
  EditPen,
  SwitchButton,
  CaretBottom
} from '@element-plus/icons-vue'
import avatar from '@/assets/default.png'

// 导入路由器
import { useRouter } from 'vue-router';
import { ElMessage } from 'element-plus';
// import DropDownMenu from "@/components/DropdownMenu.vue"
import { useUserInfoStore } from '@/stores/userinfo';
import { useTokenStore } from '@/stores/token';
const tokenStore = useTokenStore();
const userInfoStore = useUserInfoStore();
const router = useRouter();

// 跳转后台
const tarferManagePage = () => {
  // 判断当前是否为管理员
    // console.log(userInfoStore.info);
  if (userInfoStore.info.per < 3) {
    router.push("/manage");
  } else {
    ElMessage.error("您没有权限")
  }
}

// 调用函数，获取用户详细信息
import { userInfo } from '@/api/user';

const getUserIndo = async() => {
    if (!tokenStore.token) return;
    // console.log(123);
    let res = await userInfo();
    userInfoStore.setInfo(res.data);
}
getUserIndo();

const login = () => {
    router.push("/login");
}

import { ElMessageBox } from 'element-plus';

const handleCommand = (command) => {
    if (command === 'logout') {
        ElMessageBox.confirm(
            '你确认退出吗？',
            '温馨提示',
            {
                confirmButtonText: '确认',
                cancelButtonText: '取消',
                type: 'warning',
            }
        ).then( async () => {
            //用户点击了确认
            userInfoStore.removeInfo();
            tokenStore.removeToken();
            router.push('/login');
            ElMessage({
                type: 'success',
                message: '用户退出成功',
            })
        }).catch(() => {
            //用户点击了取消
            ElMessage({
                type: 'info',
                message: '用户取消退出',
            })
        })
    } else {
        // console.log('/main/' + command)
        router.push('/main/' + command)
    }
}
</script>

<template>
  <el-container class="layout-container">
      <el-container>
          <!-- 头部区域 -->
          <el-header>
              <div>吃皇粮的管理员：<strong> {{ userInfoStore.info.username ? userInfoStore.info.username : "还未登录"}} </strong></div>
              <el-button type="primary" round style="position: absolute; right: 20%;" @click="login" :disabled="userInfoStore.info.username != null">登录/注册</el-button>
              <el-button type="success" round style="position: absolute; right: 10%;" @click="tarferManagePage">后台管理</el-button>
              <el-dropdown placement="bottom-end" @command="handleCommand">
                  <span class="el-dropdown__box">
                      <el-avatar :src="userInfoStore.info.photoPath ? userInfoStore.info.photoPath : avatar" />
                      <el-icon>
                          <CaretBottom />
                      </el-icon>
                  </span>
                  <template #dropdown> 
                    <el-dropdown-menu>
                        <el-dropdown-item command="mymainpage" :icon="User">个人主页</el-dropdown-item>
                        <el-dropdown-item command="info" :icon="User">基本资料</el-dropdown-item>
                        <el-dropdown-item command="avatar" :icon="Crop">更换头像</el-dropdown-item>
                        <el-dropdown-item command="resetpd" :icon="EditPen">重置密码</el-dropdown-item>
                        <el-dropdown-item command="logout" :icon="SwitchButton" :disabled="userInfoStore.info.username == null">退出登录</el-dropdown-item>
                    </el-dropdown-menu>
                  </template>
              </el-dropdown>
          </el-header>
          <!-- 中间区域 -->
          <el-main>
              <!-- <div style="width: 1290px; height: 570px;border: 1px solid red;">
                  内容展示区
              </div> -->
              <router-view></router-view>
          </el-main>
          <!-- 底部区域 -->
          <el-footer>钨冬你是香香软软小蛋糕 By Group3</el-footer>
      </el-container>
  </el-container>
</template>

<style lang="scss" scoped>
.layout-container {
    height: 100vh;

    .el-header {
        background-color: #fff;
        display: flex;
        align-items: center;
        justify-content: space-between;

        .el-dropdown__box {
            display: flex;
            align-items: center;

            .el-icon {
                color: #999;
                margin-left: 10px;
            }

            &:active,
            &:focus {
                outline: none;
            }
        }
    }

    .el-footer {
        display: flex;
        align-items: center;
        justify-content: center;
        font-size: 14px;
        color: #666;
    }
}
</style>