package sk.sandeep.readerappcompose.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import sk.sandeep.readerappcompose.model.MBook


@Composable
fun ListCard(
    book: MBook = MBook(
        id = "abcd",
        title = "Running",
        authors = "Me and You",
        notes = "Hello World"
    ),
    onPressDetails: (String) -> Unit = {}
) {

    val context = LocalContext.current
    val resources = context.resources
    val displayMetrics = resources.displayMetrics

    val screenWidth = displayMetrics.widthPixels / displayMetrics.density
    val spacing = 10.dp
    Card(
        shape = RoundedCornerShape(29.dp),
        backgroundColor = Color.White,
        elevation = 6.dp,
        modifier = Modifier
            .padding(16.dp)
            .height(242.dp)
            .width(202.dp)
            .clickable {
                onPressDetails.invoke(book.title.toString())
            }
    ) {

        Column(
            modifier = Modifier.width(screenWidth.dp - (spacing * 2)),
            horizontalAlignment = Alignment.Start
        ) {
            Row(horizontalArrangement = Arrangement.Center) {
                Image(
                    painter = rememberImagePainter(data = "https://assets.alexandria.raywenderlich.com/books/aa/images/6ed47f2833ff6684a1467d7ec76f8ab1adb5f59b8575780cbd930991a6b5c240/w594.png"),
                    contentDescription = "",
                    modifier = Modifier
                        .width(100.dp)
                        .height(140.dp)
                        .padding(4.dp)
                )
                Spacer(modifier = Modifier.width(50.dp))
                Column(
                    modifier = Modifier.padding(top = 25.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Rounded.FavoriteBorder,
                        contentDescription = "fav Icon",
                        modifier = Modifier.padding(bottom = 1.dp)
                    )
                    BookRating(score = 3.5)
                }
            }
            Text(
                text = book.title.toString(), modifier = Modifier.padding(4.dp),
                fontWeight = FontWeight.Bold, maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            book.authors?.let {
                Text(
                    text = it, modifier = Modifier.padding(4.dp),
                    style = MaterialTheme.typography.caption,
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.Bottom
        ) {
            RoundedButton(
                label = "Reading",
                radius = 70
            )
        }
    }
}
