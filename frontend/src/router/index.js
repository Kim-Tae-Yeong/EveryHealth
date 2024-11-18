import { createRouter, createWebHistory } from 'vue-router';  // Vue 3에서 사용
import HomeForm from "@/components/HomeForm.vue";
import SaveForm from "@/components/SaveForm.vue"; // SaveForm 컴포넌트 import
import LoginForm from "@/components/LoginForm.vue"; // LoginForm 컴포넌트 import
import MyPageForm from "@/components/MyPageForm.vue";
import BoardForm from "@/components/BoardForm.vue";

const routes = [
  {
    path: "/",
    name: "HomeForm",
    component: HomeForm
  },
  {
    path: "/user/save",       // 회원가입 폼 경로
    name: "SaveForm",         // 라우터 이름
    component: SaveForm      // 사용될 컴포넌트
  },
  {
    path: "/user/login",
    name: "LoginForm",
    component: LoginForm
  },
  {
    path: "/myPage",
    name: "MyPageForm",
    component: MyPageForm
  },
  {
    path: "/board",
    name: "BoardForm",
    component: BoardForm
  }
];

// VueRouter 인스턴스를 생성
const router = createRouter({
  history: createWebHistory(),  // history 모드 사용
  routes
});

export default router;
