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
package org.springframework.samples.petclinic.owner

import org.springframework.format.Formatter
import org.springframework.stereotype.Component
import java.text.ParseException
import java.util.*

/**
 * Instructs Spring MVC on how to parse and print elements of type 'PetType'. Starting
 * from Spring 3.0, Formatters have come as an improvement in comparison to legacy
 * PropertyEditors. See the following links for more details: - The Spring ref doc:
 * https://docs.spring.io/spring-framework/docs/current/spring-framework-reference/core.html#format
 *
 * @author Mark Fisher
 * @author Juergen Hoeller
 * @author Michael Isvy
 */
@Component
class PetTypeFormatter(private val types: PetTypeRepository) : Formatter<PetType> {

    override fun print(petType: PetType, locale: Locale): String {
        return petType.name ?: ""
    }

    @Throws(ParseException::class)
    override fun parse(text: String, locale: Locale): PetType {
        val findPetTypes = this.types.findPetTypes()
        for (type in findPetTypes) {
            if (type.name == text) {
                return type
            }
        }
        throw ParseException("type not found: $text", 0)
    }
}