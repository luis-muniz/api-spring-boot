package com.greenmile.rotas.controllers

import com.greenmile.rotas.models.Route
import com.greenmile.rotas.repositories.IRouteRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/route")
class RouteController(
        private val routeRepository: IRouteRepository
) {

    @PostMapping
    fun create(@RequestBody newRoute:Route): ResponseEntity<Route>{
        this.routeRepository.save(newRoute)

        return ResponseEntity.ok().body(newRoute)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Int): ResponseEntity<Optional<Route>> {
        val route = this.routeRepository.findById(id)

        return if (route.isPresent)
            ResponseEntity.ok().body(route)
        else
            ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @GetMapping
    fun findAll() = ResponseEntity.ok().body(this.routeRepository.findAll())

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun deleteOne(@PathVariable id: Int){
        this.routeRepository.deleteById(id)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id : Int, @RequestBody newRoute:Route): ResponseEntity<Route>? {
        return this.routeRepository.findById(id)
                .map { route ->
                    val newRouteVersion = newRoute.copy(id = route.id, stops = route.stops)
                    this.routeRepository.save(newRouteVersion)
                    ResponseEntity.ok().body(newRouteVersion)
                }.orElse(ResponseEntity(HttpStatus.NOT_FOUND))
    }
}