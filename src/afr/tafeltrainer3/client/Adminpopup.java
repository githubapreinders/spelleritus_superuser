package afr.tafeltrainer3.client;

import afr.tafeltrainer3.shared.User;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Adminpopup extends Composite 
{

	
	
	public Adminpopup(final User user, final MainView main)
	{
		final DialogBox box = new DialogBox();
		initWidget(box);
        box.setStyleName("popup2");
        final VerticalPanel panel = new VerticalPanel();
        panel.setBorderWidth(0);
        panel.setSpacing(20);
        HTML htmlheading = new HTML("<h1>Leerling </h1>");
		htmlheading.setStyleName("topkader");
        panel.add(htmlheading);
		
        HorizontalPanel hpanel10 = new HorizontalPanel();
		HTML html10a = new HTML("Voornaam: ");
		html10a.setWidth("140px");
		html10a.setStyleName("html");
		html10a.setWordWrap(false);
		final TextBox txt10a = new TextBox();
		txt10a.setStyleName("page4invulbox");
		txt10a.setText(user.getName());
		
		HTML html10b = new HTML("Achternaam ");
		html10b.setWidth("140px");
		html10b.setStyleName("html");
		html10b.setWordWrap(false);
		final TextBox txt10b = new TextBox();
		txt10b.setStyleName("page4invulbox");
		txt10b.setText(user.getFamilyname());
		
		hpanel10.add(html10a);
		hpanel10.add(txt10a);
		hpanel10.add(html10b);
		hpanel10.add(txt10b);
		panel.add(hpanel10);
		//derde rij
		
		HorizontalPanel hpanel20 = new HorizontalPanel();
		
		HTML html20a = new HTML("Gebruikersnaam : ");
		html20a.setWidth("140px");
		html20a.setStyleName("html");
		html20a.setWordWrap(false);
		final TextBox txt20a = new TextBox();
		txt20a.setText(user.getFamilyname());
		txt20a.setStyleName("page4invulbox");
		new Label("");
		
		HTML html20b = new HTML("Wachtwoord : ");
		html20b.setWidth("140px");
		html20b.setStyleName("html");
		html20b.setWordWrap(false);
		final TextBox txt20b = new TextBox();
		txt20b.setText(user.getPassword());
		txt20b.setStyleName("page4invulbox");
		
		hpanel20.add(html20a);
		hpanel20.add(txt20a);
		hpanel20.add(html20b);
		hpanel20.add(txt20b);
		
		panel.add(hpanel20);
		
		//radiobuttons
		VerticalPanel vpanel40 = new VerticalPanel();
		HTML html40 = new HTML("Hou me op de hoogte");
		html40.setStyleName("html_fout");
	    final RadioButton rb0 = new RadioButton("Hou in de gaten", "nee ");
	    final RadioButton rb1 = new RadioButton("Hou in de gaten", "doe maar");
	    rb0.setStyleName("html");
	    rb1.setStyleName("html");
	    rb0.setValue(true);
	    
	    vpanel40.add(html40);
		HorizontalPanel hpanelrbutton = new HorizontalPanel();
		hpanelrbutton.add(rb0);
		hpanelrbutton.add(rb1);
		vpanel40.add(hpanelrbutton);
		panel.add(vpanel40);
		box.center();
		
        
        final Button buttonClose = new Button("Annuleren",new ClickHandler() 
	        {
				@Override
				public void onClick(ClickEvent event) 
				{
					 box.hide();				
				}
	        });
        
        final Button buttonSubmit = new Button("Opslaan",new ClickHandler() 
		{
		@Override
		public void onClick(ClickEvent event) 
		{
			boolean valid=validate();
			txt10a.getText();
			txt10b.getText();
			txt20a.getText();
			txt20b.getText();
					
			if(valid)
			{
//				main.client.updateUser(new User(user.getId(),user.getEmailsuperuser(),
//						username,familyname,loginname,passw,rb1.getValue()));
				
				box.hide();
			}
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

	}
	
	public boolean validate()
	{
		boolean valid=true; 
		
		
	return valid;
	}	
	

	
}
