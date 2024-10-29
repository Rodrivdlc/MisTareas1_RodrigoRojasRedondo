package com.example.mistareas1_rodrigorojasredondo

import android.content.ContentValues
import android.content.Context
import android.database.Cursor

fun addTask(context: Context, description: String) {
    val dbHelper = TaskDatabaseHelper(context)
    val db = dbHelper.writableDatabase

    val values = ContentValues().apply {
        put(TaskDatabaseHelper.COLUMN_DESCRIPTION, description)
        put(TaskDatabaseHelper.COLUMN_STATUS, "pending")
    }

    db.insert(TaskDatabaseHelper.TABLE_NAME, null, values)
}

fun getTasks(context: Context, status: String): List<Task> {
    val dbHelper = TaskDatabaseHelper(context)
    val db = dbHelper.readableDatabase

    val cursor: Cursor = db.query(
        TaskDatabaseHelper.TABLE_NAME,
        null,
        "${TaskDatabaseHelper.COLUMN_STATUS} = ?",
        arrayOf(status),
        null,
        null,
        null
    )

    val tasks = mutableListOf<Task>()
    with(cursor) {
        while (moveToNext()) {
            val id = getInt(getColumnIndexOrThrow(TaskDatabaseHelper.COLUMN_ID))
            val description = getString(getColumnIndexOrThrow(TaskDatabaseHelper.COLUMN_DESCRIPTION))
            tasks.add(Task(id, description, status == "pending"))
        }
    }
    cursor.close()
    return tasks
}

fun updateTaskStatus(context: Context, taskId: Int, newStatus: String) {
    val dbHelper = TaskDatabaseHelper(context)
    val db = dbHelper.writableDatabase

    val values = ContentValues().apply {
        put(TaskDatabaseHelper.COLUMN_STATUS, newStatus)
    }

    db.update(TaskDatabaseHelper.TABLE_NAME, values, "${TaskDatabaseHelper.COLUMN_ID} = ?", arrayOf(taskId.toString()))
}

fun deleteTask(context: Context, taskId: Int) {
    val dbHelper = TaskDatabaseHelper(context)
    val db = dbHelper.writableDatabase

    db.delete(TaskDatabaseHelper.TABLE_NAME, "${TaskDatabaseHelper.COLUMN_ID} = ?", arrayOf(taskId.toString()))
}