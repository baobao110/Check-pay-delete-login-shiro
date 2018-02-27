package com.control;

import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/Page")
public class PageControl {
	
	@RequestMapping("/toInformation")
	 public ModelAndView toCommodityInfo(int commodityId) {
	 	ModelAndView modelAndView = new ModelAndView();
	 	modelAndView.addObject("commodityId",commodityId);
	 	modelAndView.setViewName("home/introduction");
	 	return modelAndView;
	}
	
	@RequestMapping("/toCart")
	 public ModelAndView toCart() {
	 	ModelAndView modelAndView = new ModelAndView();
	 	int userId=33;
	 	modelAndView.addObject("userId",userId);
	 	modelAndView.setViewName("home/shopcart");
	 	return modelAndView;
	}
	
	@RequestMapping("/toAddress")
	 public ModelAndView toAddress() {
	 	ModelAndView modelAndView = new ModelAndView();
	 	int userId=33;
	 	modelAndView.addObject("userId",userId);
	 	modelAndView.setViewName("person/address");
	 	return modelAndView;
	}
	
	@RequestMapping("/toPay")
	 public ModelAndView toPay() {
	 	ModelAndView modelAndView = new ModelAndView();
	 	int userId=33;
	 	modelAndView.addObject("userId",userId);
	 	modelAndView.setViewName("home/pay");
	 	return modelAndView;
	}//ע�������spring��Control�ķ�����ʽ����addObject�������������Ҫ���ݵ�ǰ�˵ı��� ,setViewName�������ݵ�����Ҫ��ת�ĵ�ַ����
	
	@RequestMapping("/order")
	 public ModelAndView order() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("person/order");
	 	return modelAndView;
	}
	
	@RequestMapping("/toLogin")
	public String toLogin() {
		return "home/login";
	}
	
	@RequestMapping("/toRegister")
	 public ModelAndView toRegister(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home/register");
		String uuID=UUID.randomUUID().toString();
		session.setAttribute("UUID", uuID);
	 	return modelAndView;
	}
	
}
