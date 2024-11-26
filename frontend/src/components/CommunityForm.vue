<template>
    <div class="line-container"> <!-- 부모 요소 추가 -->
         <h1 class="CommunityLogo">Show off your Body!</h1>
         <div class="horizontal-line"></div> <!-- 가로선 -->
         <div v-if="boards.length > 0">
            <div v-for="(board, index) in boards" :key="index">
                <img :src="board.imageUrl" :alt="'Uploaded Image ' + (index + 1)" @click="navigateToBoard(board.boardId)"/>
            </div>
         </div>
         <button @click="navigateToSave">게시글 작성</button>
    </div>
</template>

<script>
import axios from 'axios';

const token = localStorage.getItem('token');

export default {
    data() {
        return {
            boards: [],
        };
    },
    mounted() {
        this.fetchImages();
    },
    methods: {
        fetchImages() {
            axios.get('/community', {
                headers: {
                    Authorization: `Bearer ${token}`,
                }
            })
            .then(response => {
                this.boards = response.data.map(board => ({
                    boardId: board.boardId,
                    imageUrl: `http://localhost:8082${board.imageUrl}`,
                }));
            })
            .catch(error => {
                console.error("Error fetching images: " + error);
            });
        },
        navigateToSave() {
            this.$router.push("/community/save");
        },
        navigateToBoard(boardId) {
            this.$router.push(`/community/${boardId}`);
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

.CommunityLogo {
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
