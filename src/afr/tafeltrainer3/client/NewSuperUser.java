package afr.tafeltrainer3.client;

import afr.tafeltrainer3.shared.SuperUser;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class NewSuperUser extends Composite {
	
	public Button btn1 ;
	public Button btn2 ;
	
	FlexTable flex1 = new FlexTable();
	private HorizontalPanel buttonpanel1;
	public HorizontalPanel hpanel00;
	public HorizontalPanel hpanel10;
	public HorizontalPanel hpanel20;
	public HorizontalPanel hpanel30;
	public HorizontalPanel hpanel50;
	public Label lbl20;
	public Label lbl30a;
	public Label lbl30b;
	public Label lbl10;
	public Label lbl22;
	public Label lbl32;
	private MainView main;
	private AlertWidget alertwidget;
	public PasswordTextBox txt30a;
	public PasswordTextBox txt30b;
	private RadioButton rb0;
	private RadioButton rb1;
	private RadioButton rb2;
	public TextBox txt10,txt20;
	private String emailadress;
	private String passworduser;
	VerticalPanel vpanelpage2;
	private VerticalPanel vpanel40;
	tafeltrainer3messages messages;
	
	public NewSuperUser( MainView main, tafeltrainer3messages messages, AlertWidget alertwidget)
	{
		this.main = main;
		this.messages = messages;
		this.alertwidget = alertwidget;
		flex1 = new FlexTable();
		initWidget(this.flex1);
		this.flex1.setStyleName("tafeltabel");
		flex1.setCellSpacing(0);
		flex1.setCellPadding(0);
		flex1.setBorderWidth(0);
		this.flex1.getFlexCellFormatter().setHeight(0, 0, "125px");
		this.flex1.getFlexCellFormatter().setHeight(1, 0, "50px");
		this.flex1.getFlexCellFormatter().setHeight(2, 0, "50px");
		this.flex1.getFlexCellFormatter().setHeight(3, 0, "50px");
		this.flex1.getFlexCellFormatter().setHeight(4, 0, "100px");
		this.flex1.getFlexCellFormatter().setHeight(5, 0, "125px");
		this.flex1.getFlexCellFormatter().setWidth(0, 0, "960px");
		
		//eerste rij
		hpanel00 = new HorizontalPanel();
		flex1.getFlexCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		HTML html00 = new HTML("<h1>"+messages.newsuperuser_nieuwebegeleider()+"</h1>");
		html00.setStyleName("topkader");
		hpanel00.add(html00);
		hpanel00.setSpacing(30);;
		flex1.setWidget(0, 0, hpanel00);
		
		//tweede rij
		hpanel10 = new HorizontalPanel();
		HTML html10 = new HTML(messages.newsuperuser_uwnaam());
		html10.setWidth("150px");
		html10.setStyleName("html");
		html10.setWordWrap(false);
		txt10 = new TextBox();
		txt10.setStyleName("page4invulbox");
		lbl10 = new Label();
		hpanel10.add(html10);
		hpanel10.add(txt10);
		hpanel10.add(lbl10);
		flex1.setWidget(1, 0, hpanel10);
		
		//derde rij
		hpanel20 = new HorizontalPanel();
		HTML html20 = new HTML(messages.newsuperuser_email()+":");
		html20.setWidth("150px");
		html20.setStyleName("html");
		html20.setWordWrap(false);
		txt20 = new TextBox();
		txt20.setStyleName("page4invulbox");
		lbl20 = new Label("");
		hpanel20.add(html20);
		hpanel20.add(txt20);
		hpanel20.add(lbl20);
		flex1.setWidget(2, 0, hpanel20);
		
		//vierde rij
		hpanel30 = new HorizontalPanel();
		HTML html30a = new HTML(messages.newsuperuser_invalidpw()+":");
		html30a.setWidth("150px");
		html30a.setStyleName("html");
		html30a.setWordWrap(false);
		txt30a = new PasswordTextBox();
		txt30a.setStyleName("page4invulbox");
		HTML html30b = new HTML(messages.newsuperuser_wwherhaald());
		html30b.setWidth("100px");
		html30b.setStyleName("html");
		html30b.setWordWrap(false);
		txt30b = new PasswordTextBox();
		txt30b.setStyleName("page4invulbox");
		lbl30a  = new Label();
		lbl30a.setWordWrap(true);
		lbl30b  = new Label();
		lbl30b.setWordWrap(true);
		hpanel30.add(html30a);
		hpanel30.add(txt30a);
		hpanel30.add(lbl30a);
		hpanel30.add(html30b);
		hpanel30.add(txt30b);
		hpanel30.add(lbl30b);
		flex1.setWidget(3, 0, hpanel30);
		
		//vijfde rij
		vpanel40 = new VerticalPanel();
		HTML html40 = new HTML(messages.newsuperuser_houmeopdehoogte());
		html40.setStyleName("html_goed");
	    rb0 = new RadioButton(messages.newsuperuser_houmeopdehoogte(), messages.newsuperuser_neebedankt());
	    rb1 = new RadioButton(messages.newsuperuser_houmeopdehoogte(), messages.newsuperuser_wekelijks());
	    rb2 = new RadioButton(messages.newsuperuser_houmeopdehoogte(), messages.newsuperuser_tweewekelijks());
	    rb0.setStyleName("html");
	    rb1.setStyleName("html");
	    rb2.setStyleName("html");
	    rb2.setValue(true);
		vpanel40.add(html40);
		vpanel40.add(rb0);
		vpanel40.add(rb1);
		vpanel40.add(rb2);
		flex1.setWidget(4, 0, vpanel40);
		
		//zesde rij
		hpanel50 = new HorizontalPanel();
		btn1 = new Button(messages.newsuperuser_bevestigen());
		btn1.addClickHandler(new Button1ClickHandler());
		btn1.setStyleName("standardbutton");
		btn2 = new Button(messages.newsuperuser_info());
		btn2.addClickHandler(new Button2ClickHandler());
		btn2.setStyleName("standardbutton");
		buttonpanel1 = new HorizontalPanel();
		buttonpanel1.add(btn1);
		buttonpanel1.add(btn2);
		flex1.setWidget(5, 0, buttonpanel1);
	}
	
	
	private class Button1ClickHandler implements ClickHandler
	{

		@Override
		public void onClick(ClickEvent event) 
		{
		boolean valid=false;
		boolean validpw=false;
		boolean validusername = false;
		
			String username = txt10.getText();
			validusername = Validate.checkTextbox(username);
			if(!validusername)
			{
				alertwidget.getBox().setText(messages.newsuperuser_name());
				alertwidget.getContentlabel().setText(messages.newsuperuser_validname());
				alertwidget.getBox().center();
				txt10.setText("");
				txt10.setFocus(true);
				txt10.setStyleName("page4invulbox_fout");
				return;
			}
			else
			{
				lbl10.setVisible(true);
				lbl10.setStyleName("labelgoedgekeurd");
				lbl10.setText(" V");
			}
			
			String email = txt20.getText();
			valid = Validate.checkEmail(email);
			if(!valid)
			{
				alertwidget.getBox().setText(messages.newsuperuser_email());
				alertwidget.getContentlabel().setText(messages.newsuperuser_emailcriteria());
				alertwidget.getBox().center();
				txt20.setText("");
				txt20.setFocus(true);
				txt20.setStyleName("page4invulbox_fout");
				return;
			}
			else
			{
				txt20.setStyleName("page4invulbox");
				lbl20.setStyleName("labelgoedgekeurd");
				lbl20.setText(" V");
			}
			
			String ww1 = txt30a.getText();
			String ww2 = txt30b.getText();
			if(!(ww1.equals(ww2)))
			{	
				txt30a.setText("");
				txt30b.setText("");
				txt30a.setFocus(true);
				txt30a.setStyleName("page4invulbox_fout");
				alertwidget.getBox().setText(messages.newsuperuser_unequalpwds());
				alertwidget.getContentlabel().setText(messages.newsuperuser_unequalpwdscriteria());
				alertwidget.getBox().center();
				return;
			}
			if(ww1.equals(ww2))
			
				{validpw = Validate.checkPassword(ww1);
			
			if(!validpw)
			{
				txt30a.setText("");
				txt30b.setText("");
				txt30a.setFocus(true);
				txt30a.setStyleName("page4invulbox_fout");
				txt30b.setStyleName("page4invulbox_fout");
				alertwidget.getBox().setText(messages.newsuperuser_invalidpw());
				alertwidget.getContentlabel().setText(messages.newsuperuser_invalidpwcriteria());
				alertwidget.getBox().center();
				return;
			}	
			else
			{
				lbl30a.setStyleName("labelgoedgekeurd");
				lbl30a.setText(" V");
				lbl30b.setStyleName("labelgoedgekeurd");
				lbl30b.setText(" V");
				txt30a.setStyleName("page4invulbox");
				txt30b.setStyleName("page4invulbox");
			}
			}
			
		if(valid&&validpw&&validusername)
		{
			int frequency =0;
			if(rb1.getValue()==true){frequency = 1;}
			if(rb2.getValue()==true){frequency = 2;}
			main.client.addNewSuperUser(new SuperUser(username,email,ww1,frequency));
		}
		}
		
	}
	
	private class Button2ClickHandler implements ClickHandler
	{

		@Override
		public void onClick(ClickEvent event) 
		{
			alertwidget.getBox().setText(messages.newsuperuser_info());
			alertwidget.getContentlabel().setText(messages.newsuperuser_infotext());
			alertwidget.getBox().center();
		}
		
	}
	
	public static String infoString(String input)
	{
		String returnstring= " ... ";
		int inputint=0;
		if(input.equals("general"))inputint=1;
		if(input.equals("password"))inputint=2;
		if(input.equals("unequalpasswords"))inputint=3;
		if(input.equals("email"))inputint=4;
		if(input.equals("naam"))inputint = 5;
		
		switch(inputint)
		{
		case 1 : 
		
			returnstring = "U meldt zich aan als hoofdgebruiker van de tafeltrainer; hierna kunt"
			+ "u met de knop \"groep maken\" 1 of meerdere mensen aanmelden "
			+ "als gebruiker. Uw mailadres wordt gebruikt om u op de hoogte te"
			+ "houden van uw leerling(en)";break;
			
		case 2:
			
			returnstring =  "Een wachtwoord moet uit minstens 6 tekens bestaan. Bovendien moet er minstens"
					+ "1 hoofdletter en minstens1 cijfer in voorkomen."; break;
					
		case 3: returnstring = " Beide wachtwoorden moeten gelijk zijn!"; break;
		
		case 4 : 
			
			returnstring = "Dit emailadres is niet correct, wellicht dat iets in de "
					+ "spelling fout zit...";break;
					
		case 5 : returnstring = "Het programma wil u graag met een naam kunnen aanspreken. Svp een naam"
				+ "met minimaal 2 en maximaal 20 tekens invullen.";
					
		}
	return returnstring;	
	}
	
	public void setEmailadress(String emailadress) 
	{
		this.emailadress = emailadress;
	}
	
	public void setPassworduser(String passworduser) 
	{
		this.passworduser = passworduser;
	}
	
	public String getEmailadress() 
	{
		return emailadress;
	}
	
	public String getPassworduser() 
	{
		return passworduser;
	}
}
