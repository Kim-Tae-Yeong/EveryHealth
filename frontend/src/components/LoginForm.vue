<template>
  <div class="container">
    <h1 class="login-title">Join us</h1>
    <!-- 로그인 폼 -->
    <form @submit.prevent="login" class="login-form">
      <div class="input-group">
        <label for="email">Email: </label>
        <input type="email" v-model="email" id="email" />
      </div>
      <div class="input-group">
        <label for="password">Password: </label>
        <input type="password" v-model="password" id="password" />
      </div>
      <button type="submit" class="submit-button">Log in</button>
    </form>
    
    <div class="button-container">
      <router-link to="/users/emails/search" class="accept_bt">Find Email</router-link>
      <router-link to="/users/password" class="accept_bt">Change Password</router-link>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      email: '',
      password: ''
    };
  },
  methods: {
    async login() {
      try {
        const response = await this.$axios.post("/users/login", {
          email: this.email,
          password: this.password
        });

        const responseData = response.data;
        let token = responseData.token || responseData;
        const userId = responseData.userId;

        if (token && token.startsWith("Bearer ")) {
            token = token.replace('Bearer ', '');
        }
        if (token) {
          // token을 localStorage에 저장
          localStorage.setItem("token", token);
          localStorage.setItem("user_id", userId);

          // 로그인 성공 후 홈 페이지로 리다이렉트
          this.$router.push("/");
        }
      } catch (error) {
        console.error("로그인 실패:", error);
        alert("로그인 실패! 이메일과 비밀번호를 확인해주세요.");
      }
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

.login-title {
  font-size: 48px; /* 제목 크기 조정 */
  margin-bottom: 40px; /* 제목과 폼 사이의 간격 증가 */
  color: #426B1F;
}

.container {
  display: flex;
  flex-direction: column; /* 수직 정렬 */
  align-items: center; /* 중앙 정렬 */
  justify-content: center; /* 수직 중앙 정렬 */
  height: 100vh; /* 화면 전체 높이 사용 */
}

.login-form {
  display: flex; /* Flexbox 사용 */
  flex-direction: column; /* 수직 정렬 */
  align-items: center; /* 중앙 정렬 */
  margin-bottom: 20px; /* 버튼과의 간격 */
}

.input-group {
  display: flex; /* Flexbox 사용 */
  align-items: center; /* 수직 중앙 정렬 */
  margin-bottom: 10px; /* 각 입력 필드 간의 간격 */
  width: 100%; /* 부모의 너비에 맞추기 */
  max-width: 400px; /* 최대 너비 설정 (원하는 크기로 조정) */
}

.input-group label {
  margin-right: 10px; /* 레이블과 입력 필드 간의 간격 */
  width: 100px; /* 레이블의 고정 너비 설정 */
}

.input-group input {
  flex: 1; /* 남은 공간을 차지하도록 설정 */
  padding: 5px; /* 입력 필드 안쪽 여백 */
}

.button-container {
  display: flex; /* Flexbox 사용 */
  justify-content: center; /* 중앙 정렬 */
  margin-top: 20px; /* 로그인 폼과 버튼 사이의 간격 추가 */
}

.accept_bt {
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

.accept_bt:hover {
  background-color: #45a049; /* 호버 시 색상 변경 */
}

.accept_bt:active {
  background-color: #3e8e41; /* 클릭 시 색상 변경 */
}

.submit-button {
  padding: 6px 14px;
  font-size: 12px;
  color: white;
  background-color: #426B1F; /* 버튼 배경색 */
  border: none; /* 테두리 제거 */
  border-radius: 5px; /* 모서리 둥글게 */
  cursor: pointer; /* 커서 포인터로 변경 */
  transition: background-color 0.3s ease; /* 배경색 변화 효과 */
}

.submit-button:hover {
  background-color: #45a049; /* 호버 시 색상 변경 */
}

.submit-button:active {
  background-color: #3e8e41; /* 클릭 시 색상 변경 */
}

</style>
