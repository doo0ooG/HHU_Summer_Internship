import { createRouter, createWebHistory } from 'vue-router'

// 导入组件
import LoginView from '@/views/LoginView.vue'
import ManageLayoutView from '@/views/ManageLayoutView.vue'
import MainView from "@/views/MainView.vue"
import ActivityManageView from '@/views/manage/ActivityManageView.vue'
import ClubManageView from '@/views/manage/ClubManageView.vue'
import UserManageView from '@/views/manage/UserManageView.vue'
import RemarkManageView from '@/views/manage/RemarkManageView.vue'
import EnrollManageView from '@/views/manage/EnrollManageView.vue'
import MainActivityShowView from '@/views/mainPage/MainActivityShowView.vue'
import UserBaseInfoView from '@/views/mainPage/UserBaseInfoView.vue'
import UserResetAvaterView from '@/views/mainPage/UserResetAvaterView.vue'
import UserResetPasswordView from '@/views/mainPage/UserResetPasswordView.vue'
import ActivityDetailView from "@/views/ActivityDetailView.vue"
import UserLayoutView from '@/views/UserLayoutView.vue'
import MyActivityView from '@/views/manage/MyActivityView.vue'
import MyRemarkView from '@/views/manage/MyRemarkView.vue' 
import statistic from '@/views/statistic.vue'

// 定义路由关系
const routes = [
    {path: '/', redirect: '/main'},
    {path: '/login', component: LoginView},
    {path: '/manage', component: ManageLayoutView, redirect: '/manage/club',children: [
        {path: 'club', component: ClubManageView},
        {path: 'activity', component: ActivityManageView},
        {path: 'user', component: UserManageView},
        {path: 'remark', component: RemarkManageView},
        {path: 'enroll', component: EnrollManageView},
        {path: 'statistic', component: statistic}
    ]},
    {path: '/activity', component: ActivityDetailView},
    {path: '/main', component: MainView, redirect: '/main/activity', children: [
        {path: 'mymainpage', component: UserLayoutView,children: [
            {path: 'myactivity', component: MyActivityView},
            {path: 'myremark', component: MyRemarkView}]
        },
        {path: 'activity', component: MainActivityShowView},
        {path: 'info', component: UserBaseInfoView},
        {path: 'avatar', component: UserResetAvaterView},
        {path: 'resetpd', component: UserResetPasswordView}
    ]},
]

// 创建617
const router = createRouter({
    history: createWebHistory(),
    routes:routes
})

// 导出路由
export default router;