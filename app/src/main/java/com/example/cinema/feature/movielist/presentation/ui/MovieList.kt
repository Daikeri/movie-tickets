package com.example.cinema.feature.movielist.presentation.ui

import android.app.appsearch.SearchResults
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExpandedFullScreenSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.ListItem
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.SearchBarDefaults.inputFieldColors
import androidx.compose.material3.SearchBarValue
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberSearchBarState
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cinema.R
import com.example.cinema.ui.theme.Black
import com.example.cinema.ui.theme.Blue
import com.example.cinema.ui.theme.DeepBlue
import com.example.cinema.ui.theme.OffWhite
import com.example.cinema.ui.theme.White
import kotlinx.coroutines.launch


@Composable
fun MovieListScreen(modifier: Modifier = Modifier) {
    Box(modifier = Modifier.then(modifier)) {

    }
}


@Preview(
    device = "id:pixel_7", showBackground = false, showSystemUi = true
)
@Composable
fun PreviewMovieListScreen() {
    LazyColumn(
        modifier = Modifier
            .safeDrawingPadding()
            .fillMaxSize()
            .background(White)
            .padding(horizontal = 20.dp)
    ) {
        item {
            AccountRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
            )
        }

        item {
            SearchRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp)
                    .height(52.dp)
            )
        }

        items(7) {
            MovieCard(modifier = Modifier.padding(vertical = 10.dp))
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchRow(modifier: Modifier, color: Color = Blue) {
    val searchBarState = rememberSearchBarState()
    var expanded by rememberSaveable { mutableStateOf(false) }
    val textFieldState = rememberTextFieldState()

    Row(
        modifier = modifier,
    ) {
        SearchBar(
            state = searchBarState,
            modifier = Modifier
                .weight(0.65f)
                .padding(end = 7.dp),
            inputField = {
                SearchBarDefaults.InputField(
                    colors = inputFieldColors(
                        unfocusedContainerColor = OffWhite,
                        focusedContainerColor = OffWhite,
                        disabledContainerColor = OffWhite
                    ),
                    query = textFieldState.text.toString(),
                    onQueryChange = { textFieldState.edit { replace(0, length, it) } },
                    onSearch = {
                        expanded = false
                    },
                    expanded = expanded,
                    onExpandedChange = {
                        expanded = it
                    },
                    placeholder = { Text("Search", fontSize = 16.sp, color = color) }
                )
            }
        )

        IconButton(
            modifier = Modifier
                .weight(0.1f)
                .height(52.dp)
            ,
            colors = IconButtonDefaults.iconButtonColors(containerColor = OffWhite),
            onClick = {  }
        ) {
            Icon(
                modifier = Modifier.size(27.dp),
                painter = painterResource(R.drawable.settings),
                contentDescription = null,
                tint = color
            )
        }

    }
}


@Composable
fun AccountRow(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        IconButton(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .height(52.dp)
                .width(52.dp)
            ,
            colors = IconButtonDefaults.iconButtonColors(containerColor = OffWhite),
            onClick = {  }
        ) {
            Icon(
                modifier = Modifier.size(27.dp),
                painter = painterResource(R.drawable.fox),
                contentDescription = null,
                tint = Color.Unspecified
            )
        }
    }
}

@Composable
fun MovieCard(modifier: Modifier = Modifier, headColor: Color = Black, genreColor: Color = DeepBlue) {
    ElevatedCard(
        onClick = {},
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = OffWhite)
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(R.drawable.test_image),
                contentDescription = null,
                modifier = Modifier.clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.FillBounds
            )

            Column {
                Text(
                    text = "The Outlaw Josey Wales",
                    color = headColor,
                    fontSize = 20.sp,
                    overflow = TextOverflow.StartEllipsis,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(top = 25.dp)
                        .padding(horizontal = 20.dp)
                )

                Text(
                    text = "Drama, Western",
                    color = genreColor,
                    fontSize = 16.sp,
                    overflow = TextOverflow.StartEllipsis,
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                )
            }
        }

    }
}


@RequiresApi(Build.VERSION_CODES.S)
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun Some() {
    val searchBarState = rememberSearchBarState()
    val textFieldState = rememberTextFieldState()
    val scope = rememberCoroutineScope()

    val inputField =
        @Composable {
            SearchBarDefaults.InputField(
                modifier = Modifier,
                searchBarState = searchBarState,
                textFieldState = textFieldState,
                onSearch = { scope.launch { searchBarState.animateToCollapsed() } },
                placeholder = { Text("Search...") },
                leadingIcon = {
                    if (searchBarState.currentValue == SearchBarValue.Expanded) {
                        TooltipBox(
                            positionProvider =
                            TooltipDefaults.rememberTooltipPositionProvider(

                            ),
                            tooltip = { PlainTooltip { Text("Back") } },
                            state = rememberTooltipState(),
                        ) {
                            IconButton(
                                onClick = { scope.launch { searchBarState.animateToCollapsed() } }
                            ) {
                                Icon(
                                    Icons.AutoMirrored.Default.ArrowBack,
                                    contentDescription = "Back",
                                )
                            }
                        }
                    } else {
                        Icon(Icons.Default.Search, contentDescription = null)
                    }
                },
                trailingIcon = { Icon(Icons.Default.MoreVert, contentDescription = null) },
            )
        }

    SearchBar(state = searchBarState, inputField = inputField)
    ExpandedFullScreenSearchBar(state = searchBarState, inputField = inputField) {

    }
}




