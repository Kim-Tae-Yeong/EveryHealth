<template>
  <div>
    <div class="line-container">
      <h1 class="MyPageLogo">My Exercise Log</h1>
      <div class="horizontal-line"></div> <!-- 세로선 추가 -->
    </div>
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
      <div class="Mylog">
        <button class="save-button" @click="saveInformation">Save</button>
        <button @click="addExerciseLog">운동 추가</button>
        <div v-for="(exercise, index) in exerciseLogs" :key="index">
            <div>
                <label for="exerciseName">운동 이름</label>
                <input
                    type="text"
                    v-model="exercise.exerciseName"
                    placeholder="운동 이름 입력"
                />
            </div>
            <div>
                <label for="setCount">세트 수</label>
                <input
                    type="text"
                    v-model="exercise.setCount"
                    placeholder="세트 수 입력"
                />
            </div>
            <div>
                <label for="count">횟수</label>
                <input
                    type="text"
                    v-model="exercise.count"
                    placeholder="횟수 입력"
                />
            </div>
            <div>
                <label for="weight">무게</label>
                <input
                    type="text"
                    v-model="exercise.weight"
                    placeholder="무게 입력"
                />
            </div>
            <button @click="deleteExerciseLog(index)">삭제</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted} from 'vue';
import { useRouter } from 'vue-router';
import 'v-calendar/dist/style.css';
import axios from 'axios';

const selectedDate = ref(new Date());
const calendar = ref(null);
const router = useRouter();

const exerciseLogs = ref([]);
const userId = localStorage.getItem('userId');
const token = localStorage.getItem('token');

const formatDate = (date) => {
  return date.toISOString().split('T')[0];
};

const addExerciseLog = () => {
  exerciseLogs.value.push({
    exerciseName: '',
    setCount: '',
    count: '',
    weight: '',
    userId: userId,
    date: formatDate(selectedDate.value),
  });
};

const handleDateSelect = (date) => {
  selectedDate.value = new Date(date.id);
  const formattedDate = formatDate(selectedDate.value);
  const savedData = localStorage.getItem(`exerciseLogs-${formattedDate}`);

  exerciseLogs.value = savedData ? JSON.parse(savedData) : [];
  navigateToDate(formattedDate);
};

const navigateToDate = (date) => {
  if (date) {
    router.push(`/myPageExercise/${userId}/${date}`);
  }
};

const moveToday = () => {
  const today = new Date();
  selectedDate.value = today;
  calendar.value.move(today);

  handleDateSelect({ id: today.toISOString().split('T')[0] });
};

const saveInformation = async () => {
  const formattedDate = formatDate(selectedDate.value);
  try {
    const response = await axios.post(`/myPageExercise/${userId}/${formattedDate}`, exerciseLogs.value, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
    alert("저장되었습니다.");
    router.push(`/myPageExercise/${userId}/${formattedDate}`);
    localStorage.setItem(`exerciseLogs-${formattedDate}`, JSON.stringify(exerciseLogs.value));
    console.log(response);
  } catch (error) {
    console.error(error);
  }
};

onMounted(() => {
  const formattedDate = formatDate(selectedDate.value);
  const savedData = localStorage.getItem(`exerciseLogs-${formattedDate}`);
  exerciseLogs.value = savedData ? JSON.parse(savedData) : [];
});

const deleteExerciseLog = (index) => {
  exerciseLogs.value.splice(index, 1);
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

</style>