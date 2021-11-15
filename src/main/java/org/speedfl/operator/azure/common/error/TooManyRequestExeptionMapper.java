package org.speedfl.operator.azure.common.error;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

/**
 * This is handling all errors as APIException. Except the 429 which will be handled as TooManyRequestExeption 
 */
@Provider
public class TooManyRequestExeptionMapper
    implements ResponseExceptionMapper<TooManyRequestExeption> {

  @Override
  public boolean handles(int status, MultivaluedMap<String, Object> headers) {
    return status == 429;
  }

  @Override
  public TooManyRequestExeption toThrowable(Response response) {
    return new TooManyRequestExeption(response);
  }
}
