package com.hml.admin.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.hml.admin.constant.SysConstants;
import com.hml.admin.entity.AppLogin;
import com.hml.admin.entity.User;
import com.hml.admin.security.JwtAuthenticatioToken;
import com.hml.admin.service.IUserService;
import com.hml.admin.util.PasswordUtils;
import com.hml.admin.util.SecurityUtils;
import com.hml.admin.vo.LoginBean;
import com.hml.core.http.HttpResult;
import com.hml.utils.IOUtils;

/**
 * 登录控制器
 * @author hml
 * @date Jan 14, 2019
 */
@RestController
public class LoginController {

	@Autowired
	private Producer producer;
	@Autowired
	private IUserService userService;
	@Autowired
	private AuthenticationManager authenticationManager;

	@GetMapping("captcha.jpg")
	public void captcha(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-store, no-cache");
		response.setContentType("image/jpeg");

		// 生成文字验证码
		String text = producer.createText();
		// 生成图片验证码
		BufferedImage image = producer.createImage(text);
		// 保存到验证码到 session
		request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, text);

		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(image, "jpg", out);	
		IOUtils.closeQuietly(out);
	}

	/**
	 * 登录接口
	 */
	@PostMapping(value = "/login")
	public HttpResult login(@RequestBody LoginBean loginBean, HttpServletRequest request) throws IOException {
		String username = loginBean.getAccount();
		String password = loginBean.getPassword();
		// 用户信息
		User user = userService.findByName(username);
		// 账号不存在、密码错误
		if (user == null) {
			return HttpResult.error("账号不存在");
		}
		if("".equals(user.getFPassword())){
			return HttpResult.error(101, "首次登陆App，请初始化密码");
		}
		if (!PasswordUtils.matches(SysConstants.SALT, password, user.getFPassword())) {
			return HttpResult.error("密码不正确");
		}
		
		// 系统登录认证
		JwtAuthenticatioToken token = SecurityUtils.login(request, username, password, authenticationManager);
		return HttpResult.ok(token);
	}
	@PostMapping(value="/loginRegist")
	public HttpResult saveAppLogin(@RequestBody LoginBean loginBean, HttpServletRequest request)throws Exception{
		String username = loginBean.getAccount();
		String mobile = loginBean.getMobile();
		User user = userService.findByNameAndMobile(username, mobile);
		if(user == null){
			return HttpResult.error("提交信息不存在");
		}
		AppLogin login = new AppLogin();
		login.setCode(username);
		String pwd = loginBean.getPassword();
		pwd = PasswordUtils.encode(pwd, SysConstants.SALT);
		login.setPwd(pwd);
		//login.setMobile(loginBean.getMobile());
		
		int code = userService.saveAppLogin(login);
		return HttpResult.ok(code);
	}

}
