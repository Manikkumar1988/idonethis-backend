import handler.GetHandler
import handler.LoginHandler
import model.Model
import model.Sql2OModel
import spark.Spark.before
import spark.Spark.get
import spark.Spark.port
import spark.Spark.post

fun main() {
    port(getHerokuAssignedPort())
    val db = DB()
    db.start()

    val model: Model = Sql2OModel(db.sql2o)

    get("/hello", GetHandler(model))
    get("/alive") { _, _ -> "ok" }
    get("/dbcheck") { _, _ -> db.getAllUsers() }

    get("/user", LoginHandler(model))
    post("/user/:uid/item") { _, _ -> Answer.ok("To Be Implemented")}
}

fun getHerokuAssignedPort(): Int {
    val processBuilder = ProcessBuilder()
    return if (processBuilder.environment()["PORT"] != null) {
        Integer.parseInt(processBuilder.environment()["PORT"])
    } else 4567
}
