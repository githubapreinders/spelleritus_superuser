package afr.tafeltrainer3.client;


import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;


public class MenuView extends Composite {
	
	private HorizontalPanel hpanel = new HorizontalPanel();
	public HorizontalPanel hpanel2;
	private VerticalPanel vpanel1;
	private Anchor backtostart;
	private FlexTable flex1;
	private MainView main;
	public HTML html1;
	private Button btn1;
	private Button btn2;
	private Button btn3;
	private Button btn4;
	public Label lbl12;

	public MenuView(MainView main,tafeltrainer3messages messages) {
		
		hpanel.setStyleName("menupanel");
		initWidget(this.hpanel);
		this.main = main;
		
		this.flex1 = new FlexTable();
		this.flex1.setCellPadding(0);
		this.flex1.setBorderWidth(0);
		this.flex1.getFlexCellFormatter().setHeight(0, 0, "1px");
		this.flex1.getFlexCellFormatter().setHeight(1, 0, "40px");
		this.flex1.getFlexCellFormatter().setHeight(2, 0, "22px");
		this.flex1.getFlexCellFormatter().setWidth(0, 0, "300px");
		this.flex1.getFlexCellFormatter().setWidth(0, 1, "500px");
		this.flex1.getFlexCellFormatter().setWidth(0, 2, "200px");
		this.flex1.getFlexCellFormatter().setColSpan(2, 0, 2);
		this.flex1.getFlexCellFormatter().setAlignment(1, 2, HasHorizontalAlignment.ALIGN_CENTER,
				HasVerticalAlignment.ALIGN_MIDDLE);
		this.flex1.getFlexCellFormatter().setAlignment(2, 1, HasHorizontalAlignment.ALIGN_CENTER,
				HasVerticalAlignment.ALIGN_MIDDLE);
		this.flex1.getFlexCellFormatter().setHorizontalAlignment(2, 2, HasHorizontalAlignment.ALIGN_LEFT);
		
		
		
		html1 = new HTML("<h1>"+messages.menupage_title()+"</h1>"); 
		html1.setStyleName("topkader");
		html1.addClickHandler(new html1ClickHandler());
		this.flex1.setWidget(1, 1, html1);
		
		vpanel1 = new VerticalPanel();
		vpanel1.setBorderWidth(0);
		lbl12 = new Label();
		lbl12.setStyleName("kleinelabelszwart");
		vpanel1.add(lbl12);
		this.flex1.setWidget(1, 2, vpanel1);

		this.hpanel2 = new HorizontalPanel();
		this.btn1 = new Button(messages.menupage_menutafeltrainer());
		this.btn1.setStyleName("btnmenupage");
		this.btn1.addClickHandler(new Button1ClickHandler());
		this.hpanel2.add(btn1);

		this.btn2 = new Button(messages.menupage_menuresultaten());
		this.btn2.setStyleName("btnmenupage");
		this.btn2.addClickHandler(new Button2ClickHandler());
		this.hpanel2.add(btn2);
		
		this.btn3 = new Button(messages.menupage_menuportemonnee());
		this.btn3.setStyleName("btnmenupage");
		this.btn3.addClickHandler(new Button3ClickHandler());
		this.hpanel2.add(btn3);
		
		this.btn4 = new Button(messages.menupage_menushop());
		this.btn4.setStyleName("btnmenupage");
		this.btn4.addClickHandler(new Button4ClickHandler());
		this.hpanel2.add(btn4);
		
		this.flex1.setWidget(2, 0, hpanel2);
		
		
		HorizontalPanel hpanel22 = new HorizontalPanel();
		hpanel22.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		
		backtostart = new Anchor(messages.menupage_home(), true);
		backtostart.addClickHandler(new backtostartClickHandler());
		backtostart.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		hpanel22.add(backtostart);
		this.flex1.setWidget(2, 1, hpanel22);
		
		hpanel.add(flex1);

	}
	
	private class html1ClickHandler implements ClickHandler
	{

		@Override
		public void onClick(ClickEvent event) 
		{
			
		}
	}
	
	public void showUserMenu()
	{
		hpanel2.setVisible(true);
	}

	public void showSuperUserMenu()
	{
		hpanel2.setVisible(false);
	}
	
	public void showEntranceMenu()
	{
		hpanel2.setVisible(false);
	}
	
	private class backtostartClickHandler implements ClickHandler
	{

		@Override
		public void onClick(ClickEvent event) 
		{
			main.menu.html1.setHTML("<h1>Spelleritus<h1>");
			main.showPageStartPage();
		}
		
	}
	
	
	//toont de tafeltrainer
	private class Button1ClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
					}

	}
	
	//toont beheersingsniveau van de user
	private class Button2ClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			
		}

	}
	
	//toont de portemonnee en de bezittingen
	private class Button3ClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
					}
	}
	
	//toont de shop
	private class Button4ClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			
		}

	}

}
