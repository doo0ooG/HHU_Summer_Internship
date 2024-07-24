<template>
    <div class="hello">
      <el-main>
        <el-row type="flex" class="row-bg system-intro-container">
          <!-- Left Section: System Introduction -->
          <el-col :span="2"></el-col>
          <el-col :span="10">
            <div class="system-intro">
              <h2>社团报名管理系统</h2>
              <p>欢迎使用社团报名管理系统。我们的系统提供最新的社团活动信息和便捷的报名流程，帮助您快速参与感兴趣的活动。</p>
              <p>系统功能包括：</p>
              <ul>
                <li>活动推荐</li>
                <li>社团推荐</li>
                <li>便捷报名流程</li>
                <li>活动历史记录</li>
              </ul>
            </div>
          </el-col>
          <!-- 右侧轮播图 -->
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
            <el-col :span="5" v-for="(activity, index) in filteredActivities" :key="activity.id">
              <el-card :body-style="{ padding: '0px' }">
                <img :src="activity.image" class="image">
                <div style="padding: 14px;">
                  <span>{{ activity.name }}</span>
                  <div class="details">
                    <time class="time">{{ activity.startDate }}-{{ activity.endDate }}</time>
                  </div>
                  <div class="details">
                    <p>地点: {{ activity.pos }}  人数: {{ activity.limitNum }}</p>
                  </div>
                  <div class="bottom clearfix">
                    <el-button type="text" class="button" @click="toApply(activity.id)">报名</el-button>
                  </div>
                </div>
              </el-card>
            </el-col>
          </el-row>
        </div>
        


        <div class="club_section">
          <h1 class="title-large">社团推荐</h1>
          <el-row type="flex" class="row-bg" justify="space-around">
            <el-col :span="5" v-for="(club, index) in club" :key="club.id">
              <el-card :body-style="{ padding: '0px' }">
                <img :src="club.image" class="image">
                <div style="padding: 14px;">
                  <span>{{ club.name }}</span>
                  <div class="details">
                    <time class="time">{{ club.date }}</time>
                    <p>人数限制: {{ club.limit }}</p>
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
      const res = await axios.get('http://192.168.10.166:8080/activity/list')
      activities.value = res.data.data
      filteredActivities.value = activities.value // 默认显示所有活动
      activityTypes.value = [...new Set(activities.value.map(activity => activity.cate))] // 获取活动类型列表
    } catch (error) {
      console.error(error)
    }
  }
 
  //获取社团内容
  const getList2 = async () => {
    try {
      const res = await axios.get('http://192.168.10.166:8080/club/list')
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
  margin: 20px 0;
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



