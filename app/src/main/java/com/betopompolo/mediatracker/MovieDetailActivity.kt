package com.betopompolo.mediatracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Badge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.betopompolo.mediatracker.ui.composables.CustomCard
import com.betopompolo.mediatracker.ui.theme.MediaTrackerTheme
import kotlin.time.Duration
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes

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
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = innerPadding.calculateBottomPadding())
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(450.dp), contentAlignment = Alignment.BottomStart
            ) {
                Image(
                    painter = painterResource(R.drawable.blade_runner),
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = "Movie banner"
                )
                Box(
                    Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                listOf(Color.Transparent, MaterialTheme.colorScheme.surface)
                            )
                        )
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        "Blade Runner 2049",
                        style = MaterialTheme.typography.displaySmall
                    )
                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        listOf(
                            "Movie",
                            "Sci-fi",
                            "2017",
                            movieDuration.format()
                        ).forEach {
                            Badge(containerColor = MaterialTheme.colorScheme.surfaceVariant) {
                                Text(it, modifier = Modifier.padding(4.dp))
                            }
                        }
                    }
                }
            }

            CustomCard("Synopsis") {
                Text(movieDetail.synopsis)
            }

            CustomCard("Watch progress") {
                var progress by remember { mutableFloatStateOf(0.0f) }
                val watchProgress = movieDuration * progress.toDouble()
                Text(watchProgress.format())
                Slider(
                    value = progress,
                    onValueChange = {
                        progress = it
                    }
                )
            }

            CustomCard("Details") {
                TitleValueRow("Director", movieDetail.directors.joinToString("\n"))
                TitleValueRow("Studio", movieDetail.studios.joinToString("\n"))
                TitleValueRow("Original Release", movieDetail.originalRelease)
            }
        }
    }
}

val movieDuration = 2.hours + 30.minutes

fun Duration.format(): String {
    return toComponents { hours, minutes, _, _ ->
        "${hours}h ${minutes}m"
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