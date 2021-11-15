package org.speedfl.operator.azure.common.config;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import lombok.Getter;

/**
 * This centralize the Operator configuration <br>
 * All client related information are scoped to configuration package
 */
@ApplicationScoped
@Getter
public class OperatorConfiguration {

  /**
   * The service principal clientId
   */
  @Inject
  @ConfigProperty(name = "azure.operator.client-id")
  String clientId;

  /**
   * The service principal client secret
   */
  @Inject
  @ConfigProperty(name = "azure.operator.client-secret")
  String clientSecret;

  /**
   * The service principal tenantId
   */
  @Inject
  @ConfigProperty(name = "azure.operator.tenant-id")
  String tenantId;

  /**
   * The subscriptionId which will be used
   */
  @Inject
  @ConfigProperty(name = "azure.operator.subscription-id")
  String subscriptionId;

  /**
   * The default location
   */
  @Inject
  @ConfigProperty(name = "azure.operator.location")
  String location;

  /**
   * The azure api url
   */
  @Inject
  @ConfigProperty(name = "azure-api/mp-rest/url")
  String azureApirtUrl;

}
