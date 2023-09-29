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
fun LoginScreen(
    navController: NavController,
    viewModel: FavViewModel
) {
    val scope = rememberCoroutineScope()

    var loginEmail by remember { mutableStateOf("") }
    var loginPassword by remember { mutableStateOf("") }

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
                Text(text = "Login", fontWeight = FontWeight.Bold)
            }
        }

        item {
            Column() {
                OutlinedTextField(
                    value = loginEmail, onValueChange = {
                        loginEmail = it
                    },
                    placeholder = { Text(text = "Enter email") },
                    label = { Text(text = "Email") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )

                OutlinedTextField(
                    value = loginPassword, onValueChange = {
                        loginPassword = it
                    },
                    placeholder = { Text(text = "Enter password") },
                    label = { Text(text = "Password") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                ) {
                    ClickableText(
                        text = buildAnnotatedString { append("Sign Up") },
                        style = TextStyle(
                            fontSize = 12.sp,
                            color = Color.Gray,
                        )
                    ) {
                        navController.navigate(
                            route = Screen.Register.route
                        )
//                        context.startActivity(Intent(context, AnotherActivity::class.java))
                    }
                }
            }
        }

        item {
            Button(
                onClick = {
                    isDialog = true
                    scope.launch(Dispatchers.Main) {
                        viewModel.login(
                            User(email = loginEmail, password = loginPassword)
                        ).collect {

                            isDialog = false
                            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                            if (it == "Login Successful...!!")
                            {
                                viewModel.setEmailText(loginEmail)
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
                Text(text = "Login", modifier = Modifier.padding(vertical = 10.dp))
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