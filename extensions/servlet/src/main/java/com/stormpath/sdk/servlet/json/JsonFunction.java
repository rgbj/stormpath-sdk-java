/*
 * Copyright 2016 Stormpath, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.stormpath.sdk.servlet.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stormpath.sdk.lang.Assert;
import com.stormpath.sdk.lang.Function;

/**
 * @since 1.1.0
 */
public class JsonFunction<T> implements Function<T, String> {

    private ObjectMapper objectMapper;

    public JsonFunction() {
        this(new ObjectMapper());
    }

    public JsonFunction(ObjectMapper objectMapper) {
        Assert.notNull(objectMapper, "ObjectMapper cannot be null.");
        this.objectMapper = objectMapper;
    }

    @Override
    public String apply(T value) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            String msg = "Cannot convert object value [" + value + "] to JSON string: " + e.getMessage();
            throw new IllegalArgumentException(msg, e);
        }
    }
}
