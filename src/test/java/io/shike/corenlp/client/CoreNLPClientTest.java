package io.shike.corenlp.client;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;

/**
 * @author Ranger Tsao(https://github.com/boliza)
 */
@RunWith(VertxUnitRunner.class)
public class CoreNLPClientTest {

  static Vertx vertx;
  static CoreNLPClient client;

  @BeforeClass
  public static void beforeClass() {
    vertx = Vertx.vertx();
    client = CoreNLPClient.create(vertx, new CoreNLPClientOptions().setHost("106.14.17.187"));
  }

  @AfterClass
  public static void afterClass() {

  }

  @Test
  public void annotate(TestContext ctx) throws Exception {
    Async async = ctx.async();
    client.annotate(new JsonObject().put("annotators", "tokenize,ssplit,pos,ner,depparse,openie"), "en", event -> {
      if (event.succeeded()) {
        System.out.println(event.result());
      }
      async.complete();
    });
    async.await();
  }

  @Test
  public void tokensregex() throws Exception {
  }

  @Test
  public void semgrex() throws Exception {
  }

}
