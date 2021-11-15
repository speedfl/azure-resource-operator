/**
 * ResourceManagementClient
 * Provides operations for working with resources and resource groups.
 *
 * The version of the OpenAPI document: 2021-04-01
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package org.speedfl.operator.azure.common.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
  * Common error response for all Azure Resource Manager APIs to return error details for failed operations. (This also follows the OData error response format.)
 **/


@Getter
@Setter
@EqualsAndHashCode
@ToString
@JsonInclude(Include.NON_NULL)
public class ErrorResponse  {
  
 /**
   * The error code.
  **/
  @JsonProperty("code")
  private String code;

 /**
   * The error message.
  **/
  @JsonProperty("message")
  private String message;

 /**
   * The error target.
  **/
  @JsonProperty("target")
  private String target;

 /**
   * The error details.
  **/
  @JsonProperty("details")
  private List<ErrorResponse> details = null;

 /**
   * The error additional info.
  **/
  @JsonProperty("additionalInfo")
  private List<ErrorAdditionalInfo> additionalInfo = null;
}

