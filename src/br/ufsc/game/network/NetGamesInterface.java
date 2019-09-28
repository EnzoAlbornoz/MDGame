package br.ufsc.game.network;

import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.OuvidorProxy;
import br.ufsc.inf.leobr.cliente.Proxy;;

/**
 * NetGamesInterface
 */
public class NetGamesInterface implements OuvidorProxy {

	// Variables
		// Public
		// Protected
	protected Proxy proxy;
	// Constructor
	public NetGamesInterface() {
		
	}
	// Interface
	@Override
	public void iniciarNovaPartida(Integer posicao) {
		// TODO Auto-generated method stub
		
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