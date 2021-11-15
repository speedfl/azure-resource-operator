package org.speedfl.operator.azure.common.error;

import javax.ws.rs.core.Response;

public class TooManyRequestExeption extends RuntimeException {

  private static final long serialVersionUID = 1L;
  private Response response;

  public TooManyRequestExeption() {
    super();
  }

  public TooManyRequestExeption(Response response) {
    super("TooManyRequestExeption to " + response.getLocation());
    this.response = response;
  }

  public Response getResponse() {
    return this.response;
  }
}
