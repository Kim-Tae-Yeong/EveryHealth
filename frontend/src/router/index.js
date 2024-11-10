import { createRouter, createWebHistory } from 'vue-router';  // Vue 3에서 사용
import HomeView from '../components/HomeView.vue';  // 경로에 맞는 컴포넌트 파일을 import

const routes = [
  {
    path: '/',
    name: 'HomeView',
    component: HomeView
  }
];

// VueRouter 인스턴스를 생성
const router = createRouter({
  history: createWebHistory(),  // history 모드 사용
  routes
});

export default router;
