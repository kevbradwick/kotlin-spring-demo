package com.kodefoundry.apidemo

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

/**
 * Classes in Kotlin are final by default. Spring boot configuration classes
 * cannot be final, therefore the *open* keyword is used to remove the final
 * behaviour.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan("com.kodefoundry.apidemo")
open class Application {

    /**
     * Mimics the static main method of a bog standard Java class.
     */
    companion object {
        @JvmStatic public fun main(args: Array<String>) {
            SpringApplication.run(Application::class.java, *args)
        }
    }
}
