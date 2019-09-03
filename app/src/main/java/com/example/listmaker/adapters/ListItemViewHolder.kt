package com.example.listmaker.adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.listmaker.R

class ListItemViewHolder (view: View): RecyclerView.ViewHolder(view) {

    val taskTextView = view.findViewById<TextView>(R.id.textview_task)
}