package org.speedfl.operator.azure.common.auth;

import java.time.LocalDateTime;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.speedfl.operator.azure.common.config.OperatorConfiguration;

import io.quarkus.scheduler.Scheduled;
import lombok.extern.slf4j.Slf4j;

/**
 * This service is in charge of handling the authentication
 */
@ApplicationScoped
@Slf4j
@Readiness
public class AuthenticationService implements HealthCheck {

  private static final String CLIENT_CREDENTIALS = "client_credentials";

  @Inject
  @RestClient
  Oauth2AuthenticationApi oauth2AuthenticationApi;

  @Inject
  OperatorConfiguration operatorConfiguration;

  @Inject
  Event<AuthDataEvent> onAuthDataRefreshed;

  private String accessToken;

  private LocalDateTime tokenExpiration;

  @Scheduled(every = "10s", skipExecutionIf = SkipAuthForTests.class)
  void updateAuthData() {
    
    // 10 minutes before expiration renew the token
    if (accessToken != null && tokenExpiration.isAfter(LocalDateTime.now().plusMinutes(10))) {
      return;
    }
    
    Oauth2Response response = null;
    try {
      response = oauth2AuthenticationApi.authenticate(operatorConfiguration.getTenantId(),
          CLIENT_CREDENTIALS,
          operatorConfiguration.getClientId(), operatorConfiguration.getClientSecret(),
          operatorConfiguration.getAzureApirtUrl());
      accessToken = response.getAccessToken();
      tokenExpiration = LocalDateTime.now().plusSeconds(Long.valueOf(response.getExpiresIn()));
      onAuthDataRefreshed.fire(new AuthDataEvent(accessToken, tokenExpiration));
    } catch (Exception e) {
      log.error("Unable to refresh token. Response is {}", response, e);
    }
  }
  
  @Override
  public HealthCheckResponse call() {
    return HealthCheckResponse.builder()
        .name("auth-ready")
        .status(accessToken != null && tokenExpiration.isAfter(LocalDateTime.now()))
        .build();
  }
}
