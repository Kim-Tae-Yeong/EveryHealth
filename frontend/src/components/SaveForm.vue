<template>
  <div class="container">
    <h2 class="register-title">Register</h2>
    <form @submit.prevent="submitForm" class="register-form">
      <div class="input-group">
        <label for="email">Email :</label>
        <input type="email" id="email" v-model="userForm.email" required />
      </div>

      <div class="input-group phone-group">
        <label for="phoneNumber">Phone Number :</label>
        <input type="text" id="phoneNumber" v-model="userForm.phoneNumber" required />
      </div>

      <div class="input-group">
        <label for="name">Username :</label>
        <input type="text" id="name" v-model="userForm.name" required />
      </div>

      <div class="input-group">
        <label for="nickname">Nickname :</label>
        <input type="text" id="nickname" v-model="userForm.nickname" required />
      </div>

      <div class="input-group">
        <label for="password">Password (over 8) :</label>
        <input type="password" id="password" v-model="userForm.password" required />
      </div>

      <button type="submit" class="submit-button">Join us</button>
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
      if (!this.validatePassword(this.userForm.password)) {
        alert("비밀번호는 최소 8자 이상이어야 합니다.");
        return;
      }

      // JSON 형식으로 데이터를 보내기 위해 객체 생성
      const userData = {
        email: this.userForm.email,
        phoneNumber: this.userForm.phoneNumber,
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
          if (error.response && error.response.status == 400) {
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
@import url('https://fonts.googleapis.com/css2?family=Newsreader:wght@400;700&display=swap');

.container {
  display: flex;
  flex-direction: column; /* 수직 정렬 */
  align-items: center; /* 중앙 정렬 */
  justify-content: center; /* 수직 중앙 정렬 */
  height: 100vh; /* 화면 전체 높이 사용 */
  font-family: "Newsreader", sans-serif;
}

.register-title {
  font-size: 36px; /* 제목 크기 조정 */
  margin-bottom: 40px; /* 제목과 폼 사이의 간격 증가 */
  color: #426B1F;
}

.register-form {
  display: flex; /* Flexbox 사용 */
  flex-direction: column; /* 수직 정렬 */
  align-items: center; /* 중앙 정렬 */
  width: 100%; /* 부모의 너비에 맞추기 */
  max-width: 400px; /* 최대 너비 설정 */
}

.input-group {
  display: flex; /* Flexbox 사용 */
  align-items: center; /* 수직 중앙 정렬 */
  margin-bottom: 15px; /* 각 입력 필드 간의 간격 */
  width: 100%; /* 부모의 너비에 맞추기 */
}

.input-group label {
  margin-right: 5px; /* 레이블과 입력 필드 간의 간격을 줄여서 붙임 */
  width: 130px; /* 레이블의 고정 너비 설정 */
}

.input-group input {
  flex: 1; /* 남은 공간을 차지하도록 설정 */
  padding: 8px; /* 입력 필드 안쪽 여백 */
}

.phone-group {
  display: flex; /* Flexbox 사용 */
  align-items: center; /* 수직 중앙 정렬 */
  width: 100%; /* 부모의 너비에 맞추기 */
}

.submit-button {
  padding: 8px 16px; /* 버튼 패딩 */
  font-size: 16px; /* 버튼 글자 크기 */
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
