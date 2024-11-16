<template>
  <div>
    <form @submit.prevent="submitLogin">
      <div>
        <label for="email">이메일</label>
        <input
          type="email"
          id="email"
          v-model="userForm.email"
          required
          placeholder="이메일을 입력하세요"
        />
      </div>

      <div>
        <label for="password">비밀번호</label>
        <input
          type="password"
          id="password"
          v-model="userForm.password"
          required
          placeholder="비밀번호를 입력하세요"
        />
      </div>

      <button type="submit">로그인</button>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      userForm: {
        email: "",  // name 대신 email
        password: "",
      },
    };
  },
  methods: {
    submitLogin() {
      const loginData = {
        email: this.userForm.email,  // name -> email
        password: this.userForm.password
      };

      this.$axios.post('/user/login', loginData)
        .then(response => {
          if (response.status === 200) {
            // 로그인 성공 시 처리
            alert("로그인 성공!");  // 성공 시 alert 메시지 추가
            console.log('로그인 성공:', response.data);
            this.$router.push('/');  // 홈 페이지로 이동
          }
        })
        .catch(error => {
          if (error.response && error.response.status === 401) {
            // 로그인 실패 시 alert로 안내
            alert('로그인 실패: 이메일 또는 비밀번호가 일치하지 않습니다.');
          } else {
            // 기타 오류 처리
            alert('알 수 없는 오류가 발생했습니다.');
          }
        });
    }
  },
};
</script>

<style>
/* 추가 스타일은 필요에 맞게 작성 */
</style>
