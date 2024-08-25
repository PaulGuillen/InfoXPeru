package com.devpaul.infoxperu.domain.ui.news_screen

import android.content.Context
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devpaul.infoxperu.core.extension.ResultState
import com.devpaul.infoxperu.domain.models.res.Country
import com.devpaul.infoxperu.domain.ui.skeleton.CountryCardSkeleton
import com.devpaul.infoxperu.domain.ui.skeleton.SectionsRowSkeleton

@Composable
fun CountryCards(countryState: ResultState<List<Country>>, context: Context) {
    var showSkeleton by remember { mutableStateOf(true) }

    LaunchedEffect(countryState) {
        showSkeleton = countryState is ResultState.Loading
    }

    if (showSkeleton) {
        CountryCardSkeleton()
    } else {
        CountryCardsContent(countryState = countryState, context = context)
    }
}

@Composable
fun CountryCardsContent(countryState: ResultState<List<Country>>, context: Context) {
    when (countryState) {
        is ResultState.Loading -> {
            SectionsRowSkeleton()
        }

        is ResultState.Success -> {
            if (countryState.data.isNotEmpty()) {
                Row(
                    modifier = Modifier
                        .horizontalScroll(rememberScrollState())
                        .padding(horizontal = 10.dp)
                ) {
                    countryState.data.forEach { countryItem ->
                        CountryCard(countryItem, context)
                    }
                }
            } else {
                Text(text = "No hay secciones disponibles.")
            }
        }

        is ResultState.Error -> {
            Row(
                modifier = Modifier
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 10.dp)
            ) {
                repeat(3) {
                    CountryCard(
                        Country(
                            title = "Titulo",
                            category = "Categoria",
                            summary = "Resumen",
                            imageUrl = "https://www.google.com",
                        ), context
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCountryCardContent() {
    val countryState = ResultState.Success(
        listOf(
            Country(
                title = "Titulo",
                category = "Categoria",
                summary = "Resumen",
                imageUrl = "https://www.google.com",
            ),
            Country(
                title = "Titulo",
                category = "Categoria",
                summary = "Resumen",
                imageUrl = "https://www.google.com",
            ),
            Country(
                title = "Titulo",
                category = "Categoria",
                summary = "Resumen",
                imageUrl = "https://www.google.com",
            ),
        )
    )
    CountryCardsContent(countryState = countryState, context = LocalContext.current)
}
