package org.speedfl.operator.azure.common;

import org.speedfl.operator.azure.servicebus.queues.kube.ServiceBusQueueSpec;
import org.speedfl.operator.azure.servicebus.queues.model.SBQueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Utils {

  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
      .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

  /**
   * This utility tool remove all child elements. For example: {@link ServiceBusQueueSpec} extends {@link SBQueue}.
   * Before sending to Azure we need to remove {@link ServiceBusQueueSpec#getNamespaceName()} &&
   * {@link ServiceBusQueueSpec#getResourceGroupName()}
   * 
   * @param <P>
   *          parent type
   * @param <T>
   *          child type
   * @param child
   * @param parentClass
   * @return the converted value to parent
   */
  public static <P, T extends P> P convertToParent(T child, Class<P> parentClass) {
    try {
      return OBJECT_MAPPER.readValue(OBJECT_MAPPER.writeValueAsString(child), parentClass);
    } catch (JsonProcessingException e) {
      throw new IllegalStateException("Cannot convert to parent", e);
    }
  }

}
