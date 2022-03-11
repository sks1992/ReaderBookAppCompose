package sk.sandeep.readerappcompose.ui.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import sk.sandeep.readerappcompose.components.FABContent
import sk.sandeep.readerappcompose.components.ListCard
import sk.sandeep.readerappcompose.components.ReaderAppBar
import sk.sandeep.readerappcompose.components.TitleSection
import sk.sandeep.readerappcompose.model.MBook
import sk.sandeep.readerappcompose.navigation.ReaderScreens

@Composable
fun Home(navController: NavController) {
    Scaffold(
        topBar = {
            ReaderAppBar(
                title = "A.Reader",
                navController = navController
            )
        },
        floatingActionButton = {
            FABContent {
                navController.navigate(ReaderScreens.SearchScreen.name)
            }
        },
        content = {
            Surface(modifier = Modifier.fillMaxSize()) {
                HomeContent(navController = navController)
            }
        }
    )
}


@Composable
fun HomeContent(navController: NavController) {

    val email = FirebaseAuth.getInstance().currentUser?.email
    val currentUserName = if (!email.isNullOrEmpty())
        email.split("@")[0] else "N/A"

    val listOfBooks = listOf(
        MBook(
            id = "acbs", notes = null, authors = "All of us", title = "Hello Again"
        ),
        MBook(
            id = "acbs", notes = null, authors = "All of us", title = "Hello Again"
        ),
        MBook(
            id = "acbs", notes = null, authors = "All of us", title = "Hello Again"
        ),
        MBook(
            id = "acbs", notes = null, authors = "All of us", title = "Hello Again"
        ),
        MBook(
            id = "acbs", notes = null, authors = "All of us", title = "Hello Again"
        ),
        MBook(
            id = "acbs", notes = null, authors = "All of us", title = "Hello Again"
        ),
        MBook(
            id = "acbs", notes = null, authors = "All of us", title = "Hello Again"
        ),
        MBook(
            id = "acbs", notes = null, authors = "All of us", title = "Hello Again"
        ),
        MBook(
            id = "acbs", notes = null, authors = "All of us", title = "Hello Again"
        ),
        MBook(
            id = "acbs", notes = null, authors = "All of us", title = "Hello Again"
        ),
        MBook(
            id = "acbs", notes = null, authors = "All of us", title = "Hello Again"
        )


    )
    Column(
        modifier = Modifier.padding(2.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier.align(alignment = Alignment.Start),
        ) {
            TitleSection(label = "Your reading \n" + "activity right now")
            Spacer(modifier = Modifier.fillMaxWidth(0.7f))
            Column {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "Profile",
                    modifier = Modifier
                        .clickable {
                            navController.navigate(ReaderScreens.ReaderStatsScreen.name)
                        }
                        .size(45.dp),
                    tint = MaterialTheme.colors.secondaryVariant
                )
                Text(
                    text = currentUserName,
                    modifier = Modifier.padding(2.dp),
                    style = MaterialTheme.typography.overline,
                    color = Color.Red,
                    fontSize = 15.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Clip
                )
                Divider()
            }
        }
        ReadingRightNowArea(books = listOf(), navController = navController)
        TitleSection(label = "Reading List")
        BookListArea(listOfBooks = listOfBooks, navController = navController)
    }
}

@Composable
fun BookListArea(listOfBooks: List<MBook>, navController: NavController) {

    HorizontalScrollableComponent(listOfBooks) {

    }
}

@Composable
fun HorizontalScrollableComponent(
    listOfBooks: List<MBook>,
    onCardPressed: (String) -> Unit
) {
    val scrollableState = rememberScrollState()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(280.dp)
            .horizontalScroll(scrollableState)
    ) {
        for (book in listOfBooks) {
            ListCard(book = book) {
                onCardPressed(it)
            }
        }
    }
}


@Composable
fun ReadingRightNowArea(books: List<MBook>, navController: NavController) {
    ListCard()
}

