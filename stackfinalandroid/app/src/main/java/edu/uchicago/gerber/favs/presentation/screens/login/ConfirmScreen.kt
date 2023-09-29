package edu.uchicago.gerber.favs.presentation.screens.login



import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import edu.uchicago.gerber.favs.data.models.User
import edu.uchicago.gerber.favs.presentation.navagation.Screen
import edu.uchicago.gerber.favs.presentation.viewmodels.FavViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@Composable
fun ConfirmScreen(
    navController: NavController,
    viewModel: FavViewModel
) {
    val scope = rememberCoroutineScope()

    var otp by remember { mutableStateOf("") }

    var isDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current

    LazyColumn {

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 30.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "OTP", fontWeight = FontWeight.Bold)
            }
        }

        item {
            Column() {
                OutlinedTextField(
                    value = otp, onValueChange = {
                        otp = it
                    },
                    placeholder = { Text(text = "Enter otp") },
                    label = { Text(text = "OTP") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    ClickableText(
                        text = buildAnnotatedString { append("Resend Otp") },
                        style = TextStyle(
                            fontSize = 12.sp,
                            color = Color.Gray,
                        )
                    ) {
                        isDialog = true
                        scope.launch(Dispatchers.Main) {
                            viewModel.resendOtp.collect {
                                isDialog = false
                                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
        }

        item {
            Button(
                onClick = {
                    isDialog = true
                    scope.launch(Dispatchers.Main) {
                        viewModel.verifyOtp(
                            User(email = viewModel.emailText.value, password = viewModel.passwordText.value),
                            otp
                        ).collect {
                            isDialog = false
                            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                            if (it == "otp verify")
                            {

                                navController.navigate(
                                    route = Screen.Search.route
                                )
                            }

                        }
                    }
                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(text = "Verify Otp", modifier = Modifier.padding(vertical = 10.dp))
            }
        }


    }

    if (isDialog) {
        AlertDialog(
            onDismissRequest = { },
            buttons = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
            },
            backgroundColor = Color.Transparent
        )
    }
}