package afr.tafeltrainer3.client;

import afr.tafeltrainer3.shared.User;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;

public class NewLeerlingWidget extends Composite 

{
private Button btndel;
private Button btnedit;
private Button btnresults;
private HorizontalPanel hpanel1;
private User user;
	
	public NewLeerlingWidget()
	{
		hpanel1 = new HorizontalPanel();
		initWidget(hpanel1);
	}

	public NewLeerlingWidget(User user, AdministratiePage addleerlingpage)
	{
		hpanel1 = new HorizontalPanel();
		initWidget(hpanel1);
		this.user = user;
		HTML html1 = new HTML();
		HTML html2 = new HTML();
		HTML html3 = new HTML();
		HTML html4 = new HTML();
		html1.setWidth("180px");
		html1.setHTML(formatString(user.getName()+" "+user.getFamilyname()));
		html1.setStyleName("html");
		html4.setWordWrap(false);
		
		html2.setWidth("140px");
		html2.setHTML(formatString("login: "+ user.getLoginname() ));
		html2.setStyleName("html");
		html4.setWordWrap(false);

		html3.setWidth("140px");
		html3.setHTML(formatString("ww: "+ user.getPassword() ));
		html3.setStyleName("html");
		html4.setWordWrap(false);

		
		html4.setWidth("90px");
		html4.setHTML(formatString( user.getGroupname() ));
		html4.setWordWrap(false);
		html4.setStyleName("html");
		
		btnedit = new Button("wijzig");
		btnedit.addClickHandler(new btneditClickHandler());
		btndel = new Button("X");
		btndel.addClickHandler(new btndelClickHandler());
		btnresults = new Button("resultaten");
		btnresults.addClickHandler(new btnresultatenClickHandler());
		hpanel1.add(html1);
		hpanel1.add(html2);
		hpanel1.add(html3);
		hpanel1.add(html4);
		hpanel1.add(btnedit);
		hpanel1.add(btndel);
		hpanel1.add(btnresults);
	}
	
	public static String formatString(String inputstring)
		{
			StringBuilder str = new StringBuilder(inputstring);
			for (int i = 0; i< 15-inputstring.length(); i++)
				{
					str.append("&nbsp");
				}	
		return str.toString();
		}
	
	private class btneditClickHandler implements ClickHandler
	{
		@Override
		public void onClick(ClickEvent event) 
		{
			//addleerlingpage.updateGroupmember(getNlw());
		}
	}

	private class btndelClickHandler implements ClickHandler
	{
		@Override
		public void onClick(ClickEvent event) 
		{
			//addleerlingpage.removeGroupmember(getNlw());
		}
	}

	private class btnresultatenClickHandler implements ClickHandler
	{

		@Override
		public void onClick(ClickEvent event) 
		{
			// TODO Auto-generated method stub
		}
		
	}
	
	public User getUser() {
		return user;
	}
	
	public NewLeerlingWidget getNlw()
	{
		return this;
	}
	
	
	
}
