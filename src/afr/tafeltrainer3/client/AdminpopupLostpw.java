package afr.tafeltrainer3.client;


import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class AdminpopupLostpw extends Composite
{
MainView main;
tafeltrainer3messages messages;
	
	public AdminpopupLostpw( final MainView main, final tafeltrainer3messages messages)
	{
		this.main = main;
		this.messages = messages;
		
		final DialogBox box = new DialogBox();
		initWidget(box);
        box.setStyleName("popup2");
        
        final VerticalPanel panel = new VerticalPanel();
        panel.setBorderWidth(0);
        panel.setSpacing(20);
        HTML htmlheading = new HTML("<h1>Wachtwoord Achterhalen</h1>");
		htmlheading.setStyleName("topkader");
        panel.add(htmlheading);
		
        HTML html10A = new HTML("Vul hieronder uw emailadres in. We sturen u een mail met"
        		+ "het door u opgegeven wachtwoord.");
		html10A.setWidth("140px");
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
			Utilities.alertWidget("", "Een mail is onderweg.", messages);
			
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
