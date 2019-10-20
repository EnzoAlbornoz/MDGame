package br.ufsc.game.network;

import javax.swing.JOptionPane;

import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.OuvidorProxy;
import br.ufsc.inf.leobr.cliente.Proxy;
import br.ufsc.inf.leobr.cliente.exception.ArquivoMultiplayerException;
import br.ufsc.inf.leobr.cliente.exception.JahConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
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
		String message = "Partida Iniciada com Sucesso";
		JOptionPane.showMessageDialog(null, message,
			"Partida Iniciada", JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void finalizarPartidaComErro(String message) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void receberMensagem(String msg) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void receberJogada(Jogada jogada) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void tratarConexaoPerdida() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void tratarPartidaNaoIniciada(String message) {
		// TODO Auto-generated method stub
		
	}
}