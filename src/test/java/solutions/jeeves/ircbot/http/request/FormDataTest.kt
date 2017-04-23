package solutions.jeeves.ircbot.http.request

import org.apache.commons.io.IOUtils
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import java.nio.charset.Charset

class FormDataTest {

    @Test
    fun add_addsFormParameter() {
        val formData = FormData()
                .add("key1", "value1")
                .add("key2", "value2")

        val data = formData.entity

        assertThat(
                IOUtils.toString(data.content, Charset.defaultCharset()),
                equalTo("key1=value1&key2=value2"))
    }
}