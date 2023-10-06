import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project.ui.theme.BrandColor1  // 이 부분은 BrandColor1의 위치에 따라 수정해야 할 수 있습니다.

@Composable
fun PaginationControls(
        totalItems: Int,
        currentPage: Int,
        itemsPerPage: Int,
        onPageSelected: (Int) -> Unit
) {
        val totalPages = (totalItems + itemsPerPage - 1) / itemsPerPage
        val maxPagesToShow = 5
        val startPage = ((currentPage - 1) / maxPagesToShow) * maxPagesToShow + 1
        val endPage = minOf(startPage + maxPagesToShow - 1, totalPages)

        Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
        ) {
                // 이전 페이지 버튼
                if (startPage > 1) {
                        Button(
                                onClick = { onPageSelected(startPage - 1) },
                                colors = ButtonDefaults.buttonColors(Color.Transparent,
                                        contentColor = Color.White
                                )
                        ) {
                                Text("이전", fontSize = 13.sp)
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                }

                // 페이지 번호
                for (i in startPage..endPage) {
                        Button(
                                onClick = { onPageSelected(i) },
                                colors = ButtonDefaults.buttonColors(Color.Transparent,
                                        contentColor = if (i == currentPage) Color.Gray else Color.White
                                )
                        ) {
                                Text("$i", fontSize = 13.sp)
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                }

                // 다음 페이지 버튼
                if (endPage < totalPages) {
                        Button(
                                onClick = { onPageSelected(endPage + 1) },
                                colors = ButtonDefaults.buttonColors(Color.Transparent,
                                        contentColor = Color.White
                                )
                        ) {
                                Text("다음", fontSize = 13.sp)
                        }
                }
        }
}
