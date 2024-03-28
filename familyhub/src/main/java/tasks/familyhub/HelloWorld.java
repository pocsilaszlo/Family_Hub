package tasks.familyhub;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld{

    @GetMapping(path = "/hello")
    public String helloWorld() {
        return "<h1>Hello World!</h1>";
    }

}
