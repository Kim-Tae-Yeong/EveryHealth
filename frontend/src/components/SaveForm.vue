<template>
  <div>
    <h2>회원가입</h2>
    <form @submit.prevent="submitForm">
      <div>
        <label for="name">사용자 이름:</label>
        <input type="text" id="name" v-model="userForm.name" required />
      </div>

      <div>
        <label for="nickname">닉네임:</label>
        <input type="text" id="nickname" v-model="userForm.nickname" required />
      </div>

      <div>
        <label for="email">이메일:</label>
        <input type="email" id="email" v-model="userForm.email" required />
      </div>

      <div>
        <label for="password">비밀번호:</label>
        <input type="password" id="password" v-model="userForm.password" required />
      </div>

      <button type="submit">회원가입</button>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      userForm: {
        name: '',
        nickname: '',
        email: '',
        password: ''
      }
    };
  },
  methods: {
    submitForm() {
      // JSON 형식으로 데이터를 보내기 위해 객체 생성
      const userData = {
        name: this.userForm.name,
        nickname: this.userForm.nickname,
        email: this.userForm.email,
        password: this.userForm.password
      };

      // 서버에 JSON 데이터 전송
      this.$axios.post('/user/save', userData)
        .then(response => {
          console.log('회원가입 성공:', response.data);
          this.$router.push('/'); // 홈으로 이동
        })
        .catch(error => {
          console.error('회원가입 실패:', error);
        });
    }
  }
};
</script>

<style>

</style>
