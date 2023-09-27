import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

@Composable
fun UserDetailDialog(
    userImageUrl: String?,
    userNickname: String?,
    userDeposit: Long?,
    onDismiss: () -> Unit,
    onReportClick: () -> Unit,
    onMessageClick: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = "유저 정보")
        },
        text = {
            Column {
                userImageUrl?.let {
                    val imagePainter = rememberAsyncImagePainter(model = it)
                    Image(
                        painter = imagePainter,
                        contentDescription = null,
                        modifier = Modifier.size(100.dp).align(Alignment.CenterHorizontally),
                        contentScale = ContentScale.Crop,
                    )
                }
                Text("닉네임: $userNickname", fontSize = 18.sp)
                Text("보증금: $userDeposit 원", fontSize = 18.sp)
            }
        },
        dismissButton = {
            SelectButton(text = "신고하기", onClick = onReportClick)
        },
        confirmButton = {
            SelectButton(text = "메세지 보내기(개발중)", onClick = onMessageClick)
        }
    )
}