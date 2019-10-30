package email


interface GmailService {
    fun sendMessage(recipientAddress: Array<String>, subject: String, body: String): Boolean
}