package model

import handler.DoneItem
import handler.DoneItemV1
import handler.Team
import handler.User
import org.sql2o.Sql2o
import java.util.*


class Sql2OModel(val sql2o: Sql2o): Model {
    override fun getTeamMember(teamId: String): MutableList<Team> {
        val teamList = mutableListOf<Team>()
        sql2o.open().use { conn ->
            val sql = """select * from team where teamid=:teamid"""
            teamList.addAll(conn.createQuery(sql)
                    .addParameter("teamid", teamId)
                    .executeAndFetch(Team::class.java))

        }
        return teamList
    }

    override fun addTeamMember(teamId: String, team: Team) {
        sql2o.open().use { conn ->
            conn.createQuery("""insert into team (teamid, email)
                | values(:teamid, :email) ON CONFLICT (email) DO NOTHING""".trimMargin())
                    .addParameter("teamid", teamId)
                    .addParameter("email", team.email)
                    .executeUpdate()
        }
    }

    override fun addToDo(userId: String, doneItem: DoneItem) {
        sql2o.open().use { conn ->
            conn.createQuery("""insert into doneItem (uid, name, type, createdAt)
                | values(:id, :name, :type, :createdAt)""".trimMargin())
                    .addParameter("id", userId)
                    .addParameter("name", doneItem.item)
                    .addParameter("type", doneItem.type)
                    .addParameter("createdAt", Calendar.getInstance().timeInMillis)
                    .executeUpdate()
        }
    }

    override fun getDoneItems(): MutableList<DoneItemV1> {
        val doneItems = mutableListOf<DoneItemV1>()
        sql2o.open().use { conn ->
            doneItems.addAll(conn.createQuery("""select * from doneItem""".trimMargin())
                    .executeAndFetch(DoneItemV1::class.java))
        }
        return doneItems
    }

    override fun login(user: User): MutableList<User> {
        val users = mutableListOf<User>()
        sql2o.open().use { conn ->
            val sql = """select * from user_devices where userid=:userid and password =:password"""
            users.addAll(conn.createQuery(sql)
                    .addParameter("userid",user.userId)
                    .addParameter("password",user.password)
                    .executeAndFetch(User::class.java))

        }
        return users
    }

    fun <T : AutoCloseable?, R> T.use(block: (T) -> R): R {
        try {
            return block(this)
        } finally {
            try {
                this?.close()
            } catch (e: Exception) {
                println(e.toString())
            }
        }
    }
}