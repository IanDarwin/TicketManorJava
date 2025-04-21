package com.ticketmanor.csrchat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

/**
 * CSR Chat - Customer Service Rep online chat.
 * This version implemented using Java WebSocket API.
 * Client will probably be in JavaScript, except for a simple test client.
 * @author ian
 *
 */
@ServerEndpoint(value="/csrchat")
public class CsrChatServer {
	
	List<String> log = new ArrayList<>();

	@OnOpen
	public void onOpen(Session session) throws IOException {
		System.out.println("CsrChatServer.onOpen()");
	}
	
	@OnMessage
	public void onMessage(Session session, String message) throws IOException {
		System.out.println("CsrChatServer.onMessage()");
		session.getBasicRemote().sendText("You said '+ message + "
				+ ". Unfortunately nobody is here to take your message.");
		
	}
}
