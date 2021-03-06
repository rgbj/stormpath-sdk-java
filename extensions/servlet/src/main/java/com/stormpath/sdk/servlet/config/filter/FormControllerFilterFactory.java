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
package com.stormpath.sdk.servlet.config.filter;

import com.stormpath.sdk.lang.Collections;
import com.stormpath.sdk.servlet.config.Config;
import com.stormpath.sdk.servlet.filter.ControllerConfig;
import com.stormpath.sdk.servlet.form.Field;
import com.stormpath.sdk.servlet.mvc.FormController;

import java.util.List;

/**
 * @since 1.0.0
 */
public abstract class FormControllerFilterFactory<T extends FormController> extends ControllerFilterFactory<T> {

    protected final void configure(T controller, Config config) throws Exception {
        controller.setCsrfTokenManager(config.getCsrfTokenManager());
        controller.setFieldValueResolver(config.getFieldValueResolver());
        apply(controller, getResolver(config));
        doConfigure(controller, config);
    }

    private void apply(FormController controller, ControllerConfig cr) {
        controller.setUri(cr.getUri());
        controller.setNextUri(cr.getNextUri());
        controller.setView(cr.getView());
        controller.setControllerKey(cr.getControllerKey());
        List<Field> fields = cr.getFormFields();
        if (!Collections.isEmpty(fields)) { //might be empty if the fields are static / configured within the controller
            controller.setFormFields(fields);
        }
    }

    protected abstract void doConfigure(T controller, Config config) throws Exception;

    protected abstract ControllerConfig getResolver(Config config);
}
