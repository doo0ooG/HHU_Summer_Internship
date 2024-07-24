<template>
    <div class="statistics-page">
      <h1 style="font-size: 30px;">网站数据汇总</h1>
  
      <div class="stat-section">
        <h2>（1）社团信息</h2>
        <div id="club-total" class="chart"></div>
        <div id="club-activities" class="chart"></div>
      </div>
  
      <div class="stat-section">
        <h2>（2）活动信息</h2>
        <div class="activity-charts">
          <div id="activity-total" class="chart"></div>
          <div id="activity-ended" class="chart"></div>
          <div id="activity-ongoing" class="chart"></div>
        </div>
        <div id="activity-types" class="chart"></div>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref, onMounted } from 'vue'
  import axios from 'axios'
  import * as echarts from 'echarts'


  // 数据引用
  const clubData = ref([])
  const activityData = ref([])
  
  // 获取数据的函数
  const fetchClubData = async () => {
    try {
      const response = await axios.get('http://192.168.10.166:8080/club/list')
      clubData.value = response.data.data
    } catch (error) {
      console.error('Failed to fetch club data:', error)
    }
  }
  
  const fetchActivityData = async () => {
    try {
      const response = await axios.get('http://192.168.10.166:8080/activity/list')
      activityData.value = response.data.data
    } catch (error) {
      console.error('Failed to fetch activity data:', error)
    }
  }
  
  // 渲染图表的函数
  const renderCharts = () => {
    // 社团总数量
    const clubTotalChart = echarts.init(document.getElementById('club-total'))
    clubTotalChart.setOption({
      title: { text: '社团总数量', subtext: clubData.value.length.toString(), left: 'center', top: 'middle' },
      tooltip: {},
      series: [{
        name: '社团数量',
        type: 'pie',
        label: {
          show: true,
          formatter: '{b}: {c}',
          fontSize: 30,
          color: '#000'
        },
        itemStyle: {
          color: '#67C23A'
        },
        data: [{ value: clubData.value.length, name: '社团' }]
      }]
    })
  
    // 不同社团的活动举办数量
    const clubActivitiesData = clubData.value.map(club => ({
      name: club.name,
      value: activityData.value.filter(activity => activity.clubName === club.name).length
    }))
    const clubActivitiesChart = echarts.init(document.getElementById('club-activities'))
    clubActivitiesChart.setOption({
      title: { text: '社团活动数量', left: 'center' },
      tooltip: {},
      xAxis: { type: 'category', data: clubActivitiesData.map(item => item.name) },
      yAxis: { type: 'value' },
      series: [{
        name: '活动数量',
        type: 'bar',
        label: {
          show: true,
          position: 'top',
          fontSize: 16,
          color: '#409EFF'
        },
        itemStyle: {
          color: '#409EFF'
        },
        data: clubActivitiesData.map(item => item.value)
      }]
    })
  
    // 活动总数量
    const activityTotalChart = echarts.init(document.getElementById('activity-total'))
    activityTotalChart.setOption({
      title: { text: '活动总数量', subtext: activityData.value.length.toString(), left: 'center', top: 'middle' },
      tooltip: {},
      series: [{
        name: '活动数量',
        type: 'pie',
        label: {
          show: true,
          formatter: '{b}: {c}',
          fontSize: 20,
          color: '#000'
        },
        itemStyle: {
          color: '#E6A23C'
        },
        data: [{ value: activityData.value.length, name: '活动总数量' }]
      }]
    })
  
    // 结束的活动数量
    const endedActivities = activityData.value.filter(activity => new Date(activity.endDate) < new Date())
    const activityEndedChart = echarts.init(document.getElementById('activity-ended'))
    activityEndedChart.setOption({
      title: { text: '已结束活动数量', subtext: endedActivities.length.toString(), left: 'center', top: 'middle' },
      tooltip: {},
      series: [{
        name: '已结束活动',
        type: 'pie',
        label: {
          show: true,
          formatter: '{b}: {c}',
          fontSize: 20,
          color: '#000'
        },
        itemStyle: {
          color: '#F56C6C'
        },
        data: [{ value: endedActivities.length, name: '已结束活动' }]
      }]
    })
  
    // 正在进行的活动数量
    const ongoingActivities = activityData.value.filter(activity => new Date(activity.startDate) <= new Date() && new Date(activity.endDate) >= new Date())
    const activityOngoingChart = echarts.init(document.getElementById('activity-ongoing'))
    activityOngoingChart.setOption({
      title: { text: '正在进行活动数量', subtext: ongoingActivities.length.toString(), left: 'center', top: 'middle' },
      tooltip: {},
      series: [{
        name: '正在进行活动',
        type: 'pie',
        label: {
          show: true,
          formatter: '{b}: {c}',
          fontSize: 20,
          color: '#000'
        },
        itemStyle: {
          color: '#67C23A'
        },
        data: [{ value: ongoingActivities.length, name: '正在进行活动' }]
      }]
    })
  
    // 不同类型的活动数量
    const activityTypes = [...new Set(activityData.value.map(activity => activity.cate))]
    const activityTypesData = activityTypes.map(type => ({
      name: type,
      value: activityData.value.filter(activity => activity.cate === type).length
    }))
    const activityTypesChart = echarts.init(document.getElementById('activity-types'))
    activityTypesChart.setOption({
      title: { text: '不同类型活动数量', left: 'center' },
      tooltip: {},
      xAxis: { type: 'category', data: activityTypesData.map(item => item.name) },
      yAxis: { type: 'value' },
      series: [{
        name: '活动数量',
        type: 'bar',
        label: {
          show: true,
          position: 'top',
          fontSize: 16,
          color: '#409EFF'
        },
        itemStyle: {
          color: '#409EFF'
        },
        data: activityTypesData.map(item => item.value)
      }]
    })
  }
  
  onMounted(async () => {
    await fetchClubData()
    await fetchActivityData()
    renderCharts()
  })
  </script>
  
  <style scoped>
  .statistics-page {
    padding: 20px;
    max-width: 1200px;
    margin: 0 auto;
  }
  
  .stat-section {
    margin-bottom: 40px;
  }
  
  .chart {
    margin-top: 20px;
    width: 100%;
    height: 400px;
  }
  
  .activity-charts {
    display: flex;
    justify-content: space-between;
  }
  
  #club-total, #club-activities, #activity-total, #activity-ended, #activity-ongoing, #activity-types {
    height: 300px;
  }
  
  .activity-charts .chart {
    flex: 1;
    margin: 0 10px;
  }
  </style>
  