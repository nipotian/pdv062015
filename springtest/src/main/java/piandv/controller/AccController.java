package piandv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccController {
	@RequestMapping({"","index"})
	public String getIndex(){
		return "account/index";
	}
}
