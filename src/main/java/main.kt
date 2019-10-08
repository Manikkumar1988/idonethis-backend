import handler.GetHandler
import model.Model
import spark.Spark.*

fun main() {
    get("/hello", GetHandler(Model()))
}