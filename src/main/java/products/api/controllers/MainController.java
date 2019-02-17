package products.api.controllers;

import org.springframework.web.bind.annotation.*;
import products.api.models.Product;

import javax.validation.Valid;
import java.util.Set;

/**
 * @author miguel.reyes on 2019-02-17.
 */
@RestController
public class MainController {

    @GetMapping("/products/{id}")
    public String findAll(@PathVariable(value = "id") String id) {
        return "Getting " + id; //TODO Implement
    }

    @PostMapping("/products")
    public String save(@RequestBody @Valid Set<Product> request) {
        System.out.println(request);
        return "ok"; //TODO Implement and return response object instead
    }

    @DeleteMapping("/products/{id}")
    public void delete(@PathVariable(value = "id") String id) {
        System.out.println("Deleting " + id); //TODO Implement
    }
}
