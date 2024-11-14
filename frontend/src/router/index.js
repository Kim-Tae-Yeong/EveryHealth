import { createRouter, createWebHistory } from 'vue-router';  // Vue 3에서 사용
import SaveForm from "@/components/SaveForm.vue"; // SaveForm 컴포넌트 import

const routes = [
  {
    path: "/user/save",       // 회원가입 폼 경로
    name: "SaveForm",         // 라우터 이름
    component: SaveForm      // 사용될 컴포넌트
  }
];

// VueRouter 인스턴스를 생성
const router = createRouter({
  history: createWebHistory(),  // history 모드 사용
  routes
});

export default router;
