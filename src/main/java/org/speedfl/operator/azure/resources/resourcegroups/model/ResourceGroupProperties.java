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

package org.speedfl.operator.azure.resources.resourcegroups.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


/**
  * The resource group properties.
 **/


@Getter
@Setter
@EqualsAndHashCode
@ToString
@JsonInclude(Include.NON_NULL)
public class ResourceGroupProperties  {
  
 /**
   * The provisioning state. 
  **/
  @JsonProperty("provisioningState")
  private String provisioningState;
}

