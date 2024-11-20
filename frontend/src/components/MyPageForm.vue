<template>
  <div>
    <h1>마이페이지</h1>
    <div class="mypage-container">
      <div class="calendar-container">
        <VDatePicker
          ref="calendar"
          expanded
          :rows="1"
          :step="1"
          v-model="selectedDate"
          mode="date"
          @dayclick="handleDateSelect"
        >
          <template #footer>
            <button class="today-button" @click="moveToday">Today</button>
          </template>
        </VDatePicker>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import 'v-calendar/dist/style.css';

const selectedDate = ref(new Date()); // 선택된 날짜
const calendar = ref(null); // VDatePicker 참조
const router = useRouter();

const handleDateSelect = (date) => {
  selectedDate.value = new Date(date.id); // id에서 날짜 추출하여 selectedDate 업데이트
  navigateToDate(date); // 날짜 URL로 이동
};

const userId = localStorage.getItem('userId');

const navigateToDate = (date) => {
  const formattedDate = date.id; // 'id' 속성에서 날짜 추출
  if (formattedDate) {
    router.push(`/myPage/${userId}/${formattedDate}`); // 선택된 날짜로 이동
  }
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
h1 {
  margin-bottom: 16px; /* 제목과 캘린더 간 간격 */
  text-align: center; /* 텍스트 가운데 정렬 */
}

.mypage-container {
  display: flex; /* 수평 레이아웃 */
  justify-content: space-between; /* 캘린더와 다른 요소 사이 여백 */
  align-items: flex-start; /* 상단 정렬 */
  padding: 20px;
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
  background-color: #007bff; /* 버튼 배경색 (파란색) */
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
}

.today-button:hover {
  background-color: #0056b3; /* 호버 시 색상 변경 */
}

.today-button:active {
  transform: translateX(-50%) scale(0.95); /* 클릭 시 버튼 살짝 축소 */
}
</style>
