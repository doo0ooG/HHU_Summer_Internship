import request from "@/utils/request.js"

export const userRegister = (registerData) => {
    return request.post("/user/register", registerData);
}

export const userLogin = (registerData) => {
    return request.post("/user/login", registerData);
}

export const userInfo = () => {
    return request.get("/user/userinfo");
}
export const userList = () => {
    return request.get("/user/findAll");
}

export const userAdd = (userDataModel) => {
    // console.log(userDataModel);
    return request.post("/user/add", userDataModel);
}


export const userEdit = (userDataModel) => {
    // console.log(userDataModel);
    return request.post("/user/modifyBaseInfo", userDataModel);
}



export const userAvatar = (photoPath) => {
    // console.log(photoPath);
    return request.post('/user/updateAvatar', photoPath);
}

export const userResetPassword = (userResetPasswordDataModel) => {
    // console.log(photoPath);
    return request.post('/user/resetPassword', userResetPasswordDataModel);
}

// 根据id获取详细
export const getUserDetail = (userId) => {
    return request.get(`/user/detail?userId=${userId}`);
}

