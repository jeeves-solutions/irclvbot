package solutions.jeeves.ircbot.http.client

import org.apache.http.impl.client.BasicCookieStore
import org.apache.http.impl.client.HttpClients
import org.springframework.stereotype.Component

@Component
class BasicHttpClientFactoryImpl : BasicHttpClientFactory {

    override fun get(): BasicHttpClient = BasicHttpClientImpl(getClient())

    private fun getClient() = HttpClients.custom()
            .setDefaultCookieStore(BasicCookieStore())
            .build()
}