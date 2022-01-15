package com.example.nails.activity

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nails.R
import com.example.nails.adapter.MasterAdapter
import com.example.nails.data.DataSource

class MastersActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_masters)
        val rvMaster = findViewById<RecyclerView>(R.id.rvMasters)
        rvMaster.layoutManager = LinearLayoutManager(this)
        rvMaster.adapter = MasterAdapter(DataSource.Masters) {}
    }
}