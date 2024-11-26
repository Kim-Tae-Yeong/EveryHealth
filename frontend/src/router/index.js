import { createRouter, createWebHistory } from 'vue-router';  // Vue 3에서 사용
import MainPageForm from "@/components/MainPageForm.vue";
import SaveForm from "@/components/SaveForm.vue"; // SaveForm 컴포넌트 import
import LoginForm from "@/components/LoginForm.vue"; // LoginForm 컴포넌트 import
import MyPageBodyForm from "@/components/MyPageBodyForm.vue";
import MyPageDietForm from "@/components/MyPageDietForm.vue";
import MyPageExerciseForm from "@/components/MyPageExerciseForm.vue";
import CommunityForm from "@/components/CommunityForm.vue";
import FindEmailByPhoneNumberForm from "@/components/FindEmailByPhoneNumberForm.vue";
import ChangePasswordForm from "@/components/ChangePasswordForm.vue";
import ProgramReferenceForm from "@/components/ProgramReferenceForm.vue";
import CommunitySaveForm from "@/components/CommunitySaveForm.vue";
import CommunityDetailForm from "@/components/CommunityDetailForm.vue";

const routes = [
  {
    path: "/",
    name: "MainPageForm",
    component: MainPageForm
  },
  {
    path: "/users/save",       // 회원가입 폼 경로
    name: "SaveForm",         // 라우터 이름
    component: SaveForm      // 사용될 컴포넌트
  },
  {
    path: "/users/login",
    name: "LoginForm",
    component: LoginForm
  },
  {
    path: "/myPageBody/:user_id/:date",
    name: "MyPageBodyForm",
    component: MyPageBodyForm
  },
  {
    path: "/myPageDiet/:user_id/:date",
    name: "MyPageDietForm",
    component: MyPageDietForm
  },
  {
    path: "/myPageExercise/:user_id/:date",
    name: "MyPageExerciseForm",
    component: MyPageExerciseForm
  },
  {
    path: "/community",
    name: "CommunityForm",
    component: CommunityForm
  },
  {
    path: "/community/save",
    name: "CommunitySaveForm",
    component: CommunitySaveForm
  },
  {
    path: "/community/:boardId",
    name: "CommunityDetailForm",
    component: CommunityDetailForm
  },
  {
    path: "/users/emails/search",
    name: "FindEmailByPhoneNumberForm",
    component: FindEmailByPhoneNumberForm
  },
  {
    path: "/users/password",
    name: "ChangePasswordForm",
    component: ChangePasswordForm
  },
  {
    path: "/program-reference",
    name: "ProgramReference",
    component: ProgramReferenceForm
  }
];

// VueRouter 인스턴스를 생성
const router = createRouter({
  history: createWebHistory(),  // history 모드 사용
  routes
});

export default router;
