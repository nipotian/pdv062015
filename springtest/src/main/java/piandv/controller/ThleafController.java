package piandv.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import piandv.model.Account;
import piandv.service.AccSrv;

@Controller 
@RequestMapping("/thymeleaf")
public class ThleafController {
	@Autowired
	@Qualifier(AccSrv.NAME1)
	private AccSrv accSrv;
	
	@RequestMapping("")
	public String getIndex(){
		return "redirect:thymeleaf/index";
	}
	
	@RequestMapping(value="index", method=RequestMethod.GET)
	
	public String getPageIndex(@RequestParam(required= false) Integer page, ModelMap model) {
		if(page==null) page=1;
		List<Account> accList = accSrv.getAccList(page);
		int p=accSrv.offsetNum();
		model.put("accList", accList);
		model.put("p",p);
		model.put("pn", page);
		return "thymeleaf/index";
	}
	
	@RequestMapping(value="addAcc", method=RequestMethod.GET)
	public String getPageAddAcc(Account acc){
		return "thymeleaf/addAcc";
	}
	
	@RequestMapping(value="index", method=RequestMethod.POST)
	public String getAddAcc(){
		return "redirect:/thymeleaf/addAcc";
	}
	
	
	
	@RequestMapping(value="addAcc", method=RequestMethod.POST)
	public String addAcc(@Valid Account account, BindingResult brs){
		if(brs.hasErrors())
			return null;
		
		if(accSrv.addAcc(account))
		return "redirect:/thymeleaf";
		else{
			brs.addError(new FieldError("account","id","primary key duplicated"));
		return null;
		}
	}
	
	@RequestMapping(value="editAcc", method=RequestMethod.GET)
	public String getEdit(@RequestParam(required= false) Integer id, Account account, ModelMap m){
		if(id==null)
			return "redirect:thymeleaf";
		account=accSrv.getAcc(id);
		m.put("acc", account);
		if(account==null)
			return "redirect:thymeleaf";
	
		return "thymeleaf/editAcc";
	}
	
	@RequestMapping(value="editAcc", method=RequestMethod.POST)
	public String editAcc(@Valid Account account, BindingResult brs){
		if(brs.hasErrors()) 
			return null;
		accSrv.udtAcc(account);
		return "redirect:/thymeleaf";
	}
	@RequestMapping(value="delAcc", method=RequestMethod.POST)
	public String delAcc(@RequestParam int id){
		accSrv.delAcc(id);
		return "redirect:/thymeleaf";
	}
}
