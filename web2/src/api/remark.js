import request from "@/utils/request.js"
// import { useTokenStore } from "@/stores/token";
export const remarkList = () => {
    // const tokenStore = useTokenStore();
    // 在请求头中携带token
    // console.log(tokenStore.token)
    // return request.get("/remark/list", {headers: {'Authorization': tokenStore.token}});

    // 添加了请求拦截器，所以不需要另外配置请求头中的Authorization
    return request.get("/remark/list");
}

export const remarkAdd = (remarkDataModel) => {
    console.log(remarkDataModel);
    return request.post("/remark/add", remarkDataModel);
}

// 未完成
export const remarkEdit = (remarkDataModel) => {
    console.log(remarkDataModel);
    return request.post("/remark/modify", remarkDataModel);
}