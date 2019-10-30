package handler

import AbstractRequestHandler
import Answer
import com.google.gson.Gson
import email.EmailService
import model.Model

class EmailReportHandler(valueClass: Model, private val emailService: EmailService) : AbstractRequestHandler<EmptyPayload>(EmptyPayload::class.java, valueClass) {
    override fun processImpl(value: EmptyPayload, urlParams: Map<String, String>): Answer {

        val teamList = model.getTeamMember(urlParams.getOrDefault(":teamId", "1"))

        emailService.sendReport(teamList, "")

        val gson = Gson()
        val jsonString = gson.toJson(Status("success"))
        return Answer.ok(jsonString)
    }

}