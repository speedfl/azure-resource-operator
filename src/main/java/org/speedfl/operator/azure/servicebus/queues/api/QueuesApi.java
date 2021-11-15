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

package org.speedfl.operator.azure.servicebus.queues.api;

import java.time.temporal.ChronoUnit;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.Produces;

import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.annotation.RegisterProviders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.speedfl.operator.azure.common.auth.AuthHeaderFactory;
import org.speedfl.operator.azure.common.error.ApiException;
import org.speedfl.operator.azure.common.error.ApiExceptionMapper;
import org.speedfl.operator.azure.common.error.TooManyRequestExeption;
import org.speedfl.operator.azure.common.error.TooManyRequestExeptionMapper;
import org.speedfl.operator.azure.servicebus.queues.model.SBQueue;

/**
 * ServiceBusManagementClient
 *
 * <p>Azure Service Bus client
 *
 */

@Retry(retryOn = TooManyRequestExeption.class, delay = 1, delayUnit = ChronoUnit.SECONDS, maxRetries = 5)
@RegisterRestClient(configKey="azure-api")
@RegisterClientHeaders(AuthHeaderFactory.class)
@RegisterProviders({
  @RegisterProvider(APIVersionProvider.class),
  @RegisterProvider(ApiExceptionMapper.class),
  @RegisterProvider(TooManyRequestExeptionMapper.class),
})
@Path("/subscriptions/{subscriptionId}/resourceGroups/{resourceGroupName}/providers/Microsoft.ServiceBus/namespaces/{namespaceName}/queues")
public interface QueuesApi  {

    @PUT
    @Path("/{queueName}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    public SBQueue queuesCreateOrUpdate(@PathParam("resourceGroupName") String resourceGroupName, @PathParam("namespaceName") String namespaceName, @PathParam("queueName") String queueName, @PathParam("subscriptionId") String subscriptionId, SBQueue parameters) throws ApiException, ProcessingException;

    @DELETE
    @Path("/{queueName}")
    @Produces({ "application/json" })
    public void queuesDelete(@PathParam("resourceGroupName") String resourceGroupName, @PathParam("namespaceName") String namespaceName, @PathParam("queueName") String queueName, @PathParam("subscriptionId") String subscriptionId) throws ApiException, ProcessingException;

    @GET
    @Path("/{queueName}")
    @Produces({ "application/json" })
    public SBQueue queuesGet(@PathParam("resourceGroupName") String resourceGroupName, @PathParam("namespaceName") String namespaceName, @PathParam("queueName") String queueName, @PathParam("subscriptionId") String subscriptionId) throws ApiException, ProcessingException;

}

