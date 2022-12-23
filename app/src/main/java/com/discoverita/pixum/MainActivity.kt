package com.discoverita.pixum

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import coil.request.ErrorResult
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.discoverita.pixum.comic.ComicsResult
import com.discoverita.pixum.ui.comics.AllComicsViewModel
import com.discoverita.pixum.ui.theme.Pixum_MarvelTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Pixum_MarvelTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ComicsList()
                }
            }
        }
    }
}

@Composable
fun ComicsList(comicsViewModel: AllComicsViewModel = viewModel()) {
    val comicsList = comicsViewModel.comicsPager.collectAsLazyPagingItems()
    LazyColumn(
        verticalArrangement = Arrangement.Center
    ) {
        items(comicsList) { comicsResult: ComicsResult? ->
            comicsResult?.let { ComicsCard(comicsResult = it) }
        }
    }
}

@Composable
fun ComicsCard(comicsResult: ComicsResult) {
    Card(
        backgroundColor = Color.LightGray,
        elevation = 4.dp,
        modifier = Modifier
            .padding(6.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .listener(listener = object : ImageRequest.Listener {
                        override fun onStart(request: ImageRequest) {
                            super.onStart(request)
                        }

                        override fun onError(request: ImageRequest, result: ErrorResult) {
                            super.onError(request, result)
                        }

                        override fun onSuccess(request: ImageRequest, result: SuccessResult) {
                            super.onSuccess(request, result)
                        }
                    })
                    .data(
                        Uri.parse(comicsResult.thumbnail.path + "." + comicsResult.thumbnail.extension)
                    )
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.ic_launcher_background),
                contentDescription = comicsResult.description,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .width(42.dp)
                    .height(42.dp)
            )
            Text(
                text = comicsResult.title,
                fontSize = 24.sp,
                modifier = Modifier
                    .padding(start = 12.dp)
                    .align(Alignment.CenterVertically)
            )
        }
    }
}

