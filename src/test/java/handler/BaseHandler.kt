package handler

import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import model.Model
import org.junit.Before

open class BaseHandlerTest {

    @MockK
    lateinit var model: Model

    @Before
    fun `set up`() {
        MockKAnnotations.init(this)
    }
}