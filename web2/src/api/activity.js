import request from "@/utils/request.js"
// import { useTokenStore } from "@/stores/token";
export const activityList = () => {
    // const tokenStore = useTokenStore();
    // 在请求头中携带token
    // console.log(tokenStore.token)
    // return request.get("/activity/list", {headers: {'Authorization': tokenStore.token}});
    
    // 添加了请求拦截器，所以不需要另外配置请求头中的Authorization
    return request.get("/activity/list");
}

export const activityAdd = (activityDataModel) => {
    console.log(activityDataModel);
    return request.post("/activity/add", activityDataModel);
}

// 未完成
export const activityEdit = (activityDataModel) => {
    console.log(activityDataModel);
    return request.post("/activity/modify", activityDataModel);
}

// 根据id获取详细
export const getDetail = (activityId) => {
    return request.get(`/activity/detail?activityId=${activityId}`);
}


// 报名活动
export const enrollActivity = (activityId) => {
    return request.get(`/activity/enroll?activityId=${activityId}`);
}

// 取消报名
export const withdrawActivity = (activityId) => {
    return request.get(`/activity/withdraw?activityId=${activityId}`);
}

// 获取活动id对应的评论数据列表
export const CommentById = (activityId) => {
    return request.get(`/activity/comment?activityId=${activityId}`);
}

// 发表评论
export const submitCommentService = (dataModel) => {
    return request.post("/activity/comment", dataModel);
}

// 查询所有评论
export const commentList = () => {
    return request.get('/activity/commentList');
}

// 查询所有报名
export const enrollList = () => {
    return request.get('/activity/enrollList');
}