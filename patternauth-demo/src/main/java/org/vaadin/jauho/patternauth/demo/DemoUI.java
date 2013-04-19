package org.vaadin.jauho.patternauth.demo;

import org.vaadin.jauho.patternauth.PatternAuth;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("demo")
@Title("PatternAuth Add-on Demo")
@SuppressWarnings("serial")
public class DemoUI extends UI
{

    @Override
    protected void init(VaadinRequest request) {
    	
    	// Initialize our new UI component
    	final PatternAuth patternauth = new PatternAuth();
            	
    	// Show it in the middle of the screen
    	final VerticalLayout layout = new VerticalLayout();
        layout.setStyleName("demoContentLayout");
        layout.setSizeFull();
        layout.addComponent(patternauth);
        layout.setComponentAlignment(patternauth, Alignment.MIDDLE_CENTER);
        setContent(layout);

    }

}
