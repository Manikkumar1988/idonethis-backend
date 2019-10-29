package handler

import Validable

data class User (val userId: String, val password: String, val uid: String) : Validable {
    override val isValid: Boolean
        get() = userId.isNotEmpty() && password.isNotEmpty()

}
