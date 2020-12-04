package com.ieti.duolingoproyect.connection;

import java.net.Socket;
import java.util.ArrayList;

import duolingo.lib.model.CrsModel;
import lipermi.handler.CallHandler;
import lipermi.net.IServerListener;
import lipermi.net.Server;

/**
 * Clase para probar conexion rmi no tocar ni usar
 * @author alecp
 *
 */
public class TestConnection {
	public TestConnection() {
		try {
			CallHandler callHandler = new CallHandler();
			callHandler.registerGlobal(InterfaceRMI.class, this);
			Server server = new Server();
			server.bind(7777, callHandler);
			server.addServerListener(new IServerListener() {

				@Override
				public void clientConnected(Socket socket) {
					// TODO Auto-generated method stub
					System.out.println("Client connected : "+socket.getInetAddress());
				}

				@Override
				public void clientDisconnected(Socket socket) {
					// TODO Auto-generated method stub
					System.out.println("Client disconnected : "+socket.getInetAddress());
				}
				
			});
			
			System.out.println("Server started...");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error to start server.");
		}
		
	}
}
