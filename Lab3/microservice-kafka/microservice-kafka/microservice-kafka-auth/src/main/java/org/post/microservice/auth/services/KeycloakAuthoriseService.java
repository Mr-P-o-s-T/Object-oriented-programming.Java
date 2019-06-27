package org.post.microservice.auth.services;

import org.post.microservice.auth.jwt.JwtTokenUtil;
import org.post.microservice.auth.jwt.JwtUser;
import org.post.microservice.auth.web.login.KeycloakClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class KeycloakAuthoriseService {

	@Value("${keycloak.server.url}")
	private String serverUrl;

	@Value("${keycloak.server.realmId}")
	private String realmId;

	@Value("${keycloak.server.clientId}")
	private String clientId;

	private JwtTokenUtil jwtTokenUtil;

	private final UserDetailsService userDetailsService;

	private Map<Pair<String, String>, String> db = new HashMap<>();

	public KeycloakAuthoriseService(JwtTokenUtil jwtTokenUtil, @Qualifier("jwtUserDetailsService") UserDetailsService userDetailsService) {
		this.jwtTokenUtil = jwtTokenUtil;
		this.userDetailsService = userDetailsService;
	}

	public String authorize(String login, String password) {
		Pair<String, String> key = Pair.of(login, password);
		if (!db.containsKey(key)) {
			KeycloakClient tmp = new KeycloakClient(serverUrl, realmId, clientId);
			try {
				tmp.getAccessTokenResponse(login,password);
			} catch (Exception ex) {
				return "";
			}
			db.put(key, jwtTokenUtil.generateToken(userDetailsService.loadUserByUsername(login)));
		}

		return db.get(key);
	}

	public String authorize(String accessToken) {
		for (Map.Entry<Pair<String, String>, String> entry : db.entrySet())
			if (accessToken.equals(entry.getValue())) {
				if (jwtTokenUtil.canTokenBeRefreshed(accessToken, jwtTokenUtil.getExpirationDateFromToken(accessToken)))
					entry.setValue(jwtTokenUtil.refreshToken(accessToken));
				break;
			}

		return null;
	}
}
