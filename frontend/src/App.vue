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
      <!-- 로그인 버튼은 token이 없을 때만 보임 -->
      <div v-if="!isLoggedIn">
        <router-link to="/users/login" class="auth-button" @click.prevent="handleLoginClick">Log in</router-link>
        <router-link to="/users/save" class="auth-button" @click.prevent="handleRegisterClick">Register</router-link>
      </div>

      <!-- 로그아웃 버튼은 token이 있을 때만 보임 -->
      <div v-if="isLoggedIn">
        <nav>
          <router-link v-bind:to="myPageLink" class="nav-link" style="margin-right: 20px;">Mypage</router-link>
          <router-link to="/board" class="nav-link" style="margin-right: 20px;">Community</router-link>
          <button @click="logout" class="auth-button">Log out</button>
        </nav>
      </div>
    </div>

    <!-- 로그인 이후에도 이미지와 글귀 표시 -->
    <div v-if="isLoggedIn || (!isLoggedIn && !isNavigating && !isLoginPage)" class="default-content">
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
      isLoginPage: false // 현재 페이지가 로그인 페이지인지 여부
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
      if (userId) {
        // 동적 URL 반환
        return `/myPage/${userId}/${today}`;
      }
      return '/'; // 로그인 안 된 상태일 경우 홈으로 돌아가도록 처리
    }
  },
  methods: {
    // 로그인 상태 체크
    checkLoginStatus() {
      const token = localStorage.getItem('token');
      this.isLoggedIn = token ? true : false; // token이 있으면 로그인된 상태
    },
    // 현재 페이지가 로그인 페이지인지 확인
    checkIfLoginPage() {
      this.isLoginPage = this.$route.path === '/users/login' || this.$route.path === '/users/save';
    },
    // 로그인 버튼 클릭 처리
    handleLoginClick() {
      this.isNavigating = true; // 페이지 전환 시작
      this.$router.push('/users/login'); // 페이지 전환
    },
    // 회원가입 버튼 클릭 처리
    handleRegisterClick() {
      this.isNavigating = true; // 페이지 전환 시작
      this.$router.push('/users/save'); // 페이지 전환
    },
    // 로그아웃 처리
    logout() {
      localStorage.removeItem('token'); // 토큰 제거
      this.isLoggedIn = false; // 로그인 상태 변경
      this.isNavigating = false; // 기본 콘텐츠 숨김
      this.$router.push('/'); // 홈 화면으로 리다이렉트
    },
    // 오늘 날짜를 "YYYY-MM-DD" 형식으로 반환
    getTodayDate() {
      const today = new Date();
      const year = today.getFullYear();
      const month = ("0" + (today.getMonth() + 1)).slice(-2); // 월 형식 맞추기
      const day = ("0" + today.getDate()).slice(-2); // 일 형식 맞추기
      return `${year}-${month}-${day}`;
    }
  },
  watch: {
    // route 변화 시 로그인 상태를 확인하는 부분
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

.default-content {
  display: flex; /* 플렉스 박스 사용하여 수직 정렬 */
  flex-direction: column; /* 세로 방향으로 정렬 */
  align-items: center; /* 중앙 정렬 */
  justify-content: flex-start; /* 상단에 배치 */
  height: 100vh; /* 전체 화면 높이 사용 */
  padding: 40px; /* 여백 추가 (20px에서 40px로 증가) */
}

.image-container {
  display: flex; /* 가로 정렬을 위해 Flexbox 사용 */
  justify-content: center; /* 중앙 정렬 */
  gap: 60px; /* 이미지 간의 간격 추가 */
  margin-top: 60px; /* 위쪽 여백을 늘림 */
}

.default-image {
  width: 500px; /* 이미지 너비를 500px로 키움 */
  height: auto; /* 비율 유지 */
  border-radius: 10px; /* 모서리 둥글게 */
}

.image-caption {
  text-align: left; /* 좌측 기준 정렬 */
  font-size: 18px; /* 글씨 크기 조정 */
  max-width: 600px; /* 가독성을 위해 최대 너비 설정 */
  margin: 60px 0 0; /* 위쪽 여백을 늘려서 이미지와 글귀 사이의 간격을 조정 */
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
  top: 40px;
  right: 40px;
  display: flex;
  flex-direction: row;
  align-items: center;
  gap: 10px;
}

/* 버튼 공통 스타일 */
.auth-button {
  margin-left: 20px;
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
  top: 30px;
  left: 30px;
  color: #426B1F; /* 원하는 색상으로 설정 (예: 파란색) */
  text-decoration: none; /* 링크의 밑줄 제거 */
  user-select: none; /* 텍스트 선택 비활성화 */
  font-size: 45px;
}

.logo-text:hover {
  cursor: default; /* 마우스 커서를 기본으로 설정하여 클릭 방지 */
}
</style>
