<template>
  <div>
    <h1>마이페이지</h1>
  </div>
</template>

<script>
export default {
  name: "MyPage",
  data() {
    return {
      userId: this.$route.params.user_id,
      today: this.$route.params.today,
    };
  },
  created() {
      this.$axios
        .get(`/myPage/${this.userId}/${this.today}`, {
            headers: {
                Authorization: `Bearer ${localStorage.getItem('token')}`
            }
        })
        .then((response) => {
          console.log(response.data);
        })
        .catch((error) => {
            if(error.response && error.response.status == 403) {
                console.error("인증 오류:", error);
            }
          }
        );
  }
};
</script>

<style scoped>
h1 {
  margin-top: 20px;
}
</style>
