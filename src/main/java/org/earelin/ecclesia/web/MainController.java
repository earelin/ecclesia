package org.earelin.ecclesia.web;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

	@RequestMapping(path="/", method=RequestMethod.GET)
	public String index(Map<String, Object> model) {
		return "home";
	}
    
    @RequestMapping(value="/dashboard", method=RequestMethod.GET)
	public String dashboard(Map<String, Object> model) {
		return "dashboard";
	}

}