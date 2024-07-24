<template>
  <div>
    <el-card class="page-container">
    <el-card class="page-container">
    <div class="activity-detail">
      <el-button type="danger" @click="goBack" class="back-button">返回</el-button>
      <el-row type="flex" class="activity-detail-container" justify="center">
        <el-col :span="8" class="activity-image-col">
          <img v-if="activity.photoPath" :src="activity.photoPath" width="100%"/>
          <img v-else :src="avatar" width="100%" />
        </el-col>
        <el-col :span="16" class="activity-info-col">
          <div class="activity-info">
            <h2>{{ activity.name }}</h2>
            <p>活动内容: {{ activity.info }}</p>
            <p>活动类型: {{ activity.cate }}</p>
            <p>所属社团: {{ activity.clubName }}</p>
            <p>负责人: {{ activity.username }}</p>
            <p>负责人联系方式: {{ activity.tel }}</p>
            <p>开始时间: {{ formatDate(activity.startDate) }}</p>
            <p>结束时间: {{ formatDate(activity.endDate) }}</p>
            <p>已报名人数: {{ activity.enrollNum }}</p>
            <p>报名限制人数: {{ activity.limitNum }}</p>
            <!-- <el-button type="primary" @click="apply">报名</el-button> -->
            <el-button type="primary" round :disable="activity.enrollNum >= activity.limitNum" @click="enroll">活动报名</el-button>
            <el-button type="danger" round @click="withdraw">取消报名</el-button>
          </div>
        </el-col>
      </el-row>
    </div>
  </el-card>

  <el-card class="page-container">
    <div class="common-layout">
        <el-container style="width:75%">
          
          <el-header><h3>评论区</h3></el-header>
          <!-- {{commentSubmitDataModel}} -->
          <el-main>
            <el-form  label-width="100px" :model="commentSubmitDataModel" >
                <el-form-item label="评论内容">
                  <hr><hr>
                    <div class="editor">
                      <quill-editor theme="snow" contentType="text" v-model:content = "commentSubmitDataModel.comment">
                        
                      <!-- v-model:content="articleModel.content" -->
                    </quill-editor>
                    </div>
                      
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitComment()">发布</el-button>
                </el-form-item>
            </el-form>

            <hr>
            <!-- 展示评论列表 -->
            <el-card class="comment-card" v-for = "(comment, index) in commentData" :key="index">
              <!-- 用户头像 -->
              <div class="avatar">
                <img v-if="comment.photoPath" :src="comment.photoPath" class="avatar-img" />
                <img v-else :src="userAvatar" class="avatar-img" />
                <!-- <img src="path_to_user_avatar" alt="User Avatar" class="avatar-img" /> -->
                 {{ comment.username }}
              </div>
              
              <!-- 用户评论内容 -->
              <div class="comment-content">
                 <p>{{comment.info}} </p>
              </div>
              
              <!-- 发表时间 -->
              <div class="publish-time">
                <span> {{ comment.date }} </span>
              </div>
            </el-card>
          </el-main>

        </el-container>
      </div>
  </el-card>
  </el-card>
  </div>
      
</template>

<script setup>
import avatar from "@/assets/defaultActivity.jpg";
import userAvatar from "@/assets/default.png";
import { ref } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { ElButton, ElRow, ElCol } from 'element-plus'
import { QuillEditor } from '@vueup/vue-quill'
import '@vueup/vue-quill/dist/vue-quill.snow.css'

// // 获取路由和活动ID
const route = useRoute()
const router = useRouter()
const activityId = route.query.activityId
// console.log(activityId)

// // 活动数据
const activity = ref({})
const commentData = ref([])
const userData = ref({})
const commentSubmitDataModel = ref({
  activityId: null,
  comment: ""
})

// 获取活动信息
import {getDetail, enrollActivity, withdrawActivity, CommentById, submitCommentService} from "@/api/activity"
import {getUserDetail} from "@/api/user"
import {getClubDetail} from "@/api/club"
import { ElMessage } from 'element-plus';
const getActivity = async () => {
  let res = await getDetail(activityId);
  // console.log(res.data);
  activity.value = res.data;
  // console.log(res.data.userId);
  res = await getUserDetail(activity.value.userId);
  activity.value.username = res.data.username;
  activity.value.tel = res.data.tel;
  res = await getClubDetail(activity.value.clubId)
  // console.log(res);
  activity.value.clubName = res.data.name;
}
getActivity();

const enroll = async () => {
  let res = await enrollActivity(activityId);
  ElMessage.success("报名成功");
  getActivity();
}

const withdraw = async () => {
  let res = await withdrawActivity(activityId);
  ElMessage.success("取消报名成功");
  getActivity();
}

const getCommentList = async () => {
  let res = await CommentById(activityId);
  // console.log(res.data);
  commentData.value = res.data;
  // console.log(commentData.value);
}
getCommentList();

const submitComment = async () => {
  commentSubmitDataModel.value.activityId = activityId;
  console.log(commentSubmitDataModel.value)
  if (commentSubmitDataModel.value.comment === '') {
    ElMessage.error("不能发布空内容");
    return;
  }
  let res = await submitCommentService(commentSubmitDataModel.value);
  ElMessage.success("发布成功");
  getCommentList();
  commentSubmitDataModel.value.comment = '';
}

const formatDate = (datetime) => {
  const date = new Date(datetime)
  const year = date.getFullYear()
  const month = String(date.getMonth() + 1).padStart(2, '0')
  const day = String(date.getDate()).padStart(2, '0')
  const hours = String(date.getHours()).padStart(2, '0')
  return `${year}-${month}-${day} ${hours}:00`
}

// 返回上一页
const goBack = () => {
  router.back()
}

</script>

<style scoped>
.comment-card {
  border-bottom: 20px;
  display: flex;
  flex-direction: column;
}

.avatar {
  margin: 10px;
}

.avatar-img {
  width: 50px; /* 根据需要调整头像大小 */
  height: 50px;
  border-radius: 50%; /* 圆形头像 */
  object-fit: cover; /* 确保头像内容适应圆形 */
}

.comment-content {
  flex: 1;
  padding: 0 10px;
}

.publish-time {
  padding: 10px;
  font-size: 12px;
  color: #999; /* 灰色字体，根据需要调整 */
  text-align: right; /* 发表时间右对齐 */
}
.editor {
  width: 100%;
  :deep(.ql-editor) {
    min-height: 200px;
  }
}
.activity-detail {
  padding: 20px;
  max-width: 1200px;
  margin: 0 auto;
}

.back-button {
  margin-bottom: 20px;
}

.activity-detail-container {
  margin-bottom: 20px;
}

.activity-image-col,
.activity-info-col {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
}

.activity-image {
  width: 100%;
  height: auto;
  border-radius: 10px;
}

.activity-info {
  padding: 20px;
  background: #fff;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  border-radius: 10px;
  width: 400px; /* 设置固定宽度 */  
  height: 450px; /* 设置固定高度（如果需要的话）*/
}

.activity-info h2 {
  margin-bottom: 10px;
  text-align: center;
}

.activity-info p {
  margin-bottom: 10px;
  line-height: 1.6;
}
</style>
