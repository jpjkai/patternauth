package org.vaadin.jauho.patternauth.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

// Extend any GWT Widget
public class PatternAuthWrapper extends FlowPanel {

	private PatternAuthServerRpc rpc;
	private String result = "";
	private List<NodeWidget> hoveredNodes = new ArrayList<NodeWidget>();
	private Timer timer;
	public PatternAuthWrapper() {

		// CSS class-name should not be v- prefixed
		setStyleName("wrapper");

		

	}
	
	public void addNodes(int rows, int cols) {
		clear();
		for (int i = 0; i < rows*cols; i++) {
			NodeWidget node = new NodeWidget("ebin" + i);
			node.getElement().getStyle().setWidth(100/cols-2, Unit.PCT);
			node.getElement().getStyle().setHeight(100/rows-2, Unit.PCT);
			node.addMouseOverHandler(new MyMouseEventHandler());
			node.addMouseOutHandler(new MyMouseEventHandler());
			add(node);
		}
		
	}

	public void setRpc(PatternAuthServerRpc rpc) {
		this.rpc = rpc;
	}

	public class MyMouseEventHandler implements MouseOverHandler,
			MouseOutHandler {
		public void onMouseOver(final MouseOverEvent moe) {
			NodeWidget widget = (NodeWidget) moe.getSource();
			rpc.sendValue(widget.getTag());
			result = result + widget.getTag();
			hoveredNodes.add(widget);
			widget.setStyleName("node-selected");
		}

		public void onMouseOut(final MouseOutEvent moe) {
			Widget widget = (Widget) moe.getSource();
			initTimer();
		}
	}
	
	private void initTimer() {
		if (timer != null) {
			timer.cancel();
		}
		 timer = new Timer() {
			
			@Override
			public void run() {
				result = "";
				for (NodeWidget node : hoveredNodes) {
					node.setStyleName("node");
				}
				
			}
		};
		timer.schedule(2000);
	}
}