package org.vaadin.jauho.patternauth.client;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.dom.client.TouchEndHandler;
import com.google.gwt.event.dom.client.TouchMoveEvent;
import com.google.gwt.event.dom.client.TouchMoveHandler;
import com.google.gwt.event.dom.client.TouchStartEvent;
import com.google.gwt.event.dom.client.TouchStartHandler;
import com.google.gwt.event.shared.HandlerRegistration;
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

		addTouchMoveHandler(new MyMouseEventHandler());

	}
	
	public void addNodes(int rows, int cols) {
		clear();
		for (int i = 0; i < rows*cols; i++) {
			NodeWidget node = new NodeWidget("ebin" + i);
			node.getElement().getStyle().setWidth(100/cols-8, Unit.PCT);
			//node.getElement().getStyle().setHeight(100/rows-2, Unit.PCT);
			node.getElement().getStyle().setPaddingBottom(100/cols-8, Unit.PCT);
			node.addMouseOverHandler(new MyMouseEventHandler());
			node.addMouseOutHandler(new MyMouseEventHandler());
			//node.addTouchStartHandler(new MyMouseEventHandler());
			//node.addTouchEndHandler(new MyMouseEventHandler());
			//node.addTouchMoveHandler(new MyMouseEventHandler());
			add(node);
		}
		
	}
	
	public HandlerRegistration addTouchMoveHandler(TouchMoveHandler handler) {
		return addDomHandler(handler, TouchMoveEvent.getType());
	}
	

	public void setRpc(PatternAuthServerRpc rpc) {
		this.rpc = rpc;
	}

	public class MyMouseEventHandler implements MouseOverHandler,
			MouseOutHandler, TouchStartHandler, TouchEndHandler, TouchMoveHandler {
		public void onMouseOver(final MouseOverEvent moe) {
			NodeWidget widget = (NodeWidget) moe.getSource();
			stateChangeStart(widget);
			widget.setNextState();
			System.out
					.println("PatternAuthWrapper.MyMouseEventHandler.onMouseOver()");
		}

		public void onMouseOut(final MouseOutEvent moe) {
			Widget widget = (Widget) moe.getSource();
			initTimer();
		}

		@Override
		public void onTouchStart(TouchStartEvent event) {
			NodeWidget widget = (NodeWidget) event.getSource();
			stateChangeStart(widget);
			System.out
					.println("PatternAuthWrapper.MyMouseEventHandler.onTouchStart()");
		}

		@Override
		public void onTouchEnd(TouchEndEvent event) {
			// TODO Auto-generated method stub
			initTimer();
			System.out
					.println("PatternAuthWrapper.MyMouseEventHandler.onTouchEnd()");
			
		}

		@Override
		public void onTouchMove(TouchMoveEvent event) {
			//NodeWidget widget = (NodeWidget) event.getSource();
			 Logger logger = Logger.getLogger("NameOfYourLogger");
			    logger.log(Level.SEVERE, "this message should get logged "+event.getRelativeElement().getClassName());
			//event.getRelativeElement().getClassName();
			//stateChangeStart(widget);
			System.out
					.println("PatternAuthWrapper.MyMouseEventHandler.onTouchMove()");
			
		}
	}
	
	private void stateChangeStart(NodeWidget widget) {
		rpc.sendValue(widget.getTag());
		result = result + widget.getTag();
		hoveredNodes.add(widget);
		widget.setNextState();
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
					node.resetState();
				}
				hoveredNodes.clear();
			}
		};
		timer.schedule(2000);
	}
}