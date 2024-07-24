import { defineStore } from 'pinia';
import { ref } from 'vue';


export const useUserInfoStore = defineStore("userinfo", () => {
    const info = ref({});

    const setInfo = (newInfo) => {
        info.value = newInfo;
        // console.log(1123);
        // console.log(info.value);
    }

    const removeInfo = () => {
        // console.log(123);
        info.value = {};
    }

    return {info, setInfo, removeInfo}
}, {
    persist: true
})