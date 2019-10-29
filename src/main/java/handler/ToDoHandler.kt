package handler

import AbstractRequestHandler
import Answer
import com.google.gson.Gson
import model.Model

class ToDoHandler(valueClass: Model) : AbstractRequestHandler<DoneItem>(DoneItem::class.java, valueClass) {
    override fun processImpl(value: DoneItem, urlParams: Map<String, String>): Answer {
        model.addToDo(urlParams.getOrDefault(":uid","unknown"),value)
        val gson = Gson()
        val jsonString = gson.toJson(Answer(200,"Success"))
        return Answer.ok(jsonString)
    }

}