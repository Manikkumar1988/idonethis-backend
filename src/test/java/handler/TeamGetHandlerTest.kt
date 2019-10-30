package handler

import Answer
import io.mockk.every
import io.mockk.verify
import org.junit.Assert.*
import org.junit.Test

class TeamGetHandlerTest : BaseHandlerTest() {
    @Test
    fun `should retrieve all team`() {
        val teamGetHandler = TeamGetHandler(model)

        val urlParams = mapOf(":teamId" to "1")

        every { model.getTeamMember("1") } returns mutableListOf(Team("abc@test.com"))

        assertEquals(Answer(code=200, body="[{\"email\":\"abc@test.com\",\"teamid\":\"1\"}]"), teamGetHandler.process(EmptyPayload(), urlParams))

        verify { model.getTeamMember("1")}
    }
}