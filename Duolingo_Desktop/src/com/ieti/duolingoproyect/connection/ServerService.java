package com.ieti.duolingoproyect.connection;

import java.net.Socket;
import java.util.ArrayList;
import duolingo.lib.dao.implementations.CrsImpl;
import duolingo.lib.dao.interfaces.ICrs;
import duolingo.lib.model.CrsModel;
import lipermi.handler.CallHandler;
import lipermi.net.IServerListener;
import lipermi.net.Server;

/**
 * Clase que permite implementar y publicar los metodos declarados en InterfaceRMI para 
 * que el movil los pueda llamar.
 * @author alecp
 *
 */
public class ServerService implements InterfaceRMI{
	//Anadir interfaces del dao segun datos que queremos obtener
	private ICrs crsDAO = new CrsImpl();
	
	//Contructor -> no modificar
	public ServerService() {
		try {
			CallHandler callHandler = new CallHandler();
			callHandler.registerGlobal(InterfaceRMI.class, this);
			Server server = new Server();
			server.bind(7777, callHandler);//7777 -> numero de puerto
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

	//A partir de aqui -> Implementar metodos de InterfaceRMI con metodos de las interfaces de los daos
	
	@Override
	public String sayHi() {
		// TODO Auto-generated method stub
		return "Hello!";
	}

	/*Este es de prueba hasta tener los modelo bien en app -> no hacer caso el tipo de retorno
	@Override
	public String getAllCrs() {
		// TODO Auto-generated method stub
		return crsDAO.getAllCrs();
	}*/
}
