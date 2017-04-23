package solutions.jeeves.ircbot.http.client

import org.apache.commons.io.IOUtils
import org.apache.http.HttpResponse
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpPost
import org.apache.http.client.methods.HttpUriRequest
import org.apache.http.entity.BasicHttpEntity
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.mock
import org.mockito.runners.MockitoJUnitRunner
import solutions.jeeves.ircbot.http.request.FormData
import java.io.ByteArrayInputStream
import java.nio.charset.Charset

@RunWith(MockitoJUnitRunner::class)
class BasicHttpClientImplTest {

    @Mock
    private lateinit var httpClient: HttpClient
    private lateinit var client: BasicHttpClient

    @Captor
    private lateinit var requestCaptor: ArgumentCaptor<HttpUriRequest>

    @Before
    fun setUp() {
        client = BasicHttpClientImpl(httpClient)
    }

    @Test
    fun get() {
        doReturn(response("html result")).`when`(httpClient).execute(requestCaptor.capture())

        val result = client.get("http://test.com")

        assertThat(result, equalTo("html result"))
        assertThat(requestCaptor.value.uri.toString(), equalTo("http://test.com"))
    }

    @Test
    fun post() {
        doReturn(response("html result")).`when`(httpClient).execute(requestCaptor.capture())

        val result = client.post("http://test.com", FormData()
                .add("key1", "value1")
                .add("key2", "value2"))

        val request = requestCaptor.value as HttpPost

        assertThat(result, equalTo("html result"))
        assertThat(request.uri.toString(), equalTo("http://test.com"))
        assertThat(IOUtils.toString(request.entity.content, Charset.defaultCharset()),
                equalTo("key1=value1&key2=value2"))
    }

    private fun response(content: String): HttpResponse {
        val entity = BasicHttpEntity()
        entity.content = ByteArrayInputStream(content.toByteArray())

        val response = mock(HttpResponse::class.java)
        doReturn(entity).`when`(response).entity
        return response
    }
}