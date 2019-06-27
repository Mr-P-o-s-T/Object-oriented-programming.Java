package org.post.microservice.business.events;

import org.springframework.data.util.Pair;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TokenKafkaListener {

	private String login, password;

	private Map<Pair<String, String>, String> accessTokens = new HashMap<>();

	public TokenKafkaListener() {
		super();
	}

	@KafkaListener(topics = "token")
	public synchronized void saveToken(String msg) {
		if (!msg.equals("")) {
			accessTokens.put(Pair.of(login, password), msg);
		}
		this.notify();
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Map<Pair<String, String>, String> getAccessTokens() {
		return accessTokens;
	}
}
