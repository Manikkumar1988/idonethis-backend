package model

import handler.DoneItem
import handler.User


interface Model {
    fun login(user: User): MutableList<User>
    fun addToDo(userId: String, doneItem: DoneItem)
}