package life.majiang.community.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wxl
 * @date 2020/3/3 - 11:56
 */

@Controller
public class IndexController {

    @GetMapping("/")
    public String index()
    {
        return "index";
    }

}
