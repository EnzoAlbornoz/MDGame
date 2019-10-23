package br.ufsc.game.network;

import javax.swing.JOptionPane;

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
	protected final String SERVER_IP = "0.0.0.0";
	// Public
	// Protected
	protected Proxy proxy;
	protected boolean connected;
	protected boolean matchRunning;
	protected Integer playerId;

	// Constructor
	public NetGamesInterface() {
		// Init Network Loading
		this.proxy = Proxy.getInstance();
		this.proxy.addOuvinte(this);
		this.connected = false;
		this.matchRunning = false;
	}

	// Interface
	public void joinSession(String playerName) {
		try {
			this.proxy.conectar(SERVER_IP, playerName);
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
		this.proxy.iniciarPartida(playersQuantity);
	}
	
	@Override
	public void iniciarNovaPartida(Integer posicao) {
		this.matchRunning = true;		
		this.playerId = posicao;
		String message = "Partida Iniciada com Sucesso";
		JOptionPane.showMessageDialog(null, message,
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
		// System.out.println(jogada);
		// TODO
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