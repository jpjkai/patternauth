package org.vaadin.jauho.patternauth.client;

import com.google.gwt.user.client.ui.Label;

// Extend any GWT Widget
public class PatternAuthWidget extends Label {

	public PatternAuthWidget() {

		// CSS class-name should not be v- prefixed
		setStyleName("patternauth");

		// State is set to widget in PatternAuthConnector		
	}

}