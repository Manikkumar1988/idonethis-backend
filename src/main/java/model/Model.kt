package model

import handler.DoneItem
import handler.Team
import handler.User


interface Model {
    fun login(user: User): MutableList<User>
    fun addToDo(userId: String, doneItem: DoneItem)
    fun addTeamMember(teamId: String, team: Team)
    fun getTeamMember(teamId: String): MutableList<Team>
}