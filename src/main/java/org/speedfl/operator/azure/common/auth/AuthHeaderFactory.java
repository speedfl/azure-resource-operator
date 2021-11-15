package org.speedfl.operator.azure.common.auth;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

import org.eclipse.microprofile.rest.client.ext.ClientHeadersFactory;

@ApplicationScoped
public class AuthHeaderFactory implements ClientHeadersFactory {

  private static final String AUTH_HEADER_KEY = "Authorization";

  private static final String AUTH_HEADER_VALUE_TEMPLATE = "Bearer %s";

  private String token;

  /**
   * Listen to the authDataEvent to update token
   * 
   * @param authDataEvent
   */
  void updateToken(@Observes AuthDataEvent authDataEvent) {
    this.token = authDataEvent.getAccessToken();
  }

  @Override
  public MultivaluedMap<String, String> update(MultivaluedMap<String, String> incomingHeaders,
      MultivaluedMap<String, String> clientOutgoingHeaders) {
    MultivaluedMap<String, String> result = new MultivaluedHashMap<>();
    result.add(AUTH_HEADER_KEY, String.format(AUTH_HEADER_VALUE_TEMPLATE, token));
    return result;
  }
}
