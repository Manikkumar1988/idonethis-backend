package email

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport


class EmailService  {
    fun sendReminder(recipientAddress: Array<String>) {
        val gmailService = GmailServiceImpl(GoogleNetHttpTransport.newTrustedTransport(), GmailCredentials(
                "maniksrr@gmail.com",
                "118306839541-92mci2f2978cjvlk2ininq0a8u4o7297.apps.googleusercontent.com",
                "yKvFeV8hnZN_kBG6puZliPVA",
                "ya29.Il-pB13bQxPzx6AKLzCvehvFCq8yrQeCPxoGrtsE1e0VRFec68BNw0U4sefjdgfgN4IvfMm1ADkVD2vn5jB7VpmHiTFc3gUUt0l7qKD2azU0dnaU3eijt-Ao6rCKiecLmA",
                "1//0gOZzI0D_kPpGCgYIARAAGBASNwF-L9IrevDS30zs5c3ka4JA0PnAouILIfBXJatOAVe0SrPMqXgIZYgkmZgW8cwKJkV6bnGDS6M"))

        gmailService.sendMessage(recipientAddress, "Standup Reminder", "<html><h1>Don't forget to update your status</h1></body></html>")
    }


    fun sendReport(recipientAddress: Array<String>, body: String) {
        val gmailService = GmailServiceImpl(GoogleNetHttpTransport.newTrustedTransport(), GmailCredentials(
                "maniksrr@gmail.com",
                "118306839541-92mci2f2978cjvlk2ininq0a8u4o7297.apps.googleusercontent.com",
                "yKvFeV8hnZN_kBG6puZliPVA",
                "ya29.Il-pB13bQxPzx6AKLzCvehvFCq8yrQeCPxoGrtsE1e0VRFec68BNw0U4sefjdgfgN4IvfMm1ADkVD2vn5jB7VpmHiTFc3gUUt0l7qKD2azU0dnaU3eijt-Ao6rCKiecLmA",
                "1//0gOZzI0D_kPpGCgYIARAAGBASNwF-L9IrevDS30zs5c3ka4JA0PnAouILIfBXJatOAVe0SrPMqXgIZYgkmZgW8cwKJkV6bnGDS6M"))

        gmailService.sendMessage(recipientAddress, "Here's the updates", "<html><h1>${body}</h1></body></html>")
    }
}