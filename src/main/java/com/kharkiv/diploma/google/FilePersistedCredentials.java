package com.kharkiv.diploma.google;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Persisted credential implementation to be used exclusively with {@link FileCredentialStore}.
 */
public class FilePersistedCredentials extends GenericJson {

  /** User ID to be used as the primary key. */
  @Key
  private Map<String, FilePersistedCredential> credentials = Maps.newHashMap();

  /**
   * Store information from the credential.
   *
   * @param userId user ID whose credential needs to be stored
   * @param credential credential whose {@link Credential#getAccessToken access token},
   *        {@link Credential#getRefreshToken refresh token}, and
   *        {@link Credential#getExpirationTimeMilliseconds expiration time} need to be stored
   */
  void store(String userId, Credential credential) {
    Preconditions.checkNotNull(userId);
    FilePersistedCredential fileCredential = credentials.get(userId);
    if (fileCredential == null) {
      fileCredential = new FilePersistedCredential();
      credentials.put(userId, fileCredential);
    }
    fileCredential.store(credential);
  }

  /**
   * @param userId user ID whose credential needs to be loaded
   * @param credential credential whose {@link Credential#setAccessToken access token},
   *        {@link Credential#setRefreshToken refresh token}, and
   *        {@link Credential#setExpirationTimeMilliseconds expiration time} need to be set if the
   *        credential already exists in storage
   */
  boolean load(String userId, Credential credential) {
    Preconditions.checkNotNull(userId);
    FilePersistedCredential fileCredential = credentials.get(userId);
    if (fileCredential == null) {
      return false;
    }
    fileCredential.load(credential);
    return true;
  }

  void delete(String userId) {
    Preconditions.checkNotNull(userId);
    credentials.remove(userId);
  }
}
