import { defineStore } from "pinia";
import { ref } from "vue";

export const useTokenStore = defineStore('token', () => {
    // 响应式变量
    const token = ref('');

    // 修改token函数
    const setToken = (newToken) => {
        token.value = newToken;
    }

    // 溢出token函数
    const removeToken = () => {
        token.value = '';
    }

    // 在发起请求前，或者登录之后， 或者注销使用
    return {
        token, setToken, removeToken
    }
}, {
    persist: true
});