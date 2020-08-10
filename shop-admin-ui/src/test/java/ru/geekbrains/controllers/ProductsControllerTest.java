package ru.geekbrains.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import ru.geekbrains.persist.enity.Product;
import ru.geekbrains.persist.repl.ProductRepl;
import ru.geekbrains.persist.service.interdafaces.ProductServerInterface;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
@SpringBootTest
public class ProductsControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ProductServerInterface productService;

    @WithMockUser(value = "admin", password = "admin", roles = {"ADMIN"})
    @Test
    public void indexProducts() throws Exception {
        mvc.perform(get("/product")
                .param("minCost", "2")
                .param("maxCost", "20000")
                .param("page", "1")
                .param("pageSize", "50")
                .param("title", "product"))
                .andExpect(view().name("product/products"))
                .andExpect(status().isOk());
    }

    @WithMockUser(value = "admin", password = "admin", roles = {"ADMIN"})
    @Test
    public void createProduct() throws Exception {
        mvc.perform(get("/product/create"))
                .andExpect(view().name("product/form"))
                .andExpect(status().isOk());
    }

    @WithMockUser(value = "admin", password = "admin", roles = {"ADMIN"})
    @Test
    public void updateProduct() throws Exception {
        mvc.perform(get("/product/update/" + 2))
                .andExpect(view().name("product/form"))
                .andExpect(status().isOk());
    }

    @WithMockUser(value = "admin", password = "admin", roles = {"ADMIN"})
    @Test
    public void deleteProduct() throws Exception {

        ProductRepl productRepl = new ProductRepl();
        productRepl.setTitle("Test product 104");
        productRepl.setDescription("Test product 104");
        productRepl.setCost(new BigDecimal("100"));
        productService.save(productRepl);

        mvc.perform(get("/product/delete/" + productRepl.getId()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/product"))
                .andExpect(status().is3xxRedirection());
    }

    @WithMockUser(value = "admin", password = "admin", roles = {"ADMIN"})
    @Test
    public void save() throws Exception {
        mvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "2")
                .param("title", "product test")
                .param("description", "description Test")
                .param("cost", "555")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/product"));


        Optional<Product> product = productService.findById(2);

        assertTrue(product.isPresent());
        assertEquals("product test", product.get().getTitle());
        assertEquals("description Test", product.get().getDescription());
    }

}
