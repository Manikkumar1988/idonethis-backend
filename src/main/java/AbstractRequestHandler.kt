import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import handler.EmptyPayload
import model.Model
import spark.Request
import spark.Response
import spark.Route
import java.io.IOException

abstract class AbstractRequestHandler<V : Validable>(private val valueClass: Class<V>, protected var model: Model) : RequestHandler<V>, Route {

    override fun process(value: V, urlParams: Map<String, String>, shouldReturnHtml: Boolean): Answer {
        return if (!value.isValid) {
            Answer(HTTP_BAD_REQUEST)
        } else {
            processImpl(value, urlParams, shouldReturnHtml)
        }
    }

    protected abstract fun processImpl(value: V, urlParams: Map<String, String>, shouldReturnHtml: Boolean): Answer


    @Throws(Exception::class)
    override fun handle(request: Request, response: Response): Any? {
        try {
            val objectMapper = ObjectMapper()
            var value: V
            //println(valueClass.name)
            //println(EmptyPayload::class)

            /*if (valueClass.name != EmptyPayload::class.qualifiedName)
            {
                value = objectMapper.readValue("{}", valueClass)
            }*/
            value = objectMapper.readValue("{}", valueClass)

            val urlParams = request.params()
            val answer = process(value, urlParams, shouldReturnHtml(request))
            response.status(answer.code)
            if (shouldReturnHtml(request)) {
                response.type("text/html")
            } else {
                response.type("application/json")
            }
            response.body(answer.body)
            return answer.body
        } catch (e: JsonMappingException) {
            response.status(400)
            response.body(e.message)
            return e.message
        }

    }

    companion object {

        private val HTTP_BAD_REQUEST = 400

        private fun shouldReturnHtml(request: Request): Boolean {
            val accept = request.headers("Accept")
            return accept?.contains("text/html") ?: false
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

}