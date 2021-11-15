package org.speedfl.operator.azure.servicebus.namespaces.kube;

import org.speedfl.operator.azure.common.kube.model.BaseStatus;

import io.fabric8.kubernetes.api.model.Namespaced;
import io.fabric8.kubernetes.client.CustomResource;
import io.fabric8.kubernetes.model.annotation.Group;
import io.fabric8.kubernetes.model.annotation.ShortNames;
import io.fabric8.kubernetes.model.annotation.Version;
import lombok.Getter;
import lombok.Setter;

@Group("org.speedfl.io")
@Version("v1alpha1")
@ShortNames("azsbn")
@Getter
@Setter
public class ServiceBusNamespace extends CustomResource<ServiceBusNamespaceSpec, BaseStatus> implements Namespaced {
  
  private static final long serialVersionUID = 1L;

  private ServiceBusNamespaceSpec spec;

  private BaseStatus status;

}
