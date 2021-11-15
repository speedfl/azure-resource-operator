package org.speedfl.operator.azure.common.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class Oauth2Response {

  @JsonProperty("token_type")
  private String tokenType;
  
  /**
   * Expires in seconds (usually 1h)
   */
  @JsonProperty("expires_in")
  private String expiresIn;
  
  @JsonProperty("ext_expires_in")
  private String extExpiresIn;
  
  @JsonProperty("not_before")
  private String notBefore;
  
  @JsonProperty("resource")
  private String resource;
  
  @JsonProperty("access_token")
  private String accessToken;
}
