package products.api.controllers;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import products.api.http.exceptions.InvalidProductException;
import products.api.http.exceptions.NotFoundException;
import products.api.http.exceptions.ProductExistsException;
import products.api.jpa.ProductsRepository;
import products.api.models.Product;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
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
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }

    @PostMapping("/products")
    public Set<Product> save(@Valid @RequestBody Set<Product> products) {
        LOG.debug("Saving: " + products.toString());

        //First validate that we can save all the products in the payload
        List<String> existing = new ArrayList<>();
        for (Product p : products) {
            List<Product> byName = repository.findByName(p.name);
            if (byName.size() > 0) {
                existing.add(p.name);
            }
        }

        if (existing.size() > 0) {
            // Return a list of ALL the product names in the payload that already exist in the database.
            throw new ProductExistsException("Product already exists: " + Strings.join(existing, ','));
        }

        //Once we validated that the payload is correct, save all products.
        for (Product p : products) {
            try {
                repository.save(p);
            } catch (Exception e) {
                //Turns out Bean validation with @Valid doesn't work for a java collection (Set<Products> in this case). This will catch bean validation errors.
                throw new InvalidProductException("Invalid product, name=" + p.name + ", label=" + p.label + ".");
            }
        }

        return products;
    }

    @DeleteMapping("/products/{id}")
    public void delete(@PathVariable(value = "id") Long id) {
        LOG.debug("Deleting: " + id);
        repository.delete(repository.findById(id).orElseThrow(NotFoundException::new));
    }
}
