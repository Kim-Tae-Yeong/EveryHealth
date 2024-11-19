<template>
  <div>
    <h2>회원가입</h2>
    <form @submit.prevent="submitForm">
      <div>
        <label for="email">이메일:</label>
        <input type="email" id="email" v-model="userForm.email" required />
      </div>

      <div>
        <label for="phoneNumber">전화번호(숫자만 입력):</label>
        <input type="text" id="phoneNumber" v-model="userForm.phoneNumber" required />
      </div>

      <div>
        <label for="name">사용자 이름:</label>
        <input type="text" id="name" v-model="userForm.name" required />
      </div>

      <div>
        <label for="nickname">닉네임:</label>
        <input type="text" id="nickname" v-model="userForm.nickname" required />
      </div>

      <div>
        <label for="password">비밀번호(8자리 이상):</label>
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
        email: '',
        phoneNumber: '',
        name: '',
        nickname: '',
        password: ''
      }
    };
  },
  methods: {
    validatePhoneNumber(phoneNumber) {
        const phoneRegex = /^[0-9]+$/;
        return phoneRegex.test(phoneNumber);
    },
    validatePassword(password) {
        return password.length >= 8;
    },
    submitForm() {
      if (!this.validatePhoneNumber(this.userForm.phoneNumber)) {
        alert("전화번호는 숫자만 입력 가능합니다.");
        return;
      }
      if(!this.validatePassword(this.userForm.password)) {
        alert("비밀번호는 최소 8자 이상이어야 합니다.");
        return;
      }

      // JSON 형식으로 데이터를 보내기 위해 객체 생성
      const userData = {
        email: this.userForm.email,
        phone_number: this.userForm.phoneNumber,
        name: this.userForm.name,
        nickname: this.userForm.nickname,
        password: this.userForm.password
      };

      // 서버에 JSON 데이터 전송
      this.$axios.post('/users/save', userData)
        .then(response => {
          console.log('회원가입 성공:', response.data);
          this.$router.push('/'); // 홈으로 이동
        })
        .catch(error => {
          if(error.response && error.response.status == 400) {
            alert("이미 존재하는 이메일입니다.");
          } else {
            alert("회원가입 중 오류가 발생했습니다.");
          }
        });
    }
  }
};
</script>

<style>

</style>
