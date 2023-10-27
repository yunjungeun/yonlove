 document.addEventListener("DOMContentLoaded", function () {
//지정된 연도 및 월에 대한 달력 데이터를 생성하는 함수를 정의합니다

    function generateCalendar(year, month) {
        const firstDay = new Date(year, month, 1).getDay();
        const lastDate = new Date(year, month + 1, 0).getDate();
        const days = [];
    let day = 1;
        for (let i = 0; i < 6; i++) {
            const week = { day: [] };
        for (let j = 0; j < 7; j++) {
            if (i === 0 && j < firstDay) {
                    week.day.push('');
                } else if (day > lastDate) {
            week.day.push('');
    } else {
        week.day.push(day);
            day++;
       }
     }
            days.push(week);
        }

    return { year, days };
    }

// 월 이름 정의
    const monthNames = [
            "January", "February", "March", "April",
            "May", "June", "July", "August",
            "September", "October", "November", "December"
          ];

// 현재 날짜 가져오기
    const currentDate = new Date();
    const currentYear = currentDate.getFullYear();

// Mustache 템플릿 컴파일
    const template = document.getElementById("calendar-template").innerHTML;


// 일년 전체 달력 렌더링
    let fullYearCalendar = '';
        for (let month = 0; month < 12; month++) {
        const calendarData = generateCalendar(currentYear, month);

        fullYearCalendar += Mustache.render(template, {
            year: currentYear,
            month: monthNames[month],
            days: calendarData.days,
       });
    }

    document.getElementById("calendar").innerHTML = fullYearCalendar;
     });
