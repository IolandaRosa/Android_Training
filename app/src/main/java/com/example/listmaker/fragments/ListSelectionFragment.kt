package com.example.listmaker.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listmaker.R
import com.example.listmaker.adapters.ListSelectionRecyclerViewAdapter
import com.example.listmaker.data.ListDataManager
import com.example.listmaker.models.TaskList

class ListSelectionFragment : Fragment(),
    ListSelectionRecyclerViewAdapter.ListSelectionRecyclerViewClickListener {

    //1 - listener e interface para tratar o clique do objeto
    // que deve ser implementada pela activity
    private var listener: OnListItemFragmentInteractionListener? = null

    //Variaveis da lista
    lateinit var listsRecyclerView: RecyclerView
    lateinit var listDataManager: ListDataManager
    lateinit var sharedPreferences: SharedPreferences

    interface OnListItemFragmentInteractionListener {
        fun onListItemClicked(list: TaskList)
    }

    //2 - utilizado para criar uma nova instancia do fragmento
    companion object {
        private const val PRIVATE_MODE = Context.MODE_PRIVATE
        private const val PREF_NAME = "list_maker_prefs"
        fun newInstance() = ListSelectionFragment()
    }

    //3 - primeiro metodo corre quando fragmento é associado a activity
    // deve-se iniciar todos os objetos que sao necessarios antes do fragmento
    // ser associado a uma actividade
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListItemFragmentInteractionListener) {
            listener = context

            sharedPreferences = context.getSharedPreferences(
                PREF_NAME, PRIVATE_MODE
            )

            listDataManager = ListDataManager(sharedPreferences)

        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    //4
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val lists = listDataManager.readList()

        view?.let {
            listsRecyclerView = it.findViewById(R.id.lists_reciclerview)
            listsRecyclerView.layoutManager = LinearLayoutManager(activity)

            listsRecyclerView.adapter =
                ListSelectionRecyclerViewAdapter(lists, this)
        }
    }

    //5 quando o fragmento recebe o layout - inflate
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_selection, container, false)
    }

    //6 - ultimo metodo, o fragmento já não esta associado a activity
    //deve ser limpa qualquer memoria que exista
    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun listItemClicked(list: TaskList?) {
        listener?.onListItemClicked(list!!)
    }

    fun addList(list: TaskList) {
        listDataManager.saveList(list)
        val recyclerAdapter = listsRecyclerView.adapter as
                ListSelectionRecyclerViewAdapter

        recyclerAdapter.addList(list)
    }

    fun saveList(list: TaskList) {
        listDataManager.saveList(list)
        updateLists()
    }

    private fun updateLists() {
        var lists = listDataManager.readList()

        listsRecyclerView.adapter = ListSelectionRecyclerViewAdapter(lists, this)
    }
}
