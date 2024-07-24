<template>
  <div class="hello">
    <el-main :style="mainStyle">
      <el-row type="flex" class="row-bg system-intro-container">
        <!-- Left Section: System Introduction -->
        <el-col :span="1"></el-col>
        <el-col :span="10">
          <el-card>
            <div class="system-intro">
          <h2 style="font-size: 50px;">学生社团活动报名管理系统</h2>
          <h2 style="font-size: 30px;">Club Activity Registration Management System</h2>
          <p class="intro-text">欢迎使用社团报名管理系统。我们的系统提供最新的社团活动信息和便捷的报名流程，帮助您快速参与感兴趣的活动。</p>
          <p class="intro-text">系统功能包括：</p>
          <ul>
            <li class="intro-text">活动推荐：基于您的兴趣和偏好，推荐您可能喜欢的活动，确保您不会错过任何有趣的机会。</li>
            <li class="intro-text">社团推荐：根据您的个人资料和兴趣，为您推荐适合的社团，帮助您找到志同道合的朋友。</li>
            <li class="intro-text">便捷报名流程：简化的报名流程，使您能够快速注册参加各种活动和社团。</li>
            <li class="intro-text">活动历史记录：记录您参与过的所有活动，方便您回顾和分享您的经历。</li>
          </ul>
          <p class="intro-text">即将上线功能：</p>
          <ul>
            <li class="intro-text">即时通知：活动报名和更新将通过短信或电子邮件实时通知您。</li>
            <li class="intro-text">社交互动：通过我们的平台，您可以与其他成员互动、分享经验，并建立新的友谊。</li>
            <li class="intro-text">个性化定制：根据您的反馈和建议，个性化调整推荐内容和系统功能，提升用户体验。</li>
            <li class="intro-text">多语言支持：支持多种语言，满足不同用户的需求。</li>
          </ul>
        </div>
          </el-card>
        </el-col>
        <!-- 右侧轮播图 -->
        <el-col :span="2"></el-col>
        <el-col :span="10">
          <el-carousel :interval="4000" type="flat" height="600px">
            <el-carousel-item v-for="image in carouselImages" :key="image">
              <img :src="image" class="carousel-image" alt="carousel image">
            </el-carousel-item>
          </el-carousel>
        </el-col>
      </el-row>
      <!-- 中间活动部分 -->
      <div class="activity_section">
        <el-row type="flex" justify="center"  align="middle">
          <h1 class="title-large">活动推荐</h1>
          <!-- 活动类型筛选 -->
          <el-select
            v-model="selectedType"
            placeholder="请选择活动类型"
            @change="filterActivities"
            size="20px"
            style="margin-left: 20px; width: 200px;">
            <el-option
              v-for="type in activityTypes"
              :key="type"
              :label="type"
              :value="type">
            </el-option>
          </el-select>
        </el-row>


        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="5" v-for="(activity, index) in filteredActivities.slice(0,8)" :key="activity.id">
            <el-card :body-style="{ padding: '0px' }">
              <img :src="activity.image" class="image">
              <div style="padding: 14px;">
                <span>{{ activity.name }}</span>
                <div class="details">
                  <time class="time">{{ formatDate(activity.startDate) }}-{{ formatDate(activity.endDate) }}</time>
                </div>
                <div class="details">
                  <p>地点: {{ activity.pos }} 人数: {{ activity.limitNum }}</p>
                </div>
                <div class="bottom clearfix">
                  <el-button type="text" class="button" @click="goTOActivity(activity.id)">报名</el-button>
                </div>
              </div>
            </el-card>
          </el-col>
        </el-row>


      </div>
      


      <div class="club_section">
        <h1 class="title-large">社团推荐</h1>
        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="5" v-for="(club, index) in club.slice(0,8)" :key="club.id">
            <el-card :body-style="{ padding: '0px' }">
              <img :src="club.image" class="image">
              <div style="padding: 14px;">
                <span>{{ club.name }}</span>
                
                <div class="details">
                  <span>社团简介：{{ club.info }}</span>
                </div>
                <div class="bottom clearfix">
                  <el-button type="text" class="button" @click="toApply(club.id)">报名</el-button>
                </div>
              </div>
            </el-card>
          </el-col>
        </el-row>
      </div>

    

      
    </el-main>
  </div>
</template>



<script setup>
import {useRouter} from "vue-router"
const router = useRouter();
import { ref, onMounted } from 'vue'
import axios from 'axios'
import { ElCarousel, ElCarouselItem, ElRow, ElCol, ElCard, ElButton, ElHeader, ElMain, ElSelect, ElOption } from 'element-plus'

// 动态导入图片
const carouselImages = ref([])
const importImages = async () => {
const images = await Promise.all([
  import('@/assets/HomeImages/社团1.jpg'),
  import('@/assets/HomeImages/社团2.jpg'),
  import('@/assets/HomeImages/社团3.jpg')
])
carouselImages.value = images.map(image => image.default)
}

const activities = ref([])
const club = ref([])
const filteredActivities = ref([])
const selectedType = ref('')
const activityTypes = ref([]) // Assuming you get activity types from the backend



const getList = async () => {
  try {
    const res = await axios.get('http://127.0.0.1:8080/activity/list')
    activities.value = res.data.data
    filteredActivities.value = activities.value // 默认显示所有活动
    activityTypes.value = [...new Set(activities.value.map(activity => activity.cate))] // 获取活动类型列表
  } catch (error) {
    console.error(error)
  }
}



const goTOActivity = (activityId) => {
    router.push({ path: '/activity', query: { activityId } });
  };

//获取社团内容
const getList2 = async () => {
try {
  const res = await axios.get('http://127.0.0.1:8080/club/list')
  club.value = res.data.data
  //filteredActivities.value = club.value // 默认显示所有活动
  //activityTypes.value = [...new Set(activities.value.map(activity => activity.cate))] // 获取活动类型列表
} catch (error) {
  console.error(error)
}
}



const filterActivities = () => {
if (selectedType.value) {  
  filteredActivities.value = activities.value.filter(activity => activity.cate === selectedType.value)  //筛选不同类型的数据
} else {
  filteredActivities.value = activities.value
}
}

const toApply = (id) => {
console.log('Applying for activity', id)
}


const formatDate = (datetime) => {
const date = new Date(datetime)
const year = date.getFullYear()
const month = String(date.getMonth() + 1).padStart(2, '0')
const day = String(date.getDate()).padStart(2, '0')
const hours = String(date.getHours()).padStart(2, '0')
return `${year}-${month}-${day} ${hours}:00`
}




onMounted(() => {
importImages()
getList()
getList2()
})
</script>




<style scoped>
.hello {
text-align: center;
}
.system-intro-container {
padding: 40px 20px;
background-color: #f5f5f5;
border-radius: 8px;
box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
}

.title-large {  
font-size: 36px; /* 或者你想要的任何大小 */  
} 

.system-intro {
text-align: left;
}
.carousel-image {
width: 100%;
height: 100%;
}
.image {
width: 100%;
}
.details {
margin: 10px 0;
}
.time {
display: block;
margin-bottom: 10px;
}
.button {
float: right;
}
.activity_section,
.club_section,
.activity_content {
margin-top: 20px;
}
</style>



