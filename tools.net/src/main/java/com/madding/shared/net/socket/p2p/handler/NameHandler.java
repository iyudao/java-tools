/*
 * Copyright (C) 2013-2016 aqnote.com<madding.lip@gmail.com>. 
 * This library is free software; you can redistribute it and/or modify it under the terms of
 * the GNU Lesser General Public License as published by the Free Software Foundation;
 */
package com.madding.shared.net.socket.p2p.handler;

import com.madding.shared.net.socket.p2p.Connection;
import com.madding.shared.net.socket.p2p.Handler;
import com.madding.shared.net.socket.p2p.Message;
import com.madding.shared.net.socket.p2p.Node;
import com.madding.shared.net.socket.p2p.sharer.SharerMessage;

/**
 * Handler for LIST Messages
 * LIST: Requests a peer to reply with the list of peers that it knows about.
 * @author axvelazq
 */
public class NameHandler extends Handler {

	public NameHandler(Node node) {
		super(node);
	}

	@Override
	public void handleMessage(Connection conn, Message message) {
		Node peer = this.getNode();
		conn.sendData(new Message(SharerMessage.REPLY,String.format("%d",peer.getNumberPeers())));
		for(String pid : peer.getPeerKeys()){
			String host = peer.getPeer(pid).getHost();
			int port = peer.getPeer(pid).getPort();
			conn.sendData(new Message(SharerMessage.REPLY,String.format("%s %s %d",pid,host,port)));
		}
		
	}

}
