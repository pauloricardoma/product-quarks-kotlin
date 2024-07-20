package org.acme.controller

import jakarta.inject.Inject
import jakarta.transaction.Transactional
import jakarta.ws.rs.DELETE
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.PUT
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.acme.dto.ProductDTO
import org.acme.service.ProductService

@Path("/api/products")
class ProductController {
    @Inject
    lateinit var productService: ProductService

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun findAllProducts(): List<ProductDTO> {
        return productService.getAllProducts()
    }

    @POST
    @Transactional
    fun saveProduct(productDTO: ProductDTO): Response {
        try {
            productService.createNewProduct(productDTO)
            return Response.ok().build()
        } catch(e: Exception) {
            e.printStackTrace()
            return Response.serverError().build()
        }
    }

    @PUT
    @Path("/{id}")
    @Transactional
    fun changeProduct(@PathParam("id") id: Long, productDTO: ProductDTO): Response {
        try {
            productService.changeProduct(id, productDTO)
            return Response.ok().build()
        } catch(e: Exception) {
            e.printStackTrace()
            return Response.serverError().build()
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    fun removeProduct(@PathParam("id") id: Long): Response {
        try {
            productService.deleteProduct(id)
            return Response.ok().build()
        } catch(e: Exception) {
            e.printStackTrace()
            return Response.serverError().build()
        }
    }
}