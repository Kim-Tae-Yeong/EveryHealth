import { createRouter, createWebHistory } from 'vue-router';
import MainPageForm from "@/components/MainPageForm.vue";
import SaveForm from "@/components/SaveForm.vue";
import LoginForm from "@/components/LoginForm.vue";
import MyPageBodyForm from "@/components/MyPageBodyForm.vue";
import MyPageDietForm from "@/components/MyPageDietForm.vue";
import MyPageExerciseForm from "@/components/MyPageExerciseForm.vue";
import CommunityForm from "@/components/CommunityForm.vue";
import FindEmailByPhoneNumberForm from "@/components/FindEmailByPhoneNumberForm.vue";
import ChangePasswordForm from "@/components/ChangePasswordForm.vue";
import ProgramReferenceForm from "@/components/ProgramReferenceForm.vue";
import CommunitySaveForm from "@/components/CommunitySaveForm.vue";
import CommunityDetailForm from "@/components/CommunityDetailForm.vue";
import CommunityEditForm from "@/components/CommunityEditForm.vue";
import CommunityMyBoardsForm from "@/components/CommunityMyBoardsForm.vue";
import axios from 'axios';

const routes = [
  {
    path: "/",
    name: "MainPageForm",
    component: MainPageForm
  },
  {
    path: "/users/save",
    name: "SaveForm",
    component: SaveForm
  },
  {
    path: "/users/login",
    name: "LoginForm",
    component: LoginForm
  },
  {
    path: "/myPageBody/:user_id/:date",
    name: "MyPageBodyForm",
    component: MyPageBodyForm,
    meta: { requiresAuth: true }
  },
  {
    path: "/myPageDiet/:user_id/:date",
    name: "MyPageDietForm",
    component: MyPageDietForm,
    meta: { requiresAuth: true }
  },
  {
    path: "/myPageExercise/:user_id/:date",
    name: "MyPageExerciseForm",
    component: MyPageExerciseForm,
    meta: { requiresAuth: true }
  },
  {
    path: "/community",
    name: "CommunityForm",
    component: CommunityForm,
    meta: { requiresAuth: true }
  },
  {
    path: "/community/save",
    name: "CommunitySaveForm",
    component: CommunitySaveForm,
    meta: { requiresAuth: true }
  },
  {
    path: "/community/:boardId",
    name: "CommunityDetailForm",
    component: CommunityDetailForm,
    meta: { requiresAuth: true }
  },
  {
    path: "/community/edit/:boardId",
    name: "CommunityEditForm",
    component: CommunityEditForm,
    meta: { requiresAuth: true }
  },
  {
    path: "/community/myBoards",
    name: "CommunityMyBoardsForm",
    component: CommunityMyBoardsForm,
    meta: { requiresAuth: true }
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
    component: ProgramReferenceForm,
    meta: { requiresAuth: true }
  }
];

// VueRouter 인스턴스를 생성
const router = createRouter({
  history: createWebHistory(),
  routes
});

// 전역 네비게이션 가드 추가
router.beforeEach((to, from, next) => {
  const accessToken = localStorage.getItem('accessToken');

  // 인증이 필요한 페이지로 이동하려는 경우
  if (to.matched.some(record => record.meta.requiresAuth)) {
    if (!accessToken) {
      // 토큰이 없으면 로그인 페이지로 리다이렉트
      alert('로그인이 필요합니다.');
      next('/users/login');
    } else {
      // 토큰이 있으면 토큰 유효성 검사
      checkAccessTokenValidity().then(isValid => {
        if (isValid) {
          next(); // 유효한 토큰이면 페이지 이동 허용
        } else {
          // 토큰이 유효하지 않으면 refreshToken으로 새 accessToken 발급
          refreshAccessToken().then(newAccessToken => {
            if (newAccessToken) {
              // 새 accessToken을 localStorage에 저장하고 페이지 이동 허용
              localStorage.setItem('accessToken', newAccessToken);
              next(); // 새 토큰으로 페이지 이동
            } else {
              // 새 토큰 발급이 실패하면 로그아웃 처리
              alert('토큰이 만료되었습니다. 다시 로그인해주세요.');
              localStorage.removeItem('accessToken');
              next('/users/login');
            }
          }).catch(() => {
            alert('토큰 갱신에 실패했습니다. 다시 로그인해주세요.');
            next('/users/login');
          });
        }
      }).catch(() => {
        alert('토큰 검증에 실패했습니다.');
        next('/users/login');
      });
    }
  } else {
    next(); // 인증이 필요 없는 페이지는 그대로 이동
  }
});

// 토큰 유효성 검사 함수
async function checkAccessTokenValidity() {
  const accessToken = localStorage.getItem('accessToken');

  if (!accessToken) return false;

  try {
    const response = await axios.post('/token/validate', {}, {
      headers: {
        Authorization: `Bearer ${accessToken}`
      }
    });
    return response.data.valid;
  } catch (error) {
    if(error.response && error.response.data.message === "Token is expired") {
        console.error('토큰이 만료되었습니다. 다시 로그인해주세요');
    } else {
        console.error('토큰 유효성 검사 실패:', error);
    }
    return false;
  }
}

async function refreshAccessToken() {
  try {
    // 서버에서 자동으로 쿠키를 읽을 수 있도록 withCredentials 설정
    const response = await axios.post('/token/refresh', {
      withCredentials: true // 쿠키를 포함한 요청
    });

    const newAccessToken = response.data.accessToken;
    return newAccessToken;
  } catch (error) {
    console.error('새로운 accessToken 발급 실패:', error);
    return null;
  }
}


export default router;