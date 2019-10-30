package email

data class GmailCredentials(val userEmail: String, val clientId: String, val clientSecret: String,
                            val accessToken: String, val refreshToken: String)
