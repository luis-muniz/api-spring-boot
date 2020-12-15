package com.greenmile.rotas.models

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
data class Route(
        @Id
        @GeneratedValue
        val id: Int? = null,
        val name: String? = null,
        @OneToMany(mappedBy = "route")
        val stops: List<Stop> = emptyList()
)