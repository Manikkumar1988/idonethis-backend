package handler

import Answer
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.just
import io.mockk.runs
import model.Model
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ToDoHandlerTest {
    @MockK
    lateinit var model: Model

    @Before
    fun `set up`() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `should add a todo`() {
        val toDoHandler = ToDoHandler(model)

        val doneItem = DoneItem("Task A",0)
        val urlParams = mapOf(":uid" to "1")

        every { model.addToDo("1",doneItem) } just runs

        assertEquals(Answer(200,"{\"status\":\"success\"}"), toDoHandler.process(doneItem, urlParams))
    }
}