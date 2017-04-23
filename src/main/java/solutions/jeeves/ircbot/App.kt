package solutions.jeeves.ircbot

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import solutions.jeeves.ircbot.http.client.BasicHttpClientFactory

fun main(args: Array<String>) {
    val context = AnnotationConfigApplicationContext(Config::class.java)
    val clientFactory = context.getBean(BasicHttpClientFactory::class.java)

    val client = clientFactory.get()
    println(client.get("http://irc.lv"))
}
