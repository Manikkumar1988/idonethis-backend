package handler

import AbstractRequestHandler
import Answer
import com.google.gson.Gson
import model.Model

class TeamPostHandler(valueClass: Model) : AbstractRequestHandler<Team>(Team::class.java, valueClass) {
    override fun processImpl(value: Team, urlParams: Map<String, String>): Answer {
        model.addTeamMember(urlParams.getOrDefault(":teamId","1"), value)

        val gson = Gson()
        val jsonString = gson.toJson(Status("success"))
        return Answer.ok(jsonString)
    }

}