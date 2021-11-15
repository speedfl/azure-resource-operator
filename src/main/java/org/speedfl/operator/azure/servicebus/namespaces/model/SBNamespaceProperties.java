/**
 * ServiceBusManagementClient
 * Azure Service Bus client
 *
 * The version of the OpenAPI document: 2017-04-01
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package org.speedfl.operator.azure.servicebus.namespaces.model;

import java.util.Date;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


/**
  * Properties of the namespace.
 **/


@Getter
@Setter
@EqualsAndHashCode
@ToString
@JsonInclude(Include.NON_NULL)
public class SBNamespaceProperties  {
  
 /**
   * Provisioning state of the namespace.
  **/
  @JsonProperty("provisioningState")
  private String provisioningState;

 /**
   * The time the namespace was created.
  **/
  @JsonProperty("createdAt")
  private Date createdAt;

 /**
   * The time the namespace was updated.
  **/
  @JsonProperty("updatedAt")
  private Date updatedAt;

 /**
   * Endpoint you can use to perform Service Bus operations.
  **/
  @JsonProperty("serviceBusEndpoint")
  private String serviceBusEndpoint;

 /**
   * Identifier for Azure Insights metrics
  **/
  @JsonProperty("metricId")
  private String metricId;
}

