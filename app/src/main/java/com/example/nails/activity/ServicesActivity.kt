package com.example.nails.activity

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nails.R
import com.example.nails.adapter.ServiceAdapter
import com.example.nails.data.DataSource

class ServicesActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_services)
        val rvService = findViewById<RecyclerView>(R.id.rvServices)
        rvService.layoutManager = LinearLayoutManager(this)
        rvService.adapter = ServiceAdapter(DataSource.Services) {}
    }
}
