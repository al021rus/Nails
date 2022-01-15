package com.example.nails.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nails.R
import com.example.nails.model.Service

typealias OnServiceClickListener = (Service) -> Unit

class ServiceAdapter (
    private val service : List<Service>,
    private val listener : OnServiceClickListener,
) : RecyclerView.Adapter<ServiceAdapter.ServiceVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ServiceVH (
            layoutInflater.inflate(R.layout.item_services, parent, false),
            listener
        )
    }

    override fun onBindViewHolder(holder: ServiceVH, position: Int) =
        holder.bind(service[position])

    override fun getItemCount(): Int = service.size


    class ServiceVH(
        view: View,
        listener: OnServiceClickListener
    ) : RecyclerView.ViewHolder(view) {
        private val nameService = view.findViewById<TextView>(R.id.nameService)
        private val priceCount = view.findViewById<TextView>(R.id.PriceCount)
        private val placeCount = view.findViewById<TextView>(R.id.placeCount)
        private val imageService = view.findViewById<ImageView>(R.id.imageService)

        private lateinit var service: Service

        init {
            view.setOnClickListener { listener(service) }
        }

        fun bind(service: Service) {
            this.service = service
            nameService.text = service.nameService
            priceCount.text = service.priceCount
            placeCount.text = service.placeCount
            Glide
                .with(itemView)
                .load(service.imageServiceURL)
                .centerCrop()
                .placeholder(R.drawable.ic_image)
                .into(imageService)
        }
    }
}