package handler

import Validable

data class DoneItemV1 (val uid:String = "1", val name: String, val type: Int, val createdat: String) : Validable {
    override val isValid: Boolean
        get() = name.isNotEmpty()

}
