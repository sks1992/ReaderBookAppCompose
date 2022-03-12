package sk.sandeep.readerappcompose.ui.screens.search

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import sk.sandeep.readerappcompose.components.InputField
import sk.sandeep.readerappcompose.components.ReaderAppBar
import sk.sandeep.readerappcompose.model.Item
import sk.sandeep.readerappcompose.navigation.ReaderScreens

@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: ReaderBookSearchViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            ReaderAppBar(
                title = "Search Book",
                icon = Icons.Default.ArrowBack,
                navController = navController,
                showProfile = false,
                onBackArrowClicked = {
                    navController.navigate(ReaderScreens.ReaderHomeScreen.name)
                }
            )
        },
        content = {
            Surface {
                Column {
                    SearchForm(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) { searchQuery ->
                        viewModel.searchBooks(searchQuery)
                        Log.d("TAG", "SearchScreen:$it ")
                    }
                    Spacer(modifier = Modifier.height(13.dp))
                    BookList(navController, viewModel)
                }
            }
        }
    )
}

@Composable
fun BookList(navController: NavController, viewModel: ReaderBookSearchViewModel = hiltViewModel()) {

    val listOfBooks = viewModel.list
    if (viewModel.isLoading) {
        LinearProgressIndicator()
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
        ) {
            items(listOfBooks) { books ->
                BookRow(books, navController)

            }
        }
    }
}

@Composable
fun BookRow(books: Item, navController: NavController) {
    Card(modifier = Modifier
        .clickable {
            navController.navigate(ReaderScreens.DetailScreen.name + "/${books.id}")
        }
        .fillMaxWidth()
        .height(100.dp)
        .padding(3.dp),
        shape = RectangleShape,
        elevation = 7.dp
    ) {
        Row(modifier = Modifier.padding(5.dp), verticalAlignment = Alignment.Top) {
            val imageUrl: String =
                books.volumeInfo.imageLinks.smallThumbnail.ifEmpty { "https://cdn-media-1.freecodecamp.org/images/vR7aWSla12f76YFhyeZ6oracp62orQxC5oVD" }


            Image(
                painter = rememberImagePainter(data = imageUrl),
                contentDescription = "Image Book",
                modifier = Modifier
                    .width(80.dp)
                    .fillMaxHeight()
                    .padding(end = 4.dp)
            )
            Column {
                Text(text = books.volumeInfo.title, overflow = TextOverflow.Ellipsis)
                Text(
                    text = "Author: ${books.volumeInfo.authors}",
                    overflow = TextOverflow.Clip,
                    fontStyle = FontStyle.Italic,
                    style = MaterialTheme.typography.caption
                )

                Text(
                    text = "Date: ${books.volumeInfo.publishedDate}",
                    overflow = TextOverflow.Clip,
                    fontStyle = FontStyle.Italic,
                    style = MaterialTheme.typography.caption
                )

                Text(
                    text = "Category: ${books.volumeInfo.categories}",
                    overflow = TextOverflow.Clip,
                    fontStyle = FontStyle.Italic,
                    style = MaterialTheme.typography.caption
                )
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchForm(
    modifier: Modifier = Modifier,
    loading: Boolean = false,
    hint: String = "Search",
    onSearch: (String) -> Unit = {}
) {
    val searchQueryState = rememberSaveable { mutableStateOf("") }
    val keyBoardController = LocalSoftwareKeyboardController.current
    val valid = remember(searchQueryState.value) {
        searchQueryState.value.trim().isNotEmpty()
    }
    Column {
        InputField(valueState = searchQueryState, labelId = "Search", enabled = true,
            onAction = KeyboardActions {
                if (!valid) return@KeyboardActions
                onSearch(searchQueryState.value.trim())
                searchQueryState.value = ""
                keyBoardController?.hide()
            }
        )
    }
}

/*val listOfBooks = listOf(
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


   )*/