import handler.GetHandler
import model.Model
import spark.Spark.get
import spark.Spark.port

fun main() {
    port(getHerokuAssignedPort())
    val db = DB()
    db.start()

    get("/hello", GetHandler(Model()))
    get("/alive") { _, _ -> "ok" }
    get("/dbcheck") { _, _ -> db.getAllUsers() }
}

fun getHerokuAssignedPort(): Int {
    val processBuilder = ProcessBuilder()
    return if (processBuilder.environment()["PORT"] != null) {
        Integer.parseInt(processBuilder.environment()["PORT"])
    } else 4567
}
