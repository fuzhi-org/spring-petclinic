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
package org.springframework.samples.petclinic.vet

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody

/**
 * @author Juergen Hoeller
 * @author Mark Fisher
 * @author Ken Krebs
 * @author Arjen Poutsma
 */
@Controller
internal class VetController(private val vetRepository: VetRepository) {

    @GetMapping("/vets.html")
    fun showVetList(@RequestParam(defaultValue = "1") page: Int, model: Model): String {
        // Here we are returning an object of type 'Vets' rather than a collection of Vet
        // objects so it is simpler for Object-Xml mapping
        val vets = Vets()
        val paginated = findPaginated(page)
        vets.vetList.addAll(paginated.toList())
        return addPaginationModel(page, paginated, model)
    }

    private fun addPaginationModel(page: Int, paginated: Page<Vet>, model: Model): String {
        val listVets = paginated.content
        model.addAttribute("currentPage", page)
        model.addAttribute("totalPages", paginated.totalPages)
        model.addAttribute("totalItems", paginated.totalElements)
        model.addAttribute("listVets", listVets)
        return "vets/vetList"
    }

    private fun findPaginated(page: Int): Page<Vet> {
        val pageSize = 5
        val pageable: Pageable = PageRequest.of(page - 1, pageSize)
        return vetRepository.findAll(pageable)
    }

    @GetMapping("/vets")
    @ResponseBody
    fun showResourcesVetList(): Vets {
        // Here we are returning an object of type 'Vets' rather than a collection of Vet
        // objects so it is simpler for JSon/Object mapping
        val vets = Vets()
        vets.vetList.addAll(this.vetRepository.findAll())
        return vets
    }
}
