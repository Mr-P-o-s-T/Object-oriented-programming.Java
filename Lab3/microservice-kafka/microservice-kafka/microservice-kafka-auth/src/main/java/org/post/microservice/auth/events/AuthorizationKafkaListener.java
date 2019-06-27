package org.post.microservice.auth.events;

import org.post.microservice.auth.services.KeycloakAuthoriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class AuthorizationKafkaListener {

	private KeycloakAuthoriseService authorizer;

	private KafkaTemplate<String, String> template;

	@Autowired
	public AuthorizationKafkaListener(KeycloakAuthoriseService authorizer, KafkaTemplate<String, String> template) {
		this.authorizer = authorizer;
		this.template = template;
	}

	@KafkaListener(topics = "auth")
	public void authorize(String msg) {
		String token = "";

		if (msg.contains(":")) {
			String[] msgParts = msg.split(":");

			if (msgParts.length > 1) token = authorizer.authorize(msgParts[0], msgParts[1]);
		} else token = authorizer.authorize(msg);

		try {
			template.send("token", token).get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
}
