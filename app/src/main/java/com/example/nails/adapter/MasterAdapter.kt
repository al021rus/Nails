package com.example.nails.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nails.R
import com.example.nails.model.Master

typealias OnMasterClickListener = (Master) -> Unit

class MasterAdapter (
    private val masters : List<Master>,
    private val listener : OnMasterClickListener,
) : RecyclerView.Adapter<MasterAdapter.MasterVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MasterVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MasterVH(
            layoutInflater.inflate(R.layout.item_master, parent, false),
            listener
        )
    }

    override fun onBindViewHolder(holder: MasterVH, position: Int) =
        holder.bind(masters[position])

    override fun getItemCount(): Int = masters.size


    class MasterVH(
        view: View,
        listener: OnMasterClickListener
    ) : RecyclerView.ViewHolder(view) {

        private val imageMaster = view.findViewById<ImageView>(R.id.imageMaster)
        private val nameMaster = view.findViewById<TextView>(R.id.nameMaster)
        private val positionName = view.findViewById<TextView>(R.id.positionName)
        private val expTime = view.findViewById<TextView>(R.id.ExpTime)
        private val workTime = view.findViewById<TextView>(R.id.workTime)



        private lateinit var master: Master

        init {
            view.setOnClickListener { listener(master) }
        }

        fun bind(master: Master) {
            this.master = master
            nameMaster.text = master.nameMaster
            positionName.text = master.positionName
            expTime.text = master.ExpTime
            workTime.text = master.workTime
            imageMaster.setImageResource(master.imageMaster)
        }
    }
}