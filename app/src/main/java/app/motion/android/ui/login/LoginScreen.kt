package app.motion.android.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.motion.android.R
import app.motion.android.ui.Main
import app.motion.android.ui.theme.MotionAppTheme
import app.motion.android.ui.theme.playwriteUsModern
import org.koin.compose.viewmodel.koinViewModel

@Composable // Digunakan untuk mendeklarasikan sebuah fungsi composable
fun LoginScreen(
    navController: NavHostController
) {
    val viewModel: LoginViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        val currentUserEmail = viewModel.getCurrentUserEmail()

        if (currentUserEmail != null) {
            navController.navigate(Main(currentUserEmail))
        }
    }

    uiState.errorMessage?.let { errorMessage ->
        AlertDialog(
            onDismissRequest = viewModel::clearErrorMessage,
            confirmButton = {
                TextButton(
                    onClick = viewModel::clearErrorMessage
                ) {
                    Text("Close")
                }
            },
            title = {
                Text("Error")
            },
            text = {
                Text(errorMessage)
            }
        )
    }

    if (uiState.isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFFFFFF)),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        // Column digunakan untuk menyusun content secara vertikal
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFFFFFF))
                .verticalScroll(rememberScrollState())
                .systemBarsPadding() // Digunakan untuk memberikan padding terhadap content agar tidak overlap dengan system UI
                .imePadding() // Digunakan untuk memberikan padding terhadap content agar tidak overlap dengan keyboard
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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
                value = uiState.username,
                onValueChange = viewModel::changeUsername,
                label = {
                    Text("Username")
                },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(12.dp))
            TextField(
                value = uiState.password,
                onValueChange = viewModel::changePassword,
                label = {
                    Text("Password")
                },
                // Digunakan untuk menampilkan icon pada akhir TextField
                trailingIcon = {
                    IconButton(
                        onClick = viewModel::togglePasswordVisibility
                    ) {
                        Icon(
                            painter = if (uiState.isPasswordVisible) {
                                painterResource(R.drawable.ic_visibility_off)
                            } else {
                                painterResource(R.drawable.ic_visibility)
                            },
                            contentDescription = if (uiState.isPasswordVisible) {
                                "Hide password"
                            } else {
                                "Show password"
                            }
                        )
                    }
                },
                // Digunakan untuk mengubah visualisasi password menjadi bintang (*)
                visualTransformation = if (uiState.isPasswordVisible) {
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
                        viewModel.login(
                            onSuccess = {
                                navController.navigate(Main(uiState.username))
                            }
                        )
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
                        viewModel.register(
                            onSuccess = {
                                navController.navigate(Main(uiState.username))
                            }
                        )
                    }
                )
            }
        }
    }
}

@Composable
@Preview
private fun LoginScreenPreview() {
    MotionAppTheme {
        LoginScreen(
            navController = rememberNavController()
        )
    }
}