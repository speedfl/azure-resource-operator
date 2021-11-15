package org.speedfl.operator.azure.servicebus.namespaces.kube;

import org.speedfl.operator.azure.servicebus.namespaces.model.SBNamespace;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceBusNamespaceSpec extends SBNamespace {

  private String resourceGroupName;
  
}
