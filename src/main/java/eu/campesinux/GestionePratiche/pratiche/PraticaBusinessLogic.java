package eu.campesinux.GestionePratiche.pratiche;

import java.time.LocalDateTime;
import java.util.List;

import eu.campesinux.GestionePratiche.avanzamenti.Avanzamento;
import eu.campesinux.GestionePratiche.avanzamenti.AvanzamentoService;
import eu.campesinux.GestionePratiche.statoPratica.StatoPratica;
import eu.campesinux.GestionePratiche.statoPratica.StatoPraticaService;

public class PraticaBusinessLogic {
	
	public static Avanzamento getUltimoAvanzamento(Pratica pratica, AvanzamentoService avanzamentoService) {
		
		List<Avanzamento> avanzamenti = avanzamentoService.listByPratica(pratica);
		Avanzamento ret = null;
		
		LocalDateTime dataAvanzamentoMax=null;
		
		for (Avanzamento avanzamento : avanzamenti) {
			if (dataAvanzamentoMax==null) {
				dataAvanzamentoMax = avanzamento.getData();
				ret = avanzamento;
			} else {
				if (avanzamento.getData().isAfter(dataAvanzamentoMax)) {
					dataAvanzamentoMax = avanzamento.getData();
					ret = avanzamento;
				}					
			}
		}
		
		return ret;
	}
	
	public static void presaInCarico(Pratica pratica, PraticaService praticaService, 
			StatoPraticaService statoService, AvanzamentoService avanzamentoService, String commento, LocalDateTime scadenza) {
		
		StatoPratica nuovo = statoService.findByStato(StatoPratica.STATO_IN_LAVORAZIONE);
				
		Avanzamento avanzamento = new Avanzamento();
		avanzamento.setData(LocalDateTime.now());
		avanzamento.setScadenza(scadenza);
		avanzamento.setDescrizione(commento);
		avanzamento.setPratica(pratica);
		avanzamento.setStatoAttuale(nuovo);
		avanzamento.setStatoPrecedente(pratica.getStato());
		avanzamentoService.save(avanzamento);
		
		pratica.setStato(nuovo);
		praticaService.save(pratica);
	}

	public static void prontoPerNotifica(Pratica pratica, PraticaService praticaService,
			StatoPraticaService statoService, AvanzamentoService avanzamentoService, String commento, LocalDateTime scadenza) {
		
		StatoPratica nuovo = statoService.findByStato(StatoPratica.STATO_DA_NOTIFICARE);
		
		Avanzamento avanzamento = new Avanzamento();
		avanzamento.setData(LocalDateTime.now());
		avanzamento.setScadenza(scadenza);
		avanzamento.setDescrizione(commento);
		avanzamento.setPratica(pratica);
		avanzamento.setStatoAttuale(nuovo);
		avanzamento.setStatoPrecedente(pratica.getStato());
		avanzamentoService.save(avanzamento);
		
		pratica.setStato(nuovo);
		praticaService.save(pratica);
		
	}

	public static void prontoPerDeposito(Pratica pratica, PraticaService praticaService,
			StatoPraticaService statoService, AvanzamentoService avanzamentoService, String commento, LocalDateTime scadenza) {
		
		StatoPratica nuovo = statoService.findByStato(StatoPratica.STATO_DA_DEPOSITARE);
		
		Avanzamento avanzamento = new Avanzamento();
		avanzamento.setData(LocalDateTime.now());
		avanzamento.setScadenza(scadenza);
		avanzamento.setDescrizione(commento);
		avanzamento.setPratica(pratica);
		avanzamento.setStatoAttuale(nuovo);
		avanzamento.setStatoPrecedente(pratica.getStato());
		avanzamentoService.save(avanzamento);
		
		pratica.setStato(nuovo);
		praticaService.save(pratica);
		
	}

	public static void inDibattimento(Pratica pratica, PraticaService praticaService, StatoPraticaService statoService,
			AvanzamentoService avanzamentoService, String commento, LocalDateTime scadenza) {
		
		StatoPratica nuovo = statoService.findByStato(StatoPratica.STATO_IN_DIBATTIMENTO);
		
		Avanzamento avanzamento = new Avanzamento();
		avanzamento.setData(LocalDateTime.now());
		avanzamento.setScadenza(scadenza);
		avanzamento.setDescrizione(commento);
		avanzamento.setPratica(pratica);
		avanzamento.setStatoAttuale(nuovo);
		avanzamento.setStatoPrecedente(pratica.getStato());
		avanzamentoService.save(avanzamento);
		
		pratica.setStato(nuovo);
		praticaService.save(pratica);
		
	}

	public static void daFatturare(Pratica pratica, PraticaService praticaService, StatoPraticaService statoService,
			AvanzamentoService avanzamentoService, String commento, LocalDateTime scadenza) {
		
		StatoPratica nuovo = statoService.findByStato(StatoPratica.STATO_DA_FATTURARE);
		
		Avanzamento avanzamento = new Avanzamento();
		avanzamento.setData(LocalDateTime.now());
		avanzamento.setScadenza(scadenza);
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
		avanzamento.setData(LocalDateTime.now());
		avanzamento.setScadenza(LocalDateTime.now());
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
		avanzamento.setData(LocalDateTime.now());
		avanzamento.setScadenza(LocalDateTime.now());
		avanzamento.setDescrizione(commento);
		avanzamento.setPratica(pratica);
		avanzamento.setStatoAttuale(nuovo);
		avanzamento.setStatoPrecedente(pratica.getStato());
		avanzamentoService.save(avanzamento);
		
		pratica.setStato(nuovo);
		praticaService.save(pratica);
	}

	public static void prorogaPratica(Pratica pratica, PraticaService praticaService, StatoPraticaService statoService,
			AvanzamentoService avanzamentoService, String commento) {
		
		Avanzamento ultimoAv = getUltimoAvanzamento(pratica, avanzamentoService);
		StatoPratica statoDaProrogare = ultimoAv.getStatoPrecedente();
		StatoPratica statoInScadenza = ultimoAv.getStatoAttuale();
		
		// avanzamento di proroga: da in scadenza allo stato precedente
		Avanzamento avanzamento = new Avanzamento();
		avanzamento.setData(LocalDateTime.now());
		avanzamento.setScadenza(LocalDateTime.now());
		avanzamento.setDescrizione(commento);
		avanzamento.setPratica(pratica);
		avanzamento.setStatoAttuale(statoDaProrogare);
		avanzamento.setStatoPrecedente(statoInScadenza);
		avanzamentoService.save(avanzamento);
		
		pratica.setStato(statoDaProrogare);
		praticaService.save(pratica);
		
	}
}
