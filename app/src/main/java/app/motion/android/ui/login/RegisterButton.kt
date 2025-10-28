package app.motion.android.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RegisterButton(
    onClick: () -> Unit
) {
    // Box digunakan untuk menyusun content secara stacking
    Box(
        modifier = Modifier
            .width(96.dp)
            .height(48.dp)
            .clip(RoundedCornerShape(20.dp)) // Urutan pemanggilan Modifier berpengaruh
            .background(Color(0xFFF82F1E))
            .border(1.dp, Color(0xFF000000), RoundedCornerShape(20.dp))
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(
                    RoundedCornerShape(
                        topEnd = 20.dp,
                        bottomEnd = 20.dp
                    )
                )
                .background(Color(0x80E9A319))
        )
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(
                    RoundedCornerShape(
                        topEnd = 20.dp,
                        bottomEnd = 20.dp
                    )
                )
                .background(Color(0xFFE9A319))
                .align(Alignment.CenterStart) // Alignment dapat di-override dengan menggunakan Modifier.align()
        )
        Text(
            text = "Register",
            color = Color(0xFFFFFFFF),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
@Preview
// Preview tidak dapat digunakan ketika terdapat parameter yang tidak memiliki default value
private fun RegisterButtonPreview() {
    RegisterButton(
        onClick = {}
    )
}