package products.api.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * @author miguel.reyes on 2019-02-17.
 */
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(max = 50, message = "name cannot be empty and max 50 characters long.")
    @NotBlank
    public String name;

    public String parentId;

    @NotNull
    @Enumerated(EnumType.STRING)
    public Label label;

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", parentId='" + parentId + '\'' +
                ", label=" + label +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return name.equals(product.name) &&
                label == product.label;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, label);
    }

}
