package org.speedfl.operator.azure.resources.resourcegroups;

import javax.inject.Inject;
import javax.ws.rs.ProcessingException;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.speedfl.operator.azure.common.Utils;
import org.speedfl.operator.azure.common.config.OperatorConfiguration;
import org.speedfl.operator.azure.common.error.ApiException;
import org.speedfl.operator.azure.common.kube.model.BaseStatus;
import org.speedfl.operator.azure.resources.resourcegroups.api.ResourceGroupsApi;
import org.speedfl.operator.azure.resources.resourcegroups.kube.ResourceGroup;

import io.javaoperatorsdk.operator.api.Context;
import io.javaoperatorsdk.operator.api.Controller;
import io.javaoperatorsdk.operator.api.DeleteControl;
import io.javaoperatorsdk.operator.api.ResourceController;
import io.javaoperatorsdk.operator.api.UpdateControl;
import lombok.extern.slf4j.Slf4j;

/**
 * Controller to handle ResourceGroup creation
 */
@Controller
@Slf4j
public class ResourceGrouspController implements ResourceController<ResourceGroup> {

  @Inject
  @RestClient
  ResourceGroupsApi api;

  @Inject
  OperatorConfiguration configuration;

  @Override
  public UpdateControl<ResourceGroup> createOrUpdateResource(ResourceGroup resource,
      Context<ResourceGroup> context) {
    try {
      org.speedfl.operator.azure.resources.resourcegroups.model.ResourceGroup payload = Utils.convertToParent(
          resource.getSpec(), org.speedfl.operator.azure.resources.resourcegroups.model.ResourceGroup.class);
      api.resourceGroupsCreateOrUpdate(resource.getSpec().getName(), configuration.getSubscriptionId(),
          payload);
      return UpdateControl.noUpdate();
    } catch (ProcessingException | ApiException | IllegalStateException e) {
      log.error("Error while creating {} {}", resource.getKind(), resource.getMetadata().getName(), e);
      resource.setStatus(new BaseStatus(e.getMessage()));
      return UpdateControl.updateCustomResourceAndStatus(resource);
    }
  }

  /**
   * In case of error do not remove finalizer
   */
  @Override
  public DeleteControl deleteResource(ResourceGroup resource, Context<ResourceGroup> context) {
    try {
      api.resourceGroupsDelete(resource.getSpec().getName(), configuration.getSubscriptionId(), null);
    } catch (ProcessingException | ApiException e) {
      log.error("Error while deleting {} {}", resource.getKind(), resource.getMetadata().getName(), e);
      return DeleteControl.NO_FINALIZER_REMOVAL;
    }
    return DeleteControl.DEFAULT_DELETE;
  }
}