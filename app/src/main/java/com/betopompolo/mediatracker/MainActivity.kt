package com.betopompolo.mediatracker

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.progressindicator.LinearProgressIndicator

var detail = BookDetail(
    title = "The Lord of the Rings: The Fellowship of the Ring",
    synopsis = "In a sleepy village in the Shire, a young hobbit named Frodo Baggins is entrusted with an immense task: he must make a perilous journey across Middle-earth to the Cracks of Doom, there to destroy the One Ring of Power, the only thing that can prevent the dark lord Sauron from conquering the world.",
    author = "J.R.R. Tolkien",
    publisher = "George Allen & Unwin",
    firstPublished = "July 29, 1954",
    genres = listOf("Fantasy", "Adventure", "Epic"),
    currentPage = 20,
    totalPages = 200
)
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val titleTextView = findViewById<TextView>(R.id.title)
        titleTextView.text = detail.title

        val synopsisTextView = findViewById<TextView>(R.id.synopsis)
        synopsisTextView.text = detail.synopsis

        val authorTextView = findViewById<TextView>(R.id.author)
        authorTextView.text = detail.author

        findViewById<TextView>(R.id.publisher).apply {
            text = detail.publisher
        }

        val firstPublishedTextView = findViewById<TextView>(R.id.firstPublished)
        firstPublishedTextView.text = detail.firstPublished

        val genresTextView = findViewById<TextView>(R.id.genres)
        genresTextView.text = detail.genres.joinToString(", ")

        updateReadingProgressViews()

        findViewById<Button>(R.id.cta).setOnClickListener {
            detail = detail.copy(currentPage = detail.currentPage + 1)
            updateReadingProgressViews()
        }
    }

    fun updateReadingProgressViews() {
        findViewById<TextView>(R.id.readingProgressPage).apply {
            text = "${detail.currentPage} of ${detail.totalPages}"
        }

        findViewById<TextView>(R.id.readingProgressPercent).apply {
            text = "${detail.progressPercentage}%"
        }

        findViewById<LinearProgressIndicator>(R.id.readingProgressIndicator).apply {
            progress = detail.progressPercentage
        }
    }
}

// Immutability
data class BookDetail(
    val title: String,
    val synopsis: String,
    val author: String,
    val publisher: String,
    val firstPublished: String,
    val genres: List<String>,
    val currentPage: Int,
    val totalPages: Int
) {
    val progressPercentage: Int
        get() = ((currentPage.toFloat() / totalPages) * 100).toInt()
}