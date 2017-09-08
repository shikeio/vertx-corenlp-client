package io.shike.corenlp.client;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

/**
 * @author Ranger Tsao(https://github.com/boliza)
 */
public interface CoreNLPClient {

  static CoreNLPClient create(Vertx vertx, CoreNLPClientOptions options) {
    return new CoreNLPClientImpl(vertx, options);
  }

  void annotate(RequestParameters parameters, Handler<AsyncResult<JsonObject>> handler);

  void tokensregex(RequestParameters parameters, Handler<AsyncResult<JsonObject>> handler);

  void semgrex(RequestParameters parameters, Handler<AsyncResult<JsonObject>> handler);

}
