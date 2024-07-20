package org.acme.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import java.math.BigDecimal

@Entity
data class ProductEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var name: String,
    var description: String,
    var category: String,
    var model: String,
    var price: BigDecimal,
) {
    constructor() : this(null, "", "", "", "", BigDecimal.ZERO)
}
