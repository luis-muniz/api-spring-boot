package com.greenmile.rotas.repositories

import com.greenmile.rotas.models.Route
import org.springframework.data.jpa.repository.JpaRepository

interface IRouteRepository: JpaRepository<Route,Int>