package products.api.controllers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import products.api.jpa.ProductsRepository;
import products.api.models.Label;
import products.api.models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author miguel.reyes on 2019-02-18.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(MainController.class)
public class MainControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductsRepository repository;


    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findByIdReturnsOk() throws Exception {
        Product product = new Product();
        product.name = "Hello World";
        product.label = Label.PRODUCTS;
        given(repository.findById(1L)).willReturn(Optional.of(product));

        mvc.perform(get("/products/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void findByIdReturnsNotFound() throws Exception {
        given(repository.findById(1L)).willReturn(Optional.empty());

        mvc.perform(get("/products/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void saveShouldReturnOk() throws Exception {
        Product product = new Product();
        product.name = "Hello World";
        product.label = Label.PRODUCTS;
        given(repository.findByName(anyString())).willReturn(new ArrayList<>());
        when(repository.save(any())).thenReturn(product);

        mvc.perform(post("/products").content("[\n" +
                "    {\n" +
                "        \"name\": \"Hello World\",\n" +
                "        \"label\": \"PRODUCTS\"\n" +
                "    }\n" +
                "]")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(product.name)));
    }


    @Test
    public void saveShouldReturnBadRequestWhenProductNameAlreadyExists() throws Exception {
        List<Product> existing = new ArrayList<>();
        Product product = new Product();
        product.name = "Hello World";
        product.label = Label.PRODUCTS;
        existing.add(product);

        given(repository.findByName(anyString())).willReturn(existing);
        when(repository.save(any())).thenReturn(product);

        mvc.perform(post("/products").content("[\n" +
                "    {\n" +
                "        \"name\": \"Hello World\",\n" +
                "        \"label\": \"PRODUCTS\"\n" +
                "    }\n" +
                "]")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void saveShouldReturnBadRequest() throws Exception {
        given(repository.findByName(anyString())).willReturn(new ArrayList<>());
        when(repository.save(any())).thenThrow(new RuntimeException("JPA Bean validation exception"));

        mvc.perform(post("/products").content("[\n" +
                "    {\n" +
                "        \"name\": \"\",\n" +
                "        \"label\": \"PRODUCTS\"\n" +
                "    }\n" +
                "]")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteShouldReturnOk() throws Exception {
        Product product = new Product();
        product.name = "Hello World";
        product.label = Label.PRODUCTS;
        given(repository.findById(1L)).willReturn(Optional.of(product));

        mvc.perform(delete("/products/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteShouldReturnNotFound() throws Exception {
        given(repository.findById(1L)).willReturn(Optional.empty());

        mvc.perform(delete("/products/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}