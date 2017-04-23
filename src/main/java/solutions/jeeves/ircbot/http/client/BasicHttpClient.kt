package solutions.jeeves.ircbot.http.client

import solutions.jeeves.ircbot.http.request.FormData

interface BasicHttpClient {
    fun get(url: String): String
    fun post(url: String, data: FormData): String
}