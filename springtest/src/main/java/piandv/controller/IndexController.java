package piandv.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/")
public class IndexController {

	@RequestMapping({"","index"})
	public String getIndex(){
		return "index";
	}
	@RequestMapping("login")
	public String getLogin(){
		return "login";
	}
	@RequestMapping("erpage")
	public String getErpage(){
		return "erpage";
	}
	
}
