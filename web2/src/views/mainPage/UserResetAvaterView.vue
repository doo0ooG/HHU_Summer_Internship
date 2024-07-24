<!-- 这里的代码是屎山！别乱动 -->


<script setup>
import { Plus, Upload } from "@element-plus/icons-vue";
import { ref } from "vue";
import avatar from "@/assets/default.png";
const uploadRef = ref();
import { useUserInfoStore } from '@/stores/userinfo';
import { useTokenStore } from "@/stores/token";
const tokenStore = useTokenStore();
const userInfoStore = useUserInfoStore();
//用户头像地址
const photoPath = ref(userInfoStore.info.photoPath);

const uploadSuccess = (result) => {
  photoPath.value = result.data;
};
import { userAvatar } from "@/api/user.js";
import { ElMessage } from "element-plus";

const dataModel = ref({
    photoPath: '',
});

const updateAvatar = async () => {
  dataModel.value.photoPath = photoPath.value;
  let result = await userAvatar(dataModel.value);
  ElMessage.success(result.msg ? result.msg : "修改成功");
  userInfoStore.info.photoPath = photoPath.value;
};

import { useRouter } from 'vue-router';
const router = useRouter();
</script>

<template>
  <el-card class="page-container">
    <template #header>
      <div class="header">
        <span>更换头像</span>
      </div>
    </template>
    <el-row>
      <el-col :span="12">
        <el-upload
          ref="uploadRef"
          class="avatar-uploader"
          :show-file-list="false"
          :auto-upload="true"
          action="/api/upload/photo"
          name="file"
          :headers="{ Authorization: tokenStore.token }"
          :on-success="uploadSuccess"
        >
          <img v-if="photoPath" :src="photoPath" class="avatar" />
          <img v-else :src="avatar" width="278" />
        </el-upload>
        <br />
        <el-button
          type="primary"
          :icon="Plus"
          size="large"
          @click="uploadRef.$el.querySelector('input').click()"
        >
          选择图片
        </el-button>
        <el-button
          type="success"
          :icon="Upload"
          size="large"
          @click="updateAvatar"
        >
          上传头像
        </el-button>

        <el-button type="danger" @click="router.push('/')">取消</el-button>
      </el-col>
    </el-row>
  </el-card>
</template>

<style lang="scss" scoped>
.avatar-uploader {
  :deep() {
    .avatar {
      width: 278px;
      height: 278px;
      display: block;
    }

    .el-upload {
      border: 1px dashed var(--el-border-color);
      border-radius: 6px;
      cursor: pointer;
      position: relative;
      overflow: hidden;
      transition: var(--el-transition-duration-fast);
    }

    .el-upload:hover {
      border-color: var(--el-color-primary);
    }

    .el-icon.avatar-uploader-icon {
      font-size: 28px;
      color: #8c939d;
      width: 278px;
      height: 278px;
      text-align: center;
    }
  }
}
</style>