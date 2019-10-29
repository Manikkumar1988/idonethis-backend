package handler

import Validable

data class DoneItem (val item: String, val type: Int) : Validable {
    override val isValid: Boolean
        get() = item.isNotEmpty()

}
