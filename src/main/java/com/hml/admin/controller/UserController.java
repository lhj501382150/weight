package com.hml.admin.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hml
 * @since 2020-07-17
 */
@RestController
@RequestMapping("/admin/user")
public class UserController extends BaseController {

	@PostMapping("/hello")
	public String hello() {
		return "123";
	}
}

