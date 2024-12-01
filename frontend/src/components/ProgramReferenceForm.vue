<template>
  <div class="line-container"> <!-- 부모 요소 추가 -->
    <h1 class="PRLogo">Check how to exercise</h1>
    <div class="horizontal-line"></div> <!-- 가로선 -->

    <div v-if="exerciseLists.length > 0">
      <div v-for="(exerciseList, index) in exerciseLists" :key="index" class="exercise-item">
        <!-- 이미지: 왼쪽 배치 -->
        <img :src="exerciseList.imageUrl" alt="Upload Image" class="exercise-image" />

        <!-- 텍스트 정보 박스 -->
        <div class="exercise-info-box">
          <p class="exercise-name">{{ exerciseList.exerciseName }}</p>
          <p class="exercise-category">{{ exerciseList.category }}</p>
          <p class="exercise-description">{{ exerciseList.description }}</p>
        </div>
      </div>
    </div>
  </div>
</template>




<script>
import axios from 'axios';

const token = localStorage.getItem('token');

export default {
    data() {
        return {
            exerciseLists: [],
        };
    },
    mounted() {
        this.fetchExerciseLists();
    },
    methods: {
        fetchExerciseLists() {
            axios.get('exerciseLists', {
                headers: {
                    Authorization: `Bearer ${token}`,
                }
            })
            .then(response => {
                this.exerciseLists = response.data.map(exerciseList => ({
                    exerciseName: exerciseList.exerciseName,
                    category: exerciseList.categorize,
                    description: exerciseList.description,
                    imageUrl: `http://localhost:8082/${exerciseList.exerciseListImageUrl}`
                }));
            })
            .catch(error => {
                console.error("Error fetching exerciseLists: " + error);
            });
        }
    }
}
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Newsreader:wght@400;700&display=swap');

#app {
  font-family: "Newsreader", sans-serif;
  text-align: center;
  position: relative;
}

.line-container {
  display: flex;
  flex-direction: column; /* 세로 방향으로 정렬 */
  align-items: center; /* 가운데 정렬 */
}

.PRLogo {
  color: #426B1F;
  font-weight: normal;
}

.horizontal-line {
  width: 90%; /* 가로선의 너비 */
  height: 2px; /* 가로선의 두께 */
  background-color: #426B1F; /* 가로선 색상 */
  margin-top: 10px; /* h1과 가로선 사이의 간격 */
}

/* 운동 항목 스타일 */
.exercise-item {
  display: flex; /* Flexbox로 이미지와 텍스트 나란히 정렬 */
  align-items: center; /* 세로 정렬 */
  margin: 20px 0; /* 항목 간 간격 */
  padding: 10px;
  border-bottom: 1px solid #ccc; /* 구분선 */
}

/* 이미지 스타일 */
.exercise-image {
  width: 250px; /* 이미지 표현 영역 */
  height: 250px; /* 높이를 250px로 확장 */
  object-fit: contain; /* 이미지 비율 유지 */
  border-radius: 8px; /* 둥근 모서리 */
  margin-right: 20px; /* 텍스트와의 간격 */
  background-color: #f4f4f4; /* 이미지 배경 */
  padding: 5px; /* 이미지와 배경 간 간격 */
}

/* 텍스트 정보 박스 */
.exercise-info-box {
  background-color: #ffffff; /* 흰색 배경 */
  border: 2px solid #6b8e23; /* 운동 부위 색상과 동일하게 설정 */
  border-radius: 0px; /* 각진 모서리로 설정 */
  padding: 15px; /* 내부 여백 */
  flex-grow: 1;
  text-align: left;
  box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1); /* 박스 그림자 */
}

.exercise-name {
  font-weight: bold;
  font-size: 1.2rem;
  color: #333;
  margin-bottom: 10px;
}

.exercise-category {
  font-size: 1rem;
  color: #6b8e23; /* 초록색 */
  margin-bottom: 10px;
}

/* 운동 부위 색상 동기화 */
.exercise-info-box {
  border-color: #6b8e23; /* 운동 부위 텍스트와 동일한 초록색 테두리 */
}

.exercise-description {
  font-size: 0.9rem;
  color: #555;
  line-height: 1.4;
}
</style>
