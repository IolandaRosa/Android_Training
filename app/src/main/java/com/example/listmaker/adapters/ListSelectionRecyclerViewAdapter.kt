package com.example.listmaker.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listmaker.R
import com.example.listmaker.models.TaskList

class ListSelectionRecyclerViewAdapter
    (val lists: ArrayList<TaskList>?,
     val clickListener: ListSelectionRecyclerViewClickListener) :
    RecyclerView.Adapter<ListSelectionViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListSelectionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_selection_view_holder, parent, false)

        return ListSelectionViewHolder(view)
    }

    override fun getItemCount() = lists?.size ?: 0

    override fun onBindViewHolder(holder: ListSelectionViewHolder, position: Int) {
        holder.listPosition.text = (position + 1).toString()
        holder.listTitle.text = lists?.get(position)?.name
        holder.itemView.setOnClickListener{
            clickListener.listItemClicked(lists?.get(position))
        }
    }

    fun addList(list:TaskList){
        lists?.add(list)
        notifyDataSetChanged()
    }

    interface ListSelectionRecyclerViewClickListener{
        fun listItemClicked (list: TaskList?)
    }

}
