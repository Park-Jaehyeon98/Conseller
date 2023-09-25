import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.project.viewmodels.GifticonData

@Composable
fun GifticonItem(gifticonData: GifticonData, isSelected: Boolean, onClick: () -> Unit) {
    val backgroundColor = if (isSelected) Color.Green else Color.Transparent

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(backgroundColor)
            .clickable(onClick = onClick),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier.size(130.dp)
        ) {
            val imagePainter = rememberAsyncImagePainter(model = gifticonData.gifticonAllImagName)
            Image(
                painter = imagePainter,
                contentDescription = null,
                modifier = Modifier.size(125.dp),
                contentScale = ContentScale.Crop,
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(text = gifticonData.giftconName, fontWeight = FontWeight.Bold, fontSize = 24.sp)
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = gifticonData.gifticonEndDate, fontSize = 18.sp)
        }
    }
}
