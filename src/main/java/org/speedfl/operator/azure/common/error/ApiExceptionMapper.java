package org.speedfl.operator.azure.common.error;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

/**
 * This is handling all errors as APIException. Except the 429 which will be handled as TooManyRequestExeption 
 */
@Provider
public class ApiExceptionMapper
    implements ResponseExceptionMapper<ApiException> {
  
  @Override
  public boolean handles(int status, MultivaluedMap<String, Object> headers) {
    return status >= 400 && status != 429;
  }

  @Override
  public ApiException toThrowable(Response response) {
    return new ApiException(response);
  }
}
