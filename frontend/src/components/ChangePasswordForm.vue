<template>
    <div class="container">
        <h2 class="change-password-title">Change the Password</h2>
        <form @submit.prevent="submitForm" class="change-password-form">
            <div class="input-group">
                <label for="email">Email:</label>
                <input
                    type="text"
                    id="email"
                    v-model="email"
                    required
                />
            </div>
            <div class="input-group">
                <label for="password">Password:</label>
                <input
                    type="password"
                    id="password"
                    v-model="password"
                    required
                />
            </div>
            <button type="submit" class="submit-button">Apply</button>
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
        validatePassword(password) {
            return password.length >= 8;
        },
        submitForm() {
            if (!this.validatePassword(this.password)) {
                alert("비밀번호는 8자리 이상이어야 합니다.");
                return;
            }

            this.$axios.post('/users/password', {
                email: this.email,
                password: this.password
            })
            .then(response => {
                alert("비밀번호가 변경되었습니다.");
                console.log(response.data);
                this.$router.push("/users/login");
            })
            .catch(error => {
                if (error.response && error.response.status == 404) {
                    alert(error.response.data);
                } else {
                    alert("비밀번호 변경 중 오류가 발생했습니다.");
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

.change-password-title {
    font-size: 36px; /* 제목 크기 조정 */
    margin-bottom: 40px; /* 제목과 폼 사이의 간격 증가 */
    color: #426B1F;
}

.change-password-form {
    display: flex; /* Flexbox 사용 */
    flex-direction: column; /* 수직 정렬 */
    align-items: center; /* 중앙 정렬 */
    width: 100%; /* 부모의 너비에 맞추기 */
    max-width: 400px; /* 최대 너비 설정 */
}

.input-group {
    display: flex; /* Flexbox 사용 */
    align-items: center; /* 수직 중앙 정렬 */
    margin-bottom: 30px; /* 입력 필드와 버튼 간의 간격 증가 */
    width: 100%; /* 부모의 너비에 맞추기 */
}

.input-group label {
    margin-right: 10px; /* 레이블과 입력 필드 간의 간격 조정 */
    width: 130px; /* 레이블의 고정 너비 설정 */
    text-align: right; /* 레이블을 오른쪽 정렬하여 ':' 정렬 */
}

.input-group input {
    flex: 1; /* 남은 공간을 차지하도록 설정 */
    padding: 10px; /* 입력 필드 안쪽 여백 */
}

.submit-button {
    padding: 10px 20px; /* 버튼 패딩 */
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
