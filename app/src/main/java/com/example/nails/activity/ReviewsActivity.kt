package com.example.nails.activity

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nails.R
import com.example.nails.adapter.ReviewAdapter
import com.example.nails.data.DataSource

class ReviewsActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reviews)
        val rvReview = findViewById<RecyclerView>(R.id.rvReview)
        rvReview.layoutManager = LinearLayoutManager(this)
        rvReview.adapter = ReviewAdapter(DataSource.Reviews) {}
    }
}