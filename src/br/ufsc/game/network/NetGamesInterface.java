package br.ufsc.game.network;

import javax.swing.JOptionPane;

import br.ufsc.game.gamelogic.FSMGame;
import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.OuvidorProxy;
import br.ufsc.inf.leobr.cliente.Proxy;
import br.ufsc.inf.leobr.cliente.exception.ArquivoMultiplayerException;
import br.ufsc.inf.leobr.cliente.exception.JahConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoJogandoException;
import br.ufsc.inf.leobr.cliente.exception.NaoPossivelConectarException;;

/**
 * NetGamesInterface
 */
public class NetGamesInterface implements OuvidorProxy {

	// Variables
	// Constants
	// protected final String SERVER_IP = "0.0.0.0";
	// Public
	// Protected
	protected Proxy proxy;
	protected boolean connected;
	protected boolean matchRunning;
	protected Integer playerId;
	protected FSMGame fsmGame;
	//quem começar terah a quantidade atualizada.. os outros vão pegar a quantidade de jogadores
	//quando receberem a primeira jogada, mas atualmente, isso não eh alterado aqui.
	protected int playersQuantity = 4;

	// Constructor
	public NetGamesInterface() {
		// Init Network Loading
		this.proxy = Proxy.getInstance();
		this.proxy.addOuvinte(this);
		this.connected = false;
		this.matchRunning = false;
	}

	public FSMGame getFSMGame(){
		return fsmGame;
	}

	public void setFSMGame(FSMGame fsmGame){
		this.fsmGame = fsmGame;
	}

	public int getPlayersQuantity(){
		return playersQuantity;
	}

	// Interface
	public void joinSession(String playerName) {
		/*
		boolean PreguicaDeDigitarIP = true;
		if (PreguicaDeDigitarIP){
			try {
				this.proxy.conectar("localhost", playerName);
				this.connected = true;
			} catch (JahConectadoException e) {}
			  catch (NaoPossivelConectarException e) {}
			  catch (ArquivoMultiplayerException e) {}
			return;
		}
		*/

		// Adicionado para compatibilidade - Necessário alterar modelagem
		String ip = JOptionPane.showInputDialog(null, "Insira o IP do Servidor", "Login", JOptionPane.QUESTION_MESSAGE);
		try {
			this.proxy.conectar(ip, playerName);
			// --------------------------------------
			this.connected = true;
		} catch (JahConectadoException e) {
			String message = "Você já está conectado!";
			JOptionPane.showMessageDialog(null, message, "Erro - NetGames NRT - JCE", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (NaoPossivelConectarException e) {
			String message = "Desculpe,não foi possível conectar à sessão";
			e.printStackTrace(System.out);
			JOptionPane.showMessageDialog(null, message, "Erro - NetGames NRT - NPCE", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (ArquivoMultiplayerException e) {
			String message = "Desculpe,não foi possivel ler o arquivo de configurações - AME";
			JOptionPane.showMessageDialog(null, message, "Erro - NetGames NRT", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	public void sendPlay(Jogada jogada) {
		try {
			this.proxy.enviaJogada(jogada);
		} catch (NaoJogandoException e) {
			String message = "Desculpe,você não está jogando.";
			JOptionPane.showMessageDialog(null, message, "Erro - NetGames NRT", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	public void disconnect() {
		if(isConnected()) {
			try {
				this.proxy.desconectar();
			} catch (NaoConectadoException e) {}
		}
	}

	/**
	 * @return the connected
	 */
	public boolean isConnected() {
		return this.connected;
	}

	public boolean isMatchRunning() {
		return this.matchRunning;
	}

	public void beginMatch(int playersQuantity) throws NaoConectadoException {
		this.playersQuantity = playersQuantity;
		this.proxy.iniciarPartida(playersQuantity);
	}
	
	@Override
	public void iniciarNovaPartida(Integer clientId) {
		this.matchRunning = true;		
		this.playerId = clientId;
		String message = "Partida Iniciada com Sucesso. Teu número é ";
		JOptionPane.showMessageDialog(null, message + clientId,
			"Partida Iniciada", JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void finalizarPartidaComErro(String message) {
		JOptionPane.showMessageDialog(null, message, "Partida Terminou com Erro", JOptionPane.ERROR_MESSAGE);
		
	}
	@Override
	public void receberMensagem(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Server Message", JOptionPane.INFORMATION_MESSAGE);
		
	}
	@Override
	public void receberJogada(Jogada jogada) {
		PlayerPacket playerPacket = ( (SerializablePacket) jogada).generatePlayerPacket(fsmGame.getGameField());
		fsmGame.receivePlay(playerPacket);
	}
	@Override
	public void tratarConexaoPerdida() {
		JOptionPane.showMessageDialog(null, "Conexão Perdida!");
	}
	@Override
	public void tratarPartidaNaoIniciada(String message) {
		JOptionPane.showMessageDialog(null, message, "Houve um erro ao iniciar partida", JOptionPane.ERROR_MESSAGE);
	}

	public Integer getPlayerId() {
		return this.playerId;
	}
}