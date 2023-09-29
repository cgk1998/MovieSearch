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
fun RegisterScreen(
    navController: NavController,
    viewModel: FavViewModel
) {
    val scope = rememberCoroutineScope()
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
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
                Text(text = "REGISTER", fontWeight = FontWeight.Bold)
            }
        }

        item {
            Column() {
                OutlinedTextField(
                    value = name, onValueChange = {
                        name = it
                    },
                    label = { Text(text = "Name") },
                    placeholder = { Text(text = "Enter name") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )

                OutlinedTextField(
                    value = email, onValueChange = {
                        email = it
                    },
                    placeholder = { Text(text = "Enter email") },
                    label = { Text(text = "Email") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )

                OutlinedTextField(
                    value = password, onValueChange = {
                        password = it
                    },
                    placeholder = { Text(text = "Enter password") },
                    label = { Text(text = "Password") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )
            }
        }

        item {
            Button(
                onClick = {
                    isDialog = true
                    scope.launch(Dispatchers.Main) {
                        viewModel.registerUser(
                            User(
                                name,
                                email,
                                password
                            )
                        ).collect {
                            isDialog = false
                            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                            if(it == "otp sent"){
                                viewModel.setEmailText(email)
                                viewModel.setPassText(password)
                                navController.navigate(
                                    route = Screen.Confirm.route
                                )
                            }
                        }
                    }
                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(text = "Register", modifier = Modifier.padding(vertical = 10.dp))
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