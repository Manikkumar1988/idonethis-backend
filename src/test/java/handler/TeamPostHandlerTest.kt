package handler

import Answer
import io.mockk.every
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Test

class TeamPostHandlerTest : BaseHandlerTest() {

    @Test
    fun `should add a todo`() {
        val teamHandler = TeamPostHandler(model)

        val team = Team("abc@test.com")
        val urlParams = mapOf(":teamId" to "1")

        every { model.addTeamMember("1",team) } just runs

        assertEquals(Answer(200,"{\"status\":\"success\"}"), teamHandler.process(team, urlParams))

        verify { model.addTeamMember("1", team)}
    }
}