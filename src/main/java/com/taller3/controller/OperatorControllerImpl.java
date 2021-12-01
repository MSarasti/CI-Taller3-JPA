package com.taller3.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taller3.model.prod.*;
import com.taller3.model.sales.*;
import com.taller3.model.validation.addValidation;
import com.taller3.model.validation.updateValidation;
import com.taller3.service.implementation.*;

@Controller
public class OperatorControllerImpl {
	@Autowired
	public ProductServiceImpl pService;
	@Autowired
	public SpecialOfferServiceImpl soService;
	@Autowired
	public SpecialOfferProductServiceImpl sopService;
	@Autowired
	public SalesOrderDetailServiceImpl sodService;
	
	public OperatorControllerImpl() {
	}

	@GetMapping("/operator/")
	public String indexOperator(Model model) {
		return "operator/index";
	}
	
	@GetMapping("/specialoffer")
    public String indexSpecialOffer(Model model) {
		model.addAttribute("specialoffers", soService.findAll());
        return "operator/specialoffers";
    }
	
	@GetMapping("/specialofferproduct")
    public String indexSpecialOfferProduct(Model model) {
		model.addAttribute("specialofferproducts", sopService.findAll());
        return "operator/specialofferproducts";
    }
	
	@GetMapping("/salesorderdetail")
    public String indexSOD(Model model) {
		model.addAttribute("salesorderdetails", sodService.findAll());
        return "operator/salesorderdetails";
    }
	
	@GetMapping("/specialoffer/add")
	public String addSO(Model model) {
		model.addAttribute("specialoffer", new Specialoffer());
		return "operator/addSO";
	}
	
	@PostMapping("/specialoffer/add")
	public String saveSO(@Validated({ addValidation.class }) @ModelAttribute("specialoffer") Specialoffer specialoffer, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		if(!action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("specialoffer", specialoffer);
				return "operator/addSO";
			}
			soService.saveSpecialOffer(specialoffer);
		}
		return "redirect:/specialoffer/";
	}
	
	@GetMapping("/specialofferproduct/add")
	public String addSOP(Model model) {
		model.addAttribute("specialofferproduct", new Specialofferproduct());
		model.addAttribute("offers", soService.findAll());
		model.addAttribute("products", pService.findAllProducts());
		return "operator/addSOP";
	}
	
	@PostMapping("/specialofferproduct/add")
	public String saveSOP(@Validated({ addValidation.class }) @ModelAttribute("specialofferproduct") Specialofferproduct specialofferproduct, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		if(!action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("specialofferproduct", specialofferproduct);
				model.addAttribute("offers", soService.findAll());
				model.addAttribute("products", pService.findAllProducts());
				return "operator/addSOP";
			}
			sopService.saveSpecialOfferProduct(specialofferproduct, specialofferproduct.getpId(), specialofferproduct.getsId());
		}
		return "redirect:/specialofferproduct/";
	}
	
	@GetMapping("/salesorderdetail/add")
	public String addSOD(Model model) {
		model.addAttribute("specialofferproduct", new Specialofferproduct());
		model.addAttribute("offers", soService.findAll());
		model.addAttribute("products", pService.findAllProducts());
		return "operator/addSOD";
	}
	
	@PostMapping("/salesorderdetail/add")
	public String saveSOD(@Validated({ addValidation.class }) @ModelAttribute("salesorderdetail") Salesorderdetail salesorderdetail, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		if(!action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("salesorderdetail", salesorderdetail);
				model.addAttribute("offers", soService.findAll());
				model.addAttribute("products", pService.findAllProducts());
				return "operator/addSOD";
			}
			sodService.saveSalesOrderDetail(salesorderdetail, salesorderdetail.getpId(), salesorderdetail.getsoId());
		}
		return "redirect:/salesorderdetail/";
	}
	
	@GetMapping("/specialoffer/edit/{id}")
	public String editSO(@PathVariable("id") Integer id, Model model) {
		Optional<Specialoffer> so = soService.findById(id);
		if (so.isEmpty())
			throw new IllegalArgumentException("Invalid special offer Id:" + id);
		
		model.addAttribute("specialoffer", so.get());
		return "operator/editSO";
	}
	
	@PostMapping("/specialoffer/edit/{id}")
	public String updateSO(@PathVariable("id") Integer id, @Validated(updateValidation.class) Specialoffer specialoffer, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		if (!action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("specialoffer", soService.findById(id).get());
				return "operator/editSO";
			}
			specialoffer.setSpecialofferid(id);
			soService.updateSpecialOffer(id, specialoffer);
		}
		return "redirect:/specialoffer";
	}
	
	@GetMapping("/specialofferproduct/edit/{id}")
	public String editSOP(@PathVariable("id") SpecialofferproductPK id, Model model) {
		Optional<Specialofferproduct> so = sopService.findById(id);
		if (so.isEmpty())
			throw new IllegalArgumentException("Invalid special offer product Id:" + id);
		
		model.addAttribute("specialoffer", so.get());
		return "operator/editSOP";
	}
	
	@PostMapping("/specialofferproduct/edit/{id}")
	public String updateSOP(@PathVariable("id") SpecialofferproductPK id, @Validated(updateValidation.class) Specialofferproduct specialofferproduct, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		if (!action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("specialofferproduct", sopService.findById(id).get());
				return "operator/editSOP";
			}
			specialofferproduct.setId(id);
			sopService.updateSpecialOfferProduct(id, specialofferproduct);
		}
		return "redirect:/specialofferproduct";
	}
	
	@GetMapping("/salesorderdetail/edit/{id}")
	public String editSOD(@PathVariable("id") SalesorderdetailPK id, Model model) {
		Optional<Salesorderdetail> so = sodService.findById(id);
		if (so.isEmpty())
			throw new IllegalArgumentException("Invalid order detail Id:" + id);
		
		model.addAttribute("salesorderdetail", so.get());
		return "operator/editSOD";
	}
	
	@PostMapping("/salesorderdetail/edit/{id}")
	public String updateSOD(@PathVariable("id") SalesorderdetailPK id, @Validated(updateValidation.class) Salesorderdetail salesorderdetail, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		if (!action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("salesorderdetail", sodService.findById(id).get());
				return "operator/editSOD";
			}
			salesorderdetail.setId(id);
			sodService.updateSalesOrderDetail(id, salesorderdetail);
		}
		return "redirect:/salesorderdetail";
	}
	
	@GetMapping("/specialoffer/delete/{id}")
	public String deleteSO(@PathVariable("id") Integer id, Model model) {
		soService.deleteSpecialOffer(id);
		return "redirect:/specialoffer";
	}
	
	@GetMapping("/specialofferproduct/delete/{id}")
	public String deleteSOP(@PathVariable("id") SpecialofferproductPK id, Model model) {
		sopService.deleteSpecialOfferProduct(id);
		return "redirect:/specialofferproduct";
	}
	
	@GetMapping("/salesorderdetail/delete/{id}")
	public String deleteSOD(@PathVariable("id") SalesorderdetailPK id, Model model) {
		sodService.deleteSalesOrderDetail(id);
		return "redirect:/salesorderdetail";
	}
}
