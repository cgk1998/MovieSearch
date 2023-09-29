package edu.uchicago.gerber.favs.presentation.screens.details


import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import edu.uchicago.gerber.favs.presentation.navagation.Screen
import edu.uchicago.gerber.favs.presentation.viewmodels.FavViewModel
import edu.uchicago.gerber.favs.presentation.widgets.BottomNavigationBar
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


@Composable
fun CommentScreen(navController: NavController, favViewModel: FavViewModel) {


    val activity = (LocalContext.current as? Activity)
    var comment by rememberSaveable { mutableStateOf("") };

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) },
        topBar = {
            TopAppBar(
                backgroundColor = Color.Transparent,
                elevation = 1.dp,

                ) {
                Text(
                    text = "Comment On the Movie",
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )

            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 0.dp, 0.dp, 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = comment,
                onValueChange = { comment = it },
                modifier = Modifier
                    .padding(10.dp, 0.dp)
                    .size(width = 280.dp, height = 150.dp),
                label = { Text("Commennt") },
                maxLines = 5
            )

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                modifier =
                Modifier
                    .padding(10.dp, 0.dp)
                    .size(width = 100.dp, height = 40.dp),


                onClick = {
                    val movieDB = favViewModel.transformToDB(favViewModel.movie.value)
                    movieDB.comment = comment
                    favViewModel.comment(favViewModel.movie.value.id!!, movieDB)

                    Toast.makeText(activity, "Comment Successfully.", Toast.LENGTH_LONG).show()
                    navController.popBackStack()


                },

                colors =
                ButtonDefaults.buttonColors(backgroundColor = Color.Blue)

            ) {

                Text(text = "Send", color = Color.White)

            }




            Spacer(modifier = Modifier.height(10.dp))


        }
    }
}


