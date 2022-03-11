package sk.sandeep.readerappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import sk.sandeep.readerappcompose.navigation.ReaderNavigation
import sk.sandeep.readerappcompose.ui.theme.ReaderAppComposeTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ReaderAppComposeTheme {
                ReaderApp()

                /*
                        //Check FireBase
                        val db = FirebaseFirestore.getInstance()
                        val user: MutableMap<String, Any> = HashMap()

                        user["firstName"] = "Sandeep"
                        user["lastName"] = "Kumar"

                        db.collection("users")
                            .add(user)
                            .addOnSuccessListener {
                                Log.d("FB", "onCreate: ${it.id}")
                            }
                            .addOnFailureListener {
                                 Log.d("FB", "onFailure: $it")
                            }
                */


            }
        }
    }
}


@Composable
fun ReaderApp() {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ReaderNavigation()
        }
    }
}