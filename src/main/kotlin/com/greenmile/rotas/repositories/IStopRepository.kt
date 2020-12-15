package com.greenmile.rotas.repositories

import com.greenmile.rotas.models.Stop
import org.springframework.data.jpa.repository.JpaRepository

interface IStopRepository:JpaRepository<Stop,Int>