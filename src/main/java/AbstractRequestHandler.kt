import com.google.gson.Gson
import model.Model
import spark.Request
import spark.Response
import spark.Route

abstract class AbstractRequestHandler<V : Validable>(private val valueClass: Class<V>, protected var model: Model) : RequestHandler<V>, Route {

    private val HTTP_BAD_REQUEST = 400

    override fun process(value: V, urlParams: Map<String, String>): Answer {
        return if (!value.isValid) {
            Answer(HTTP_BAD_REQUEST)
        } else {
            processImpl(value, urlParams)
        }
    }

    protected abstract fun processImpl(value: V, urlParams: Map<String, String>): Answer

    @Throws(Exception::class)
    override fun handle(request: Request, response: Response): Any? {
        val unparsedBody: String = if(request.body().isNullOrBlank() || request.body().isEmpty()) "{}"
        else
            request.body()
        println("with body: $unparsedBody")

        val gson = Gson()
        val value = gson.fromJson(unparsedBody, valueClass)
        val queryParams = request.params().toMutableMap()

        for (key in request.headers()) {
                queryParams.putIfAbsent(key,request.headers(key))
        }


        println("with parameters: $queryParams")
        val (code, body) = process(value, queryParams)
        response.status(code)
        response.type("application/json")
        response.body(body)
        return body

    }

}