package com.taller3.controller;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
		Specialoffer so = soService.findById(id);
		
		model.addAttribute("specialoffer", so);
		return "operator/editSO";
	}
	
	@PostMapping("/specialoffer/edit/{id}")
	public String updateSO(@PathVariable("id") Integer id, @Validated(updateValidation.class) Specialoffer specialoffer, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		if (!action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("specialoffer", soService.findById(id));
				return "operator/editSO";
			}
			specialoffer.setSpecialofferid(id);
			soService.updateSpecialOffer(id, specialoffer);
		}
		return "redirect:/specialoffer";
	}
	
	@GetMapping("/specialofferproduct/edit/{id}")
	public String editSOP(@PathVariable("id") SpecialofferproductPK id, Model model) {
		Specialofferproduct so = sopService.findById(id);
		
		model.addAttribute("specialoffer", so);
		return "operator/editSOP";
	}
	
	@PostMapping("/specialofferproduct/edit/{id}")
	public String updateSOP(@PathVariable("id") SpecialofferproductPK id, @Validated(updateValidation.class) Specialofferproduct specialofferproduct, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		if (!action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("specialofferproduct", sopService.findById(id));
				return "operator/editSOP";
			}
			specialofferproduct.setId(id);
			sopService.updateSpecialOfferProduct(id, specialofferproduct);
		}
		return "redirect:/specialofferproduct";
	}
	
	@GetMapping("/salesorderdetail/edit/{id}")
	public String editSOD(@PathVariable("id") Integer id, Model model) {
		Salesorderdetail so = sodService.findById(id);
		
		model.addAttribute("salesorderdetail", so);
		return "operator/editSOD";
	}
	
	@PostMapping("/salesorderdetail/edit/{id}")
	public String updateSOD(@PathVariable("id") Integer id, @Validated(updateValidation.class) Salesorderdetail salesorderdetail, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		if (!action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("salesorderdetail", sodService.findById(id));
				return "operator/editSOD";
			}
			salesorderdetail.setSalesOrderDetailId(id);
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
	public String deleteSOD(@PathVariable("id") Integer id, Model model) {
		sodService.deleteSalesOrderDetail(id);
		return "redirect:/salesorderdetail";
	}
	
	@GetMapping("/specialoffer/get/{startdate}")
	public String querySOStartDateGet(@PathVariable("startdate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startdate, Model model) {
		return "operator/soQuery";
	}
	
	@PostMapping("/specialoffer/get/{startdate}")
	public String querySOStartDatePost(@PathVariable("startdate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startdate, Model model) {
		if(startdate!=null) {
			model.addAttribute("specialoffers", soService.findByStartDate(startdate));
			return "operator/soQuery";
		}else
			return "redirect:/specialoffer";
		
	}
	
	@GetMapping("/specialoffer/get/{enddate}")
	public String querySOEndDateGet(@PathVariable("enddate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate enddate, Model model) {
		return "operator/soQuery";
	}
	
	@PostMapping("/specialoffer/get/{enddate}")
	public String querySOEndDatePost(@PathVariable("enddate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate enddate, Model model) {
		if(enddate!=null) {
			model.addAttribute("specialoffers", soService.findByEndDate(enddate));
			return "operator/soQuery";
		}else
			return "redirect:/specialoffer";
	}
	
	@GetMapping("/salesorderdetail/get/{productid}")
	public String querySODProductIdGet(@PathVariable("productid") Integer productid, Model model) {
		return "operator/sodQuery";
	}
	
	@PostMapping("/salesorderdetail/get/{productid}")
	public String querySODProductIdPost(@PathVariable("productid") Integer productid, Model model) {
		if(productid!=null) {
			model.addAttribute("salesorderdetails", sodService.findByProductId(productid));
			return "operator/sodQuery";
		}else
			return "redirect:/salesorderdetail";
	}
	
	@GetMapping("/salesorderdetail/get/SOP")
	public String querySODMoreSOPGet(Model model) {
		return "operator/sodSOPQuery";
	}
	
	@PostMapping("/salesorderdetail/get/SOP")
	public String querySODMoreSOPPost(Model model) {
		model.addAttribute("sales", sodService.findOrderDetailByProductWithMoreThanOneSOP());
		return "operator/sodSOPQuery";
	}
}
