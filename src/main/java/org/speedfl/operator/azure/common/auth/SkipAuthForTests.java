package org.speedfl.operator.azure.common.auth;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.runtime.LaunchMode;
import io.quarkus.runtime.configuration.ProfileManager;
import io.quarkus.scheduler.Scheduled.SkipPredicate;
import io.quarkus.scheduler.ScheduledExecution;

/**
 * Skip predicate workaround to remove the authentication during testing
 */
@ApplicationScoped
public class SkipAuthForTests implements SkipPredicate {
  
  @Override
  public boolean test(ScheduledExecution execution) {
    return ProfileManager.getLaunchMode().equals(LaunchMode.TEST);
  }
}
