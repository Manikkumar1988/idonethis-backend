data class Answer(val code: Int = 0, val body: String = "") {

    companion object {
        fun ok(body: String): Answer {
            return Answer(200, body)
        }
    }
}