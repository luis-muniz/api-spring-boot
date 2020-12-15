package com.greenmile.rotas.models

import com.fasterxml.jackson.annotation.JsonBackReference
import javax.persistence.*

@Entity
data class Stop(
        @Id
        @GeneratedValue
        val id: Int? = null,
        val clientName: String? = null,
        val address: String? = null,
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "route_id", nullable = false)
        @JsonBackReference
        val route: Route = Route()
)