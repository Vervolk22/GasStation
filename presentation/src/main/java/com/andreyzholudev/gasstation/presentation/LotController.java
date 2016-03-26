package com.andreyzholudev.gasstation.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/lot")
public class LotController {
    @RequestMapping(value="/addlot")
    public String addLot() {
        return "addlot";
    }
}
