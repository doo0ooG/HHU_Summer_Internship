import request from "@/utils/request.js"
// import { useTokenStore } from "@/stores/token";
export const myactivityList = () => {
    // const tokenStore = useTokenStore();
    // 在请求头中携带token
    // console.log(tokenStore.token)
    // return request.get("/remark/list", {headers: {'Authorization': tokenStore.token}});
    // 添加了请求拦截器，所以不需要另外配置请求头中的Authorization
    return request.get("/myactivity/list");
}

// 未完成
export const myactivityDelete = (myactivityDataModel) => {
    console.log(myactivityDataModel);
    return request.post("/myactivity/delete", myactivityDataModel);
}

