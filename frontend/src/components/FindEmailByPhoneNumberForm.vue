<template>
    <div>
        <h2>이메일 찾기</h2>
        <form @submit.prevent="submitForm">
            <div>
                <label for="phoneNumber">전화번호(숫자만 입력):</label>
                <input
                    type="text"
                    id="phoneNumber"
                    v-model="phoneNumber"
                    required
                />
            </div>
            <button type="submit">이메일 찾기</button>
        </form>
    </div>
</template>

<script>
export default {
    data() {
        return {
            phoneNumber: '' // UserDTO의 phoneNumber 필드와 매핑
        };
    },
    methods: {
        validatePhoneNumber(phoneNumber) {
            const phoneRegex = /^[0-9]+$/;
            return phoneRegex.test(phoneNumber);
        },
        submitForm() {
            if (!this.validatePhoneNumber(this.phoneNumber)) {
                alert("전화번호는 숫자만 입력 가능합니다.");
                return;
            }

            // UserDTO 형식으로 요청 보내기
            this.$axios.post('/users/emails/search', {
                phoneNumber: this.phoneNumber
            })
            .then(response => {
                // 성공적으로 이메일 목록을 반환받은 경우
                if (Array.isArray(response.data)) {
                    alert("찾은 이메일: " + response.data.join(", "));
                    this.$router.push("/users/login");
                }
            })
            .catch(error => {
                // 404 상태일 경우 사용자에게 안내 메시지 표시
                if (error.response && error.response.status === 404) {
                    alert(error.response.data); // "해당 전화번호로 가입된 이메일이 없습니다."
                } else {
                    alert("이메일 찾기 중 오류가 발생했습니다.");
                }
                console.error("이메일 찾기 실패:", error);
            });
        }
    }
};
</script>

<style>
/* 스타일은 필요에 따라 추가 */
</style>
