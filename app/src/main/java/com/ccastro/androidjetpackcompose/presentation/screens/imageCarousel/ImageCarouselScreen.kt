package com.ccastro.androidjetpackcompose.presentation.screens.imageCarousel

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ccastro.androidjetpackcompose.presentation.screens.imageCarousel.components.ImageCarouselContent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageCarouselScreen(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {},
        content = {
            ImageCarouselContent(modifier)
        },
        bottomBar = {}
    )
}