package com.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.ajax.ajaxDAO;
import com.domain.user;
import com.mapper.userMapper;
import com.service.userServer;

@Controller
@RequestMapping("/user")
public class UserControl {
	@Autowired
	private userServer u;
	
	private  Logger log=LoggerFactory.getLogger(UserControl.class);
	
	@RequestMapping("/login")
	@ResponseBody
	public ajaxDAO login(String username,String password) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken();
		token.setUsername(username);
		token.setPassword( DigestUtils.md5Hex(password).toCharArray());

		subject.login(token);

		user x=u.getByname(username);
		if(null!=x) {
			subject.getSession().setAttribute("User",x );
			return ajaxDAO.success();
		}
		else {
			return ajaxDAO.failure("用户不正确或密码不存在");
		}

	}
	
	@RequestMapping("/register")
	@ResponseBody
	public ajaxDAO  Register(String username, String password,HttpSession session,String confirm,String code,String uuID) throws ServletException, IOException {
		
		if(null==uuID||"".equals(uuID)) {
			return ajaxDAO.failure("请刷新");
		}
		
		String id=(String)session.getAttribute("UUID");
		if(!id.equals(uuID)) {
			return ajaxDAO.failure("请刷新");
		}
		
		if(null==code||"".equals(code)) {
			return ajaxDAO.failure("验证码不能为空");
		}
		code=code.trim();
		String cod=(String)session.getAttribute("code");
		if(!cod.equalsIgnoreCase(code)) {
			return ajaxDAO.failure("验证码错误");
		}
		
		if(!confirm.equals(password)) {
			return ajaxDAO.failure("密码错误");
		}
		
		user x=u.getByname(username);
		
		if(null==x) {
			try {
				
				user i= u.register(username, password);
				session.setAttribute("user",u);
				session.removeAttribute("uuID");
				return ajaxDAO.success();
			}
				catch (Exception e) {
					// TODO: handle exception
					log.error("}}}}}}}"+e.getMessage(),e);
					return ajaxDAO.failure(e.getMessage());
				}
		}
		else {
			return ajaxDAO.failure("注册失败 ");
		}
		
	}

	


}
