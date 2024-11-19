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
        .get("`/myPage/${this.userId}/${this.today}`")
        .then((response) => {
          console.log(response);
        })
        .catch((error) => {
            console.error("페이지 로드 중 오류:", error);
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
