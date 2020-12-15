package com.greenmile.rotas.controllers

import com.greenmile.rotas.models.Route
import com.greenmile.rotas.models.Stop
import com.greenmile.rotas.repositories.IRouteRepository
import com.greenmile.rotas.repositories.IStopRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/stop")
class StopController(
        private val routeRepository: IRouteRepository,
        private val stopRepository: IStopRepository,
) {

    @PostMapping("/{routeId}")
    fun addStop(@PathVariable routeId: Int, @RequestBody stop : Stop): ResponseEntity<Stop> {
        return if (this.routeRepository.existsById(routeId)){
            val newStop = stop.copy(route = Route(id = routeId))
            this.stopRepository.save(newStop)
            ResponseEntity.ok().body(newStop)
        }else{
            ResponseEntity(HttpStatus.PRECONDITION_FAILED)
        }
    }

    @DeleteMapping("/{routeId}/{stopId}")
    fun deleteStop(@PathVariable routeId: Int, @PathVariable stopId: Int,){
       if (this.routeRepository.existsById(routeId)) {
            this.stopRepository.deleteById(stopId)
       }
    }
}