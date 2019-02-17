package products.api.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import products.api.models.Product;

import java.util.List;

/**
 * @author miguel.reyes on 2019-02-17.
 */

@RepositoryRestResource(path = "products", collectionResourceRel = "products")
public interface ProductsRestRepository extends
        PagingAndSortingRepository<Product, Long> {
    List<Product> findByName(@Param("name") String role);
}
