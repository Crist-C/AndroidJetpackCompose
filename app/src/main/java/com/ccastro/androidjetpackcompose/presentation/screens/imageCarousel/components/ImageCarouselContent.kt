package com.ccastro.androidjetpackcompose.presentation.screens.imageCarousel.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.ccastro.androidjetpackcompose.R
import com.ccastro.androidjetpackcompose.presentation.ui.theme.AndroidJetpackComposeTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ImageCarouselContent(modifier: Modifier = Modifier) {

    val pageState = rememberPagerState(initialPage = 2)
    val sliderImageList = listOf(
        "https://picsum.photos/id/10/500/700",
        "https://picsum.photos/id/11/500/700",
        "https://picsum.photos/id/12/500/700",
        "https://picsum.photos/id/13/500/700",
        "https://picsum.photos/id/14/500/700",
        "https://picsum.photos/id/15/500/700",
        "https://picsum.photos/id/16/500/700",
        "https://picsum.photos/id/17/500/700",
        "https://picsum.photos/id/18/500/700",
        "https://picsum.photos/id/19/500/700",
    )
    val scope = rememberCoroutineScope()
    Column(
        modifier = modifier
            .fillMaxSize()
    )
    {
        BoxWithConstraints(
            contentAlignment = Alignment.Center
        ) {

            HorizontalPager(
                count = sliderImageList.size,
                state = pageState,
                contentPadding = PaddingValues(horizontal = 120.dp),
                modifier = Modifier
                    .height(350.dp)
            )
            { page ->
                Card(
                    modifier = Modifier
                        .graphicsLayer {
                            val pagerOffset = calculateCurrentOffsetForPage(page).absoluteValue
                            lerp(
                                start = 0.55f,
                                stop = 1f,
                                fraction = 1f - pagerOffset.coerceIn(
                                    minimumValue = 0f,
                                    maximumValue = 1f
                                )
                            )
                                .also { scale ->
                                    scaleX = scale
                                    scaleY = scale
                                }
                            alpha = lerp(
                                start = 0.50f, // Transparencia
                                stop = 1f,
                                fraction = 1f - pagerOffset.coerceIn(
                                    minimumValue = 0f,
                                    maximumValue = 1f
                                )
                            )
                        },
                    shape = RoundedCornerShape(10.dp),
                    elevation = CardDefaults.cardElevation(16.dp)
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(sliderImageList[page])
                            .crossfade(true)
                            .scale(Scale.FILL)
                            .build(),
                        contentDescription = null,
                        placeholder = painterResource(id = R.drawable.placeholder_image_icon),
                        error = painterResource(id = R.drawable.error_image_placeholder)
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            )
            {
                IconButton(
                    enabled = pageState.currentPage > 0,
                    onClick = {
                        scope.launch {
                            pageState.animateScrollToPage(pageState.currentPage - 1)
                        }
                    }) {
                    Icon(Icons.Default.KeyboardArrowLeft, contentDescription = "move left")
                }

                IconButton(
                    enabled = pageState.currentPage < pageState.pageCount - 1,
                    onClick = {
                        scope.launch {
                            pageState.animateScrollToPage(pageState.currentPage + 1)
                        }
                    }) {
                    Icon(Icons.Default.KeyboardArrowRight, contentDescription = "move left")
                }
            }
        }

        Row(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(sliderImageList.size) {
                val colorBox = if (pageState.currentPage == it) {
                    Color.DarkGray
                } else {
                    Color.LightGray
                }
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .clip(CircleShape)
                        .size(10.dp)
                        .background(colorBox)
                        .clickable {
                            scope.launch {
                                pageState.animateScrollToPage(it)
                            }
                        }
                ) {

                }
            }
        }

    }

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ImageCarouselContentPreview() {
    AndroidJetpackComposeTheme {
        ImageCarouselContent()
    }
}


@Composable
fun AnimatedImagePlaceholder() {
    val progress by remember { mutableStateOf(0f) }


    AnimateProgress()

    val alpha = if (progress <= 0.5f) {
        2 * progress
    } else {
        2 * (1 - progress)
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        val image = painterResource(id = R.drawable.placeholder_image_icon)
        Image(
            painter = image,  // Remplaza con tu imagen placeholder
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            alpha = alpha
        )
    }
}

@Composable
private fun AnimateProgress() {
    val infiniteTransition = rememberInfiniteTransition(label = "infiniteTransition")
    val progress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        ), label = "infiniteTransition"
    )
    LaunchedEffect(progress) {
        delay(2000)
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun AnimatedImagePlaceholderPreview() {
    AndroidJetpackComposeTheme {
        AnimatedImagePlaceholder()
    }
}
