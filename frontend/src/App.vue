<template>
  <div id="app">
    <!-- 중앙에 로고 글씨 추가 -->
    <div class="header">
      <div class="logo">
        <router-link to="/" class="logo-text">EveryHealth</router-link>
      </div>
      <!-- 네비게이션을 로고 밑에 위치 -->
    </div>

    <!-- 로그인 상태에 따른 버튼 표시 -->
    <div class="auth-buttons">
      <!-- 로그인 버튼은 token이 없을 때만 보임 -->
      <div v-if="!isLoggedIn">
        <router-link to="/users/login" class="auth-button">Log in</router-link>
        <router-link to="/users/save" class="auth-button">Register</router-link>
      </div>

      <!-- 로그아웃 버튼은 token이 있을 때만 보임 -->
      <div v-if="isLoggedIn">
        <nav>
          <router-link to="/myPage" class="nav-link" style="margin-right: 20px;">Mypage</router-link>
          <router-link to="/board" class="nav-link" style="margin-right: 20px;">Community</router-link>
          <button @click="logout" class="auth-button">Log out</button>
        </nav>
      </div>
    </div>

    <!-- 라우터에 맞는 컴포넌트가 이곳에 렌더링됩니다 -->
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'App',
  data() {
    return {
      isLoggedIn: false // 로그인 상태를 추적하는 변수
    };
  },
  mounted() {
    // 페이지 로드 시 토큰을 확인하고 로그인 상태를 설정
    this.checkLoginStatus();
  },
  methods: {
    // 로그인 상태 체크
    checkLoginStatus() {
      const token = localStorage.getItem('token');
      this.isLoggedIn = token ? true : false; // token이 있으면 로그인된 상태
    },
    // 로그아웃 처리
    logout() {
      localStorage.removeItem('token'); // 토큰 제거
      this.isLoggedIn = false; // 로그인 상태 변경
      this.$router.push('/'); // 홈 화면으로 리다이렉트
    }
  },
  watch: {
    // route 변화 시 로그인 상태를 확인하는 부분
    '$route' : function() {
      this.checkLoginStatus(); // 라우트 변경 시 로그인 상태 확인
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
}

/* 네비게이션 스타일 */
nav {
  margin-top: 10px;
  display: flex;
  gap: 20px; /* 링크 간 간격 설정 */
}

/* 네비게이션 링크 스타일 */
.nav-link {
  text-decoration: none;
  color: black;
  font-weight: bold;
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

/* 로그인/회원가입/로그아웃 버튼 우측 상단 배치 */
.auth-buttons {
  position: absolute;
  top: 20px;
  right: 10px;
  display: flex;
  flex-direction: row;
  align-items: center;
}

/* 버튼 공통 스타일 */
.auth-button {
  margin-left: 10px;
  padding: 10px 20px;
  font-size: 16px;
  color: white;
  background-color: #426B1F;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  text-decoration: none; /* router-link 스타일링을 위해 추가 */
  transition: background-color 0.3s ease;
}

.auth-button:hover {
  background-color: #45a049; /* 호버 시 색상 변경 */
}

.auth-button:active {
  background-color: #3e8e41; /* 클릭 시 색상 변경 */
}

.logo-text {
  color: #426B1F; /* 원하는 색상으로 설정 (예: 파란색) */
  text-decoration: none; /* 링크의 밑줄 제거 */
  user-select: none; /* 텍스트 선택 비활성화 */
}

.logo-text:hover {
  cursor: default; /* 마우스 커서를 기본으로 설정하여 클릭 방지 */
}

</style>