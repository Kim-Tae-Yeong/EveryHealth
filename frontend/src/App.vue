<template>
  <div id="app">
    <!-- 중앙에 로고 글씨 추가 -->
    <div class="header">
      <div class="logo">
        <router-link to="/">EveryHealth</router-link>
      </div>
      <!-- 네비게이션을 로고 밑에 위치 -->
      <nav>
        <router-link to="/myPage" class="nav-link">마이페이지</router-link>
        <router-link to="/board" class="nav-link">커뮤니티</router-link>
      </nav>
    </div>

    <!-- 로그인 상태에 따른 버튼 표시 -->
    <div class="auth-buttons">
      <!-- 로그인 버튼은 token이 없을 때만 보임 -->
      <div v-if="!isLoggedIn">
        <router-link to="/users/login" class="auth-button">로그인</router-link>
        <router-link to="/users/save" class="auth-button">회원가입</router-link>
      </div>

      <!-- 로그아웃 버튼은 token이 있을 때만 보임 -->
      <div v-if="isLoggedIn">
        <button @click="logout" class="auth-button">로그아웃</button>
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
#app {
  font-family: Arial, sans-serif;
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
  font-size: 24px;
  font-weight: bold;
}

/* 로그인/회원가입/로그아웃 버튼 우측 상단 배치 */
.auth-buttons {
  position: absolute;
  top: 10px;
  right: 20px;
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
  background-color: #4CAF50;
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
</style>
