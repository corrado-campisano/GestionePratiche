package eu.campesinux.GestionePratiche.pratiche;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import eu.campesinux.GestionePratiche.avanzamenti.Avanzamento;
import eu.campesinux.GestionePratiche.avanzamenti.AvanzamentoService;
import eu.campesinux.GestionePratiche.statoPratica.StatoPratica;
import eu.campesinux.GestionePratiche.statoPratica.StatoPraticaService;
import eu.campesinux.GestionePratiche.utenti.Utente;
import eu.campesinux.GestionePratiche.utenti.UtenteService;

public class PraticaBusinessLogic {

	public static boolean isUtenteWithAuthority(String authority) {

		boolean isUtenteWithAuthority = false;

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
			for (GrantedAuthority ga : authorities) {
				if (ga.getAuthority().equals(authority)) {
					isUtenteWithAuthority = true;
					break;
				}
			}
		}

		return isUtenteWithAuthority;
	}

	public static boolean doesUtenteGestiscePratica(Pratica pratica, UtenteService utenteService) {

		boolean doesUtenteGestiscePratica = false;
		Utente utente = null;

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUserName = authentication.getName();
			utente = utenteService.listByUsername(currentUserName);
		}

		if (utente != null) {
			for (Utente professionista : pratica.getUtenti()) {
				if (utente.getId() == professionista.getId()) {
					doesUtenteGestiscePratica = true;
					break;
				}
			}
		}

		return doesUtenteGestiscePratica;
	}

	public static Avanzamento getUltimoAvanzamento(Pratica pratica, AvanzamentoService avanzamentoService) {

		List<Avanzamento> avanzamenti = avanzamentoService.listByPratica(pratica);
		Avanzamento ret = null;

		LocalDateTime dataAvanzamentoMax = null;

		for (Avanzamento avanzamento : avanzamenti) {
			if (dataAvanzamentoMax == null) {
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

	public static void presaInCarico(Pratica pratica, Long[] utenti, String commento, LocalDateTime scadenza,
			UtenteService utenteService, AvanzamentoService avanzamentoService, 
			PraticaService praticaService, StatoPraticaService statoService) throws Exception {

		if (!isUtenteWithAuthority("ADMIN") && !isUtenteWithAuthority("MANAGER")) {
			throw new Exception("Errore, l'utente corrente non e' autorizzato a questa operazione");
		}
		if (utenti==null) {
			throw new Exception("Errore, specificare almeno un utente a cui assegnare la pratica");
		}
		
		Utente utente = null;
		for (int i = 0; i < utenti.length; i++) {
			Long id = utenti[i];
			utente = utenteService.get(id);
			pratica.addUtente(utente);
		}
		
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

	public static void prontoPerNotifica(Pratica pratica, String commento, LocalDateTime scadenza,
			StatoPraticaService statoService, AvanzamentoService avanzamentoService, 
			PraticaService praticaService, UtenteService utenteService) throws Exception {
		
		if (!isUtenteWithAuthority("ADMIN") && !isUtenteWithAuthority("PROFESSIONISTA")) {
			throw new Exception("Errore, l'utente corrente non e' autorizzato a questa operazione");
		}
		
		if (isUtenteWithAuthority("PROFESSIONISTA") && !PraticaBusinessLogic.doesUtenteGestiscePratica(pratica, utenteService)) {
			String msg = "L'utente corrente non e' assegnatario della pratica";
			throw new Exception(msg);
		}
		
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

	public static void prontoPerDeposito(Pratica pratica, String commento, LocalDateTime scadenza,
			StatoPraticaService statoService, AvanzamentoService avanzamentoService, 
			PraticaService praticaService, UtenteService utenteService) throws Exception {

		if (!isUtenteWithAuthority("ADMIN") && !isUtenteWithAuthority("PROFESSIONISTA")) {
			throw new Exception("Errore, l'utente corrente non e' autorizzato a questa operazione");
		}
		
		if (isUtenteWithAuthority("PROFESSIONISTA") && !PraticaBusinessLogic.doesUtenteGestiscePratica(pratica, utenteService)) {
			String msg = "L'utente corrente non e' assegnatario della pratica";
			throw new Exception(msg);
		}
		
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

	public static void inDibattimento(Pratica pratica, String commento, LocalDateTime scadenza,
			StatoPraticaService statoService, AvanzamentoService avanzamentoService, 
			PraticaService praticaService, UtenteService utenteService) throws Exception {

		if (!isUtenteWithAuthority("ADMIN") && !isUtenteWithAuthority("PROFESSIONISTA")) {
			throw new Exception("Errore, l'utente corrente non e' autorizzato a questa operazione");
		}
		
		if (isUtenteWithAuthority("PROFESSIONISTA") && !PraticaBusinessLogic.doesUtenteGestiscePratica(pratica, utenteService)) {
			String msg = "L'utente corrente non e' assegnatario della pratica";
			throw new Exception(msg);
		}
		
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

	public static void daFatturare(Pratica pratica, String commento, LocalDateTime scadenza,
			StatoPraticaService statoService, AvanzamentoService avanzamentoService, 
			PraticaService praticaService, UtenteService utenteService) throws Exception {

		if (!isUtenteWithAuthority("ADMIN") && !isUtenteWithAuthority("PROFESSIONISTA")) {
			throw new Exception("Errore, l'utente corrente non e' autorizzato a questa operazione");
		}
		
		if (isUtenteWithAuthority("PROFESSIONISTA") && !PraticaBusinessLogic.doesUtenteGestiscePratica(pratica, utenteService)) {
			String msg = "L'utente corrente non e' assegnatario della pratica";
			throw new Exception(msg);
		}
		
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

	public static void praticaEvasa(Pratica pratica, String commento,
			StatoPraticaService statoService, AvanzamentoService avanzamentoService, 
			PraticaService praticaService) throws Exception {

		if (!isUtenteWithAuthority("ADMIN") && !isUtenteWithAuthority("MANAGER")) {
			throw new Exception("Errore, l'utente corrente non e' autorizzato a questa operazione");
		}
		
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

	public static void praticaRigettata(Pratica pratica, String commento,
			StatoPraticaService statoService, AvanzamentoService avanzamentoService, 
			PraticaService praticaService, UtenteService utenteService) throws Exception {
		
		if (!isUtenteWithAuthority("ADMIN") && !isUtenteWithAuthority("PROFESSIONISTA")) {
			throw new Exception("Errore, l'utente corrente non e' autorizzato a questa operazione");
		}
		
		if (isUtenteWithAuthority("PROFESSIONISTA") && !PraticaBusinessLogic.doesUtenteGestiscePratica(pratica, utenteService)) {
			String msg = "L'utente corrente non e' assegnatario della pratica";
			throw new Exception(msg);
		}
		
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

	public static void prorogaPratica(Pratica pratica, String commento,
			AvanzamentoService avanzamentoService, PraticaService praticaService) throws Exception {

		if (!isUtenteWithAuthority("ADMIN") && !isUtenteWithAuthority("MANAGER")) {
			throw new Exception("Errore, l'utente corrente non e' autorizzato a questa operazione");
		}
		
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
