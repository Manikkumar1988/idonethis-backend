package handler

import Answer
import email.EmailService
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import org.junit.Assert.*
import org.junit.Test

class EmailRemindHandlerTest : BaseHandlerTest() {

    @MockK
    lateinit var emailService: EmailService

    @Test
    fun `should call email remind with appropriate mail list`() {
        val team = mutableListOf(Team("abc@test.com","1"))
        every { model.getTeamMember("1") } returns team
        every { emailService.sendReminder(team) } just runs

        val urlParams = mapOf(":teamId" to "1")
        val emailRemindHandler = EmailRemindHandler(model, emailService)
        val answer = emailRemindHandler.process(EmptyPayload(), urlParams)

        assertEquals(Answer(200,"{\"status\":\"success\"}"), answer)

        verify { emailService.sendReminder(team) }
        verify { model.getTeamMember("1") }
    }
}