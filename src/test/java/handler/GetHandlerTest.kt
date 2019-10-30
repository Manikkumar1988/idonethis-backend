package handler

import Answer
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import model.Model
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.util.*

class GetHandlerTest : BaseHandlerTest() {

    @Test
    fun `should return Hello World`() {
        val getHandler = GetHandler(model)

        assertEquals(Answer(200,"Hello World"), getHandler.process(EmptyPayload(), Collections.emptyMap()))
    }
}