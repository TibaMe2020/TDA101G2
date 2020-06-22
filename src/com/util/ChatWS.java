package com.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;

@ServerEndpoint(value="/chat/{member_id}", configurator=ChatConfig.class)
public class ChatWS {
//	static Set<Session> memberSessions = Collections.synchronizedSet(new HashSet<Session>());
	static Map<String, Session> memberSessions = new ConcurrentHashMap<>();
 	Gson gson = new Gson();
	
	@OnOpen
	public void onOpen(@PathParam("member_id") String member_id, EndpointConfig ec, Session memberSession) {
		memberSession.getUserProperties().put("member_id", ec.getUserProperties().get("member_id"));
		memberSessions.put(member_id, memberSession);
//		memberSessions.add(memberSession);
	}
	
	@OnMessage
	public void onMessage(String message, Session memberSession) {
		String member_id = (String) memberSession.getUserProperties().get("member_id");
		String name = (String) memberSession.getUserProperties().get("name");
		String recepient = message.substring(0, message.indexOf(","));
		String content = message.substring(message.indexOf(",") + 1);
		try {
			if(memberSessions.get(recepient) != null) {
//				memberSessions.get(recepient).getBasicRemote().sendText(toJsonData(
//						member_id, "Hello, " + recepient + " this is from " + name + " : " + content));
				memberSessions.get(recepient).getBasicRemote().sendText(toJsonData(member_id, content));
			}
		} catch (IOException e) {
			e.printStackTrace();
		};
//		if(!"".equals(member_id)) {
//			memberSessions.stream().forEach(s -> {
//				try {
////					s.getBasicRemote().sendText(toJsonData(member_id, message));
//					s.getBasicRemote().sendText(toJsonData(name, message));
//				} catch(Exception e) {
//					e.printStackTrace();
//				}
//			});
//		}
	}
	
	@OnClose
	public void onClose(@PathParam("member_id") String member_id, Session memberSession) {
		memberSessions.remove(member_id);
	}
	
	@OnError
	public void onError(Throwable t) {
		t.printStackTrace();
	}
	
	private String toJsonData(String name, String message) {
		Map<String, String> map = new HashMap<>();
		map.put("message" , name + ": " + message);
		return gson.toJson(map).toString();
 	}
}
