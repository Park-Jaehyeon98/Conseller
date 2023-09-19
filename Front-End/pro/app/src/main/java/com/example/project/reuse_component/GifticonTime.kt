import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.project.viewmodels.GifticonData

@Composable
fun FormattedDateText(gifticonTime: String, prefix: String, modifier: Modifier = Modifier) {
    val year = gifticonTime.substring(0, 4).takeLast(2)   // YYYY
    val month = gifticonTime.substring(4, 6)              // MM
    val day = gifticonTime.substring(6, 8)                // DD
    val hour = gifticonTime.substring(8, 10)              // HH
    val minute = gifticonTime.substring(10, 12)           // MM

    val formattedTime = "${year}년 ${month}월 ${day}일"
    Text("$prefix : $formattedTime", modifier = modifier)
}