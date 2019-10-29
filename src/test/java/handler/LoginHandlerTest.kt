package handler

import Answer
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import model.Model
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.util.*

class LoginHandlerTest {

    @MockK
    lateinit var model: Model

    @Before
    fun `set up`() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `should return Hello World`() {
        val getHandler = LoginHandler(model)

        val value = User("test1@abc.com", "pass", "")

        every{ model.login(value) } returns mutableListOf(value)

        assertEquals(Answer(200,"{\"userId\":\"test1@abc.com\",\"password\":\"pass\",\"uid\":\"\"}"),
                getHandler.process(EmptyPayload(), mapOf("Userid" to "test1@abc.com", "Password" to "pass")))
    }
}