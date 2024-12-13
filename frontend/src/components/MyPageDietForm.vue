<template>
  <div>
    <div class="line-container">
      <h1 class="MyPageLogo">My Diet Log</h1>
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
        <button class="save-button" @click="saveInformation">Save</button>
        
         <div class="user-info">
          <div class="Diet-log">
            <h2 style="color: #426B1F; text-align: center;">Diet Information</h2>
            
            <div class="input-group">
              <strong style="color: #426B1F;">Breakfast :</strong>
              <input
                type="text"
                :value="dietData.breakfast === 0 ? '' : dietData.breakfast"
                @input="dietData.breakfast = $event.target.value"
                name="breakfast"
              />
            </div>
            
            <div class="input-group">
              <strong style="color: #426B1F;">Lunch :</strong>
              <input
                type="text"
                :value="dietData.lunch === 0 ? '' : dietData.lunch"
                @input="dietData.lunch = $event.target.value"
                name="lunch"
              />
            </div>
            
            <div class="input-group">
              <strong style="color: #426B1F;">Dinner:</strong>
              <input
                type="text"
                :value="dietData.dinner === 0 ? '' : dietData.dinner"
                @input="dietData.dinner = $event.target.value"
                name="dinner"
              />
            </div>
          </div>
        </div>
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
const dietData = ref({
  breakfast: '',
  lunch: '',
  dinner: ''
});

// 서버에서 데이터를 가져오는 함수
const fetchBodyData = async (formattedDate) => {
  try {
    const accessToken = localStorage.getItem('accessToken');
    const response = await axios.get(`/myPageDiet/${userId}/${formattedDate}`, {
      headers: {
        Authorization: `Bearer ${accessToken}`
      }
    });
    dietData.value = response.data || { breakfast: '', lunch: '', dinner: ''};
  } catch (error) {
    console.error('Failed to fetch diet data:', error);
  }
};

// 페이지가 로드되었을 때 호출하여 데이터 로드
onMounted(() => {
  const formattedDate = selectedDate.value.toISOString().split('T')[0]; // ISO 형식으로 날짜 변환
  fetchBodyData(formattedDate);
});

const savedDietData = localStorage.getItem('dietData');
if (savedDietData) {
  dietData.value = JSON.parse(savedDietData); // 저장된 데이터를 bodyData로 설정
}

const userId = localStorage.getItem('userId');

const navigateToDate = async (date) => {
  const formattedDate = date.id; // 'id' 속성에서 날짜 추출
  router.push(`/myPageDiet/${userId}/${formattedDate}`);
  if (formattedDate) {
    try {
      const accessToken = localStorage.getItem('accessToken');
      const response = await axios.get(`/myPageDiet/${userId}/${formattedDate}`, {
        headers: {
          Authorization: `Bearer ${accessToken}`
        }
      });
      // 받아온 데이터로 dietData 업데이트, 없으면 빈 값으로 초기화
      dietData.value = response.data || { breakfast: '', lunch: '', dinner: '' };
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
  const today = new Date();
  selectedDate.value = today; // 선택된 날짜를 오늘로 설정
  calendar.value.move(today); // 캘린더를 오늘로 이동

  // 오늘 날짜로 navigateToDate 함수 호출
  navigateToDate({ id: today.toISOString().split('T')[0] }); // ISO 형식으로 날짜를 전달
};

const saveInformation = async () => {
  const formattedDate = selectedDate.value.toISOString().split('T')[0];

  const requestData = {
    ...dietData.value,
    userId: userId,
    date: formattedDate,
  };

  try {
    const accessToken = localStorage.getItem('accessToken');
    const response = await axios.post(`/myPageDiet/${userId}/${formattedDate}`, requestData, {
      headers: {
        Authorization: `Bearer ${accessToken}`
      }
    });
    alert("저장되었습니다.");
    router.push(`/myPageDiet/${userId}/${formattedDate}`);
    console.log(response);
    localStorage.setItem('dietData', JSON.stringify(requestData));
  } catch (error) {
    console.error(error);
  }
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
  display: flex; /* 플렉스 박스 사용 */
  flex-direction: column; /* 세로 방향으로 정렬 */
  align-items: flex-start; /* 내용물 왼쪽 정렬 */
  margin-left: 0; /* 왼쪽 여백을 0으로 설정하여 왼쪽으로 이동 */
}

.Diet-log {
  width: 600px; /* 원하는 너비로 설정 */
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 10px;
  background-color: #f9f9f9;
  margin-right: 100px;
  margin-top: 20px;
  display: flex; /* 플렉스 박스 사용 */
  flex-direction: column; /* 세로 방향으로 정렬 */
  align-items: center; /* 내용물 수평 중앙 정렬 */
}

.input-group {
  display: flex; /* 플렉스 박스를 사용하여 정렬 */
  align-items: center; /* 수직 중앙 정렬 */
  justify-content: center; /* 수평 중앙 정렬 */
  margin-bottom: 15px; /* 각 그룹 사이의 간격 */
  width: 100%; /* 입력 그룹의 너비를 100%로 설정 */
}

.input-group strong {
  color: #426B1F; /* 텍스트 색상 */
  margin-right: 10px; /* 레이블과 입력 필드 사이의 간격 */
  min-width: 100px; /* 레이블의 최소 너비 설정 */
  text-align: right; /* 레이블을 오른쪽 정렬 */
}

.Diet-log input {
  width: 50%; /* 입력 필드 너비를 50%로 설정 */
  padding: 8px;
  border: 1px solid #ddd;
  border-radius: 5px;
  font-size: 1rem;
  box-sizing: border-box; /* 패딩과 테두리를 포함하여 총 너비를 계산 */
}

</style>