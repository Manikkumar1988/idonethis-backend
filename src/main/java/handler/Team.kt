package handler

import Validable

data class Team (val email: String, val teamid: String = "1") : Validable {
    override val isValid: Boolean
        get() = email.isNotEmpty()

}
