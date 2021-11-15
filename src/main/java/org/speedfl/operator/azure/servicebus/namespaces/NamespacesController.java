package org.speedfl.operator.azure.servicebus.namespaces;

import javax.inject.Inject;
import javax.ws.rs.ProcessingException;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.speedfl.operator.azure.common.Utils;
import org.speedfl.operator.azure.common.config.OperatorConfiguration;
import org.speedfl.operator.azure.common.error.ApiException;
import org.speedfl.operator.azure.common.kube.model.BaseStatus;
import org.speedfl.operator.azure.servicebus.namespaces.api.NamespacesApi;
import org.speedfl.operator.azure.servicebus.namespaces.kube.ServiceBusNamespace;
import org.speedfl.operator.azure.servicebus.namespaces.model.SBNamespace;

import io.javaoperatorsdk.operator.api.Context;
import io.javaoperatorsdk.operator.api.Controller;
import io.javaoperatorsdk.operator.api.DeleteControl;
import io.javaoperatorsdk.operator.api.ResourceController;
import io.javaoperatorsdk.operator.api.UpdateControl;
import lombok.extern.slf4j.Slf4j;

/**
 * Controller to handle ServiceBusNamespace creation
 */
@Controller
@Slf4j
public class NamespacesController implements ResourceController<ServiceBusNamespace> {
  
  @Inject
  @RestClient
  NamespacesApi api;

  @Inject
  OperatorConfiguration configuration;

  @Override
  public UpdateControl<ServiceBusNamespace> createOrUpdateResource(ServiceBusNamespace resource,
      Context<ServiceBusNamespace> context) {
    try {
      SBNamespace payload = Utils.convertToParent(resource.getSpec(), SBNamespace.class);
      api.namespacesCreateOrUpdate(resource.getSpec().getResourceGroupName(),
          resource.getSpec().getName(),
          configuration.getSubscriptionId(),
          payload);
      return UpdateControl.noUpdate();
    } catch (ProcessingException | ApiException| IllegalStateException e) {
      log.error("Error while creating {} {}", resource.getKind(), resource.getMetadata().getName(), e);
      resource.setStatus(new BaseStatus(e.getMessage()));
      return UpdateControl.updateCustomResourceAndStatus(resource);
    }
  }

  /**
   * In case of error do not remove finalizer
   */
  @Override
  public DeleteControl deleteResource(ServiceBusNamespace resource, Context<ServiceBusNamespace> context) {
    try {
      api.namespacesDelete(resource.getSpec().getResourceGroupName(), 
          resource.getSpec().getName(),
          configuration.getSubscriptionId());
    } catch (ProcessingException | ApiException e) {
      log.error("Error while deleting {} {}", resource.getKind(), resource.getMetadata().getName(), e);
      return DeleteControl.NO_FINALIZER_REMOVAL;
    }
    return DeleteControl.DEFAULT_DELETE;
  }
}