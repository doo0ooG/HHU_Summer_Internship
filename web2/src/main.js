import './assets/main.scss'

import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import router from "@/router/index.js"
import { createPinia } from 'pinia'
import { createPersistedState } from 'pinia-persistedstate-plugin'
import UndrawUi from 'undraw-ui'
import 'undraw-ui/dist/style.css'
import locale from 'element-plus/dist/locale/zh-cn.js'


import App from './App.vue'

// createApp(App).mount('#app')
const app = createApp(App);
const pinia = createPinia();
const persist = createPersistedState();

pinia.use(persist);

app.use(router);
app.use(ElementPlus,{locale});
app.use(pinia);
app.use(UndrawUi);
app.mount("#app");
