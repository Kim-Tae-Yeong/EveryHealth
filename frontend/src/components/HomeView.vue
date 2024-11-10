<template>
  <div>
    <p>{{ message }}</p>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: "HomeView",
  data() {
    return {
      message: ''
    };
  },
  created() {
    axios.get('http://localhost:8081/')
      .then(response => {
        this.message = response.data;
      })
      .catch(error => {
        console.error(error);
      });
  }
};
</script>
