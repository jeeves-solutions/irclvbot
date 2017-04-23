package solutions.jeeves.ircbot.http.client

interface BasicHttpClientFactory {
    fun get(): BasicHttpClient
}