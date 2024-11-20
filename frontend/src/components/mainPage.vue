<template>
  <div class="default-content">
    <div class="image-container">
      <img :src="require('@/assets/image1.jpg')" alt="Image 1" class="default-image" />
      <img :src="require('@/assets/image2.jpg')" alt="Image 2" class="default-image" />
    </div>
    <p class="image-caption">
      Welcome to EveryHealth!<br><br>
      EveryHealth is your all-in-one health management app designed to seamlessly integrate your diet and exercise routines.<br>
      With our intuitive platform, you can easily log your meals and workouts, track your progress, and receive personalized workout recommendations based on your unique fitness data.<br><br>
      Connect with a community of fitness enthusiasts through our interactive bulletin board, where you can share your achievements, motivate each other, and celebrate your journeys together.<br>
      Whether you're aiming to lose weight, build muscle, or maintain a healthy lifestyle, FitConnect is here to support you every step of the way.<br><br>
      Join us in transforming your health and fitness experience!<br><br>
      Feel free to modify any part to better fit your vision!
    </p>
  </div>
</template>

<script>
export default {
  name: 'MainPage',
};
</script>

<style scoped>
.default-content {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: flex-start;
  height: 100vh;
  padding: 40px;
}

.image-container {
  display: flex;
  justify-content: center;
  gap: 60px;
}

.default-image {
  width: 500px;
  height: auto;
  border-radius: 10px;
}

.image-caption {
  text-align: left;
  font-size: 18px;
  max-width: 600px;
  margin: 60px 0 0;
}
</style>
