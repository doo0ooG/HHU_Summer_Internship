//定制请求的实例

//导入axios  npm install axios
import axios from 'axios';
//定义一个变量,记录公共的前缀  ,  baseURL
const baseURL = '/api';
const instance = axios.create({baseURL})

// 导入消息提示框
import { ElMessage } from 'element-plus';
import { useTokenStore } from '@/stores/token';
// 请求拦截器
instance.interceptors.request.use(
    (config) => {
        // 请求前的回调函数
        // 添加 token
        const tokenStore = useTokenStore();
        if (tokenStore.token) {
            config.headers.Authorization = tokenStore.token;
        }
        return config;
    }, (err) => {
        // 请求错误的回调函数
        Promise.reject(err);
    }
)

// 导入usesRounter
// import { useRouter } from 'vue-router';
// const router = useRouter();
import router from "@/router"

//添加响应拦截器
instance.interceptors.response.use(
    result => {
        if (result.data.code === 0) {
            return result.data;
        } 
        // 操作失败
        ElMessage.error(result.data.message ? result.data.message : "操作失败")
        // alert(result.data.message ? result.data.message : "操作失败")
        // 异步操作状态转为失败
        return Promise.reject(result.data)
    },
    err=>{
        // 判断相应状态码，如果为401，证明未登录
        if (err.response.status === 401) {
            ElMessage.error("还未登录，请登录")
            router.push('/login');
        } else {
            ElMessage.error("服务异常")
        }
        return Promise.reject(err);//异步的状态转化成失败的状态
    }
)

export default instance;