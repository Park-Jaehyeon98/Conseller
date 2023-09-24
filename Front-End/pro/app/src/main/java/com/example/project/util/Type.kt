import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun FormattedDateText(dateString: String, modifier: Modifier = Modifier, fontSize: TextUnit = 14.sp, fontWeight: FontWeight = FontWeight.Normal) {
        val formattedDate = formatToYearMonthDay(dateString)
        Text("유효기간 : $formattedDate", modifier = modifier, fontSize = fontSize, fontWeight = fontWeight)
}

private fun formatToYearMonthDay(dateString: String): String {
        // 입력 문자열 검사: 입력이 예상대로 오는지 (길이, 숫자 등) 확인
        if (dateString.length != 14 || !dateString.all { it.isDigit() }) {
                return "Invalid Date" // 유효하지 않은 날짜 문자열인 경우
        }

        val year = dateString.substring(0, 2)
        val month = dateString.substring(2, 4)
        val day = dateString.substring(4, 6)

        return "${year}년 ${month}월 ${day}일"
}
