import handler.GetHandler
import model.Model
import spark.Spark.*

fun main() {
    port(getHerokuAssignedPort())
    get("/hello", GetHandler(Model()))
}

fun getHerokuAssignedPort(): Int {
    val processBuilder = ProcessBuilder()
    return if (processBuilder.environment()["PORT"] != null) {
        Integer.parseInt(processBuilder.environment()["PORT"])
    } else 4567
}