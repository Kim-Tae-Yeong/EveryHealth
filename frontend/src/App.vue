<template>
  <div id="app">
    <!-- 중앙에 로고 글씨 추가 -->
    <div class="header">
      <div class="logo">
        <router-link to="/">EveryHealth</router-link>
      </div>
      <!-- 네비게이션을 로고 밑에 위치 -->
      <nav></nav>
    </div>

    <!-- 우측 상단 로그인 버튼 -->
    <div class="login-button">
      <router-link to="/user/login">로그인</router-link>
    </div>

    <!-- 우측 상단 회원가입 버튼 -->
    <div class="signup-button">
      <router-link to="/user/save">회원가입</router-link>
    </div>

    <!-- 라우터에 맞는 컴포넌트가 이곳에 렌더링됩니다 -->
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'App'
};
</script>

<style>
#app {
  font-family: Arial, sans-serif;
  text-align: center;
  position: relative;
}

/* 헤더를 수직 정렬 */
.header {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-bottom: 20px;
}

/* 네비게이션 스타일 */
nav {
  margin-top: 10px;
}

/* 중앙 로고 스타일 */
.logo {
  font-size: 24px;
  font-weight: bold;
}

/* 우측 상단 로그인 버튼 */
.login-button {
  position: absolute;
  top: 10px;
  right: 100px; /* 회원가입 버튼보다 왼쪽에 위치하도록 설정 */
}

/* 우측 상단 회원가입 버튼 */
.signup-button {
  position: absolute;
  top: 10px;
  right: 20px;
}
</style>
