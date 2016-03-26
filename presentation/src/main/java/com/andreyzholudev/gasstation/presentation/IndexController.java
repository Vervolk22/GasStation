package com.andreyzholudev.gasstation.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value={"/main", "/"})
public class IndexController {
    @RequestMapping(value={"/index",""})
    public String index() {
        return "index";
    }
}
