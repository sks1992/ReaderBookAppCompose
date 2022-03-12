package sk.sandeep.readerappcompose.ui.screens.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import sk.sandeep.readerappcompose.components.ReaderAppBar
import sk.sandeep.readerappcompose.navigation.ReaderScreens

@Composable
fun BookDetailScreen(navController: NavController, bookId: String) {

    Scaffold(
        topBar = {
            ReaderAppBar(
                title = "Book Details",
                icon = Icons.Default.ArrowBack,
                showProfile = false,
                navController = navController,
                onBackArrowClicked = {
                    navController.navigate(ReaderScreens.SearchScreen.name)
                }
            )
        },
        content = {
            Surface(
                modifier = Modifier
                    .padding(3.dp)
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier.padding(top = 12.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Book id :$bookId")
                }
            }
        }
    )


}