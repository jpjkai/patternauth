package org.vaadin.jauho.patternauth.client;

import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

// Extend any GWT Widget
public class PatternAuthWrapper extends FlowPanel {

	public PatternAuthWrapper() {

		// CSS class-name should not be v- prefixed
		setStyleName("wrapper");


		for (int i = 0; i < 8; i++) {
			NodeWidget node = new NodeWidget("ebin"+i);
			node.addMouseOverHandler(new MyMouseEventHandler());
			add(node);
		}
		
		
	}

	public class MyMouseEventHandler implements MouseOverHandler,
			MouseOutHandler {
		public void onMouseOver(final MouseOverEvent moe) {
			NodeWidget widget = (NodeWidget) moe.getSource();
			Window.alert(widget.getTag());

		}

		public void onMouseOut(final MouseOutEvent moe) {
			Widget widget = (Widget) moe.getSource();

		}
	}
}