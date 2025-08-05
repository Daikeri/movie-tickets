package com.example.cinema.feature.movielist.presentation.ui

import androidx.compose.foundation.background
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
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSearchBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cinema.R
import com.example.cinema.ui.theme.OffWhite
import com.example.cinema.ui.theme.White


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
    Column (
        modifier = Modifier
            .safeDrawingPadding()
            .fillMaxSize()
            .background(White)
    ) {
        AccountRow(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .padding(horizontal = 10.dp)

        )
        SearchRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .padding(top = 40.dp)
                .height(52.dp)
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchRow(modifier: Modifier) {
    val searchBarState = rememberSearchBarState()
    var expanded by rememberSaveable { mutableStateOf(false) }
    val textFieldState = rememberTextFieldState()

    Row(
        modifier = modifier,
    ) {
        SearchBar(
            state = searchBarState,
            modifier = Modifier.weight(0.65f).padding(end = 7.dp),
            inputField = {
                SearchBarDefaults.InputField(
                    query = textFieldState.text.toString(),
                    onQueryChange = { textFieldState.edit { replace(0, length, it) } },
                    onSearch = {
                        expanded = false
                    },
                    expanded = expanded,
                    onExpandedChange = { expanded = it },
                    placeholder = { Text("Search", fontSize = 13.sp) }
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
                contentDescription = null
            )
        }

    }
}


@Composable
fun AccountRow(modifier: Modifier = Modifier) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.End) {
        IconButton(
            modifier = Modifier
                .height(52.dp)
                .width(52.dp)
            ,
            colors = IconButtonDefaults.iconButtonColors(containerColor = OffWhite),
            onClick = {  }
        ) {
            Icon(
                modifier = Modifier.size(27.dp),
                painter = painterResource(R.drawable.fox),
                contentDescription = null
            )
        }
    }
}

@Composable
fun Tabs(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Row(modifier = modifier) {
        content()
    }
}