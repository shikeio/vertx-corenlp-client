package examples;

import java.util.Arrays;

import io.shike.corenlp.client.CoreNLPClient;
import io.shike.corenlp.client.CoreNLPClientOptions;
import io.shike.corenlp.client.RequestParameters;
import io.vertx.core.Vertx;

/**
 * @author Ranger Tsao(https://github.com/boliza)
 */
public class CoreNLPClientExamples {

  public void example1(Vertx vertx) {
    CoreNLPClient client = CoreNLPClient.create(vertx, new CoreNLPClientOptions());
    client.annotate(new RequestParameters().setText("Vert.x created by Tim Fox, maintain by Julien Viet")
                                           .setAnnotators(Arrays.asList("tokenize", "ssplit", "pos", "ner", "depparse", "openie")),
                    handler -> {
                      //su
                      if (handler.succeeded()) {
                        // do with result
                        System.out.println(handler.result());
                      } else {
                        System.out.println(handler.cause());
                      }
                    });
  }

  public void example2(Vertx vertx) {
    CoreNLPClient client = CoreNLPClient.create(vertx, new CoreNLPClientOptions());
    client.tokensregex(new RequestParameters().setText("Vert.x created by Tim Fox, maintain by Julien Viet")
                                              .setPattern("[ner: PERSON]")
                                              .setAnnotators(Arrays.asList("tokenize", "ssplit", "pos", "ner", "depparse", "openie")),
                       handler -> {
                         //su
                         if (handler.succeeded()) {
                           // do with result
                           System.out.println(handler.result());
                         } else {
                           System.out.println(handler.cause());
                         }
                       });
  }

  public void example3(Vertx vertx) {
    CoreNLPClient client = CoreNLPClient.create(vertx, new CoreNLPClientOptions());
    client.semgrex(new RequestParameters().setText("Vert.x created by Tim Fox, maintain by Julien Viet")
                                          .setPattern("{}=A <<nsubj {}=B")
                                          .setAnnotators(Arrays.asList("tokenize", "ssplit", "pos", "ner", "depparse", "openie")),
                   handler -> {
                     //su
                     if (handler.succeeded()) {
                       // do with result
                       System.out.println(handler.result());
                     } else {
                       System.out.println(handler.cause());
                     }
                   });
  }

}
