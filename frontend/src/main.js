import { createApp } from 'vue';  // Vue 3에서 사용
import App from './App.vue';
import router from './router';

const app = createApp(App);  // Vue 3에서는 createApp을 사용
app.use(router);  // router 등록
app.mount('#app');
