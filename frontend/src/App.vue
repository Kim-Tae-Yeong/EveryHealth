<template>
  <div id="app">
    <!-- 중앙에 로고 글씨 추가 -->
    <div class="header">
      <div class="logo">
        <router-link to="/" class="logo-text">EveryHealth</router-link>
      </div>
    </div>

    <!-- 로그인 상태에 따른 버튼 표시 -->
    <div class="auth-buttons">
      <div v-if="!isLoggedIn">
        <button class="auth-button" @click.prevent="handleLoginClick" style="margin-right: 20px;">Log in</button>
        <button class="auth-button" @click.prevent="handleRegisterClick">Register</button>
      </div>

      <div v-if="isLoggedIn">
        <router-link to="/ProgramReference" class="nav-link" exact-active-class="active-link" @click.prevent="handlePRClick" style="margin-right: 20px;">Program Reference</router-link>
        <router-link to="/board" class="nav-link" exact-active-class="active-link" @click.prevent="handleBoardClick" style="margin-right: 20px;">Community</router-link>
        <router-link :to="myPageLink" class="nav-link" exact-active-class="active-link" @click.prevent="handleMypageClick" style="margin-right: 20px;">Mypage</router-link>
        <button @click="logout" class="auth-button">Log out</button>
      </div>
    </div>

    <!-- 라우터에 맞는 컴포넌트가 이곳에 렌더링됩니다 -->
    <router-view class="router-view" @navigation-start="isNavigating = true" @navigation-end="isNavigating = false"></router-view>
  </div>
</template>

<script>
export default {
  name: 'App',
  data() {
    return {
      isLoggedIn: false, // 로그인 상태를 추적하는 변수
      isNavigating: false, // 현재 페이지 전환 상태를 추적하는 변수
      isLoginPage: false, // 현재 페이지가 로그인 페이지인지 여부
      activeLink: false // 활성화된 링크를 추적하는 변수
    };
  },
  mounted() {
    // 페이지 로드 시 토큰을 확인하고 로그인 상태를 설정
    this.checkLoginStatus();
    this.checkIfLoginPage();
  },
  computed: {
    // 마이페이지로 이동하는 동적 링크
    myPageLink() {
      const userId = localStorage.getItem('user_id');
      const today = this.getTodayDate();
      return userId ? `/myPage/${userId}/${today}` : '/'; // 로그인 안 된 상태일 경우 홈으로 돌아가도록 처리
    }
  },
  methods: {
    checkLoginStatus() {
      const token = localStorage.getItem('token');
      this.isLoggedIn = !!token; // token이 있으면 로그인된 상태
    },
    checkIfLoginPage() {
      this.isLoginPage = this.$route.path === '/users/login' || this.$route.path === '/users/save';
    },
    handleLoginClick() {
      this.activeLink = true; // 활성화된 링크 설정
      this.isNavigating = true; // 페이지 전환 시작
      this.$router.push('/users/login').then(() => {
        this.isNavigating = false; // 페이지 전환 완료 후 상태 초기화
      });
    },
    handleRegisterClick() {
      this.activeLink = true; // 활성화된 링크 설정
      this.isNavigating = true; // 페이지 전환 시작
      this.$router.push('/users/save').then(() => {
        this.isNavigating = false; // 페이지 전환 완료 후 상태 초기화
      });
    },
    logout() {
      localStorage.removeItem('token'); // 토큰 제거
      this.isLoggedIn = false; // 로그인 상태 변경
      this.activeLink = false; // 활성화된 링크 초기화
      this.isNavigating = false; // 기본 콘텐츠 숨김
      this.$router.push('/'); // 홈 화면으로 리다이렉트
    },
    getTodayDate() {
      const today = new Date();
      const year = today.getFullYear();
      const month = ("0" + (today.getMonth() + 1)).slice(-2);
      const day = ("0" + today.getDate()).slice(-2);
      return `${year}-${month}-${day}`;
    },
    handlePRClick() {
      this.activeLink = true; // 활성화된 링크 설정
      this.isNavigating = true; // 페이지 전환 시작
      this.$router.push('/ProgramReference').then(() => {
        this.isNavigating = true; // 페이지 전환 완료 후 상태 초기화
      });
    },
    handleBoardClick() {
      this.activeLink = true; // 활성화된 링크 설정
      this.isNavigating = true; // 페이지 전환 시작
      this.$router.push('/board').then(() => {
        this.isNavigating = false; // 페이지 전환 완료 후 상태 초기화
      });
    },
    handleMypageClick() {
      this.activeLink = true; // 활성화된 링크 설정
      this.isNavigating = true; // 페이지 전환 시작
      this.$router.push(this.myPageLink).then(() => {
        this.isNavigating = false; // 페이지 전환 완료 후 상태 초기화
      });
    },
  },
  watch: {
    '$route': function () {
      this.checkLoginStatus(); // 라우트 변경 시 로그인 상태 확인
      this.checkIfLoginPage(); // 현재 페이지가 로그인 페이지인지 확인
    }
  }
};
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Newsreader:wght@400;700&display=swap');

#app {
  font-family: "Newsreader", sans-serif;
  text-align: center;
  position: relative;
}

/* 헤더를 수직 정렬 */
.header {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-bottom: 20px;
}


/* 네비게이션 스타일 */
nav {
  margin-top: 10px;
  display: flex;
  gap: 20px;
}

/* 네비게이션 링크 스타일 */
.nav-link {
  text-decoration: none;
  color: black;
  padding: 5px 10px;
  transition: color 0.3s;
}

/* 중앙 로고 스타일 */
.logo {
  position: absolute;
  top: 10px;
  left: 10px;
  font-size: 32px;
  font-weight: bold;
}

.active-link {
  font-weight: bold;
}

/* 로그인/회원가입/로그아웃 버튼 우측 상단 배치 */
.auth-buttons {
  position: absolute;
  top: 30px;
  right: 30px;
  display: flex;
  flex-direction: row;
  align-items: center;
  gap: 30px;
}

/* 버튼 공통 스타일 */
.auth-button {
  padding: 10px 20px;
  font-size: 16px;
  color: white;
  background-color: #426B1F;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  text-decoration: none;
  transition: background-color 0.3s ease, transform 0.2s ease; /* transform 추가 */
}

.auth-button:hover {
  background-color: #45a049;
}

.auth-button:active {
  background-color: #3e8e41;
  transform : scale(0.95);
}

.logo-text {
  color: #426B1F;
  text-decoration: none;
  user-select: none;
  font-size: 45px;
}

.logo-text:hover {
  cursor: default;
}

.router-view {
  margin-top: 70px;
}
</style>
