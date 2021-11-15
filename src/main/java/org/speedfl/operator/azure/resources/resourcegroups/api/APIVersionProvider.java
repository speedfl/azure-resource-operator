package org.speedfl.operator.azure.resources.resourcegroups.api;

import java.io.IOException;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.UriBuilder;

/**
 * Provide a convenient way to append "api-version" query parameter to avoid to add it at each calls
 */
@ApplicationScoped
public class APIVersionProvider implements ClientRequestFilter {

  private static final String API_VERSION_KEY = "api-version";

  private static final String API_VERSION = "2021-04-01";

  @Override
  public void filter(ClientRequestContext requestContext) throws IOException {
    requestContext.setUri(UriBuilder.fromUri(requestContext.getUri())
        .queryParam(API_VERSION_KEY, API_VERSION)
        .build());
  }
}
