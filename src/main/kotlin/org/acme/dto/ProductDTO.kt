package org.acme.dto

import java.math.BigDecimal

data class ProductDTO(
    val name: String,
    val description: String,
    val category: String,
    val model: String,
    val price: BigDecimal,
)
