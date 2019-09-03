package com.example.listmaker.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listmaker.MainActivity
import com.example.listmaker.R
import com.example.listmaker.adapters.ListItemsRecyclerViewAdapter
import com.example.listmaker.models.TaskList

class ListDetailsFragment : Fragment() {

    var list: TaskList? = null
    lateinit var listItemsRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        list = arguments?.getParcelable<TaskList?>(MainActivity.INTENT_LIST_KEY)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list_details, container, false)

        view?.let {
            listItemsRecyclerView = it.findViewById(R.id.list_items_recyclerview)
            listItemsRecyclerView.layoutManager = LinearLayoutManager(activity)
            listItemsRecyclerView.adapter = ListItemsRecyclerViewAdapter(list!!)
        }

        return view
    }

    companion object {
        fun newInstance(list: TaskList): ListDetailsFragment {
            val fragment = ListDetailsFragment()
            val args = Bundle()
            args.putParcelable(MainActivity.INTENT_LIST_KEY, list)
            fragment.arguments = args
            return fragment
        }
    }

    fun addTask(item: String) {
        list?.tasks?.add(item)
        val recyclerAdapter = listItemsRecyclerView.adapter as ListItemsRecyclerViewAdapter

        recyclerAdapter.notifyDataSetChanged()
    }
}
