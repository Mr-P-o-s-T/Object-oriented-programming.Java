package org.post.microservice.business.web;

import org.springframework.web.bind.annotation.GetMapping;

public class ErrorController {
	@GetMapping("/error")
	public String error() {
		return "error";
	}
}
