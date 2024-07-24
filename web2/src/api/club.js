import request from "@/utils/request.js"
// import { useTokenStore } from "@/stores/token";
export const clubList = () => {
    // const tokenStore = useTokenStore();
    // 在请求头中携带token
    // console.log(tokenStore.token)
    // return request.get("/club/list", {headers: {'Authorization': tokenStore.token}});
    
    // 添加了请求拦截器，所以不需要另外配置请求头中的Authorization
    return request.get("/club/list");
}

export const clubPageList = (pageNum, pageSize) => {
    // alert("假装实现");
    const params = {
        pageNum: pageNum,
        pageSize: pageSize
    };
    return request.get("/club/pageList", {params});
}

export const clubAdd = (clubDataModel) => {
    console.log(clubDataModel);
    return request.post("/club/add", clubDataModel);
}

// 未完成
export const clubEdit = (clubDataModel) => {
    console.log(clubDataModel);
    return request.post("/club/modify", clubDataModel);
}

// 根据id获取详细
export const getClubDetail = (clubId) => {
    return request.get(`/club/detail?clubId=${clubId}`);
}

// 根据id删除社团
export const clubDel = (id) => {
    return request.post("/club/delete", id);
}
