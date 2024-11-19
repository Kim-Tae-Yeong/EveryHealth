<template>
    <div>
        <h2>비밀번호 변경</h2>
        <form @submit.prevent="submitForm">
            <div>
                <label for="email">이메일:</label>
                    <input
                        type="text"
                        id="email"
                        v-model="email"
                        required
                    />
            </div>
            <div>
                <label for="password">비밀번호(8자리 이상):</label>
                    <input
                        type="password"
                        id="password"
                        v-model="password"
                        required
                    />
            </div>
            <button type="submit">비밀번호 변경</button>
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
            return password.length >=8;
        },
        submitForm() {
            if(!this.validatePassword(this.password)) {
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
                if(error.response && error.response.status == 404) {
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

</style>