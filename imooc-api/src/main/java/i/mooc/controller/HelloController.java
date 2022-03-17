package i.mooc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

//@Controller
@ApiIgnore
@RestController
public class HelloController {
    @GetMapping("/hello")
    public Object hello() {
        return "Hello World~";
    }
}
