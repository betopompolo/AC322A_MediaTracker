package com.betopompolo.mediatracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.betopompolo.mediatracker.ui.composables.CustomCard
import com.betopompolo.mediatracker.ui.theme.MediaTrackerTheme

val movieDetail = MovieDetail(
    movieName = "Blade Runner 2049",
    durationInSeconds = 120,
    watchProgress = 50,
    synopsis = """Thirty years after the events of thefirst film, a new blade runner, LAPDOfficer K, unearths a long-buriedsecret that has the potential toplunge what's left of society intochaos. K's discovery leads him on aquest to find Rick Deckard, a formerLAPD blade runner who has beenmissing for 30 years. The journeytakes him deep into the irradiatedwastelands outside Los Angeles andinto the heart of the WallaceCorporation's secretive operations.""",
    directors = listOf(
        "Denis Villeneuve"
    ),
    studios = listOf(
        "Warnes Bros.",
        "Alcon Entertainment"
    ),
    originalRelease = "October 6, 2017",
    originalReleaseYear = 2017
)

class MovieDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MediaTrackerTheme {
                MovieDetailPage()
            }
        }
    }
}

@Composable
fun MovieDetailPage() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(innerPadding)
        ) {
            CustomCard("Synopsis") {
                Text(movieDetail.synopsis)
            }

            CustomCard("Details") {
                TitleValueRow("Director", movieDetail.directors.joinToString("\n"))
                TitleValueRow("Studio", movieDetail.studios.joinToString("\n"))
                TitleValueRow("Original Release", movieDetail.originalRelease)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MediaTrackerTheme {
        MovieDetailPage()
    }
}

data class MovieDetail(
    val movieName: String,
    val durationInSeconds: Int,
    val watchProgress: Int,
    val synopsis: String,
    val directors: List<String>,
    val studios: List<String>,
    val originalRelease: String,
    val originalReleaseYear: Int,
)