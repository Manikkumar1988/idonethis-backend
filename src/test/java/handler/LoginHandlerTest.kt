package handler

import Answer
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import model.Model
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class LoginHandlerTest {

    @MockK
    lateinit var model: Model

    @Before
    fun `set up`() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `should valid user `() {
        val loginHandler = LoginHandler(model)

        val value = User("test1@abc.com", "pass", "")

        every{ model.login(value) } returns mutableListOf(value)

        assertEquals(Answer(200,"{\"userId\":\"test1@abc.com\",\"password\":\"pass\",\"uid\":\"\"}"),
                loginHandler.process(EmptyPayload(), mapOf("Userid" to "test1@abc.com", "Password" to "pass")))
    }

    @Test
    fun `should valid invalid `() {
        val loginHandler = LoginHandler(model)

        val value = User("test1@abc.com", "pass", "")

        every{ model.login(value) } returns mutableListOf()

        assertEquals(Answer(401,""),
                loginHandler.process(EmptyPayload(), mapOf("Userid" to "test1@abc.com", "Password" to "pass")))
    }
}