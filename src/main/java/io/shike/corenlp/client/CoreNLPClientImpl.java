package io.shike.corenlp.client;

import java.util.Base64;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.HttpRequest;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.WebClientOptions;
import io.vertx.ext.web.codec.BodyCodec;

/**
 * @author Ranger Tsao(https://github.com/boliza)
 */
public class CoreNLPClientImpl implements CoreNLPClient {

  private WebClient client;
  private CoreNLPClientOptions options;

  CoreNLPClientImpl(Vertx vertx, CoreNLPClientOptions options) {
    this.client = WebClient.create(vertx, new WebClientOptions().setDefaultHost(options.getHost()).setDefaultPort(options.getPort()));
    this.options = options;
  }

  @Override
  public void annotate(JsonObject properties, String language, Handler<AsyncResult<JsonObject>> handler) {
    buildRequest("/", properties).send(h -> {
      if (h.succeeded()) {
        handler.handle(Future.succeededFuture(h.result().body()));
      } else {
        handler.handle(Future.failedFuture(h.cause()));
      }
    });
  }

  @Override
  public void tokensregex(JsonObject properties, String language, Handler<AsyncResult<JsonObject>> handler) {
    buildRequest("/tokensregex", properties).send(h -> {
      if (h.succeeded()) {
        handler.handle(Future.succeededFuture(h.result().body()));
      } else {
        handler.handle(Future.failedFuture(h.cause()));
      }
    });
  }

  @Override
  public void semgrex(JsonObject properties, String language, Handler<AsyncResult<JsonObject>> handler) {
    buildRequest("/semgrex", properties).send(h -> {
      if (h.succeeded()) {
        handler.handle(Future.succeededFuture(h.result().body()));
      } else {
        handler.handle(Future.failedFuture(h.cause()));
      }
    });
  }

  private HttpRequest<JsonObject> buildRequest(String requestURI, JsonObject properties) {
    HttpRequest<JsonObject> request = client.post(requestURI)
                                            .as(BodyCodec.jsonObject())
                                            .setQueryParam("properties", properties.toString());
    if (options.getUsername() != null && options.getPassword() != null) {
      request.putHeader("Authorization",
                        "Basic " + Base64.getEncoder().encodeToString(String.join(":", options.getUsername(), options.getPassword()).getBytes()));
    }
    return request;
  }
}
