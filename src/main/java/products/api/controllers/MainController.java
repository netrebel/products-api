package products.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author miguel.reyes on 2019-02-17.
 */
@RestController
public class MainController {

    @GetMapping("/hello")
    public String getHello(@RequestParam(value = "name", defaultValue = "") String name) {
        return "Hello " + name;
    }
}
