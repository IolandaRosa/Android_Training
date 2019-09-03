package com.example.listmaker.models

import android.os.Parcel
import android.os.Parcelable
import java.util.ArrayList

class TaskList(
    val name: String?,
    val tasks: ArrayList<String> = ArrayList())
    : Parcelable{

    constructor(parcel: Parcel):this(
        parcel.readString(),
        parcel.createStringArrayList() as ArrayList<String>
    )


    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(name)
        dest?.writeStringList(tasks)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<TaskList> {
        override fun createFromParcel(parcel: Parcel): TaskList  = TaskList(parcel)

        override fun newArray(size: Int): Array<TaskList?>  = arrayOfNulls(size)
    }
}