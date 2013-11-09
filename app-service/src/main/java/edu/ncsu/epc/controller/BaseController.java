package edu.ncsu.epc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BaseController {
	@RequestMapping(value="/index", method = RequestMethod.GET)
    public String index() {
		System.out.println("index");
        return "/views/social_apt_hunt.html";
    }
}
