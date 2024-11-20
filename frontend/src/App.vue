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
        <router-link to="/users/login" class="auth-button" @click.prevent="handleLoginClick" style="margin-right: 20px;">Log in</router-link>
        <router-link to="/users/save" class="auth-button" @click.prevent="handleRegisterClick">Register</router-link>
      </div>

      <div v-if="isLoggedIn">
        <router-link to="/ProgramReference" class="nav-link" exact-active-class="active-link" @click.prevent="handlePRClick" style="margin-right: 20px;">Program Reference</router-link>
        <router-link to="/board" class="nav-link" exact-active-class="active-link" @click.prevent="handleBoardClick" style="margin-right: 20px;">Community</router-link>
        <router-link :to="myPageLink" class="nav-link" exact-active-class="active-link" @click.prevent="handleMypageClick" style="margin-right: 20px;">Mypage</router-link>
        <button @click="logout" class="auth-button">Log out</button>
      </div>
    </div>

    <div v-if="(isLoggedIn || (!isLoggedIn && !isLoginPage && !isNavigating)) && !activeLink" class="default-content">
      <div class="image-container">
        <img :src="require('@/assets/image1.jpg')" alt="Image 1" class="default-image" />
        <img :src="require('@/assets/image2.jpg')" alt="Image 2" class="default-image" />
      </div>
      <p class="image-caption">
        Welcome to EveryHealth!<br><br>
        EveryHealth is your all-in-one health management app designed to seamlessly integrate your diet and exercise routines.<br>
        With our intuitive platform, you can easily log your meals and workouts, track your progress, and receive personalized workout recommendations based on your unique fitness data.<br><br>
        Connect with a community of fitness enthusiasts through our interactive bulletin board, where you can share your achievements, motivate each other, and celebrate your journeys together.<br>
        Whether you're aiming to lose weight, build muscle, or maintain a healthy lifestyle, FitConnect is here to support you every step of the way.<br><br>
        Join us in transforming your health and fitness experience!<br><br>
        Feel free to modify any part to better fit your vision!
      </p>  
    </div>

    <!-- 라우터에 맞는 컴포넌트가 이곳에 렌더링됩니다 -->
    <router-view @navigation-start="isNavigating = true" @navigation-end="isNavigating = false"></router-view>
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
      this.isNavigating = true; // 페이지 전환 시작
      this.activeLink = true; // 활성화된 링크 설정
      this.$router.push('/users/login'); // 페이지 전환
    },
    handleRegisterClick() {
      this.isNavigating = true; // 페이지 전환 시작
      this.activeLink = true; // 활성화된 링크 설정
      this.$router.push('/users/save'); // 페이지 전환
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
      this.isNavigating = true; // 페이지 전환 시작
      this.activeLink = true; // 활성화된 링크 설정
      this.$router.push('/ProgramReference'); // 페이지 전환
    },
    handleBoardClick() {
      this.isNavigating = true; // 페이지 전환 시작
      this.activeLink = true; // 활성화된 링크 설정
      this.$router.push('/board'); // 페이지 전환
    },
    handleMypageClick() {
      this.isNavigating = true; // 페이지 전환 시작
      this.activeLink = true; // 활성화된 링크 설정
      this.$router.push(this.myPageLink); // 페이지 전환
    },
  },
  watch: {
    '$route': function () {
      this.checkLoginStatus(); // 라우트 변경 시 로그인 상태 확인
      this.checkIfLoginPage(); // 현재 페이지가 로그인 페이지인지 확인
      this.activeLink = false; // 라우트 변경 시 활성화된 링크 초기화
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

.default-content {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: flex-start;
  height: 100vh;
  padding: 40px;
}

.image-container {
  display: flex;
  justify-content: center;
  gap: 60px;
  margin-top: 60px;
}

.default-image {
  width: 500px;
  height: auto;
  border-radius: 10px;
}

.image-caption {
  text-align: left;
  font-size: 18px;
  max-width: 600px;
  margin: 60px 0 0;
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

/* 활성화된 링크 스타일 */
.active-link {
  font-weight: bold; /* 글씨체 굵게 설정 */
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
  transition: background-color 0.3s ease;
}

.auth-button:hover {
  background-color: #45a049;
}

.auth-button:active {
  background-color: #3e8e41;
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
</style>
