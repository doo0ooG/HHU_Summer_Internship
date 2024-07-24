import request from "@/utils/request.js"
// import { useTokenStore } from "@/stores/token";
export const enrollList = () => {
    // const tokenStore = useTokenStore();
    // 在请求头中携带token
    // console.log(tokenStore.token)
    // return request.get("/enroll/list", {headers: {'Authorization': tokenStore.token}});

    // 添加了请求拦截器，所以不需要另外配置请求头中的Authorization
    return request.get("/enroll/list");
}

export const enrollAdd = (enrollDataModel) => {
    console.log(enrollDataModel);
    return request.post("/enroll/add", enrollDataModel);
}

// 未完成
export const enrollEdit = (enrollDataModel) => {
    console.log(enrollDataModel);
    return request.post("/enroll/modify", enrollDataModel);
}