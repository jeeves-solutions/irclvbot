package solutions.jeeves.ircbot.http.client

import org.apache.commons.io.IOUtils
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpPost
import org.apache.http.client.methods.HttpUriRequest
import solutions.jeeves.ircbot.http.request.FormData
import java.nio.charset.Charset

class BasicHttpClientImpl(private val client: HttpClient) : BasicHttpClient {

    override fun get(url: String): String {
        return execute(HttpGet(url))
    }

    override fun post(url: String, data: FormData): String {
        val req = HttpPost(url)
        req.entity = data.entity
        return execute(req)
    }

    private fun execute(req: HttpUriRequest): String {
        val content = client.execute(req).entity.content
        return IOUtils.toString(content, Charset.defaultCharset())
    }
}