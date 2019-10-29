package model

import handler.User
import org.sql2o.Sql2o



class Sql2OModel(val sql2o: Sql2o): Model {
    override fun login(user: User): MutableList<User> {
        val users = mutableListOf<User>()
        sql2o.open().use { conn ->
            val sql = """select * from user_devices where userid=:userid and password =:password"""
            println(sql)
            println(user.userId)
            println(user.password)
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