package com.example.android.architecture.blueprints.todoapp.data.source

import androidx.lifecycle.LiveData
import com.example.android.architecture.blueprints.todoapp.data.Result
import com.example.android.architecture.blueprints.todoapp.data.Result.Error
import com.example.android.architecture.blueprints.todoapp.data.Result.Success
import com.example.android.architecture.blueprints.todoapp.data.Task

/**
 * FakeDataSource is a specific type of test double called a fake.
 * A fake is a test double that has a "working" implementation of the class,
 * but it's implemented in a way that makes it good for tests but unsuitable for production.
 * "Working" implementation means that the class will produce realistic outputs given inputs.

 * For example, your fake data source won't connect to the network or save anything to a database—instead
 * it will just use an in-memory list. This will "work as you might expect" in that methods to get or save
 * tasks will return expected results, but you could never use this implementation in production,
 * because it's not saved to the server or a database.
 */
class FakeDataSource(var tasks: MutableList<Task>? = mutableListOf()) : TasksDataSource{
    override fun observeTasks(): LiveData<Result<List<Task>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getTasks(): Result<List<Task>> {
        tasks?.let { return Success(ArrayList(it)) }
        return Error(
                Exception("Tasks not found")
        )
    }

    override suspend fun refreshTasks() {
        TODO("Not yet implemented")
    }

    override fun observeTask(taskId: String): LiveData<Result<Task>> {
        TODO("Not yet implemented")
    }

    override suspend fun getTask(taskId: String): Result<Task> {
        TODO("Not yet implemented")
    }

    override suspend fun refreshTask(taskId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun saveTask(task: Task) {
        tasks?.add(task)
    }

    override suspend fun completeTask(task: Task) {
        TODO("Not yet implemented")
    }

    override suspend fun completeTask(taskId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun activateTask(task: Task) {
        TODO("Not yet implemented")
    }

    override suspend fun activateTask(taskId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun clearCompletedTasks() {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllTasks() {
        tasks?.clear()
    }

    override suspend fun deleteTask(taskId: String) {
        TODO("Not yet implemented")
    }
}