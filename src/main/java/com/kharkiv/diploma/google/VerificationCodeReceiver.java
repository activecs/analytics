package com.kharkiv.diploma.google;

/**
 * OAuth 2.0 verification code receiver.
 *
 * <p>
 * Implementation should be thread-safe.
 * </p>
 */
public interface VerificationCodeReceiver {

  /** Returns the redirect URI. */
  String getRedirectUri() throws Exception;

  /** Waits for a verification code. */
  String waitForCode() throws Exception;

  /** Releases any resources and stops any processes started. */
  void stop() throws Exception;
}
