import handler.GetHandler
import handler.LoginHandler
import handler.ToDoHandler
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

    before("/*") { q, _ -> println("Received api call: ${q.url()}") }
    get("/hello", GetHandler(model))
    get("/alive") { _, _ -> "ok" }

    get("/user", LoginHandler(model))
    post("/user/:uid/item", ToDoHandler(model))
}

fun getHerokuAssignedPort(): Int {
    val processBuilder = ProcessBuilder()
    return if (processBuilder.environment()["PORT"] != null) {
        Integer.parseInt(processBuilder.environment()["PORT"])
    } else 4567
}
