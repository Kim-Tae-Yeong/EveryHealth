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
        <button @click="addExerciseLog" class="exercise-button">Add your Exercise</button>
        <div class="exercise-logs">
          <div v-for="(exercise, index) in exerciseLogs" :key="index" class="exercise-log">
            <div class="input-group">
              <label for="exerciseName" style="color: #426B1F;">Exercise name</label>
              <input
                type="text"
                v-model="exercise.exerciseName"
                placeholder="운동 이름 입력"
              />
            </div>
            <div class="input-group">
              <label for="setCount" style="color: #426B1F;">Set count</label>
              <input
                type="text"
                v-model="exercise.setCount"
                placeholder="세트 수 입력"
              />
            </div>
            <div class="input-group">
              <label for="count" style="color: #426B1F;">Count</label>
              <input
                type="text"
                v-model="exercise.count"
                placeholder="횟수 입력"
              />
            </div>
            <div class="input-group">
              <label for="weight" style="color: #426B1F;">Weight</label>
              <input
                type="text"
                v-model="exercise.weight"
                placeholder="무게 입력"
              />
            </div>
            <button @click="deleteExerciseLog(index)" class="delete-button">Delete</button>
          </div>
        </div>
        <button v-if="exerciseLogs.length > 0" class="save-button" @click="saveInformation">Save</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import 'v-calendar/dist/style.css';
import axios from 'axios';

const selectedDate = ref(new Date());
const calendar = ref(null);
const router = useRouter();

const exerciseLogs = ref([]);
const userId = localStorage.getItem('userId');


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
    const accessToken = localStorage.getItem('accessToken');
    const response = await axios.post(`/myPageExercise/${userId}/${formattedDate}`, exerciseLogs.value, {
      headers: {
        Authorization: `Bearer ${accessToken}`,
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
  margin-top: 20px; /* 위쪽 여백 추가 */
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

.Mylog {
  display: flex; /* Flexbox로 설정 */
  flex-direction: column; /* 세로 방향으로 정렬 */
  align-items: flex-start; /* 왼쪽 정렬 */
  margin-right: 300px; /* 오른쪽 여백 조정 */
  margin-top: 20px; /* 상단 여백 추가 */
}

.delete-button {
  background-color: #426B1F; /* 버튼 배경색 (파란색) */
  color: #fff; /* 텍스트 색상 (흰색) */
  border: none; /* 기본 테두리 제거 */
  border-radius: 5px; /* 둥근 모서리 */
  padding: 5px 10px; /* 버튼 크기 */
  font-size: 1rem; /* 글씨 크기 */
  cursor: pointer; /* 커서 모양 변경 */
  transition: background-color 0.3s, transform 0.2s; /* 호버 및 클릭 시 애니메이션 */
  margin-bottom: 10px; /* 버튼 간 간격 */
}

.delete-button:hover {
  background-color: #71B636 !important; /* 호버 시 색상 변경 */
}

.delete-button:active {
  transform: scale(0.95); /* 클릭 시 버튼 살짝 축소 */
}

.exercise-button {
  background-color: #426B1F; /* 버튼 배경색 (파란색) */
  color: #fff; /* 텍스트 색상 (흰색) */
  border: none; /* 기본 테두리 제거 */
  border-radius: 5px; /* 둥근 모서리 */
  padding: 5px 10px; /* 버튼 크기 */
  font-size: 1rem; /* 글씨 크기 */
  cursor: pointer; /* 커서 모양 변경 */
  transition: background-color 0.3s, transform 0.2s; /* 호버 및 클릭 시 애니메이션 */
  margin-bottom: 10px; /* 버튼 간 간격 */
  margin-right: 200px;
}

.exercise-button:hover {
  background-color: #71B636 !important; /* 호버 시 색상 변경 */
}

.exercise-button:active {
  transform: scale(0.95); /* 클릭 시 버튼 살짝 축소 */
}

.exercise-logs {
  display: flex;
  flex-direction: column; /* 수직으로 정렬 */
  width: 100%; /* 전체 너비 사용 */
  margin-top: 10px; /* 버튼과 운동 로그 간의 간격을 유지 */
}

.exercise-log {
  width: 100%; /* 전체 너비 사용 */
  margin-bottom: 15px; /* 각 운동 로그 간의 간격 */
  padding: 10px; /* 내부 여백 */
  border: 1px solid #ddd; /* 경계선 추가 */
  border-radius: 5px; /* 둥근 모서리 */
  background-color: #f9f9f9; /* 배경색 추가 */
}

.input-group {
  display: flex;
  align-items: center; /* 레이블과 입력 필드 수직 정렬 */
  margin-bottom: 10px; /* 각 그룹 간의 간격 */
}

.input-group label {
  margin-right: 10px; /* 레이블과 입력 필드 간의 간격 */
  width: 120px; /* 레이블의 고정 너비 */
}

.input-group input {
  flex: 1; /* 입력 필드가 가로로 가능한 공간을 차지하도록 설정 */
}
</style>