package products.api.http;


import products.api.models.Label;

import javax.validation.constraints.*;

/**
 * @author miguel.reyes on 2019-02-17.
 */
public class ProductsRequest {

    @Size(max = 50, message = "name cannot be empty and max 50 characters long.")
    @NotBlank
    private String name;

    private String parentId;

    @NotNull
    private Label label;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }
}


