<template>
  <div>
    <h1>마이페이지</h1>
    <p>{{ message }}</p>
  </div>
</template>

<script>
export default {
  name: "MyPage",
  data() {
    return {
      message: "",
    };
  },
  created() {
    const token = localStorage.getItem("token");
    if (token) {
      this.$axios
        .get("/myPage", {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        })
        .then((response) => {
          this.message = response.data; // 서버에서 받은 데이터
        })
        .catch((error) => {
          if (error.response && error.response.status === 401) {
            alert("로그인이 필요합니다.");
            this.$router.push("/user/login");
          } else {
            console.error("페이지 로드 중 오류:", error);
          }
        });
    } else {
      alert("로그인이 필요합니다.");
      this.$router.push("/user/login");
    }
  },
};
</script>

<style scoped>
h1 {
  margin-top: 20px;
}
</style>
