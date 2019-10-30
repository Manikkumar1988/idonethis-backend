import email.EmailService
import handler.EmailRemindHandler
import handler.EmailReportHandler
import handler.GetHandler
import handler.LoginHandler
import handler.TeamGetHandler
import handler.TeamPostHandler
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

    before("/*") { q, _ -> println("Received ${q.requestMethod()} call: ${q.url()}") }

    get("/hello", GetHandler(model))
    get("/alive") { _, _ -> "ok" }

    get("/user", LoginHandler(model))
    post("/user/:uid/item", ToDoHandler(model))

    get("/team/:teamId", TeamGetHandler(model))
    post("/team/:teamId", TeamPostHandler(model))

    post("/team/:teamId/remind", EmailRemindHandler(model, EmailService()))
    post("/team/:teamId/report", EmailReportHandler(model, EmailService()))

}

fun getHerokuAssignedPort(): Int {
    val processBuilder = ProcessBuilder()
    return if (processBuilder.environment()["PORT"] != null) {
        Integer.parseInt(processBuilder.environment()["PORT"])
    } else 4567
}
