package handler

import Validable

class EmptyPayload : Validable {
    override val isValid: Boolean
        get() = true
}