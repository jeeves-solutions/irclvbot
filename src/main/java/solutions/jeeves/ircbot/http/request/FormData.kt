package solutions.jeeves.ircbot.http.request

import org.apache.http.HttpEntity
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.message.BasicNameValuePair

class FormData {

    private val data = mutableMapOf<String, String>()

    fun add(key: String, value: String): FormData {
        data[key] = value
        return this
    }

    val entity: HttpEntity
        get() = UrlEncodedFormEntity(data.map { BasicNameValuePair(it.key, it.value) })
}