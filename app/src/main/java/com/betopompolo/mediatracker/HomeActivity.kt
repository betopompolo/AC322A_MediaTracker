package com.betopompolo.mediatracker

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.betopompolo.mediatracker.databinding.ActivityHomeBinding
import com.betopompolo.mediatracker.databinding.HomeListItemLayoutBinding

val mockList = List(200) {
    HomeListItem("id-${it}", "Book $it", 10)
}

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.listView.adapter = HomeListAdapter(mockList)
        binding.listView.layoutManager = LinearLayoutManager(this)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}

class HomeListAdapter(val list: List<HomeListItem>) :
    RecyclerView.Adapter<HomeListAdapter.ViewHolder>() {
    class ViewHolder(val listItemBinding: HomeListItemLayoutBinding) :
        RecyclerView.ViewHolder(listItemBinding.root) {
        val context: Context = listItemBinding.root.context
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val listItemBinding =
            HomeListItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(listItemBinding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val item = list[position]
        
        holder.listItemBinding.title.text = item.title
        holder.listItemBinding.pageCount.text =
            holder.context.getString(R.string.home_list_item_page_count, item.totalPages)
        holder.listItemBinding.badge.badgeText.text = holder.context.getString(R.string.book)

        holder.listItemBinding.root.setOnClickListener {
            val intent = Intent(holder.context, BookDetailActivity::class.java)
            intent.putExtra(BookDetailActivity.BOOK_ID_KEY, item.id)
            holder.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

data class HomeListItem(
    val id: String,
    val title: String,
    val totalPages: Int
)