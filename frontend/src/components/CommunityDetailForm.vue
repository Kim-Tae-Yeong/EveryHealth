<template>
    <div class="detail-container">
        <h2 class="title">Detail Post</h2>
        <div class="image-container">
            <img :src="imageUrl" alt="게시글 이미지" class="post-image" />
        </div>
        <p class="detail-text"><strong>Writer: </strong> {{ writer }}</p>
        <p class="detail-text"><strong>Upload date: </strong> {{ date }}</p>
        <p class="detail-text"><strong>Content: </strong> {{ content }}</p>

        <div class="button-container">
            <button @click="navigateToDelete" class="delete-button">Delete</button>
            <button @click="navigateToEdit" class="modify-button">Modify</button>
        </div>
    </div>
</template>

<script>
import axios from 'axios';

const token = localStorage.getItem('token');

export default {
    data() {
        return {
            imageUrl: "",
            date: "",
            content: ""
        };
    },
    mounted() {
        this.fetchDetail();
    },
    methods: {
        fetchDetail() {
            const boardId = this.$route.params.boardId;
            axios.get(`/community/${boardId}`, {
                headers: {
                    Authorization: `Bearer ${token}`,
                }
            })
                .then(response => {
                    const data = response.data;
                    this.imageUrl = `http://localhost:8082${data.board.imageUrl}`;
                    this.date = data.board.date;
                    this.content = data.board.content;
                    this.writer = data.name;
                })
                .catch(error => {
                    console.error("Error fetching board details: ", error);
                });
        },
        navigateToDelete() {
            const boardId = this.$route.params.boardId;
            axios.delete(`/community/${boardId}`, {
                headers: {
                    Authorization: `Bearer ${token}`,
                }
            })
            .then(() => {
                alert("삭제되었습니다.");
                this.$router.push("/community");
            })
            .catch((error) => {
                console.error("Error deleting board: ", error);
                alert("삭제 중 오류가 발생했습니다.");
            })
        },
        navigateToEdit() {
            const boardId = this.$route.params.boardId;
            this.$router.push(`/community/edit/${boardId}?date=${this.date}`);
        }
    }
}
</script>

<style>
.detail-container {
    max-width: 800px; /* 최대 너비 설정 */
    margin: 80px auto; /* 중앙 정렬 */
    padding: 20px; /* 패딩 추가 */
    border: 1px solid #ddd; /* 테두리 추가 */
    border-radius: 8px; /* 둥근 모서리 */
    background-color: #f9f9f9; /* 배경색 */
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); /* 그림자 효과 */
}

.title {
    color: #426B1F; /* 제목 색상 */
    font-size: 24px; /* 제목 크기 */
    margin-bottom: 20px; /* 아래 여백 */
    text-align: center; /* 중앙 정렬 */
}

.image-container {
    display: flex; /* Flexbox 사용 */
    justify-content: center; /* 수평 중앙 정렬 */
    margin-bottom: 15px; /* 아래 여백 */
}

.post-image {
    max-width: 100%; /* 이미지가 컨테이너를 넘지 않도록 설정 */
    height: auto; /* 비율 유지 */
    border-radius: 4px; /* 둥근 모서리 */
}

.detail-text {
    font-size: 18px; /* 글씨 크기 */
    margin: 10px 0; /* 위 아래 여백 */
}

.button-container {
    display: flex; /* Flexbox 사용 */
    justify-content: space-between; /* 버튼 간격 조정 */
}

.delete-button, .modify-button {
    padding: 10px 15px; /* 버튼 패딩 */
    font-size: 16px; /* 버튼 글씨 크기 */
    color: white; /* 글씨 색상 */
    border: none; /* 테두리 제거 */
    border-radius: 5px; /* 둥근 모서리 */
    cursor: pointer; /* 커서 포인터 */
    transition: background-color 0.3s ease; /* 배경색 전환 효과 */
}

.delete-button {
    background-color: #e74c3c; /* 삭제 버튼 색상 */
}

.delete-button:hover {
    background-color: #c0392b; /* 삭제 버튼 호버 색상 */
}

.modify-button {
    background-color: #426B1F; /* 수정 버튼 색상 */
}

.modify-button:hover {
    background-color: #45a049; /* 수정 버튼 호버 색상 */
}
</style>
