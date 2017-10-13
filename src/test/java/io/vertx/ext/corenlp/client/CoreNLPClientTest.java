package io.vertx.ext.corenlp.client;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;

import io.vertx.core.Vertx;
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
    // Be case, only use corenlp.run for test
    client = CoreNLPClient.create(vertx, new CoreNLPClientOptions().setHost("corenlp.run").setPort(80));
  }

  @AfterClass
  public static void afterClass() {

  }

  @Test
  public void annotate(TestContext ctx) throws Exception {
    Async async = ctx.async();
    client.annotate(new RequestParameters()
                      .setAnnotators(Arrays.asList("tokenize,ssplit,pos,ner,depparse,openie".split(",")))
                      .setText("Vert.x created by Tim Fox, maintain by Julien Viet"),
                    event -> {
                      if (event.succeeded()) {
                        ctx.assertFalse(event.result().isEmpty());
                      }
                      async.complete();
                    });
    async.await();
  }

  @Test
  public void tokensregex(TestContext ctx) throws Exception {
    Async async = ctx.async();
    client.tokensregex(new RequestParameters()
                         .setAnnotators(Arrays.asList("tokenize,ssplit,pos,ner,depparse,openie".split(",")))
                         .setPattern("[ner: PERSON]")
                         .setText("Vert.x created by Tim Fox, maintain by Julien Viet"),
                       event -> {
                         if (event.succeeded()) {
                           ctx.assertFalse(event.result().isEmpty());
                         }
                         async.complete();
                       });
    async.await();
  }

  @Test
  public void semgrex(TestContext ctx) throws Exception {
    Async async = ctx.async();
    client.semgrex(new RequestParameters()
                     .setAnnotators(Arrays.asList("tokenize,ssplit,pos,ner,depparse,openie".split(",")))
                     .setPattern("{}=A <<nsubj {}=B")
                     .setText("Vert.x created by Tim Fox, maintain by Julien Viet"),
                   event -> {
                     if (event.succeeded()) {
                       ctx.assertFalse(event.result().isEmpty());
                     }
                     async.complete();
                   });
    async.await();
  }

}
