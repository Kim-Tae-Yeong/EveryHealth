<template>
  <div>
    <h1>마이페이지</h1>
    <!-- 날짜 클릭 이벤트 처리 -->
    <VDatePicker v-model="selectedDate" mode="date" @dayclick="handleDateSelect" />
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import 'v-calendar/dist/style.css';

const selectedDate = ref(new Date());
const router = useRouter();

// 날짜가 변경될 때 호출되는 함수
const handleDateSelect = (date) => {
  selectedDate.value = new Date(date.id);  // id에서 날짜 추출하여 selectedDate 업데이트
  navigateToDate(date);  // 날짜 URL로 이동
};

const userId = localStorage.getItem('userId');
// 날짜를 형식에 맞게 URL로 이동하는 함수
const navigateToDate = (date) => {
  const formattedDate = date.id;  // 'id' 속성에서 날짜 추출
  if (formattedDate) {
    router.push(`/myPage/${userId}/${formattedDate}`);  // 선택된 날짜로 이동 (예: /myPage/123/2024-11-23)
  }
};
</script>

<style>
h1 {
  margin-top: 20px;
}
</style>
