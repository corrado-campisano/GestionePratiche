package eu.campesinux.GestionePratiche.pratiche;

import java.util.Date;

import eu.campesinux.GestionePratiche.avanzamenti.Avanzamento;
import eu.campesinux.GestionePratiche.avanzamenti.AvanzamentoService;
import eu.campesinux.GestionePratiche.statoPratica.StatoPratica;
import eu.campesinux.GestionePratiche.statoPratica.StatoPraticaService;

// TODO : implementare altri metodi
public class PraticaBusinessLogic {
		
	public static void presaInCarico(Pratica pratica, PraticaService praticaService, 
			StatoPraticaService statoService, AvanzamentoService avanzamentoService) {
		
		StatoPratica nuovo = statoService.findByStato(StatoPratica.STATO_IN_LAVORAZIONE);
				
		Avanzamento avanzamento = new Avanzamento();
		avanzamento.setData(new Date());
		avanzamento.setDescrizione("maciao per ora");
		avanzamento.setPratica(pratica);
		avanzamento.setStatoAttuale(nuovo);
		avanzamento.setStatoPrecedente(pratica.getStato());
		avanzamentoService.save(avanzamento);
		
		pratica.setStato(nuovo);
		praticaService.save(pratica);
	}
}
