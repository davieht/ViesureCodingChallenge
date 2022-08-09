package com.daveloper.viesurecodingchallenge.presentation.overview

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.daveloper.viesurecodingchallenge.R
import com.daveloper.viesurecodingchallenge.articles
import com.daveloper.viesurecodingchallenge.domain.entities.Article
import com.daveloper.viesurecodingchallenge.presentation.Screen
import com.daveloper.viesurecodingchallenge.presentation.utils.customFormat
import kotlinx.coroutines.launch

@Composable
fun OverviewScreen(
    viewModel: OverviewViewModel = hiltViewModel(),
    navController: NavController = rememberNavController(),
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(0.dp, 16.dp)
        ) {
            items(viewModel.state.items, key = { it.id }) {
                ArticleItem(
                    article = it,
                    onItemClick = { id ->
                        navController.navigate("${Screen.DetailScreen.route}/${id}")
                    }
                )
            }
        }
        if (viewModel.state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
        if (viewModel.state.isError) {
            val coroutineScope = rememberCoroutineScope()
            val message = viewModel.state.errorMsg.asString()
            LaunchedEffect(key1 = true) {
                coroutineScope.launch {
                    scaffoldState.snackbarHostState.showSnackbar(message)
                }
            }
        }
        if (viewModel.state.items.isEmpty() && !viewModel.state.isLoading) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = stringResource(id = R.string.no_data),
            )
        }
    }
}

@Preview
@Composable
private fun OverviewScreenPreview() {
    OverviewScreen()
}

@Composable
private fun ArticleItem(
    article: Article,
    onItemClick: (Int) -> Unit = {}
) {
    Card(
        modifier = Modifier
            .padding(8.dp, 0.dp)
            .clickable {
                onItemClick(article.id)
            },
        backgroundColor = Color.hsl(0f, 0f, .96f)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp, 16.dp, 48.dp, 16.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(article.image)
                    .scale(Scale.FIT)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.placeholder),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = article.title,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = article.description,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                    color = Color.hsl(0f, 0f, .51f)
                )
                Text(
                    text = article.releaseDate.customFormat(),
                    style = MaterialTheme.typography.overline
                )
            }
        }
    }
}

@Preview
@Composable
private fun ArticleItemPreview() {
    ArticleItem(articles[0])
}