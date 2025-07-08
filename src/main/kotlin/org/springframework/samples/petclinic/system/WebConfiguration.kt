/*
 * Copyright 2012-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.system

import java.util.Locale
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.LocaleResolver
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor
import org.springframework.web.servlet.i18n.SessionLocaleResolver

/**
 * Configures internationalization (i18n) support for the application.
 *
 * Handles loading language-specific messages, tracking the user's language, and allowing
 * language changes via the URL parameter (e.g., `?lang=de`).
 *
 * @author Anuj Ashok Potdar
 */
@Configuration
@Suppress("unused")
class WebConfiguration : WebMvcConfigurer {

    /**
     * Uses session storage to remember the user's language setting across requests.
     * Defaults to English if nothing is specified.
     * @return session-based [LocaleResolver]
     */
    @Bean
    fun localeResolver(): LocaleResolver {
        val resolver = SessionLocaleResolver()
        resolver.setDefaultLocale(Locale.ENGLISH)
        return resolver
    }

    /**
     * Allows the app to switch languages using a URL parameter like
     * `?lang=es`.
     * @return a [LocaleChangeInterceptor] that handles the change
     */
    @Bean
    fun localeChangeInterceptor(): LocaleChangeInterceptor {
        val interceptor = LocaleChangeInterceptor()
        interceptor.paramName = "lang"
        return interceptor
    }

    /**
     * Registers the locale change interceptor so it can run on each request.
     * @param registry where interceptors are added
     */
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(localeChangeInterceptor())
    }
}
