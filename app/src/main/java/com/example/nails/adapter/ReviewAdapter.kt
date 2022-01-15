package com.example.nails.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nails.model.Review
import com.example.nails.R

typealias OnReviewClickListener = (Review) -> Unit

class ReviewAdapter (
    private val reviews : List<Review>,
    private val listener : OnReviewClickListener,
) : RecyclerView.Adapter<ReviewAdapter.ReviewVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ReviewVH(
            layoutInflater.inflate(R.layout.item_review, parent, false),
            listener
        )
    }

    override fun onBindViewHolder(holder: ReviewVH, position: Int) =
        holder.bind(reviews[position])

    override fun getItemCount(): Int = reviews.size


    class ReviewVH(
        view: View,
        listener: OnReviewClickListener
    ) : RecyclerView.ViewHolder(view) {

        private val userName = view.findViewById<TextView>(R.id.userName)
        private val commentsText = view.findViewById<TextView>(R.id.comments_text)

        private lateinit var review: Review

        init {
            view.setOnClickListener { listener(review) }
        }

        fun bind(review: Review) {
            this.review = review
            userName.text = review.userName
            commentsText.text = review.comments_text
        }
    }
}