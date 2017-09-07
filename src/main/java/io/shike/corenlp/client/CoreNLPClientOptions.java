package io.shike.corenlp.client;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

/**
 * @author Ranger Tsao(https://github.com/boliza)
 */
@DataObject(generateConverter = true)
public class CoreNLPClientOptions {

  private final String DEFAULT_HOST = "127.0.0.1";
  private final int DEFAULT_PORT = 8080;

  private String host = DEFAULT_HOST;
  private int port = DEFAULT_PORT;

  private String username;
  private String password;

  private boolean ssl = false;
  private String sslKey;

  public CoreNLPClientOptions() {
  }

  public CoreNLPClientOptions(CoreNLPClientOptions other) {
    this.host = other.host;
    this.port = other.port;
    this.username = other.username;
    this.password = other.password;
    this.ssl = other.ssl;
    this.sslKey = other.sslKey;
  }

  public CoreNLPClientOptions(JsonObject json) {
    CoreNLPClientOptionsConverter.fromJson(json, this);
  }

  public JsonObject toJson() {
    JsonObject json = new JsonObject();
    CoreNLPClientOptionsConverter.toJson(this, json);
    return json;
  }

  public String getHost() {
    return host;
  }

  public CoreNLPClientOptions setHost(String host) {
    this.host = host;
    return this;
  }

  public int getPort() {
    return port;
  }

  public CoreNLPClientOptions setPort(int port) {
    this.port = port;
    return this;
  }

  public String getUsername() {
    return username;
  }

  public CoreNLPClientOptions setUsername(String username) {
    this.username = username;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public CoreNLPClientOptions setPassword(String password) {
    this.password = password;
    return this;
  }

  public boolean isSsl() {
    return ssl;
  }

  public CoreNLPClientOptions setSsl(boolean ssl) {
    this.ssl = ssl;
    return this;
  }

  public String getSslKey() {
    return sslKey;
  }

  public CoreNLPClientOptions setSslKey(String sslKey) {
    this.sslKey = sslKey;
    return this;
  }
}
