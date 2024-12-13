<template>
  <div id="app">
    <div class="header">
      <div class="logo">
        <router-link to="/" class="logo-text">EveryHealth</router-link>
      </div>
    </div>

    <div class="auth-buttons">
      <div v-if="!isLoggedIn">
        <router-link to="/users/login" class="auth-button" style="margin-right: 20px;">Log in</router-link>
        <router-link to="/users/save" class="auth-button" style="margin-right: 20px;">Register</router-link>
      </div>

      <div v-if="isLoggedIn">
        <router-link to="/program-reference" class="nav-link" exact-active-class="active-link" style="margin-right: 20px;">Program Reference</router-link>
        <router-link to="/community" class="nav-link" exact-active-class="active-link" :class="{'active-link': isCommunityActive}" style="margin-right: 20px;">Community</router-link>
        <router-link :to="myPageBodyLink" class="nav-link" active-class="active-link" :class="{'active-link': isMyPageBodyActive}" @click.prevent="handleMyPageBodyClick" style="margin-right: 20px;">My Body Log</router-link>
        <router-link :to="myPageDietLink" class="nav-link" active-class="active-link" :class="{'active-link': isMyPageDietActive}" @click.prevent="handleMyPageDietClick" style="margin-right: 20px;">My Diet Log</router-link>
        <router-link :to="myPageExerciseLink" class="nav-link" active-class="active-link" :class="{'active-link': isMyPageExerciseActive}" @click.prevent="handleMyPageExerciseClick" style="margin-right: 20px;">My Exercise Log</router-link>
        <button @click="logout" class="auth-button">Log out</button>
      </div>
    </div>

    <router-view class="router-view"></router-view>
  </div>
</template>

<script>
export default {
  name: 'App',
  data() {
    return {
      isLoggedIn: false,
      isNavigating: false,
      bodyData: null,
      isMyPageBodyActive: false, // 추가: myPage 활성화 여부 확인
      isMyPageDietActive: false,
      isMyPageExerciseActive: false,
    };
  },
  mounted() {
    this.checkLoginStatus();
  },
  computed: {
    myPageBodyLink() {
      const userId = localStorage.getItem('userId');
      const today = this.getTodayDate();
      return userId ? `/myPageBody/${userId}/${today}` : '/';
    },
    myPageDietLink() {
      const userId = localStorage.getItem('userId');
      const today = this.getTodayDate();
      return userId ? `/myPageDiet/${userId}/${today}` : '/';
    },
    myPageExerciseLink() {
      const userId = localStorage.getItem('userId');
      const today = this.getTodayDate();
      return userId ? `/myPageExercise/${userId}/${today}` : '/';
    },
    isCommunityActive() {
      return this.$route.path.startsWith('/community');
    }
  },
  methods: {
    checkLoginStatus() {
      const accessToken = localStorage.getItem('accessToken');
      this.isLoggedIn = !!accessToken;
    },
    logout() {
      localStorage.removeItem('accessToken');
      localStorage.removeItem('userId');
      localStorage.removeItem('bodyData');
      localStorage.removeItem('dietData');
      localStorage.removeItem('exerciseData');
      this.isLoggedIn = false;
      this.$router.push('/');
    },
    getTodayDate() {
      const today = new Date();
      const year = today.getFullYear();
      const month = ("0" + (today.getMonth() + 1)).slice(-2);
      const day = ("0" + today.getDate()).slice(-2);
      return `${year}-${month}-${day}`;
    },
    async navigateToMyPageBody() {
      this.isNavigating = true;
      const link = this.myPageBodyLink;
      const accessToken = localStorage.getItem('accessToken');

      // 페이지 전환이 완료될 때까지 기다리기 위해 then() 사용
      this.$router.push(link).then(async () => {
        try {
          const response = await this.$axios.get(link, {
            headers: { Authorization: `Bearer ${accessToken}` }
          });
          this.bodyData = response.data;
          localStorage.setItem('bodyData', JSON.stringify(response.data));
        } catch (error) {
            console.error(error);
        } finally {
          this.isNavigating = false;
        }
      });
    },
    async navigateToMyPageExercise() {
      this.isNavigating = true;
      const link = this.myPageExerciseLink;
      const accessToken = localStorage.getItem('accessToken');

      // 페이지 전환이 완료될 때까지 기다리기 위해 then() 사용
      this.$router.push(link).then(async () => {
        try {
          const response = await this.$axios.get(link, {
            headers: { Authorization: `Bearer ${accessToken}` }
          });
          this.exerciseData = response.data;
          localStorage.setItem('exerciseData', JSON.stringify(response.data));
        } catch (error) {
            console.error(error);
        } finally {
          this.isNavigating = false;
        }
      });
    },
    async navigateToMyPageDiet() {
      this.isNavigating = true;
      const link = this.myPageDietLink;
      const accessToken = localStorage.getItem('accessToken');


      // 페이지 전환이 완료될 때까지 기다리기 위해 then() 사용
      this.$router.push(link).then(async () => {
        try {
          const response = await this.$axios.get(link, {
            headers: { Authorization: `Bearer ${accessToken}` }
          });
          this.dietData = response.data;
          localStorage.setItem('dietData', JSON.stringify(response.data));
        } catch (error) {
            console.error(error);
        } finally {
          this.isNavigating = false;
        }
      });
    },
    handleMyPageBodyClick() {
      this.isNavigating = true;
      this.navigateToMyPageBody();
    },
    handleMyPageDietClick() {
      this.isNavigating = true;
      this.navigateToMyPageDiet();
    },
    handleMyPageExerciseClick() {
      this.isNavigating = true;
      this.navigateToMyPageExercise();
    },
    // myPage가 활성화된 상태인지 확인
    checkIfMyPageBodyIsActive() {
      const currentPath = this.$route.path;
      const userId = localStorage.getItem('userId');
      if (currentPath.startsWith(`/myPageBody/${userId}`)) {
        this.isMyPageBodyActive = true;
      } else {
        this.isMyPageBodyActive = false;
      }
    },
    checkIfMyPageDietIsActive() {
      const currentPath = this.$route.path;
      const userId = localStorage.getItem('userId');
      if (currentPath.startsWith(`/myPageDiet/${userId}`)) {
        this.isMyPageDietActive = true;
      } else {
        this.isMyPageDietActive = false;
      }
    },
    checkIfMyPageExerciseIsActive() {
      const currentPath = this.$route.path;
      const userId = localStorage.getItem('userId');
      if (currentPath.startsWith(`/myPageExercise/${userId}`)) {
        this.isMyPageExerciseActive = true;
      } else {
        this.isMyPageExerciseActive = false;
      }
    },
    checkIfCommunityIsActive() {
      const currentPath = this.$route.path;
      if (currentPath.startsWith(`/community/`)) {
        this.isCommunityActive = true;
      } else {
        this.isCommunityActive = false;
      }
    },
  },
  watch: {
    '$route': function () {
      this.checkLoginStatus();
      this.checkIfMyPageBodyIsActive(); // 경로가 변경될 때마다 myPage 활성화 상태 점검
      this.checkIfMyPageDietIsActive();
      this.checkIfMyPageExerciseIsActive();
      this.checkIfCommunityIsActive();
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

.nav-link.active-link {
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
