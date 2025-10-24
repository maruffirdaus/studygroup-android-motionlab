package app.motion.android.ui.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.motion.android.R
import app.motion.android.ui.theme.playwriteUsModern

@Composable // Digunakan untuk mendeklarasikan sebuah fungsi composable
@Preview // Digunakan untuk menampilkan preview dari sebuah fungsi composable
fun LoginScreen() {
    val context = LocalContext.current // Digunakan untuk mendapatkan Context dari sebuah composable, Context merupakan sebuah objek yang merepresentasikan informasi tentang lingkungan aplikasi saat ini

    // Column digunakan untuk menyusun content secara vertikal
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF))
            .systemBarsPadding() // Digunakan untuk memberikan padding terhadap content agar tidak overlap dengan system UI
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var username by remember { mutableStateOf("") } // Berisi nilai default dari username
        var password by remember { mutableStateOf("") } // Berisi nilai default dari password
        var isPasswordVisible by remember { mutableStateOf(false) }

        val users = mapOf(
            "admin" to "admin",
            "member" to "member"
        )

        Text(
            text = "Motion Laboratory",
            fontSize = 36.sp,
            fontWeight = FontWeight.Light,
            fontFamily = playwriteUsModern
        )
        Spacer(Modifier.height(64.dp))
        Image(
            painter = painterResource(R.drawable.img_motion),
            contentDescription = "Motion logo",
            modifier = Modifier.size(256.dp)
        )
        Spacer(Modifier.height(64.dp))
        TextField(
            value = username,
            onValueChange = { value ->
                username = value // Setiap kali value dari TextField berubah, akan mengubah state username
            },
            label = {
                Text("Username")
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(12.dp))
        TextField(
            value = password,
            onValueChange = { value ->
                password = value
            },
            label = {
                Text("Password")
            },
            // Digunakan untuk menampilkan icon pada akhir TextField
            trailingIcon = {
                IconButton(
                    onClick = {
                        isPasswordVisible = !isPasswordVisible // Mengubah state isPasswordVisible saat icon ditekan
                    }
                ) {
                    Icon(
                        painter = if (isPasswordVisible) {
                            painterResource(R.drawable.ic_visibility_off)
                        } else {
                            painterResource(R.drawable.ic_visibility)
                        },
                        contentDescription = if (isPasswordVisible) {
                            "Hide password"
                        } else {
                            "Show password"
                        }
                    )
                }
            },
            // Digunakan untuk mengubah visualisasi password menjadi bintang (*)
            visualTransformation = if (isPasswordVisible) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(
            modifier = Modifier.height(64.dp)
        )
        // Row digunakan untuk menyusun content secara horizontal
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    if (username in users && password == users[username]) {
                        Toast
                            .makeText(
                                context,
                                "Welcome $username",
                                Toast.LENGTH_SHORT
                            )
                            .show()
                    } else {
                        Toast
                            .makeText(
                                context,
                                "Invalid username or password",
                                Toast.LENGTH_SHORT
                            )
                            .show()
                    }
                },
                // Digunakan untuk mengubah warna background dan content dari button
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF000000),
                    contentColor = Color(0xFFFFFFFF)
                ),
                modifier = Modifier.weight(1f)
            ) {
                Text("Login")
            }
            Spacer(Modifier.width(8.dp))
            RegisterButton(
                onClick = {
                    Toast
                        .makeText(
                            context,
                            "Register button clicked",
                            Toast.LENGTH_SHORT
                        )
                        .show()
                }
            )
        }
    }
}