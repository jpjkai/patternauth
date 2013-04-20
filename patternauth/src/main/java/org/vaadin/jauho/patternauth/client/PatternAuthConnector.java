package org.vaadin.jauho.patternauth.client;

import org.vaadin.jauho.patternauth.PatternAuth;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.communication.RpcProxy;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.shared.ui.Connect;

// Connector binds client-side widget class to server-side component class
// Connector lives in the client and the @Connect annotation specifies the
// corresponding server-side component
@Connect(PatternAuth.class)
public class PatternAuthConnector extends AbstractComponentConnector {

	// ServerRpc is used to send events to server. Communication implementation
	// is automatically created here
	PatternAuthServerRpc rpc = RpcProxy
			.create(PatternAuthServerRpc.class, this);

	public PatternAuthConnector() {

		// To receive RPC events from server, we register ClientRpc
		// implementation
		registerRpc(PatternAuthClientRpc.class, new PatternAuthClientRpc() {
			public void alert(String message) {
				Window.alert(message);
			}
		});

		// We choose listed for mouse clicks for the widget
		// getWidget().addClickHandler(new ClickHandler() {
		// public void onClick(ClickEvent event) {
		// final MouseEventDetails mouseDetails = MouseEventDetailsBuilder
		// .buildMouseEventDetails(event.getNativeEvent(),
		// getWidget().getElement());
		//
		// // When the widget is clicked, the event is sent to server with
		// ServerRpc
		// rpc.clicked(mouseDetails);
		// }
		// });
		//getWidget().addMouseOverHandler(new MyMouseEventHandler());
	}

	

	// We must implement createWidget() to create correct type of widget
	@Override
	protected Widget createWidget() {
		return GWT.create(PatternAuthWrapper.class);
	}

	// We must implement getWidget() to cast to correct type
	@Override
	public PatternAuthWrapper getWidget() {
		return (PatternAuthWrapper) super.getWidget();
	}

	// We must implement getState() to cast to correct type
	@Override
	public PatternAuthState getState() {
		return (PatternAuthState) super.getState();
	}

	// Whenever the state changes in the server-side, this method is called
	@Override
	public void onStateChanged(StateChangeEvent stateChangeEvent) {
		super.onStateChanged(stateChangeEvent);

		// State is directly readable in the client after it is set in server
		final String text = getState().text;
		// getWidget().setText(text);
	}

}
