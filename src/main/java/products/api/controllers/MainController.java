package products.api.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import products.api.http.NotFound;
import products.api.jpa.ProductsRepository;
import products.api.models.Product;

import javax.validation.Valid;
import java.util.Set;

/**
 * @author miguel.reyes on 2019-02-17.
 */
@RestController
public class MainController {

    Logger LOG = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private ProductsRepository repository;

    @GetMapping("/products/{id}")
    public Product findById(@PathVariable(value = "id") Long id) {
        LOG.debug("Find product with Id: " + id);
        return repository.findById(id).orElseThrow(NotFound::new);
    }

    @PostMapping("/products")
    public Set<Product> save(@RequestBody @Valid Set<Product> products) {
        LOG.debug("Saving: " + products.toString());
        for (Product p : products) {
            repository.save(p);
        }
        return products;
    }

    @DeleteMapping("/products/{id}")
    public void delete(@PathVariable(value = "id") Long id) {
        LOG.debug("Deleting: " + id);
        repository.delete(repository.findById(id).orElseThrow(NotFound::new));
    }
}
