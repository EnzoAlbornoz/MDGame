// Search for the string "change me" to identify what code must be
// altered when using your own gui class.

/* This file is part of the DualRpcServer libraries
 Copyright (c) 2004-2005, Vick Perry

 This library is free software; you can redistribute it and/or
 modify it under the terms of the BSD license. See the contents
 of the file LICENSE.txt for more details.
 */
package br.ufsc.inf.leobr.cliente;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.MarshalledObject;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.retrogui.dualrpc.client.DualRpcClient;
import com.retrogui.dualrpc.client.NotConnectedException;
import com.retrogui.dualrpc.common.CallException;

/**
 * The client. The default configuration file is 'client.properties'. The
 * configuration file resides in the root of the class directory. The default
 * configuration file path can be overridden with the VM argument
 * '-Dclient.configuration.file=myfile.properties'
 * 
 * Replace the Gui class with one of your own. The Gui class should have a
 * constructor that accepts a client.Client parameter and has a postProc()
 * method. See the connect() method below where the Gui instantiation code is
 * located.
 * 
 * The data members are intentionally public so that gui and client handler can
 * manipulate them.
 * 
 * Prefer IPv4 stack if both IPv4 and IPv6 are running.
 * 
 * A heartbeat thread is launched to do periodic heartbeat pings to server. This
 * is especially useful to avoid proxy connection timeout due to inactivity
 * 
 * @author vickp
 */

public final class Cliente {

	public static final String LOGFILE_PROPERTY = "log4j.appender.rollinglogfile.File";

	public static final String DUALRPC_DIRECTORY = ".dualrpc";

	public static final String SYSTEM_PROPERTY_USER_HOME = "user.home";

	public static final String CONFIG_SERVER_PORT = "server.port";

	public static final String CONFIG_SERVER_HOST = "server.host";

	public static final String PRIMARY_SERVER_HANDLER_CLASSNAME = "br.ufsc.inf.leobr.servidor.ServerHandler";

	public static final String HANDSHAKE_RPC_METHOD = "handshake";

	/*
	 * A localização desse arquivo de configuração é relativa ao diretorio root.
	 * Quando o arquivo é localizado no root então o arquivo não tem prefixo de
	 * pacote. Se o arquivo properties é localizado abaixo em um pacote então o
	 * prefixo é seguido por /. ex: ufsc/inf/meuArquivo.properties
	 */
	public static final String DEFAULT_CONFIGURATION_FILE = "client.properties";

	public static final String CONFIGURATION_FILE_VM_ARG = "client.configuration.file";

	public static Logger logger = Logger.getLogger(Cliente.class.getName());

	private DualRpcClient dualRpcClient = null;

	private String serverHost = null;

	private int serverPort = 1099;

	public long serverSessionId = 0;

	public long clientSessionId = 0;

	/**
	 * Conecta o cliente/jogador no servidor
	 * 
	 * @param clienteReceptor
	 * @param idJogo
	 * @param nomeJogador
	 * @throws IOException
	 * @throws CallException
	 * @throws GeneralSecurityException
	 * @throws NotConnectedException
	 */

	 
	public void connect(ClienteReceptor clienteReceptor, Long idJogo,
			String ipServidor, String nomeJogador) throws IOException,
			CallException, GeneralSecurityException, NotConnectedException {

		// get configuration file
		String configurationFile = DEFAULT_CONFIGURATION_FILE;

		// use alternate config file if one is specified as VM arg
		String alternateConfigurationFile = System
				.getProperty(CONFIGURATION_FILE_VM_ARG);

		if (alternateConfigurationFile != null) {
			configurationFile = alternateConfigurationFile;
		}

		// Read the properties file and store the data in a Properties object
		// Use classloader getResourceAsStream() because Properties file is on
		// the classpath.
		Properties configurationProperties = new Properties();
		InputStream in = this.getClass().getClassLoader().getResourceAsStream(
				configurationFile);
		if (in == null) {
			throw new IOException(
					"Não é possível abrir o documento de configuração no classpath "
							+ configurationFile);
		}
		configurationProperties.load(in);
		in.close();

		// Retain the original log filename but place it in the log directory.
		// Override the log file properties "log4j.appender.rollinglogfile.File"
		// to place the log file in the $HOME/.dualrpc directory.
		String homedir = System.getProperty(SYSTEM_PROPERTY_USER_HOME);
		String logdir = (homedir == null ? "" : homedir + File.separator)
				+ DUALRPC_DIRECTORY;

		// If the log directory doesn't exist, create it
		File dir = new File(logdir);
		if (!dir.exists()) {
			if (!dir.mkdirs()) {
				throw new IOException(
						"Não é possível criar o diretório de log: " + logdir);
			}
		}

		// is the logging directory really a directory?
		if (!dir.isDirectory()) {
			throw new IOException(
					"Logging directory path conflicts with file: " + logdir);
		}

		// prepend the logging directory to the log file name
		String originalLogFile = configurationProperties
				.getProperty(LOGFILE_PROPERTY);
		String newLogFile = logdir + File.separator + originalLogFile;
		configurationProperties.setProperty(LOGFILE_PROPERTY, newLogFile);

		// configure log4j logger from properties
		org.apache.log4j.PropertyConfigurator
				.configure(configurationProperties);

		// startup log entry
		logger.info("Starting client with configuration file "
				+ configurationFile);
		logger.info("Logging to " + newLogFile);

		// To insure communication when both IPv4 and IPv6 stacks are running
		// force
		// everything to IPv4. Later when IPv6 is in everyday use then make this
		// configurable
		logger.info("Forcing use of IPv4");
		System.setProperty("java.net.preferIPv4Stack", "true");

		// uncomment this line if you want to see SSL and other debug
		// information dumped to stdout
		// System.setProperty("javax.net.debug", "ssl");

		// Read properties, store values
		if (ipServidor != null) {
			if (!(ipServidor.trim().equals(""))) {
				serverHost = ipServidor;
			} else {
				serverHost = configurationProperties
						.getProperty(CONFIG_SERVER_HOST);

			}
		} else {
			serverHost = configurationProperties
					.getProperty(CONFIG_SERVER_HOST);

		}

		logger.info(CONFIG_SERVER_HOST + "=" + this.serverHost);
		this.serverPort = Integer.parseInt(configurationProperties
				.getProperty(CONFIG_SERVER_PORT));
		logger.info(CONFIG_SERVER_PORT + "=" + this.serverPort);


		// create dual rpc client
		this.dualRpcClient = new DualRpcClient(this.serverHost, this.serverPort);


		// Create and register the client handler. The client handler receives
		// RPC calls from the server.
		ClienteTratador clientHandler = new ClienteTratador(clienteReceptor);
		this.dualRpcClient.registerClientSideHandler(clientHandler);

		// Create and register a lost connection handler
		ConexaoPerdidaTratadorCliente lcHandler = new ConexaoPerdidaTratadorCliente(
				clienteReceptor);
		this.dualRpcClient.setCallbackHandler(lcHandler);

		// Connect to server
		this.dualRpcClient.connect();

		// Obtain client session id
		this.clientSessionId = this.dualRpcClient.getSessionId();

		// Do handshake and exchange session ids
		DadosAplicacao appData = (DadosAplicacao) this.dualRpcClient.call(
				PRIMARY_SERVER_HANDLER_CLASSNAME, "estabelecerSessao",
				new Long(this.clientSessionId), idJogo, nomeJogador);

		this.serverSessionId = appData.serverSessionId.longValue();
		logger.info("Server session id: " + this.serverSessionId
				+ " corresponds to client session id: " + this.clientSessionId);

		// Start the heartbeat thread to avoid connection timeouts. This is
		// mostly
		// useful when a proxy times out due to inactivity.
		HeartbeatThread heartbeat = new HeartbeatThread(this.dualRpcClient,
				PRIMARY_SERVER_HANDLER_CLASSNAME, 240);
		heartbeat.start();

	}

	public void iniciarPartida(Integer qtdeJogadoresNaPartida)
			throws CallException {
		this.dualRpcClient.callAsync(Cliente.PRIMARY_SERVER_HANDLER_CLASSNAME,
				"iniciarPartida", qtdeJogadoresNaPartida);

	}

	public void enviaJogada(Jogada jogada) throws IOException, CallException {
		MarshalledObject jogadaM = new MarshalledObject(jogada);
		this.dualRpcClient.call(Cliente.PRIMARY_SERVER_HANDLER_CLASSNAME,
				"enviaJogada", jogadaM);

	}

	public void reiniciarPartida() throws CallException {
		this.dualRpcClient.callAsync(Cliente.PRIMARY_SERVER_HANDLER_CLASSNAME,
				"reiniciarPartida");

	}

	public void desconectar() throws CallException, NotConnectedException {
		this.dualRpcClient.disconnect();

	}

	public void finalizarPartida() throws CallException {
		this.dualRpcClient.callAsync(Cliente.PRIMARY_SERVER_HANDLER_CLASSNAME,
				"finalizarPartida");

	}

	public List<String> obterNomeJogadores() throws CallException {
		List<String> nomes = (List<String>) this.dualRpcClient.call(
				Cliente.PRIMARY_SERVER_HANDLER_CLASSNAME, "obterNomeJogadores");
		return nomes;

	}

}

/**
 * Heartbeat periodicamente faz um ping no servidor handler para ajudar a
 * prevenir um proxy timeout por inatividade se a conexão é via HTTP ou socks
 * prxy
 */
class HeartbeatThread extends Thread {
	private static Logger logger = Logger.getLogger(HeartbeatThread.class
			.getName());

	private DualRpcClient dualRpcClient = null;

	private String handlerClass = null;

	private int intervalInSeconds = 300;

	
	public HeartbeatThread(DualRpcClient dualRpcClient, String handlerClass,
			int intervalInSeconds) {
		// insure that this thread stops if it is the last one left
		this.setDaemon(true);

		this.dualRpcClient = dualRpcClient;
		this.handlerClass = handlerClass;
		this.intervalInSeconds = intervalInSeconds;

		logger.info("Starting heartbeat thread " + this.getClass().getName());
	}

	 
	public void run() {
		Thread.currentThread().setName(this.getClass().getName() + " thread");
		while (true) {
			try {
				logger.info("Pinging servidor");
				this.dualRpcClient.call(this.handlerClass, "ping",
						"client heartbeat");
			} catch (Exception ex) {
				// log exception but don't stop loop
				logger.error("Error in heartbeat loop", ex);
			}

			// insure that any heartbeat call above is encapsulated within
			// its own try..catch so that an exception doesn't skip past
			// the sleep and start a fast exception loop that chews up
			// the cpu
			try {
				Thread.sleep(this.intervalInSeconds * 1000);
			} catch (InterruptedException iex) {
				// do nothing
			}

		}
	}
}
