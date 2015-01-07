package afr.tafeltrainer3.client;

import java.util.ArrayList;

import afr.tafeltrainer3.client.events.DataEvent;
import afr.tafeltrainer3.client.events.EventAddSuperUser;
import afr.tafeltrainer3.client.events.EventGetGroup;
import afr.tafeltrainer3.client.events.EventSuperUserRetrieved;
import afr.tafeltrainer3.client.events.EventUserNew;
import afr.tafeltrainer3.client.events.EventVerifyMail;
import afr.tafeltrainer3.client.events.EventWpsRetrieved;
import afr.tafeltrainer3.shared.SuperUser;
import afr.tafeltrainer3.shared.User;
import afr.tafeltrainer3.shared.Woordpakket;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class MainView extends Composite implements ValueChangeHandler
{
	private static final String Startpagina_Token = "startpagina";
	private static final String Loginbegeleiderpagina_Token = "loginbegeleiderpagina";
	private static final String Administratiepagina_Token = "administratiepagina";
	private static final String Nieuwebegeleiderpagina_Token = "inschrijven";
	private static final String Demobegeleiderpagina_Token = "demobegeleiderpagina";
	private static final String Demokidspagina_Token = "demokidspagina";
	private static final String Landingspagina_Token = "landingpage";

	public AdministratiePage administratiepage;
	public ClientImp client;
	public AlertWidget alertwidget;
	public LoginSuperUser loginsuperuser;
	public MenuView menu;
	public NewSuperUser newsuperuser;
	public StartPage startpage;
	private SuperUser superuser;
	public tafeltrainer3messages messages;
	public User user;
	private VerticalPanel vpanel;
	private VerticalPanel contentpanel;
	public LandingPage landingpage;
	public ArrayList<Woordpakket> wordpackages;
	
	// alle pagina's worden hier gemaakt en vanuit hier geupdatet. Het
	// contentpanel bevat deze pagina's,
	// het menu wordt hier ook geinstantieerd. De rpc-calls gaan allemaal via de
	// clientimplementation.
	@SuppressWarnings("unchecked")
	public MainView()
	{
		//logger.entering(getClass().getName(), "constructor");
		
		vpanel = new VerticalPanel();
		vpanel.setStyleName("vpanelmain");
		initWidget(this.vpanel);
		this.messages = GWT.create(tafeltrainer3messages.class);
		this.menu = new MenuView(this, messages);
		this.vpanel.add(menu);
		this.contentpanel = new VerticalPanel();
		this.contentpanel.setStyleName("contentpanel");
		alertwidget = new AlertWidget(messages);
		this.startpage = new StartPage(this, messages, alertwidget);
		this.contentpanel.add(startpage);
		this.vpanel.add(contentpanel);
		this.loginsuperuser = new LoginSuperUser(this);
		this.newsuperuser = new NewSuperUser(this, messages, alertwidget);
		this.wordpackages = new ArrayList<Woordpakket>();
		History.addValueChangeHandler(this);
		History.newItem(Startpagina_Token);

		// verification email-adress
		String s = Window.Location.getParameter("codestring");
		if (s != null)
		{
			client.verifyMailadress(Window.Location.getParameter("codestring"));
		}
	}

	// Backbuttonkliks worden hier afgehandeld om te voorkomen dat gebruikers
	// van de site af kukelen bij
	// een klik op "terug"
	@Override
	public void onValueChange(ValueChangeEvent event)
	{
		String historytoken = (String) event.getValue();
		if (historytoken.equals(Startpagina_Token))
		{
			showPageStartPage();
		}

		if (historytoken.equals(Administratiepagina_Token))
		{
			showPageAdministratie();
		}
		if (historytoken.equals(Loginbegeleiderpagina_Token))
		{
			showPageLoginBegeleider();
		}

		if (historytoken.equals(Nieuwebegeleiderpagina_Token))
		{
			showPageNieuweBegeleider();
		}
		if (historytoken.equals(Demobegeleiderpagina_Token))
		{
			showDemoSuperuserpage();
		}
		if (historytoken.equals(Demokidspagina_Token))
		{
			showDemopage();
		}
		if (historytoken.equals(Landingspagina_Token))
		{
			showLandingpage();
		}

	}

	// hanteert rpc-calls die door de users worden geintieerd : inloggen,
	// gebruikers aanmaken en wijzigen
	// groepslijsten maken en dergelijke.
	public void handleEvent(DataEvent result)
	{

		if (result instanceof EventVerifyMail)
		{
			EventVerifyMail evm = (EventVerifyMail) result;
			if (evm.getParameter().equals("succes"))
			{
				showLandingpage();
			} else
			{
				alertwidget.getBox().setText(messages.main_helaas());
				alertwidget.getContentlabel().setText(messages.verificatienietgelukt());
				alertwidget.getBox().center();
			}
			// Utilities.alertWidget("", "Verificatie niet gelukt....",
			// messages).center();

		}

		// na een inlogpoging van een superuser
		if (result instanceof EventSuperUserRetrieved)
		{
			EventSuperUserRetrieved event = (EventSuperUserRetrieved) result;
			if (event.getSuperuser() != null)
			{
				this.superuser = event.getSuperuser();
				if (superuser.getVerified())
				{
					this.administratiepage = new AdministratiePage(this, this.superuser, messages);
					showPageAdministratie();
					client.getGroup(superuser);
				} else
				{
					Utilities.alertWidgetButtonVerifMail("", messages.main_mailadresnognietgeverifieerd(), messages,
							superuser, this).center();
				}
			} else
			{
				this.loginsuperuser.txtlogin.setStyleName("admininvulbox_fouteinvoer");
				this.loginsuperuser.txtpw.setStyleName("admininvulbox_fouteinvoer");
				this.loginsuperuser.btnsubmit.setEnabled(true);
			}
		}

		// na het aanmaken van een superuser
		if (result instanceof EventAddSuperUser)
		{
			EventAddSuperUser event = (EventAddSuperUser) result;
			if (event.getSuperuser() != null)
			{
				if (event.getSuperuser().getEmail().equals("Emailadres bestaat al"))
				{
					Utilities.alertWidget("", "Dit E-mailadres bestaat al, probeer een ander e-mailadres", messages)
							.center();
				} else
				{
					Utilities.alertWidget("", "Klik op de link in uw verificatiemail om uw account te activeren.",
							messages).center();
				}

			} else
			{
				newsuperuser.lbl20.setText("probeer een ander emailadres...");
				Utilities.alertWidget("", "Emailadres staat al in de lijst", messages).center();
			}
		}

		//haalt de leerlingen van de hoofdgebruiker op en zet ze op de administratiepagina
		if (result instanceof EventGetGroup)
		{
			EventGetGroup egg = (EventGetGroup) result;
			ArrayList<User> users = egg.getUsers();
			if (users != null)
			{
				this.administratiepage.mygroup = users;
				client.getWps(this.administratiepage.superuser);
			} 	
			else
			{
				Utilities.alertWidget("","Geen gebruikers gevonden",messages).center();
			}
			
		}	

		//haalt alle woordpakketten op
		if(result instanceof EventWpsRetrieved)
		{
			EventWpsRetrieved ewr = (EventWpsRetrieved)result;
			ArrayList<Woordpakket> wps =  ewr.getWp();
			this.administratiepage.mypackages = wps;
			this.administratiepage.addData();
		}
		
		
		// na het invoeren van een nieuwe user
		if (result instanceof EventUserNew)
		{
			EventUserNew eun = (EventUserNew) result;
			User usernew = eun.getUser();
			if (usernew == null)
			{
				System.out.println("fout in main.eventusernew");
			} else
			{
				administratiepage.addGroupmember(usernew);
			}
		}

	}

	
	
	public void showPageAdministratie()
	{
		History.newItem(Administratiepagina_Token);
		this.contentpanel.clear();
		this.contentpanel.add(this.administratiepage);
		//logger.exiting(getClass().getName(), "showPageAdministrati()");

	}

	// Toon het formulier om een nieuwe superuser aan te maken
	public void showPageNieuweBegeleider()
	{
		History.newItem(Nieuwebegeleiderpagina_Token);
		this.contentpanel.clear();
		this.contentpanel.add(new NewSuperUser(this, messages, alertwidget));
	}

	// Toont het loginscherm voor de superuser
	public void showPageLoginBegeleider()
	{
		History.newItem(Loginbegeleiderpagina_Token);
		this.loginsuperuser.btnsubmit.setEnabled(true);
		this.contentpanel.clear();
		this.contentpanel.add(this.loginsuperuser);
	}

	// Toonthet beginscherm
	public void showPageStartPage()
	{
		History.newItem(Startpagina_Token);
		this.startpage.btn10.setEnabled(true);
		// this.startpage.txtpassw.setText("");
		// this.startpage.txtlogin.setText("");
		this.menu.lbl12.setText("");
		this.menu.showEntranceMenu();
		this.contentpanel.clear();
		this.contentpanel.add(this.startpage);
	}

	public void showDemopage()
	{
		History.newItem(Demokidspagina_Token);
		this.contentpanel.clear();
		this.contentpanel.add(new DemoPage());
	}

	public void showLandingpage()
	{
		this.contentpanel.clear();
		landingpage = new LandingPage(this, messages, client);
		this.contentpanel.add(new LandingPage(this, messages, client));
	}

	public void showDemoSuperuserpage()
	{
		History.newItem(Demobegeleiderpagina_Token);
		this.contentpanel.clear();
		this.contentpanel.add(new DemoSuperuserPage());
	}

	public User getUser()
	{
		return this.user;
	}

	public DialogBox getAlertWidget()
	{
		DialogBox box = new DialogBox();
		Label contentlabel = new Label();
		box.setPixelSize(300, 400);
		box.setStyleName("popup2");
		VerticalPanel panel = new VerticalPanel();
		panel.setBorderWidth(0);
		// TODO
		// box.setText(header);
		Image img1 = new Image("/images/startpage_curious.png");
		img1.setHeight("200px");
		panel.add(img1);
		panel.add(contentlabel);
		Button buttonClose = new Button(messages.utilities_sluitbutton());
		buttonClose.addClickHandler(new buttonCloseClickHandler(box));
		buttonClose.setStyleName("standardbutton");
		Label emptyLabel = new Label("");
		emptyLabel.setSize("auto", "25px");
		panel.add(emptyLabel);
		panel.add(emptyLabel);
		buttonClose.setWidth("90px");
		panel.add(buttonClose);
		panel.setCellHorizontalAlignment(buttonClose, HasAlignment.ALIGN_CENTER);
		box.add(panel);
		return box;

	}

	public class buttonCloseClickHandler implements ClickHandler
	{
		DialogBox box;

		public buttonCloseClickHandler(DialogBox box)
		{
			this.box = box;
		}

		@Override
		public void onClick(ClickEvent event)
		{
			box.hide();
		}
	}

}
