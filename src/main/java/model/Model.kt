package model

import handler.DoneItem
import handler.DoneItemV1
import handler.Team
import handler.User


interface Model {
    fun login(user: User): MutableList<User>
    fun addToDo(userId: String, doneItem: DoneItem)
    fun getDoneItems(): MutableList<DoneItemV1>
    fun addTeamMember(teamId: String, team: Team)
    fun getTeamMember(teamId: String): MutableList<Team>
}