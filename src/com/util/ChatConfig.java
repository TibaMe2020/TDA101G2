package com.util;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

import com.member.model.MemberVO;

public class ChatConfig extends ServerEndpointConfig.Configurator{
	@Override
	public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest req, HandshakeResponse res) {
		sec.getUserProperties().put("member_id", 
				(String)((MemberVO)((HttpSession)req.getHttpSession()).getAttribute("memberVO")).getMember_id());
		sec.getUserProperties().put("name", 
				(String)((MemberVO)((HttpSession)req.getHttpSession()).getAttribute("memberVO")).getName());
	}
}
