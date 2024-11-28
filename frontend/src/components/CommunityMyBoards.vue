<template>
    <div class="line-container"> <!-- 부모 요소 추가 -->
        <h1 class="CommunityLogo">Show off your Body!</h1>
        <div class="horizontal-line"></div> <!-- 가로선 -->
        <div class="button-container">
            <button @click="navigateToSave" class="post-button">Post it</button>
            <button @click="navigateToAllBoards" class="post-button">Show Total Post</button>
        </div>
        <div v-if="boards.length > 0" class="image-grid">
            <div v-for="(board, index) in boards" :key="index" class="image-item">
                <img :src="board.imageUrl" :alt="'Uploaded Image ' + (index + 1)" @click="navigateToBoard(board.boardId)"/>
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
            boards: [],
        };
    },
    mounted() {
        this.fetchImages();
    },
    methods: {
        fetchImages() {
            const userId = localStorage.getItem('userId');
            if(!userId) {
                alert('로그인이 필요합니다.');
                return;
            }
            axios.get('/community/myBoards', {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
                params: {
                    userId: userId,
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
        },
        navigateToAllBoards() {
            this.$router.push("/community");
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

.image-item {
  flex: 0 0 calc(20% - 8px); /* 한 줄에 5개 표시 (20% 너비 - 간격) */
  max-width: calc(20% - 8px); /* 최대 너비 20% - 간격 */
  box-sizing: border-box; /* 패딩과 보더를 포함하여 계산 */
}

.image-grid {
  display: flex; /* Flexbox 사용 */
  flex-wrap: wrap; /* 줄 바꿈 */
  justify-content: center; /* 가운데 정렬 */
  gap: 10px; /* 사진 간격 */
}

.image-item img {
  width: 100%; /* 이미지 너비를 부모 요소에 맞춤 */
  max-height: 150px; /* 최대 세로 길이 설정 */
  height: auto; /* 비율 유지 */
  object-fit: contain; /* 비율을 유지하면서 전체가 보이도록 조정 */
}

.post-button {
  background-color: #426B1F; /* 버튼 배경색 (파란색) */
  color: #fff; /* 텍스트 색상 (흰색) */
  border: none; /* 기본 테두리 제거 */
  border-radius: 5px; /* 둥근 모서리 */
  padding: 5px 10px; /* 버튼 크기 */
  font-size: 1rem; /* 글씨 크기 */
  cursor: pointer; /* 커서 모양 변경 */
  transition: background-color 0.3s, transform 0.2s; /* 호버 및 클릭 시 애니메이션 */
  margin-bottom: 5px;
}

.post-button:hover {
  background-color: #71B636 !important; /* 호버 시 색상 변경 */
}

.post-button:active {
  transform: scale(0.95); /* 클릭 시 버튼 살짝 축소 */
}

.button-container {
    display: flex; /* 버튼을 한 행에 배치 */
    gap: 10px; /* 버튼 간의 간격 */
    margin-top: 10px; /* 가로선과 버튼 사이의 간격 */
}

</style>
