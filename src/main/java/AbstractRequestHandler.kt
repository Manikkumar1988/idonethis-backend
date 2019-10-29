import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import model.Model
import spark.Request
import spark.Response
import spark.Route
import java.io.IOException
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
        val objectMapper = ObjectMapper()
        val unparsedBody: String = if(request.body().isNullOrBlank() || request.body().isNotEmpty()) "{}"
        else
            request.body()
        val value = objectMapper.readValue(unparsedBody, valueClass)
        val queryParams = request.params().toMutableMap()

        for (key in request.headers()) {
                queryParams.putIfAbsent(key,request.headers(key))
        }
        val (code, body) = process(value, queryParams)
        response.status(code)
        response.type("application/json")
        response.body(body)
        return body

    }

    fun dataToJson(data: Any): String {
        try {
            val mapper = ObjectMapper()
            mapper.enable(SerializationFeature.INDENT_OUTPUT)
            return mapper.writeValueAsString(data)
        } catch (e: IOException) {
            throw RuntimeException("IOException from a StringWriter?")
        }

    }

}