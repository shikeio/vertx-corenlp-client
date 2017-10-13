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
 * Converter for {@link CoreNLPClientOptions}.
 *
 * NOTE: This class has been automatically generated from the {@link CoreNLPClientOptions} original class using Vert.x codegen.
 */
public class CoreNLPClientOptionsConverter {

  public static void fromJson(JsonObject json, CoreNLPClientOptions obj) {
    if (json.getValue("host") instanceof String) {
      obj.setHost((String) json.getValue("host"));
    }
    if (json.getValue("password") instanceof String) {
      obj.setPassword((String) json.getValue("password"));
    }
    if (json.getValue("port") instanceof Number) {
      obj.setPort(((Number) json.getValue("port")).intValue());
    }
    if (json.getValue("ssl") instanceof Boolean) {
      obj.setSsl((Boolean) json.getValue("ssl"));
    }
    if (json.getValue("sslKey") instanceof String) {
      obj.setSslKey((String) json.getValue("sslKey"));
    }
    if (json.getValue("username") instanceof String) {
      obj.setUsername((String) json.getValue("username"));
    }
  }

  public static void toJson(CoreNLPClientOptions obj, JsonObject json) {
    if (obj.getHost() != null) {
      json.put("host", obj.getHost());
    }
    if (obj.getPassword() != null) {
      json.put("password", obj.getPassword());
    }
    json.put("port", obj.getPort());
    json.put("ssl", obj.isSsl());
    if (obj.getSslKey() != null) {
      json.put("sslKey", obj.getSslKey());
    }
    if (obj.getUsername() != null) {
      json.put("username", obj.getUsername());
    }
  }
}
