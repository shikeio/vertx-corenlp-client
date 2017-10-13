/*
 * Copyright 2014 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package io.vertx.ext.corenlp.client;

import io.vertx.core.json.JsonObject;

/**
 * Converter for {@link RequestParameters}.
 *
 * NOTE: This class has been automatically generated from the {@link RequestParameters} original class using Vert.x codegen.
 */
public class RequestParametersConverter {

  public static void fromJson(JsonObject json, RequestParameters obj) {
    if (json.getValue("filter") instanceof Boolean) {
      obj.setFilter((Boolean) json.getValue("filter"));
    }
    if (json.getValue("language") instanceof String) {
      obj.setLanguage(Language.valueOf((String) json.getValue("language")));
    }
    if (json.getValue("pattern") instanceof String) {
      obj.setPattern((String) json.getValue("pattern"));
    }
    if (json.getValue("properties") instanceof JsonObject) {
      obj.setProperties(((JsonObject) json.getValue("properties")).copy());
    }
    if (json.getValue("text") instanceof String) {
      obj.setText((String) json.getValue("text"));
    }
  }

  public static void toJson(RequestParameters obj, JsonObject json) {
    json.put("filter", obj.isFilter());
    if (obj.getLanguage() != null) {
      json.put("language", obj.getLanguage().name());
    }
    if (obj.getPattern() != null) {
      json.put("pattern", obj.getPattern());
    }
    if (obj.getProperties() != null) {
      json.put("properties", obj.getProperties());
    }
    if (obj.getText() != null) {
      json.put("text", obj.getText());
    }
  }
}
