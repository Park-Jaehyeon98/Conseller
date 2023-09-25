import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project.ui.theme.logocolor

@Composable
fun SelectButton(
        text: String,
        modifier: Modifier = Modifier,
        onClick: () -> Unit
) {
        Button(
                onClick = onClick,
                modifier = modifier,
                colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White,
                        containerColor = logocolor
                ),
                shape = RoundedCornerShape(5.dp)
        ) {
                Text(text = text, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
}