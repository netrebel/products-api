package products.api.jpa;

import org.springframework.data.repository.CrudRepository;
import products.api.models.Product;

import java.util.List;

/**
 * @author miguel.reyes on 2019-02-17.
 */

public interface ProductsRepository extends CrudRepository<Product, Long> {
    List<Product> findByName(String name);
}