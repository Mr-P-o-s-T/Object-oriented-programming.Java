package org.post.microservice.auth.web.login;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.token.TokenManager;
import org.keycloak.representations.AccessTokenResponse;

public class KeycloakClient {

	private String serverUrl;
	private String realmId;
	private String clientId;

	private Keycloak keycloak;

	public KeycloakClient(String serverUrl, String realmId, String clientId) {
		this.serverUrl = serverUrl;
		this.realmId = realmId;
		this.clientId = clientId;
	}

	public AccessTokenResponse getAccessTokenResponse(String login, String password) {
		 keycloak = newKeycloakBuilderWithPassword(login, password).build();
		return getAccessTokenResponse();
	}

	public AccessTokenResponse getAccessTokenResponse() {
		return getTokenManager().getAccessToken();
	}

	private KeycloakBuilder newKeycloakBuilder() {
		return KeycloakBuilder.builder()
				.realm(realmId)
				.serverUrl(serverUrl)
				.clientId(clientId);
	}

	private KeycloakBuilder newKeycloakBuilderWithPassword(String username, String password) {
		return newKeycloakBuilder()
				.username(username)
				.password(password)
				.grantType(OAuth2Constants.PASSWORD);
	}

	private TokenManager getTokenManager() {
		return keycloak.tokenManager();
	}
}

