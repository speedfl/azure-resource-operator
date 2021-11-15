package org.speedfl.operator.azure.servicebus.queues.kube;

import org.speedfl.operator.azure.servicebus.queues.model.SBQueue;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceBusQueueSpec extends SBQueue {

  private String resourceGroupName;

  private String namespaceName;
  
}
