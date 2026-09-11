package com.betopompolo.mediatracker

import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.betopompolo.mediatracker.databinding.BadgeLayoutBinding
import com.betopompolo.mediatracker.databinding.BookDetailLayoutBinding

var detail = BookDetail(
    title = "The Lord of the Rings: The Fellowship of the Ring",
    synopsis = "In a sleepy village in the Shire, a young hobbit named Frodo Baggins is entrusted with an immense task: he must make a perilous journey across Middle-earth to the Cracks of Doom, there to destroy the One Ring of Power, the only thing that can prevent the dark lord Sauron from conquering the world.",
    author = "J.R.R. Tolkien",
    publisher = "George Allen & Unwin",
    firstPublished = "July 29, 1954",
    genres = listOf("Fantasy", "Adventure", "Epic"),
    totalPages = 200
)

class BookDetailActivity : AppCompatActivity() {

    companion object {
        const val BOOK_ID_KEY = "book_id"
    }
    private lateinit var binding: BookDetailLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = BookDetailLayoutBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        val bookId = intent.getStringExtra(BOOK_ID_KEY)
        Log.d("MyTag", "Book id is $bookId")

        binding.title.text = detail.title
        binding.synopsis.text = detail.synopsis
        binding.author.text = detail.author
        binding.publisher.text = detail.publisher
        binding.firstPublished.text = detail.firstPublished
        binding.genres.text = detail.genres.joinToString(", ")

        createAndLayoutBadges(
            listOf(
                "Book",
                detail.genres.first(),
                "${detail.totalPages} pages",
                "9.2"
            )
        )

        binding.fabPreviousPage.setOnClickListener {
            detail.getPreviousPage()?.let { detail = it }
            updateReadingProgressViews()
        }
        binding.fabNextPage.setOnClickListener {
            detail.getNextPage()?.let { detail = it }
            updateReadingProgressViews()
        }

        updateReadingProgressViews()
    }

    private fun updateReadingProgressViews() {
        binding.readingProgressPage.text =
            getString(R.string.of, detail.currentPage, detail.totalPages)
        binding.readingProgressPercent.text = "${detail.progressPercentage}%"
        binding.readingProgressIndicator.progress = detail.progressPercentage
        binding.fabPreviousPage.isEnabled = detail.currentPage > 0
        binding.fabNextPage.isEnabled = detail.currentPage < detail.totalPages
    }

    private fun createAndLayoutBadges(badgesTexts: List<String>) {
        val maxBadgesPerRow = 3

        badgesTexts.take(maxBadgesPerRow).forEach {
            createBadgeView(binding.badgeRow1, it)
        }

        badgesTexts.drop(maxBadgesPerRow).take(maxBadgesPerRow).forEach { badgeText ->
            createBadgeView(binding.badgeRow2, badgeText)
        }
    }

    private fun createBadgeView(row: LinearLayout, text: String) {
        val badgeLayoutBinding = BadgeLayoutBinding.inflate(layoutInflater, row, false)
        badgeLayoutBinding.badgeText.text = text
        row.addView(badgeLayoutBinding.root)
    }
}

data class BookDetail(
    val title: String,
    val synopsis: String,
    val author: String,
    val publisher: String,
    val firstPublished: String,
    val genres: List<String>,
    val currentPage: Int = 0,
    val totalPages: Int
) {
    init {
        require(totalPages >= 0) { "totalPages is not valid" }
        require(currentPage in 0..totalPages) { "currentPage is not valid" }
    }

    val progressPercentage: Int
        get() = ((currentPage.toFloat() / totalPages) * 100).toInt()

    fun getNextPage(): BookDetail? {
        return if (currentPage < totalPages) {
            copy(currentPage = currentPage + 1)
        } else {
            null
        }
    }

    fun getPreviousPage(): BookDetail? {
        return if (currentPage > 0) {
            copy(currentPage = currentPage - 1)
        } else {
            null
        }
    }
}