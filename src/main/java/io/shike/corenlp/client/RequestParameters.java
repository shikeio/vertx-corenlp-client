package io.shike.corenlp.client;

import java.util.List;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.core.json.JsonObject;

/**
 * @author Ranger Tsao(https://github.com/boliza)
 */
@DataObject(generateConverter = true)
public class RequestParameters {

  private JsonObject properties;
  private String text;

  private Language language = Language.en;

  private String pattern;
  private boolean filter;

  public RequestParameters() {
  }

  public RequestParameters(JsonObject json) {
    RequestParametersConverter.fromJson(json, this);
  }

  public JsonObject toJson() {
    JsonObject json = new JsonObject();
    RequestParametersConverter.toJson(this, json);
    return json;
  }

  public JsonObject getProperties() {
    return properties;
  }

  public void setProperties(JsonObject properties) {
    this.properties = properties;
  }

  @GenIgnore
  public RequestParameters setAnnotators(List<String> annotators) {
    checkProperties();
    properties.put("annotators", String.join(",", annotators));
    return this;
  }

  @GenIgnore
  public RequestParameters addAnnotator(String annotator) {
    checkProperties();
    properties.put("annotators", String.join(",", properties.getString("annotators", annotator).split(",")));
    return this;
  }

  @GenIgnore
  public RequestParameters addProperty(String key, Object value) {
    checkProperties();
    properties.put("key", key);
    return this;
  }

  private void checkProperties() {
    if (properties == null) {
      properties = new JsonObject();
    }
  }

  public String getText() {
    return text;
  }

  public RequestParameters setText(String text) {
    this.text = text;
    return this;
  }

  public Language getLanguage() {
    return language;
  }

  public RequestParameters setLanguage(Language language) {
    this.language = language;
    return this;
  }

  public String getPattern() {
    return pattern;
  }

  public RequestParameters setPattern(String pattern) {
    this.pattern = pattern;
    return this;
  }

  public boolean isFilter() {
    return filter;
  }

  public RequestParameters setFilter(boolean filter) {
    this.filter = filter;
    return this;
  }
}
