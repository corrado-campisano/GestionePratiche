package eu.campesinux.GestionePratiche.pratiche;

import java.util.Date;

import eu.campesinux.GestionePratiche.avanzamenti.Avanzamento;
import eu.campesinux.GestionePratiche.avanzamenti.AvanzamentoService;
import eu.campesinux.GestionePratiche.statoPratica.StatoPratica;
import eu.campesinux.GestionePratiche.statoPratica.StatoPraticaService;

// TODO : implementare altri metodi
public class PraticaBusinessLogic {
		
	public static void presaInCarico(Pratica pratica, PraticaService praticaService, 
			StatoPraticaService statoService, AvanzamentoService avanzamentoService, String commento) {
		
		StatoPratica nuovo = statoService.findByStato(StatoPratica.STATO_IN_LAVORAZIONE);
				
		Avanzamento avanzamento = new Avanzamento();
		avanzamento.setData(new Date());
		avanzamento.setDescrizione(commento);
		avanzamento.setPratica(pratica);
		avanzamento.setStatoAttuale(nuovo);
		avanzamento.setStatoPrecedente(pratica.getStato());
		avanzamentoService.save(avanzamento);
		
		pratica.setStato(nuovo);
		praticaService.save(pratica);
	}

	public static void prontoPerNotifica(Pratica pratica, PraticaService praticaService,
			StatoPraticaService statoService, AvanzamentoService avanzamentoService, String commento) {
		
		StatoPratica nuovo = statoService.findByStato(StatoPratica.STATO_DA_NOTIFICARE);
		
		Avanzamento avanzamento = new Avanzamento();
		avanzamento.setData(new Date());
		avanzamento.setDescrizione(commento);
		avanzamento.setPratica(pratica);
		avanzamento.setStatoAttuale(nuovo);
		avanzamento.setStatoPrecedente(pratica.getStato());
		avanzamentoService.save(avanzamento);
		
		pratica.setStato(nuovo);
		praticaService.save(pratica);
		
	}

	public static void prontoPerDeposito(Pratica pratica, PraticaService praticaService,
			StatoPraticaService statoService, AvanzamentoService avanzamentoService, String commento) {
		
		StatoPratica nuovo = statoService.findByStato(StatoPratica.STATO_DA_DEPOSITARE);
		
		Avanzamento avanzamento = new Avanzamento();
		avanzamento.setData(new Date());
		avanzamento.setDescrizione(commento);
		avanzamento.setPratica(pratica);
		avanzamento.setStatoAttuale(nuovo);
		avanzamento.setStatoPrecedente(pratica.getStato());
		avanzamentoService.save(avanzamento);
		
		pratica.setStato(nuovo);
		praticaService.save(pratica);
		
	}

	public static void inDibattimento(Pratica pratica, PraticaService praticaService, StatoPraticaService statoService,
			AvanzamentoService avanzamentoService, String commento) {
		
		StatoPratica nuovo = statoService.findByStato(StatoPratica.STATO_IN_DIBATTIMENTO);
		
		Avanzamento avanzamento = new Avanzamento();
		avanzamento.setData(new Date());
		avanzamento.setDescrizione(commento);
		avanzamento.setPratica(pratica);
		avanzamento.setStatoAttuale(nuovo);
		avanzamento.setStatoPrecedente(pratica.getStato());
		avanzamentoService.save(avanzamento);
		
		pratica.setStato(nuovo);
		praticaService.save(pratica);
		
	}

	public static void daFatturare(Pratica pratica, PraticaService praticaService, StatoPraticaService statoService,
			AvanzamentoService avanzamentoService, String commento) {
		
		StatoPratica nuovo = statoService.findByStato(StatoPratica.STATO_DA_FATTURARE);
		
		Avanzamento avanzamento = new Avanzamento();
		avanzamento.setData(new Date());
		avanzamento.setDescrizione(commento);
		avanzamento.setPratica(pratica);
		avanzamento.setStatoAttuale(nuovo);
		avanzamento.setStatoPrecedente(pratica.getStato());
		avanzamentoService.save(avanzamento);
		
		pratica.setStato(nuovo);
		praticaService.save(pratica);
		
	}

	public static void praticaEvasa(Pratica pratica, PraticaService praticaService, StatoPraticaService statoService,
			AvanzamentoService avanzamentoService, String commento) {

		StatoPratica nuovo = statoService.findByStato(StatoPratica.STATO_EVASA);
		
		Avanzamento avanzamento = new Avanzamento();
		avanzamento.setData(new Date());
		avanzamento.setDescrizione(commento);
		avanzamento.setPratica(pratica);
		avanzamento.setStatoAttuale(nuovo);
		avanzamento.setStatoPrecedente(pratica.getStato());
		avanzamentoService.save(avanzamento);
		
		pratica.setStato(nuovo);
		praticaService.save(pratica);
	}

	public static void praticaRigettata(Pratica pratica, PraticaService praticaService,
			StatoPraticaService statoService, AvanzamentoService avanzamentoService, String commento) {

		StatoPratica nuovo = statoService.findByStato(StatoPratica.STATO_RIGETTATA);
		
		Avanzamento avanzamento = new Avanzamento();
		avanzamento.setData(new Date());
		avanzamento.setDescrizione(commento);
		avanzamento.setPratica(pratica);
		avanzamento.setStatoAttuale(nuovo);
		avanzamento.setStatoPrecedente(pratica.getStato());
		avanzamentoService.save(avanzamento);
		
		pratica.setStato(nuovo);
		praticaService.save(pratica);
	}
}
