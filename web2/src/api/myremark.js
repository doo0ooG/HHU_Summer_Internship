import request from "@/utils/request.js"
// import { useTokenStore } from "@/stores/token";
export const myremarkList = () => {
    // const tokenStore = useTokenStore();
    // 在请求头中携带token
    // console.log(tokenStore.token)
    // return request.get("/remark/list", {headers: {'Authorization': tokenStore.token}});

    // 添加了请求拦截器，所以不需要另外配置请求头中的Authorization
    return request.get("/myremark/list");
}


export const myremarkEdit = (myremarkDataModel) => {
    console.log(myremarkDataModel);
    return request.post("/myremark/modify", myremarkDataModel);
}

export const myremarkDelete = (myremarkDataModel) => {
    console.log(myremarkDataModel);
    return request.post("/myremark/delete", myremarkDataModel);
}

// 未完成
