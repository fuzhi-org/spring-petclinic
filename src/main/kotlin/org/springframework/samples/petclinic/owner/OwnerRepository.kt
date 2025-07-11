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

import java.util.Optional
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Repository class for [Owner] domain objects. All method names are compliant
 * with Spring Data naming conventions so this interface can easily be extended for Spring
 * Data. See:
 * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.query-creation
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @author Michael Isvy
 * @author Wick Dynex
 */
interface OwnerRepository : JpaRepository<Owner, Int> {

    /**
     * Retrieve [Owner]s from the data store by last name, returning all owners
     * whose last name *starts* with the given name.
     * @param lastName Value to search for
     * @return a Collection of matching [Owner]s (or an empty Collection if none
     * found)
     */
    fun findByLastNameStartingWith(lastName: String, pageable: Pageable): Page<Owner>

    /**
     * Retrieve an [Owner] from the data store by id.
     *
     * This method returns an [Optional] containing the [Owner] if found. If
     * no [Owner] is found with the provided id, it will return an empty
     * [Optional].
     *
     * @param id the id to search for
     * @return an [Optional] containing the [Owner] if found, or an empty
     * [Optional] if not found.
     * @throws IllegalArgumentException if the id is null (assuming null is not a valid
     * input for id)
     */
    override fun findById(id: Int): Optional<Owner>
}
