import { createApp } from 'vue';  // Vue 3에서 사용
import App from './App.vue';
import router from './router';
import axios from 'axios';
import VCalendar from 'v-calendar';
import 'v-calendar/style.css';

const app = createApp(App);  // Vue 3에서는 createApp을 사용
app.use(router); // router 등록
app.use(VCalendar, {});
app.config.globalProperties.$axios = axios;  // axios를 전역에서 사용할 수 있도록 설정
app.mount('#app');
