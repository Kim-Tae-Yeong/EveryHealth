<template>
    <div>
        <h2>게시글 상세 조회</h2>
        <img :src="imageUrl" alt="게시글 이미지" />
        <p><strong>작성 날짜 : </strong> {{date}}</p>
        <p><strong>내용 : </strong> {{content}}</p>

        <button @click="submit">삭제하기</button>
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
                    this.imageUrl = `http://localhost:8082${response.data.imageUrl}`;
                    this.date = response.data.date;
                    this.content = response.data.content;
                })
                .catch(error => {
                    console.error("Error fetching board details : ", error);
                });
        },
        submit() {
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
                console.error("Error deleting board : ", error);
                alert("삭제 중 오류가 발생했습니다.");
            })
        }
    }
}
</script>

<style>

</style>