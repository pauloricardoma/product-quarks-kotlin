package org.acme.service

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import org.acme.dto.ProductDTO
import org.acme.entity.ProductEntity
import org.acme.repository.ProductRepository

@ApplicationScoped
class ProductService {
    @Inject
    lateinit var productRepository: ProductRepository

    fun getAllProducts(): List<ProductDTO> {
        val products = ArrayList<ProductDTO>()

        productRepository.findAll().stream().forEach { product ->
            products.add(product.toDTO())
        }

        return products
    }

    fun createNewProduct(productDTO: ProductDTO) {
        productRepository.persist(productDTO.toEntity())
    }

    fun changeProduct(id: Long, productDTO: ProductDTO) {
        val productEntity = productRepository.findById(id)

        if (productEntity !== null) {
            productEntity.name = productDTO.name
            productEntity.description = productDTO.description
            productEntity.category = productDTO.category
            productEntity.model = productDTO.model
            productEntity.price = productDTO.price

            productRepository.persist(productEntity)
        }
    }

    fun deleteProduct(id: Long) {
        productRepository.deleteById(id)
    }

    private fun ProductEntity.toDTO(): ProductDTO {
        val product = ProductDTO(
            this.name,
            this.description,
            this.category,
            this.model,
            this.price
        )
        return product
    }
    private fun ProductDTO.toEntity(): ProductEntity {
        val product = ProductEntity(
            name = this.name,
            description = this.description,
            category = this.category,
            model = this.model,
            price = this.price
        )
        return product
    }
}