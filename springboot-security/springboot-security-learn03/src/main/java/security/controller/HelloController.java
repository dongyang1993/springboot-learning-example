package security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import security.entity.MineUser;
import security.service.UserService;

@RestController
public class HelloController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/user")
    public void user(@ModelAttribute MineUser mineUser) {
        MineUser po = userService.save(mineUser);
        System.out.println(po);
    }

}
