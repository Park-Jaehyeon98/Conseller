import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.project.viewmodels.MyAuctionViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InquiryPage(navController: NavHostController, opponentIdx: String?) {
    val myAuctionViewModel: MyAuctionViewModel = hiltViewModel()
    val error by myAuctionViewModel.error.collectAsState()
    val inquiryNavi by myAuctionViewModel.inquiryNavi.collectAsState()
    // 상태 관리를 위한 변수들
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) } // 취소하기 물어보기

    var showSnackbar by remember { mutableStateOf(false) } // 에러처리스낵바
    var snackbarText by remember { mutableStateOf("") }

    // 신고타입
    var type = 1
    if(opponentIdx=="0"){
        type = 4
    }

    LaunchedEffect(error) {
        if (error != null) {
            showSnackbar = true
            snackbarText = error!!
        }
    }
    LaunchedEffect(showSnackbar) {
        if (showSnackbar) {
            delay(5000)
            showSnackbar = false
        }
    }
    LaunchedEffect(inquiryNavi) {
        if (inquiryNavi != null) {
            navController.navigateUp()
        }
    }

    // 레이아웃 구성
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (showSnackbar) {
            Snackbar(
            ) {
                Text(text = snackbarText, style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                )
            }
        }
        Text(text = "제목", style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold))
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "제목을 입력해주세요.") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "내용", style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold))
        TextField(
            value = content,
            onValueChange = { content = it },
            modifier = Modifier.fillMaxWidth().height(200.dp),
            placeholder = { Text(text = "내용을 입력해주세요.") }
        )

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            SelectButton(
                text = if (opponentIdx == null) "문의하기" else "신고하기",
                onClick = {
                    myAuctionViewModel.resistInquiry(title,content,type,opponentIdx!!.toLong())
                }
            )
            Spacer(modifier = Modifier.width(16.dp))

            SelectButton(
                text = "뒤로가기",
                onClick = { showDialog = true }
            )
        }
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("알림") },
                text = { Text("작성하시던 글을 삭제하시고 뒤로 가시겠습니까?") },
                confirmButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text("취소")
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        navController.navigateUp()
                        showDialog = false
                    }) {
                        Text("확인")
                    }
                }
            )
        }
    }
}