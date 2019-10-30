package email

import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.client.util.Base64
import com.google.api.services.gmail.Gmail
import com.google.api.services.gmail.model.Message
import handler.Team
import java.io.ByteArrayOutputStream
import java.util.*
import javax.mail.Session
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeBodyPart
import javax.mail.internet.MimeMessage
import javax.mail.internet.MimeMultipart


class GmailServiceImpl(private val newTrustedTransport: NetHttpTransport, private val gmailCredentials: GmailCredentials): GmailService {
    private val s = "gmailapitest-257514"

    private val jacksonFactory = JacksonFactory.getDefaultInstance()

    override fun sendMessage(recipientAddress: MutableList<Team>, subject: String, body: String): Boolean {
        val message = createMessageWithEmail(
                createEmail(recipientAddress, gmailCredentials.userEmail, subject, body))

        return createGmail().users()
                .messages()
                .send(gmailCredentials.userEmail, message)
                .execute()
                .labelIds.contains("SENT")
    }

    private fun createGmail(): Gmail {
        val credential = authorize()
        return Gmail.Builder(newTrustedTransport, jacksonFactory, credential)
                .setApplicationName(s)
                .build()
    }

    private fun createEmail(to: MutableList<Team>, from: String, subject: String, bodyText: String): MimeMessage {
        val email = MimeMessage(Session.getDefaultInstance(Properties(), null))
        email.setFrom(InternetAddress(from))

        for(t in to) {
            email.addRecipients(javax.mail.Message.RecipientType.TO, t.email)
        }

        email.subject = subject
        val multiPart = MimeMultipart("alternative")

        val htmlPart = MimeBodyPart()
        //htmlPart.setContent("<html><body><table><tr><td><b>aa</b></td><td>bb</td></tr></table><h1>html content</h1></body></html>", "text/html; charset=utf-8")
        htmlPart.setContent(bodyText, "text/html; charset=utf-8")

        multiPart.addBodyPart(htmlPart)

        email.setContent(multiPart)

        return email
    }

    private fun createMessageWithEmail(emailContent: MimeMessage): Message {
        val buffer = ByteArrayOutputStream()
        emailContent.writeTo(buffer)

        return Message()
                .setRaw(Base64.encodeBase64URLSafeString(buffer.toByteArray()))
    }

    private fun authorize(): Credential {
        return GoogleCredential.Builder()
                .setTransport(newTrustedTransport)
                .setJsonFactory(jacksonFactory)
                .setClientSecrets(gmailCredentials.clientId, gmailCredentials.clientSecret)
                .build()
                .setAccessToken(gmailCredentials.accessToken)
                .setRefreshToken(gmailCredentials.refreshToken)
    }
}
