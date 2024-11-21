<template>
  <div>
    <div class="line-container">
      <h1 class="MyPageLogo">My Page</h1>
      <div class="horizontal-line"></div> <!-- 세로선 추가 -->
    </div>
    <div class="mypage-container">
      <div class="calendar-container">
        <VDatePicker
          ref="calendar"
          expanded
          v-model="selectedDate"
          mode="date"
          @dayclick="handleDateSelect"
        >
          <template #footer>
            <button class="today-button" @click="moveToday">Today</button>
          </template>
        </VDatePicker>
      </div>
      <div class="My log">
        <!-- exersice log, body log, diet log와 save 버튼을 구현할 부분 -->

        <button class="save-button">Save</button>
        
        <div class="user-info">
          <h2>Body Information</h2>
          <label>
            <strong>키:</strong>
            <input
              type="text"
              v-model="bodyData.height"
              :placeholder="bodyData?.height || ''"
            />
          </label>
          <label>
            <strong>몸무게:</strong>
            <input
              type="text"
              v-model="bodyData.weight"
              :placeholder="bodyData?.weight || ''"
            />
          </label>
          <label>
            <strong>BMI:</strong>
            <input
              type="text"
              v-model="bodyData.bmi"
              :placeholder="bodyData?.bmi || ''"
            />
          </label>
          <label>
            <strong>골격근량:</strong>
            <input
              type="text"
              v-model="bodyData.smm"
              :placeholder="bodyData?.smm || ''"
            />
          </label>
          <label>
            <strong>체지방률:</strong>
            <input
              type="text"
              v-model="bodyData.pbf"
              :placeholder="bodyData?.pbf || ''"
            />
          </label>
        </div>
      </div>
    </div>
  </div>
</template>


<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import 'v-calendar/dist/style.css';
import axios from 'axios';

const selectedDate = ref(new Date()); // 선택된 날짜
const calendar = ref(null); // VDatePicker 참조
const router = useRouter();
const bodyData = ref({
  height: '',
  weight: '',
  bmi: '',
  smm: '',
  pbf: ''
});


const userId = localStorage.getItem('userId');
const token = localStorage.getItem('token');

const navigateToDate = async (date) => {
  const formattedDate = date.id; // 'id' 속성에서 날짜 추출
  router.push(`/myPage/${userId}/${formattedDate}`)
  if (formattedDate) {
    try {
        const response = await axios.get(`/myPage/${userId}/${formattedDate}`, {
            headers: {
                Authorization: `Bearer ${token}`
            }
        });
        bodyData.value = response.data || { height: '', weight: '', bmi: '', smm: '', pbf: '' };

    } catch (error) {
        console.error(error);
    }
  }
};

const handleDateSelect = (date) => {
  selectedDate.value = new Date(date.id); // id에서 날짜 추출하여 selectedDate 업데이트
  navigateToDate(date); // 날짜 URL로 이동
};

// 오늘 날짜로 이동
const moveToday = () => {
  const today = new Date(); // 오늘 날짜 가져오기
  selectedDate.value = today; // 선택된 날짜를 오늘로 설정
  calendar.value.move(today); // 캘린더를 오늘로 이동

  // 오늘 날짜로 navigateToDate 함수 호출
  navigateToDate({ id: today.toISOString().split('T')[0] }); // ISO 형식으로 날짜를 전달
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Newsreader:wght@400;700&display=swap');

#app {
  font-family: "Newsreader", sans-serif;
  text-align: center;
  position: relative;
}

h1 {
  margin-bottom: 16px; /* 제목과 캘린더 간 간격 */
  text-align: center; /* 텍스트 가운데 정렬 */
}

.calendar-container {
  flex: 0 0 40%; /* 왼쪽에서 40% 비율로 배치 */
  padding: 20px;
  position: relative; /* 버튼 위치 조정을 위한 상대 위치 */
}

/* 캘린더 전체 높이 조정 */
::v-deep(.vc-grid-container) {
  height: 400px !important; /* 캘린더 높이 설정 */
  display: flex;
  flex-direction: column;
}

/* 날짜 셀 크기 조정 */
::v-deep(.vc-grid) {
  flex-grow: 1;
  height: 400px !important;
}

::v-deep(.vc-day) {
  height: 80px; /* 날짜 셀 높이 */
  line-height: 80px; /* 셀 내 텍스트 중앙 정렬 */
}

/* Today 버튼 스타일 */
.today-button {
  appearance: none; /* 기본 스타일 초기화 */
  -webkit-appearance: none; /* Safari의 기본 스타일 초기화 */
  -moz-appearance: none; /* Firefox의 기본 스타일 초기화 */
  background-color: #426B1F; /* 버튼 배경색 (파란색) */
  color: #fff; /* 텍스트 색상 (흰색) */
  border: none; /* 기본 테두리 제거 */
  border-radius: 5px; /* 둥근 모서리 */
  padding: 10px 20px; /* 버튼 크기 */
  font-size: 1rem; /* 글씨 크기 */
  cursor: pointer; /* 커서 모양 변경 */
  transition: background-color 0.3s, transform 0.2s; /* 호버 및 클릭 시 애니메이션 */
  position: absolute; /* 부모 요소 기준으로 위치 조정 */
  bottom: 20px; /* 캘린더 하단에서 위로 20px 이동 */
  left: 50%; /* 가로로 가운데 정렬 */
  transform: translateX(-50%); /* 가운데 정렬 */
  z-index: 10;
}

.today-button:hover {
  background-color: #71B636 !important; /* 호버 시 색상 변경 */
}

.today-button:active {
  transform: translateX(-50%) scale(0.95); /* 클릭 시 버튼 살짝 축소 */
}

.save-button {
  background-color: #426B1F; /* 버튼 배경색 (파란색) */
  color: #fff; /* 텍스트 색상 (흰색) */
  border: none; /* 기본 테두리 제거 */
  border-radius: 5px; /* 둥근 모서리 */
  padding: 10px 20px; /* 버튼 크기 */
  font-size: 1rem; /* 글씨 크기 */
  cursor: pointer; /* 커서 모양 변경 */
  transition: background-color 0.3s, transform 0.2s; /* 호버 및 클릭 시 애니메이션 */
  position: absolute;
  right: 100px;
  bottom: 30px;
}

.save-button:hover {
  background-color: #71B636 !important; /* 호버 시 색상 변경 */
}

.save-button:active {
  transform: scale(0.95); /* 클릭 시 버튼 살짝 축소 */
}

.MyPageLogo {
  color: #426B1F;
  font-weight: normal;
}

.line-container {
  display: flex;
  flex-direction: column; /* 세로 방향으로 정렬 */
  align-items: center; /* 가운데 정렬 */
}

.horizontal-line {
  width: 90%; /* 가로선의 너비 */
  height: 2px; /* 가로선의 두께 */
  background-color: #426B1F; /* 가로선 색상 */
  margin-top: 10px; /* h1과 가로선 사이의 간격 */
}

.mypage-container {
  display: flex; /* 수평 레이아웃 */
  justify-content: space-between; /* 캘린더와 다른 요소 사이 여백 */
  align-items: flex-start; /* 상단 정렬 */
  padding: 20px;
}

.user-info {
  width: 600px;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 10px;
  background-color: #f9f9f9;
  position: relative;
  left: -100px;
  top : 20px;
}

.user-info label {
  display: block;
  margin-bottom: 10px;
}

.user-info input {
  width: 100%;
  padding: 8px;
  margin-top: 5px;
  border: 1px solid #ddd;
  border-radius: 5px;
  font-size: 1rem;
  box-sizing: border-box;
}

.user-info input:focus {
  border-color: #426B1F;
  outline: none;
  box-shadow: 0 0 5px rgba(66, 107, 31, 0.5);
}

/* 나머지 CSS 스타일은 그대로 유지 */

</style>
