package com.example.mistareas1_rodrigorojasredondo

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.mistareas1_rodrigorojasredondo.ui.theme.MisTareas1_RodrigoRojasRedondoTheme
import com.example.mistareas1_rodrigorojasredondo.ui.theme.LightBlue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MisTareas1_RodrigoRojasRedondoTheme {
                TaskApp(this)
            }
        }
    }
}

@Composable
fun TaskApp(context: Context) {
    var showPendingTasks by remember { mutableStateOf(true) }
    val tasks = remember { mutableStateListOf<Task>() }
    var newTaskDescription by remember { mutableStateOf(TextFieldValue("")) }

    // Load tasks from the database
    LaunchedEffect(showPendingTasks) {
        val status = if (showPendingTasks) "pending" else "done"
        tasks.clear()
        tasks.addAll(getTasks(context, status))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(LightBlue)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Mis Recordatorios", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Input field and button to add new tasks
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            TextField(
                value = newTaskDescription,
                onValueChange = { newTaskDescription = it },
                modifier = Modifier
                    .width(300.dp)
                    .padding(end = 8.dp)
            )
            Button(onClick = {
                addTask(context, newTaskDescription.text)
                newTaskDescription = TextFieldValue("") // Clear the input field
                val status = if (showPendingTasks) "pending" else "done"
                tasks.clear()
                tasks.addAll(getTasks(context, status)) // Reload tasks
            }) {
                Text("+")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(tasks) { task ->
                TaskItem(task, context, tasks)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = { showPendingTasks = true }) {
                Text("Pendientes")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = { showPendingTasks = false }) {
                Text("Realizadas")
            }
        }
    }
}

@Composable
fun TaskItem(task: Task, context: Context, tasks: MutableList<Task>) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = task.description)
        Row {
            Button(onClick = {
                updateTaskStatus(context, task.id, if (task.isPending) "done" else "pending")
                tasks.remove(task)
            }) {
                Text(if (task.isPending) "Hecha" else "Pendiente")
            }
            Button(onClick = {
                deleteTask(context, task.id)
                tasks.remove(task)
            }) {
                Text("Eliminar")
            }
        }
    }
}

data class Task(val id: Int, val description: String, val isPending: Boolean)