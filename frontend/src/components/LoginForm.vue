<template>
  <div>
    <!-- 로그인 폼 -->
    <form @submit.prevent="login">
      <div>
        <label for="email">이메일</label>
        <input type="email" v-model="email" id="email" />
      </div>
      <div>
        <label for="password">비밀번호</label>
        <input type="password" v-model="password" id="password" />
      </div>
      <button type="submit">로그인</button>
    </form>
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
        const response = await this.$axios.post("/user/login", {
          email: this.email,
          password: this.password
        });

        const token = response.data.replace('Bearer ', '');  // "Bearer " 접두어 제거
        if (token) {
          // token을 localStorage에 저장
          localStorage.setItem("token", token);

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
