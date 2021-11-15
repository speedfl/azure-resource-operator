package org.speedfl.operator.azure.common.auth;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * This event is emitted when AuthData are available
 */
@Getter
@RequiredArgsConstructor
public final class AuthDataEvent {
  
  private final String accessToken;

  private final LocalDateTime tokenExpiration;

}
