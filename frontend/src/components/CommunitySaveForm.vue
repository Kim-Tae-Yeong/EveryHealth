<template>
    <div class="outer-container">
        <div class="upload-container">
            <h2 class="title">Upload your Post</h2>

            <div class="upload-field">
                <label for="image" class="label-text">Upload Photo</label>
                <input type="file" id="image" @change="handleImageUpload" />
            </div>

            <div class="content-field">
                <label for="content" class="label-text">Content</label>
                <textarea id="content" v-model="content" placeholder="내용을 작성하세요."></textarea>
            </div>

            <button @click="submit" class="submit-button">Save</button>
        </div>
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
.outer-container {
    display: flex;
    justify-content: center; /* 수평 중앙 정렬 */
    align-items: center; /* 수직 중앙 정렬 */
    height: 100vh; /* 전체 화면 높이 */
    width: 100%; /* 부모 요소의 너비를 100%로 설정 */
}

.upload-container {
    width: 800px; /* 너비를 800px로 고정 */
    margin: 20px; /* 여백 추가 */
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
}

.upload-field, .content-field {
    margin-bottom: 15px; /* 아래 여백 */
}

.label-text {
    display: block; /* 블록 요소로 설정 */
    margin-bottom: 5px; /* 아래 여백 */
    color: #426B1F; /* 텍스트 색상 수정 (여기서 원하는 색상으로 변경) */
    text-align: left;
}

input[type="file"] {
    display: block; /* 블록 요소로 설정 */
    margin-top: 5px; /* 위 여백 */
    width: 100%; /* 너비 100% */
}

textarea {
    width: 100%; /* 너비 100% */
    height: 100px; /* 높이 설정 */
    border: 1px solid #ccc; /* 테두리 */
    border-radius: 4px; /* 둥근 모서리 */
    padding: 10px; /* 패딩 추가 */
}

.submit-button {
    padding: 10px 20px; /* 버튼 패딩 */
    font-size: 16px; /* 버튼 글씨 크기 */
    color: white; /* 글씨 색상 */
    background-color: #426B1F; /* 버튼 배경색 */
    border: none; /* 테두리 제거 */
    border-radius: 5px; /* 둥근 모서리 */
    cursor: pointer; /* 커서 포인터 */
    transition: background-color 0.3s ease; /* 배경색 전환 효과 */
}

.submit-button:hover {
    background-color: #45a049; /* 호버 시 색상 변경 */
}

.submit-button:active {
    background-color: #3e8e41; /* 클릭 시 색상 변경 */
}
</style>
