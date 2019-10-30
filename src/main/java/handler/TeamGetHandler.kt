package handler

import AbstractRequestHandler
import Answer
import com.google.gson.Gson
import model.Model

class TeamGetHandler(valueClass: Model) : AbstractRequestHandler<EmptyPayload>(EmptyPayload::class.java, valueClass) {
    override fun processImpl(value: EmptyPayload, urlParams: Map<String, String>): Answer {

        val teamList = model.getTeamMember(urlParams.getOrDefault(":teamid","1"))
        val gson = Gson()
        val jsonString = gson.toJson(teamList)
        return Answer.ok(jsonString)
    }

}