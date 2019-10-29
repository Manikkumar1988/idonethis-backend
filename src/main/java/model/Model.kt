package model

import handler.User


interface Model {
    fun login(user: User): MutableList<User>
}