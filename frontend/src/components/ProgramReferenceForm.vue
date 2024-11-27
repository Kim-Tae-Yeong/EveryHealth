<template>
    <div class="line-container"> <!-- 부모 요소 추가 -->
         <h1 class="PRLogo">Check how to exercise</h1>
         <div class="horizontal-line"></div> <!-- 가로선 -->
         <div v-if="exerciseLists.length > 0">
            <div v-for="(exerciseList, index) in exerciseLists" :key="index">
                <p>{{exerciseList.exerciseName}}</p>
                <p>{{exerciseList.category}}</p>
                <p>{{exerciseList.description}}</p>
                <img :src="exerciseList.imageUrl" alt="Upload Image" />
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

</style>
