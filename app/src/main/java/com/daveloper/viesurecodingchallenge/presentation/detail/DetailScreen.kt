package com.daveloper.viesurecodingchallenge.presentation.detail

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import coil.size.Size
import com.daveloper.viesurecodingchallenge.R
import com.daveloper.viesurecodingchallenge.articles
import com.daveloper.viesurecodingchallenge.presentation.utils.customFormat
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@Composable
fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    viewModel.state.article?.let { article ->
        Column(
            modifier = Modifier.verticalScroll(scrollState)
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(article.image)
                    .size(Size.ORIGINAL)
                    .scale(Scale.FILL)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.placeholder),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = article.title,
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = article.releaseDate.customFormat(),
                    modifier = Modifier.align(Alignment.End),
                    style = MaterialTheme.typography.caption
                )
                Text(
                    text = article.description,
                )
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(stringResource(R.string.author))
                        }
                        append(article.author)
                    },
                    style = MaterialTheme.typography.caption
                )
            }
        }
    }
}

@Preview
@Composable
private fun DetailScreenPreview() {
    DetailScreen()
}