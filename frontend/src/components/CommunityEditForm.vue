<template>
    <div>
        <h2>Edit your Post</h2>

        <div>
            <label for="image">Upload your Photo</label>
            <input
                type="file"
                id="image"
                @change="handleImageUpload"
            />
        </div>

        <div>
            <label for="content">Content</label>
            <textarea
                type="content"
                v-model="content"
                placeholder="내용을 작성하세요."
            />
        </div>

        <button @click="submit">Save</button>
    </div>
</template>

<script>
export default {
    data() {
        return {
            image: null,
            content: "",
            date: "", // 날짜를 저장할 데이터 속성
            boardId: null,
        };
    },
    mounted() {
        this.fetchDateFromParams(); // 컴포넌트가 마운트될 때 실행
    },
    methods: {
        fetchDateFromParams() {
            const dateQuery = this.$route.query.date; // URL 쿼리에서 date 값 가져오기
            if (dateQuery) {
                this.date = decodeURIComponent(dateQuery);
            } else {
                alert("수정하려는 게시글의 날짜 정보를 찾을 수 없습니다.");
            }
        },
        handleImageUpload(event) {
            const file = event.target.files[0];
            if (file) {
                this.image = file;
            }
        },
        submit() {
            if (!this.image || !this.content || !this.date) {
                alert("사진, 내용을 모두 입력해주세요.");
                return;
            }
            const token = localStorage.getItem('token');
            const userId = localStorage.getItem('userId');
            const boardId = this.$route.params.boardId;

            const formData = new FormData();
            formData.append("date", this.date); // URL에서 가져온 날짜 포함
            formData.append("content", this.content);
            formData.append("userId", userId);
            formData.append("boardFile", this.image);

            this.$axios.post(`/community/${boardId}`, formData, {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            })
            .then(() => {
                alert("게시글이 성공적으로 수정되었습니다.");
                this.$router.push("/community");
            })
            .catch((error) => {
                console.error("Error submitting post: ", error);
                alert("게시글 수정 중 오류가 발생했습니다.");
            });
        },
    },
};
</script>
