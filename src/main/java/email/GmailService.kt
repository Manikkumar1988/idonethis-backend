package email

import handler.Team


interface GmailService {
    fun sendMessage(recipientAddress: MutableList<Team>, subject: String, body: String): Boolean
}