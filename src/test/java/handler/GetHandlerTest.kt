package handler

import Answer
import model.Model
import org.junit.Assert.*
import org.junit.Test
import java.util.*

class GetHandlerTest {

    @Test
    fun `should return Hello World`() {
        val getHandler = GetHandler(Model())

        assertEquals(Answer(200,"Hello World"), getHandler.process(EmptyPayload(), Collections.emptyMap()))
    }
}