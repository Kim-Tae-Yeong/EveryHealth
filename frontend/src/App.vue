<template>
  <div id="app">
    <nav>
      <router-link to="/">Home</router-link>
    </nav>
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
}

nav {
  margin-bottom: 20px;
}
</style>
