package afr.tafeltrainer3.client;

import com.google.gwt.i18n.client.Messages;

public interface tafeltrainer3messages extends Messages 

{
	@DefaultMessage("Onderweg")
	String administratiepage_onderweg();

	@DefaultMessage("Mail is verstuurd")
	String administratiepage_mail_is_verstuurd();
	
	@DefaultMessage("demo ouders")
	String startpage_demo_ouders();

	@DefaultMessage("Welkom!")
	String startpage_welkom();
	
	@DefaultMessage("Je Loginnaam")
	String startpage_jeloginnaam();
	
	@DefaultMessage("Je Wachtwoord")
	String startpage_jewachtwoord();
	
	@DefaultMessage("aanmelden nieuwe begeleider")
	String startpage_aanmnwbeg();
	
	@DefaultMessage("bekijk de demo")
	String startpage_bekijkdedemo();
	
	@DefaultMessage("OK")
	String startpage_ok();

	
	@DefaultMessage("inloggen begeleider")
	String startpage_inloggenbeg();
	
	@DefaultMessage("deflogin")
	String mainpage_blankloginname();
	
	@DefaultMessage("verysecret")
	String mainpage_blankloginpassw();
	
	@DefaultMessage("Tafeltrainer")
	String menupage_title();
	
	@DefaultMessage("Home")
	String menupage_home();
	
	@DefaultMessage("Let Op!")
	String startpage_blanklogintitle();
	
	@DefaultMessage("Je kunt nu de tafeltrainer spelen.Maar de computer onthoudt nu je resultaten niet!Vraag straks een volwassene om een naam en wachtwoord.")
	String startpage_blankloginwarning();
	
	@DefaultMessage("Veel plezier met oefenen. Ben jij ook zo benieuwd wat je straks allemaal kan kopen?")
	String startpage_normalloginmessage();
	
	@DefaultMessage("Oeps, je gebruikersnaam of wachtwoord was fout. Probeer het nog eens!")
	String startpage_failedloginmessage();
	
	@DefaultMessage("tafeltrainer")
	String menupage_menutafeltrainer();
	
	@DefaultMessage("resultaten")
	String menupage_menuresultaten();
	
	@DefaultMessage("portemonnee")
	String menupage_menuportemonnee();
	
	@DefaultMessage("shop")
	String menupage_menushop();
	
	@DefaultMessage("Welkom")
	String menupage_welkomgebruiker();
	
	@DefaultMessage("Die kennen we niet...")
	String menupage_diekennenweniet();
	
	@DefaultMessage("sluit")
	String utilities_sluitbutton();
	
	@DefaultMessage("Nog even oefenen!! Je moet nog wat geld verdienen!!")
	String utilities_shop_not_enough_money();

	@DefaultMessage("Welke Tafels")
	String tafeltrainerpage_welketafels();
	
	@DefaultMessage("alle tafels")
	String tafeltrainerpage_alletafels();
	
	@DefaultMessage("moeilijk")	
	String tafeltrainerpage_moeilijk();
	
	@DefaultMessage("start")	
	String tafeltrainerpage_startknop();
	
	@DefaultMessage("stop")	
	String tafeltrainerpage_stopknop();
	
	@DefaultMessage("info")	
	String tafeltrainerpage_infoknop();
	
	@DefaultMessage("Score")
	String gaugewidget_score();
	
	@DefaultMessage("Tafel van")
	String gaugewidget_tableof();
	
	@DefaultMessage("Je bent een gast dus wat je op andere dagen hebt gedaan ben ik vergeten. Vraag een volwassene om je te registreren!")
	String feedbackpage_blankloginwarning();
	
	@DefaultMessage("Sommen gemaakt")
	String feedbackpage_sommengemaakt();
	
	@DefaultMessage("Als je alle tafels gehaald heb krijg je het tafeldiploma. Vraag een volwassene om het uit te printen en te ondertekenen!")
	String feedbackpage_tafeldiploma();
	
	@DefaultMessage("Gewoon doen wat de beloning zegt, dan verdien je hem vanzelf")
	String feedbackpage_andereawards();
	
	@DefaultMessage("Je haalt deze beloning door minstens 100 moeilijke tafelsommen gemaakt te hebben met een score van 90%")
	String feedbackpage_hoehaalikmoeilijketafels();
	
	@DefaultMessage("Je haalt een tafel door 50 tafelsommen te maken en er minstens 45 goed te hebben.")
	String feedbackpage_hoehaaliktafel();
	
	@DefaultMessage("Mijn Resultaten")
	String feedbackpage_mijnresultaten();
	
	@DefaultMessage("Begonnen op: ")
	String feedbackpage_begonnenop();
	
	@DefaultMessage("Oefentijd: ")
	String feedbackpage_oefentijd();
	
	@DefaultMessage("Check voor nieuwe resultaten")
	String feedbackpage_checkvoornieuweresultaten();
	
	@DefaultMessage("Check")
	String feedbackpage_checkbutton();
	
	@DefaultMessage("Portemonnee")
	String wallet_title();
	
	@DefaultMessage("Winkeltjes")
	String winkeltje_title();
	
	@DefaultMessage("Kies je categorie")
	String winkeltje_kiesjecategorie();
	
	@DefaultMessage("autos")
	String winkeltje_categorie_autos();
	
	@DefaultMessage("gadgets")
	String winkeltje_categorie_gadgets();
	
	@DefaultMessage("landdieren")
	String winkeltje_categorie_landdieren();
	
	@DefaultMessage("zeedieren")
	String winkeltje_categorie_zeedieren();
	
	@DefaultMessage("luchtdieren")
	String winkeltje_categorie_luchtdieren();
	
	@DefaultMessage("cartoons")
	String winkeltje_categorie_cartoons();
	
	@DefaultMessage("edelstenen")
	String winkeltje_categorie_edelstenen();

	@DefaultMessage("Voetballers")
	String winkeltje_categorie_voetballers();
	
	@DefaultMessage("Voetballers")
	String winkeltje_categorie_grappig();
	
	@DefaultMessage("Voetballers")
	String winkeltje_categorie_wereldsteden();
	
	@DefaultMessage("Afrekenen")
	String winkeltje_afrekenenbutton();
	
	@DefaultMessage("Uit wagentje")
	String winkeltje_teruginwagentjebutton();
	
	@DefaultMessage("in wagentje")
	String winkeltje_productwidget_inwagentje();
	
	@DefaultMessage("uur")
	String mysqlaccess_oefentijd_uren();
	
	@DefaultMessage("minuten")
	String mysqlaccess_oefentijd_minuten();

	@DefaultMessage("administratie")
	String administratiepage_administratie();
	
	@DefaultMessage("nieuw")
	String administratiepage_nieuw();

	@DefaultMessage("naam")
	String administratiepage_naam();

	@DefaultMessage("opgaven")
	String administratiepage_opgaven();

	@DefaultMessage("score")
	String administratiepage_score();

	@DefaultMessage("snelheid")
	String administratiepage_snelheid();

	@DefaultMessage("bewerken")
	String administratiepage_bewerk();

	@DefaultMessage("resultaten")
	String administratiepage_resultaten();

	@DefaultMessage("mail me")
	String administratiepage_mail();
	
	@DefaultMessage("naam fout")
	String newsuperuser_name();
	
	@DefaultMessage("naam fout criteria")
	String newsuperuser_validname();
	
	@DefaultMessage("email fout")
	String newsuperuser_email();
	
	@DefaultMessage("emailadres criteria")
	String newsuperuser_emailcriteria();
	
	@DefaultMessage("ongelijke wachtwoorden")
	String newsuperuser_unequalpwds();
	
	@DefaultMessage("ongelijke wachtwoord criteria")
	String newsuperuser_unequalpwdscriteria();
	
	@DefaultMessage("wachtwoord fout")
	String newsuperuser_invalidpwcriteria();
	
	@DefaultMessage("wachtwoord fout criteria")
	String newsuperuser_invalidpw();
	
	@DefaultMessage("info")
	String newsuperuser_info();

	@DefaultMessage("infotext")
	String newsuperuser_infotext();

	@DefaultMessage("nee bedankt")
	String newsuperuser_neebedankt();
	
	@DefaultMessage("hou me op de hoogte")
	String newsuperuser_houmeopdehoogte();

	@DefaultMessage("wekelijks")
	String newsuperuser_wekelijks();
	
	@DefaultMessage("tweewekelijks")
	String newsuperuser_tweewekelijks();

	@DefaultMessage("bevestigen")
	String newsuperuser_bevestigen();
	
	@DefaultMessage("nieuwe begeleider")
	String newsuperuser_nieuwebegeleider();
	
	@DefaultMessage("uw naam")
	String newsuperuser_uwnaam();
	
	@DefaultMessage("ww herhaald")
	String newsuperuser_wwherhaald();
	
	@DefaultMessage("Helaas")
	String main_helaas();
	
	@DefaultMessage("Verificatie niet gelukt")
	String verificatienietgelukt();
	
	@DefaultMessage("Mailadres nog niet geverifieerd")
	String main_mailadresnognietgeverifieerd();
	

	
	
	
	
	
	
	
	
	
	
	
}
