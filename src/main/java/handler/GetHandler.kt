package handler

import AbstractRequestHandler
import Answer
import model.Model

class GetHandler(valueClass: Model) : AbstractRequestHandler<EmptyPayload>(EmptyPayload::class.java, valueClass) {
    override fun processImpl(value: EmptyPayload, urlParams: Map<String, String>, shouldReturnHtml: Boolean): Answer {
        return Answer.ok("Hello World")
    }

}