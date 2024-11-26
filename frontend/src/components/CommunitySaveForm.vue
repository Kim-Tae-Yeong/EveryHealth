<template>
    <div>
        <h2>게시글 작성</h2>

        <div>
            <label for="image">사진 업로드</label>
            <input
                type="file"
                id="image"
                @change="handleImageUpload"
            />
        </div>

        <div>
            <label for="content">내용</label>
            <textarea
                type="content"
                v-model="content"
                placeholder="내용을 작성하세요."
            />
        </div>

        <button @click="submit">저장하기</button>
    </div>
</template>

<script>
export default {
    data() {
        return {
            image: null,
            content: ""
        };
    },
    methods: {
        handleImageUpload(event) {
            const file = event.target.files[0];
            if(file) {
                this.image = file;
            }
        },
        submit() {
            if(!this.image || !this.content) {
                alert("사진과 내용을 모두 입력해주세요.");
                return;
            }
            const token = localStorage.getItem('token');
            const userId = localStorage.getItem('userId');
            const today = new Date();
            const date = today.toISOString().replace("Z", "");

            const formData = new FormData();
            formData.append("date", date);
            formData.append("content", this.content);
            formData.append("userId", userId);
            formData.append("boardFile", this.image);

            this.$axios.post("/community/save", formData, {
                headers: {
                    Authorization: `Bearer ${token}`
                },
            })
            .then(() => {
                alert("게시글이 성공적으로 작성되었습니다.");
                this.$router.push("/community");
            })
            .catch((error) => {
                console.error("Error submitting post: ", error);
                alert("게시글 작성 중 오류가 발생했습니다.");
            })
        }
    }
}
</script>

<style>

</style>