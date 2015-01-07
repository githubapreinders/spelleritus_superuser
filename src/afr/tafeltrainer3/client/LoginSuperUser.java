package afr.tafeltrainer3.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class LoginSuperUser extends Composite 
{

	private MainView main;
	private FlexTable flex1;
	private HorizontalPanel hpanel00;
	private HTML html00;
	private HorizontalPanel hpanel10;
	private HorizontalPanel hpanel20;
	public TextBox txtlogin;
	public PasswordTextBox txtpw;
	public Button btnsubmit;
	private VerticalPanel vpanel20;

	
	public LoginSuperUser(MainView main)
	{
		this.main = main;
		flex1 = new FlexTable();
		initWidget(this.flex1);
		this.flex1.setStyleName("tafeltabel");
		flex1.setCellSpacing(0);
		flex1.setCellPadding(0);
		flex1.setBorderWidth(0);
		this.flex1.getFlexCellFormatter().setHeight(0, 0, "125px");
		this.flex1.getFlexCellFormatter().setHeight(1, 0, "75px");
		this.flex1.getFlexCellFormatter().setHeight(2, 0, "75px");
		this.flex1.getFlexCellFormatter().setHeight(3, 0, "225px");
		this.flex1.getFlexCellFormatter().setWidth(0, 0, "960px");
		
		//eerste rij
		hpanel00 = new HorizontalPanel();
		flex1.getFlexCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		html00 = new HTML("<h1>Inloggen Begeleider</h1>");
		html00.setStyleName("topkader");
		hpanel00.add(html00);
		hpanel00.setSpacing(30);;
		flex1.setWidget(0, 0, hpanel00);
		
		//tweede rij
		hpanel10 = new HorizontalPanel();
		flex1.getFlexCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);
		hpanel10.setSpacing(4);
		txtlogin = new TextBox();
		txtlogin.setText("voorbeeld@hetnet.nl");
		txtlogin.setStyleName("admininvulbox");
		txtpw = new PasswordTextBox();
		txtpw.setText("Osiris74");
		txtpw.setStyleName("admininvulbox");
		hpanel10.add(txtlogin);
		hpanel10.add(txtpw);
		this.flex1.setWidget(1, 0, hpanel10);
		
		//derde rij
		vpanel20 = new VerticalPanel();
		vpanel20.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		hpanel20 = new HorizontalPanel();
		hpanel20.setSpacing(10);
		flex1.getFlexCellFormatter().setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_CENTER);
		btnsubmit = new Button("login");
		btnsubmit.setStyleName("standardbutton");
		btnsubmit.addClickHandler(new btnsubmitClickHandler());
		Anchor hyper20a = new Anchor("wachtwoord vergeten",true);
		hyper20a.addClickHandler(new anchor20aClickHandler());
		Anchor hyper20b = new Anchor("gebruikersnaam vergeten",true);
		hyper20b.addClickHandler(new anchor20bClickHandler());
		hpanel20.add(hyper20a);
		hpanel20.add(hyper20b);
		vpanel20.add(btnsubmit);
		vpanel20.add(hpanel20);
		this.flex1.setWidget(2, 0, vpanel20);
	}
	
	private class btnsubmitClickHandler implements ClickHandler
	{
		@Override
		public void onClick(ClickEvent event) 
		{
			btnsubmit.setEnabled(false);
			txtlogin.setStyleName("admininvulbox");
			txtpw.setStyleName("admininvulbox");
			main.client.getSuperUser(txtlogin.getText(),txtpw.getText());
		}
	}
	
	//Afhandeling verloren wachtwoord
	private class anchor20aClickHandler implements ClickHandler
	{
		@Override
		public void onClick(ClickEvent event) 
		{
			final DialogBox box = new DialogBox();
	        box.setStyleName("popup2");
	        
	        final VerticalPanel panel = new VerticalPanel();
	        panel.setBorderWidth(0);
	        panel.setSpacing(20);
	        panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
	        HTML htmlheading = new HTML("<H3>Wachtwoord Sturen</H3>");
			htmlheading.setStyleName("topkader");
	        panel.add(htmlheading);
			
	        HTML html10A = new HTML("Vul hieronder uw emailadres in. We sturen u een mail met"
	        		+ " met een tijdelijk wachtwoord.");
			html10A.setWidth("240px");
			html10A.setStyleName("html");
			//html10A.setWordWrap(false);
			panel.add(html10A);
	        
	        HorizontalPanel hpanel10 = new HorizontalPanel();
			HTML html10a = new HTML("Uw E-mailadres");
			html10a.setWidth("140px");
			html10a.setStyleName("html");
			html10a.setWordWrap(false);
			final TextBox txt10a = new TextBox();
			txt10a.setStyleName("page4invulbox");
			hpanel10.add(html10a);
			hpanel10.add(txt10a);
			panel.add(hpanel10);
			
			
	        
	        final Button buttonClose = new Button("Annuleren",new ClickHandler() 
		        {
					@Override
					public void onClick(ClickEvent event) 
					{
						 box.hide();				
					}
		        });
	        
	        final Button buttonSubmit = new Button("Verstuur",new ClickHandler() 
			{
			@Override
			public void onClick(ClickEvent event) 
			{
				box.hide();
				Utilities.alertWidget("", "Een mail is onderweg.", main.messages).center();;
				main.client.sendPw(txt10a.getText());
				
			}
			});
	        
	        buttonClose.setStyleName("standardbutton");
	        buttonSubmit.setStyleName("standardbutton");
	        buttonClose.setWidth("120px");
	        buttonSubmit.setWidth("120px");
	        HorizontalPanel hpanelbuttons = new HorizontalPanel();
	        hpanelbuttons.add(buttonClose);
	        hpanelbuttons.add(buttonSubmit);
	        panel.add(hpanelbuttons);
	        box.add(panel);
	        box.center();

		}
	}
	
	private class anchor20bClickHandler implements ClickHandler
	{
		@Override
		public void onClick(ClickEvent event) 
		{
			
		}
	}

	
	
	
	
	
	
}
