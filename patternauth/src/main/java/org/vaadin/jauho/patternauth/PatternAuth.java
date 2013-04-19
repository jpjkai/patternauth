package org.vaadin.jauho.patternauth;

import org.vaadin.jauho.patternauth.client.PatternAuthClientRpc;
import org.vaadin.jauho.patternauth.client.PatternAuthServerRpc;
import org.vaadin.jauho.patternauth.client.PatternAuthState;

import com.vaadin.shared.MouseEventDetails;

// This is the server-side UI component that provides public API 
// for PatternAuth
public class PatternAuth extends com.vaadin.ui.AbstractComponent {

	private int clickCount = 0;

	// To process events from the client, we implement ServerRpc
	private PatternAuthServerRpc rpc = new PatternAuthServerRpc() {

		// Event received from client - user clicked our widget
		public void clicked(MouseEventDetails mouseDetails) {
			
			// Send nag message every 5:th click with ClientRpc
			if (++clickCount % 5 == 0) {
				getRpcProxy(PatternAuthClientRpc.class)
						.alert("Ok, that's enough!");
			}
			
			// Update shared state. This state update is automatically 
			// sent to the client. 
			getState().text = "You have clicked " + clickCount + " times";
		}
	};

	public PatternAuth() {

		// To receive events from the client, we register ServerRpc
		registerRpc(rpc);
	}

	// We must override getState() to cast the state to PatternAuthState
	@Override
	public PatternAuthState getState() {
		return (PatternAuthState) super.getState();
	}
}
