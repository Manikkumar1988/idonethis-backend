package handler

import AbstractRequestHandler
import Answer
import com.google.gson.Gson
import model.Model

class LoginHandler(valueClass: Model) : AbstractRequestHandler<EmptyPayload>(EmptyPayload::class.java, valueClass) {
    override fun processImpl(value: EmptyPayload, urlParams: Map<String, String>): Answer {
        val user = model.login(User(urlParams.getOrDefault("Userid",""),urlParams.getOrDefault("Password",""),""))
        return if (user.size>0) {
            val gson = Gson()
            val jsonString = gson.toJson(user.first())
            Answer.ok(jsonString)
        } else
            Answer(401,"")
    }

}