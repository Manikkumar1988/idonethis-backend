package handler

import AbstractRequestHandler
import Answer
import com.google.gson.Gson
import model.Model

class LoginHandler(valueClass: Model) : AbstractRequestHandler<EmptyPayload>(EmptyPayload::class.java, valueClass) {
    override fun processImpl(value: EmptyPayload, urlParams: Map<String, String>): Answer {
        val user = model.login(User(urlParams.getOrDefault("Userid",""),urlParams.getOrDefault("Password",""),""))
        var gson = Gson()
        var jsonString = gson.toJson(user.first())
        return Answer.ok(jsonString)
    }

}