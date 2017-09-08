package io.shike.corenlp.client;

import java.util.Base64;
import java.util.Objects;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
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
  public void annotate(RequestParameters parameters, Handler<AsyncResult<JsonObject>> handler) {
    buildRequest("/", parameters)
      .sendBuffer(Buffer.buffer(parameters.getText()),
                  h -> {
                    if (h.succeeded()) {
                      handler.handle(Future.succeededFuture(h.result().body()));
                    } else {
                      handler.handle(Future.failedFuture(h.cause()));
                    }
                  });
  }

  @Override
  public void tokensregex(RequestParameters parameters, Handler<AsyncResult<JsonObject>> handler) {
    Objects.requireNonNull(parameters.getPattern(), "pattern must have a value");
    buildRequest("/tokensregex", parameters)
      .sendBuffer(Buffer.buffer(parameters.getText()), h -> {
        if (h.succeeded()) {
          handler.handle(Future.succeededFuture(h.result().body()));
        } else {
          handler.handle(Future.failedFuture(h.cause()));
        }
      });
  }

  @Override
  public void semgrex(RequestParameters parameters, Handler<AsyncResult<JsonObject>> handler) {
    Objects.requireNonNull(parameters.getPattern(), "pattern must have a value");
    buildRequest("/semgrex", parameters)
      .sendBuffer(Buffer.buffer(parameters.getText()), h -> {
        if (h.succeeded()) {
          handler.handle(Future.succeededFuture(h.result().body()));
        } else {
          handler.handle(Future.failedFuture(h.cause()));
        }
      });
  }

  private HttpRequest<JsonObject> buildRequest(String requestURI, RequestParameters parameters) {
    HttpRequest<JsonObject> request = client.post(requestURI)
                                            .as(BodyCodec.jsonObject())
                                            .putHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8")
                                            .setQueryParam("properties", parameters.getProperties().toString())
                                            .setQueryParam("pipelineLanguage", parameters.getLanguage().name());
    if (options.getUsername() != null && options.getPassword() != null) {
      request.putHeader("Authorization",
                        "Basic " + Base64.getEncoder().encodeToString(String.join(":", options.getUsername(), options.getPassword()).getBytes()));
    }
    if (parameters.getPattern() != null) {
      request.setQueryParam("pattern", parameters.getPattern())
             .setQueryParam("filter", String.valueOf(parameters.isFilter()));
    }
    return request;
  }
}
