import handler.User
import org.sql2o.Sql2o
import java.net.URI
import org.flywaydb.core.Flyway

class DB {
    lateinit var sql2o: Sql2o

    fun start() {
        val dbUri: URI = if (System.getenv("DATABASE_URL") == null) {
            URI("postgres://user:pass@db:5432/db")
        } else {
            URI(System.getenv("DATABASE_URL"))
        }

        val port = dbUri.port
        val host = dbUri.host
        val path = dbUri.path
        val username = dbUri.userInfo.split(":")[0]
        val password = dbUri.userInfo.split(":")[1]

        val jdbcConnectionUrl = "jdbc:postgresql://$host:$port$path"
        println(jdbcConnectionUrl)
        sql2o = Sql2o(jdbcConnectionUrl, username, password)

        migrate(jdbcConnectionUrl = jdbcConnectionUrl,username = username, password = password)
    }

    private fun migrate(clean: Boolean = false, jdbcConnectionUrl: String,
                        username: String, password: String) {
        val flyway = Flyway()
        flyway.setDataSource(jdbcConnectionUrl, username, password)

        // DANGER ZONE: Do not run in production!!!
        if (clean) {
            flyway.clean()
        }

        flyway.migrate()
    }

}