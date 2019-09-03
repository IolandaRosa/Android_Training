package com.example.listmaker.adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.listmaker.R

class ListSelectionViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    //Componentes visuais
    val listPosition = itemView?.findViewById<TextView>(R.id.itemNumber)
    val listTitle = itemView?.findViewById<TextView>(R.id.itemString)

}