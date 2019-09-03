package com.example.listmaker.data

import android.content.SharedPreferences
import com.example.listmaker.models.TaskList

class ListDataManager(private val sharedPreferences: SharedPreferences) {

    fun saveList(list: TaskList) {
        if (sharedPreferences != null) {
            val editor = sharedPreferences.edit()
            editor.putStringSet(list.name, list.tasks.toHashSet())
            editor.apply()
        }
    }

    fun readList(): ArrayList<TaskList>? {

        if (sharedPreferences != null) {
            val sharedPreferencesContents = sharedPreferences.all

            val taskLists = ArrayList<TaskList>()

            for (taskList in sharedPreferencesContents) {

                val itemHashSet = taskList.value as HashSet<String>

                val list = TaskList(taskList.key, ArrayList(itemHashSet))

                taskLists.add(list)
            }

            return taskLists
        }

        return null
    }
}