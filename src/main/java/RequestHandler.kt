interface RequestHandler<V : Validable> {

    fun process(value: V, urlParams: Map<String, String>, shouldReturnHtml: Boolean): Answer

}