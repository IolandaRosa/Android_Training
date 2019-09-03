package com.example.listmaker

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.listmaker.fragments.ListDetailsFragment
import com.example.listmaker.models.TaskList
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListDetailActivity : AppCompatActivity() {

    lateinit var addTaskButton: FloatingActionButton

    private lateinit var list: TaskList
    private lateinit var listDetailsFragment: ListDetailsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_detail)

        list = intent.getParcelableExtra(MainActivity.INTENT_LIST_KEY)

        listDetailsFragment = ListDetailsFragment.newInstance(list)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container_details, listDetailsFragment)
            .commit()

        addTaskButton = findViewById(R.id.add_task_button)

        addTaskButton.setOnClickListener {
            showCreateTaskDialog()
        }

    }

    private fun showCreateTaskDialog() {
        val taskEditText = EditText(this)
        taskEditText.inputType = InputType.TYPE_CLASS_TEXT

        AlertDialog.Builder(this)
            .setTitle(R.string.task_to_add)
            .setView(taskEditText)
            .setPositiveButton(R.string.add_task) { dialog, _ ->
                val task = taskEditText.text.toString()

                listDetailsFragment.addTask(task)

                dialog.dismiss()
            }
            .create()
            .show()
    }

    override fun onBackPressed() {
        var bundle = Bundle()
        bundle.putParcelable(MainActivity.INTENT_LIST_KEY, list)
        val intent = Intent()
        intent.putExtras(bundle)
        setResult(Activity.RESULT_OK, intent)
        super.onBackPressed()
    }
}
