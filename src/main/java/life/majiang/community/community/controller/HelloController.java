package life.majiang.community.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.PublicKey;

/**
 * @author wxl
 * @date 2020/3/3 - 11:56
 */

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(@RequestParam(name="name") String name, Model model)
    {
        model.addAttribute("name",name);
        return "hello";
    }

}
