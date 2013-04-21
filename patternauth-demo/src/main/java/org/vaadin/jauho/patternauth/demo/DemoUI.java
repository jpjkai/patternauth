package org.vaadin.jauho.patternauth.demo;

import org.vaadin.jauho.patternauth.PatternAuth;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

@Theme("demo")
@Title("PatternAuth Add-on Demo")
@SuppressWarnings("serial")
public class DemoUI extends UI {

	private Label currentValue; 
	@Override
	protected void init(VaadinRequest request) {
		currentValue = new Label("Test Label");
		currentValue. setImmediate(true);
		// Initialize our new UI component
		final PatternAuth patternauth = new PatternAuth();
		patternauth.setImmediate(true);

		// Show it in the middle of the screen
		final VerticalLayout layout = new VerticalLayout();
		layout.setStyleName("demoContentLayout");
		layout.setSizeFull();
		layout.addComponent(patternauth);
		layout.setComponentAlignment(patternauth, Alignment.MIDDLE_CENTER);
		patternauth.addValueChangeListener(new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				currentValue.setValue((String)event.getProperty().getValue());
			}
		});
		setContent(layout);
		final TextField rows = new TextField("rows");
		final TextField cols = new TextField("cols");
		
		Button set = new Button("set");
		set.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				patternauth.getState().cols = Integer.parseInt(cols.getValue());
				patternauth.getState().rows = Integer.parseInt(rows.getValue());
			}
		});
		layout.addComponent(rows);
		layout.addComponent(cols);
		layout.addComponent(currentValue);
		layout.addComponent(set);
	}

}
