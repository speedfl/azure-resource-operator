package org.speedfl.operator.azure.common.auth;

import java.time.temporal.ChronoUnit;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.annotation.RegisterProviders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.FormParam;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.speedfl.operator.azure.common.error.ApiExceptionMapper;
import org.speedfl.operator.azure.common.error.TooManyRequestExeption;
import org.speedfl.operator.azure.common.error.TooManyRequestExeptionMapper;

@Retry(retryOn = TooManyRequestExeption.class, delay = 1, delayUnit = ChronoUnit.SECONDS, maxRetries = 5)
@RegisterRestClient(configKey="oauth2-api")
@RegisterProviders({
  @RegisterProvider(TooManyRequestExeptionMapper.class),
  @RegisterProvider(ApiExceptionMapper.class)
})
public interface Oauth2AuthenticationApi {

  @POST
  @Path("/{tenantId}/oauth2/token")
  @Produces(MediaType.APPLICATION_FORM_URLENCODED)
  @Consumes(MediaType.APPLICATION_JSON)
  public Oauth2Response authenticate(@PathParam String tenantId, @FormParam("grant_type") String grantType,
      @FormParam("client_id") String clientId, @FormParam("client_secret") String clientSecret,
      @FormParam String resource);

}
