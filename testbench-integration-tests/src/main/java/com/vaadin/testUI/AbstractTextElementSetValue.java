/*
 * Copyright 2000-2014 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.vaadin.testUI;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.tests.AbstractTestUI;
import com.vaadin.ui.AbstractTextField;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

/**
 *
 * @since
 * @author Vaadin Ltd
 */
public class AbstractTextElementSetValue extends AbstractTestUI {

    @WebServlet(value = { "/VAADIN/*", "/AbstractTextElementSetValue/*" }, asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = AbstractTextElementSetValue.class)
    public static class Servlet extends VaadinServlet {
    }

    AbstractTextField[] comps = { new TextField(), new PasswordField(),
            new TextArea() };
    // one extra label for DateField, which we create in a separate method
    Label[] eventCountLabels = new Label[comps.length + 1];
    int[] eventCounters = new int[comps.length + 1];
    public static final String INITIAL_VALUE = "initial value";
    public static final Date INITIAL_DATE = Calendar.getInstance().getTime();

    @Override
    protected void setup(VaadinRequest request) {

        for (int i = 0; i < comps.length; i++) {
            comps[i].setValue(INITIAL_VALUE);
            eventCountLabels[i] = new Label();
            eventCountLabels[i].setCaption("event count");
            // create an valueChangeListener, to count valueChangeListener
            // events
            comps[i].addValueChangeListener(new ValueChangeCounter(i));
            addComponent(comps[i]);
            addComponent(eventCountLabels[i]);

        }
        // add one extra label for DateField, which we create in a separate
        // method
        eventCountLabels[comps.length] = new Label();
        DateField df = createDateField();
        df.addValueChangeListener(new ValueChangeCounter(comps.length));
        addComponent(df);
        eventCountLabels[comps.length].setCaption("event  count");
        addComponent(eventCountLabels[comps.length]);
    }

    @Override
    protected String getTestDescription() {
        return "Test type method of AbstractTextField components";
    }

    private DateField createDateField() {
        DateField df = new DateField();
        df.setValue(INITIAL_DATE);
        return df;
    }

    @Override
    protected Integer getTicketNumber() {
        return 13365;
    }

    // helper class, which increases valuechange event counter
    private class ValueChangeCounter implements ValueChangeListener {
        private int index;

        public ValueChangeCounter(int index) {
            this.index = index;
        }

        public void valueChange(ValueChangeEvent event) {
            eventCounters[index]++;
            String value = "" + eventCounters[index];
            eventCountLabels[index].setValue(value);
        }

    }
}
