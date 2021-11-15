package org.speedfl.operator.azure.common.error;

import java.util.Optional;

import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class ApiException extends Exception {

  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  private static final long serialVersionUID = 1L;

  /**
   * This header can be sent in input to correlate Request with response
   */
  private static final String CORRELATION_ID_HEADER = "x-ms-correlation-request-id";

  /**
   * The status code
   */
  private int status;

  /**
   * The correlation ID coming from header x-ms-correlation-request-id
   */
  private String correlationId;

  /**
   * The resource error
   */
  private String errorCode;

  /**
   * The final error message
   */
  private String errorMessage;

  public ApiException(Response response) {
    this.status = response.getStatus();
    this.correlationId = response.getHeaderString(CORRELATION_ID_HEADER);
    try {
      JsonNode tree = OBJECT_MAPPER.readTree(response.readEntity(String.class));
      this.errorMessage = getValue(tree, "/error/message").orElse(null);
      this.errorCode = getValue(tree, "/error/code").orElse(null);
    } catch (Exception e) {
      log.error("Cannot process body", e);
    }
  }

  private Optional<String> getValue(JsonNode tree, String path) {
    JsonNode value = tree.at(path);
    if (value.isMissingNode()) {
      return Optional.empty();
    }
    return Optional.of(value.asText());
  }

  @Override
  public String getMessage() {
    return String.format("ApiException(CorrelationId: %s / Status Code: %d / Error Code: %s / Error Message: %s)", correlationId,
        status, errorCode, errorMessage);
  }
}
