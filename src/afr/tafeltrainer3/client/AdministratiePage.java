package afr.tafeltrainer3.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import afr.tafeltrainer3.shared.SuperUser;
import afr.tafeltrainer3.shared.User;
import afr.tafeltrainer3.shared.Woordpakket;

import com.google.gwt.cell.client.ClickableTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.SelectionCell;
import com.google.gwt.cell.client.TextButtonCell;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;

/**
 * Todo : -eerste van de userlijst kan niet verwijderd worden, herstelen
 * -gebruiksaanwijzingen user bijvoegen
 *
 */

public class AdministratiePage extends Composite implements ClientBundle
{

	public Anchor anchor11;
	public ArrayList<User> mygroup;
	public ArrayList<Woordpakket> mypackages;
	private ArrayList<WpRepresentation> list;
	private int flag = 1;
	private Button btnadminpage2;
	private Button btnmailme;
	private Button btnsavewp;
	private Button btnsettings;
	private Button btndummy;
	public FlexTable flex2;
	public FlexTable flex1 = new FlexTable();
	private FieldUpdater<WpRepresentation, String> fieldupdatercol1;
	private MainView main;
	private ScrollPanel spanel10;
	public String defaultpassword;
	public SuperUser superuser;
	private User currentuser;
	public boolean editingmode = false;
	tafeltrainer3messages messages;
	private VerticalPanel vpanel11;
	public CellTable<WpRepresentation> celltable = null;
	private TextArea textarea;
	private ListBox lb;
	private Label lblviewer;

	public AdministratiePage(MainView main, SuperUser superuser, tafeltrainer3messages messages)
	{
		this.main = main;
		this.superuser = superuser;
		this.messages = messages;
		mygroup = new ArrayList<User>();
		mypackages = main.wordpackages;
		main.menu.hpanel2.setVisible(false);
		initWidget(flex1);

		this.flex1.setStyleName("tafeltabel");
		flex1.setCellSpacing(0);
		flex1.setCellPadding(0);
		flex1.setBorderWidth(0);
		// flex1.setStyleName("tafeltabel");
		this.flex1.setWidth("960px");
		this.flex1.setHeight("500px");

		// eerste rij
		anchor11 = new Anchor(correctName(superuser.getName()));
		anchor11.setStyleName("html");
		anchor11.addClickHandler(new anchor11ClickHandler());
		this.flex1.getFlexCellFormatter().setWidth(0, 0, "250px");
		this.flex1.getFlexCellFormatter().setHeight(0, 0, "50px");
		this.flex1.getFlexCellFormatter().setAlignment(0, 0, HasHorizontalAlignment.ALIGN_LEFT,
				HasVerticalAlignment.ALIGN_MIDDLE);
		flex1.setWidget(0, 0, anchor11);

		// tweede rij
		btnadminpage2 = new Button(messages.administratiepage_nieuw());
		btnadminpage2.setStyleName("standardbutton");
		btnadminpage2.addClickHandler(new BtnNewUserClickHandler(this.superuser.getEmail()));

		btnmailme = new Button("Mail...");
		btnmailme.setStyleName("standardbutton");
		btnmailme.addClickHandler(new BtnMailMeClickHandler());

		btnsavewp = new Button("Pakket...");
		btnsavewp.setStyleName("standardbutton");
		btnsavewp.addClickHandler(new btnSaveWpClickHandler());
		
		btnsettings = new Button("Instellingen");
		btnsettings.setStyleName("standardbutton");
		btnsettings.addClickHandler(new btnSettingsClickHandler());
		
		btndummy = new Button("Instellingen");
		btndummy.setStyleName("standardbutton");
		btndummy.setVisible(false);
		
		
		
		this.spanel10 = new ScrollPanel();
		spanel10.setHeight("420px");

		flex1.getFlexCellFormatter().setAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER,
				HasVerticalAlignment.ALIGN_TOP);
		this.vpanel11 = new VerticalPanel();
		this.vpanel11.setSpacing(10);
		//this.vpanel11.setBorderWidth(1);
		this.vpanel11.setHeight("450px");
		flex1.setWidget(1, 0, vpanel11);
		main.menu.html1.setHTML("<h1>" + messages.administratiepage_administratie() + "<h1>");

	}

	private class btnSettingsClickHandler implements ClickHandler
	{

		@Override
		public void onClick(ClickEvent event)
		{
			// TODO Auto-generated method stub

		}
	}

	private class btnSaveWpClickHandler implements ClickHandler
	{

		@Override
		public void onClick(ClickEvent event)
		{
			Woordpakket wp = null;
			String s = lb.getItemText(lb.getSelectedIndex());
			int index=0 ;
			for(Woordpakket w : mypackages)
			{
				if((w.getIdentifier().trim()).equals(s.trim()))
				{
					wp = w ; 
					break;
				}
			index++;
			}
			if(wp==null)
			{
				
			}
			mypackages.get(index).setContents(textarea.getText());
			wp.setContents(textarea.getText());
			main.client.updateWoordpakket(wp, superuser);

		}

	}

	final class WpRepresentation
	{
		private int id;
		private String username;
		private String defaultwp;
		private String extrawp;

		public WpRepresentation()
		{
			this.username = "";
			this.defaultwp = "";
			this.extrawp = "";
		}
		
		public WpRepresentation(int id, String username, String defaultwp, String extrawp)
		{
			super();
			this.id = id;
			this.username = username;
			this.defaultwp = defaultwp;
			this.extrawp = extrawp;
		}

		public int getId()
		{
			return this.id;
		}

		public String getDefaultWp()
		{
			return this.defaultwp;
		}

		public void  setExtraWp(String extrawp)
		{
			this.extrawp = extrawp;
		}

		public void setDefaultWp(String defaultwp)
		{
			this.defaultwp = defaultwp;
		}

		public String getExtraWp()
		{
			return this.extrawp;
		}

		
		
		public void setUsername(String username)
		{
			this.username = username;
		}

		public String getUsername()
		{
			return this.username;
		}

	}

	/**
	 * Textbuttoncells can only be styled via a separate css-file; you therefore
	 * need to create an interface in which the css is referenced. The interface
	 * is defined as a resource. When the column of buttons is defined the
	 * resource is given inside the constructor.
	 */
	public void addData()
	{
		MyButtonResource buttonresource = GWT.create(MyButtonResource.class);
		new TextButtonCell.DefaultAppearance(buttonresource);

		final ListDataProvider<WpRepresentation> provider = new ListDataProvider<WpRepresentation>();
		list = new ArrayList<WpRepresentation>();
		if(mygroup.size()==0)
		{
			list.add(new WpRepresentation());
		}
		HashMap<Long, Woordpakket> wpmap = new HashMap<Long, Woordpakket>();
		for (Woordpakket w : mypackages)
		{
			wpmap.put(w.getId(), w);
		}

		for (User u : mygroup)
		{
			WpRepresentation wpr = new WpRepresentation(u.getId(), u.getName() + " " + u.getFamilyname(), wpmap.get(
					(long) u.getCurrentwp()).getIdentifier(), wpmap.get((long) u.getExtrawp()).getIdentifier());
			list.add(wpr);
		}
		provider.setList(list);

		// adding values to the selectionlist from the general data list;
		final List<String> sc1list = new ArrayList<String>();
		final List<String> sc2list = new ArrayList<String>();
		for (Woordpakket w : mypackages)
		{
			if (!(sc1list.contains(w.getIdentifier())))
				sc1list.add(w.getIdentifier());

			if (!(sc2list.contains(w.getIdentifier())))
				sc2list.add(w.getIdentifier());
		}

		CellTable.Resources resources = GWT.create(TableResources.class);
		celltable = new CellTable<WpRepresentation>(40, resources);

		ClickableTextCell namebutton = new ClickableTextCell();
		Column<WpRepresentation, String> col1 = new Column<WpRepresentation, String>(namebutton)
		{
			@Override
			public String getValue(WpRepresentation object)
			{
				return object.username;
			}
		};
		fieldupdatercol1 = new FieldUpdater<WpRepresentation, String>()
		{

			@Override
			public void update(int index, WpRepresentation object, String value)
			{
				// case 1 : updating an existing record from the list
				// case 2 : inserting a new record at the beginning of the list
				// case 3 : deleting a record
				switch (flag)
				{
				case 1:
					updateLeerling(index, object.getId(), superuser.getEmail(), object);
					break;
				case 2:
					provider.getList().add(0, object);
					provider.refresh();
					flag = 1;
					break;
				case 3:
					WpRepresentation wp = provider.getList().get(index);
					User user = new User();
					user.setId(wp.getId());
					main.client.deleteUser(user);
					provider.getList().remove(index);
					provider.refresh();
					flag = 1;
					break;
				}
			}
		};
		col1.setFieldUpdater(fieldupdatercol1);

		celltable.addColumn(col1, "gebruikersnaam", "");

		ClickableTextCell resultsbutton = new ClickableTextCell();
		Column<WpRepresentation, String> col2 = new Column<WpRepresentation, String>(resultsbutton)
		{
			@Override
			public String getValue(WpRepresentation object)
			{
				return "Resultaten";
			}
		};
		col2.setFieldUpdater(new FieldUpdater<WpRepresentation, String>()
		{

			@Override
			public void update(int index, WpRepresentation object, String value)
			{
				Window.alert("index: " + index + "\nobject: " + object.username + " \nValue: " + value);
			}
		});
		celltable.addColumn(col2, " ");

		SelectionCell sc1 = new SelectionCell(sc1list);
		Column<WpRepresentation, String> col3 = new Column<WpRepresentation, String>(sc1)
		{
			@Override
			public String getValue(WpRepresentation object)
			{
				return object.getDefaultWp();
			}
		};

		col3.setFieldUpdater(new FieldUpdater<WpRepresentation, String>()
		{

			// updates the current wp of this user and writes it to the db;
			@Override
			public void update(int index, WpRepresentation object, String value)
			{
				
				for (User u : mygroup)
				{
					if (u.getId() == object.getId())
					{
						currentuser = u;
					}
				}
				for (Woordpakket wp : mypackages)
				{
					if (wp.getIdentifier().equals(value))
					{
						int c = (wp.getId()).intValue();
						currentuser.setCurrentwp(c);
						main.client.updateUser(currentuser);
						provider.getList().get(index).setDefaultWp(value);
						provider.refresh();
					}
				}
			}
		});

		celltable.addColumn(col3, "oefenpakket");

		SelectionCell sc2 = new SelectionCell(sc2list);
		Column<WpRepresentation, String> col4 = new Column<WpRepresentation, String>(sc2)
		{
			@Override
			public String getValue(WpRepresentation object)
			{
				String str = object.getExtraWp();
				return str;
			}
		};
		col4.setFieldUpdater(new FieldUpdater<WpRepresentation, String>()
		{

			@Override
			public void update(int index, WpRepresentation object, String value)
			{
				for (User u : mygroup)
				{
					if (u.getId() == object.getId())
					{
						currentuser = u;
					}
				}
				for (Woordpakket wp : mypackages)
				{
					if (wp.getIdentifier().equals(value))
					{
						int c = (wp.getId()).intValue();
						currentuser.setExtrawp(c);
						main.client.updateUser(currentuser);
						provider.getList().get(index).setExtraWp(value);
						provider.refresh();
					}
				}
			}
		});

		celltable.addColumn(col4, "extra pakket");
		//celltable.setRowCount(15);
		//celltable.setRowData(0, list);
		provider.addDataDisplay(celltable);
		flex1.getFlexCellFormatter().setAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER,
				HasVerticalAlignment.ALIGN_TOP);
		spanel10.add(celltable);
		currentuser = new User();
		flex1.setWidget(1, 1, spanel10);
		makeWpViewer();

	}

	private void makeWpViewer()
	{
		lb = new ListBox();
		// lb.setStyleName("listbox");
		for (Woordpakket w : mypackages)
		{
			lb.addItem(w.getIdentifier());
		}

		lb.addChangeHandler(new Lb1ChangeHandler());
		lblviewer = new Label("Pakketviewer");
		lblviewer.setStyleName("middellabels");
		textarea = new TextArea();
		textarea.setHeight("120px");
		textarea.setWidth("200px");

		String s = list.get(0).getDefaultWp();
		int i = 0;
		for (Woordpakket wp : mypackages)
		{
			if (wp.getIdentifier().equals(s))
			{
				textarea.setText(wp.getContents());
				lb.setItemSelected(i, true);
			}
			i++;
		}
		vpanel11.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		vpanel11.setBorderWidth(0);
		vpanel11.add(lblviewer);
		vpanel11.add(lb);
		vpanel11.add(textarea);
		vpanel11.add(btndummy);

		vpanel11.setCellHeight(lblviewer, Integer.toString(lblviewer.getElement().getOffsetHeight())+"px");
		vpanel11.setCellHeight(lb, Integer.toString(lb.getElement().getOffsetHeight())+"px");
		vpanel11.setCellHeight(textarea, Integer.toString(textarea.getElement().getOffsetHeight())+"px");
		vpanel11.setCellHeight(btndummy, "120px");
		
		FlexTable flexhelper = new FlexTable();
		flexhelper.setWidget(0, 0, btnadminpage2);
		flexhelper.setWidget(0, 1, btnmailme);
		flexhelper.setWidget(1,0 , btnsavewp);
		flexhelper.setWidget(1, 1, btnsettings);
		vpanel11.setCellHeight(flexhelper, Integer.toString(flexhelper.getElement().getOffsetHeight())+"px");
		vpanel11.add(flexhelper);
		
		if(mygroup.size()==0)
		{
			flag = 3;
			fieldupdatercol1.update(0, new WpRepresentation(), "");
		}
		
	}

	public class Lb1ChangeHandler implements ChangeHandler
	{

		@Override
		public void onChange(ChangeEvent event)
		{
			textarea.setText(mypackages.get(lb.getSelectedIndex()).getContents());
		}

	}

	public class lbClickHandler implements ClickHandler
	{

		@Override
		public void onClick(ClickEvent event)
		{
			Window.alert("item selected! " + lb.getSelectedIndex() + " text:" + lb.getItemText(lb.getSelectedIndex()));

		}

	}

	private class BtnMailMeClickHandler implements ClickHandler
	{

		@Override
		public void onClick(ClickEvent event)
		{
			Utilities.alertWidget(messages.administratiepage_onderweg(),
					messages.administratiepage_mail_is_verstuurd(), messages).center();
			main.client.sendMail(superuser);
		}

	}

	private class BtnNewUserClickHandler implements ClickHandler
	{
		String emailsuperuser = "";

		public BtnNewUserClickHandler(String emailsuperuser)
		{
			this.emailsuperuser = emailsuperuser;
		}

		@Override
		public void onClick(ClickEvent event)
		{
			defaultpassword = "";
			if (!mygroup.isEmpty())
			{
				defaultpassword = mygroup.get(0).getPassword();
			}
			final DialogBox box = new DialogBox();
			box.setStyleName("popup2");
			final VerticalPanel panel = new VerticalPanel();
			panel.setBorderWidth(0);
			panel.setSpacing(20);
			HTML htmlheading = new HTML("<h1>Nieuwe Leerling </h1>");
			htmlheading.setStyleName("topkader");
			panel.add(htmlheading);

			HorizontalPanel hpanel10 = new HorizontalPanel();
			HTML html10a = new HTML("Voornaam: ");
			html10a.setWidth("140px");
			html10a.setStyleName("html");
			html10a.setWordWrap(false);
			final TextBox txt10a = new TextBox();
			txt10a.setStyleName("page4invulbox");

			HTML html10b = new HTML("Achternaam: ");
			html10b.setWidth("140px");
			html10b.setStyleName("html");
			html10b.setWordWrap(false);
			final TextBox txt10b = new TextBox();
			txt10b.setStyleName("page4invulbox");

			new Label();
			hpanel10.add(html10a);
			hpanel10.add(txt10a);
			hpanel10.add(html10b);
			hpanel10.add(txt10b);
			panel.add(hpanel10);
			// derde rij

			HorizontalPanel hpanel20 = new HorizontalPanel();

			HTML html20a = new HTML("Gebruikersnaam: ");
			html20a.setWidth("140px");
			html20a.setStyleName("html");
			html20a.setWordWrap(false);
			final TextBox txt20a = new TextBox();
			txt20a.setStyleName("page4invulbox");

			HTML html20b = new HTML("Wachtwoord: ");
			html20b.setWidth("140px");
			html20b.setStyleName("html");
			html20b.setWordWrap(false);
			final TextBox txt20b = new TextBox();
			txt20b.setText(defaultpassword);
			txt20b.setStyleName("page4invulbox");

			hpanel20.add(html20a);
			hpanel20.add(txt20a);
			hpanel20.add(html20b);
			hpanel20.add(txt20b);

			panel.add(hpanel20);

			// radiobuttons
			VerticalPanel vpanel40 = new VerticalPanel();
			HTML html40 = new HTML("Hou me op de hoogte");
			html40.setStyleName("html_fout");
			final RadioButton rb0 = new RadioButton("Hou in de gaten", "nee ");
			final RadioButton rb1 = new RadioButton("Hou in de gaten", "doe maar");
			rb0.setStyleName("html");
			rb1.setStyleName("html");
			rb0.setValue(true);

			HorizontalPanel hpanelrbrow = new HorizontalPanel();
			vpanel40.add(html40);
			HorizontalPanel hpanelrbutton = new HorizontalPanel();
			hpanelrbutton.add(rb0);
			hpanelrbutton.add(rb1);
			vpanel40.add(hpanelrbutton);
			hpanelrbrow.add(vpanel40);

			final Anchor deleteuser = new Anchor(" X ", false);
			deleteuser.setStyleName("anchor_red");

			panel.add(hpanelrbrow);
			final Button buttonClose = new Button("Annuleren", new ClickHandler()
			{
				@Override
				public void onClick(ClickEvent event)
				{
					box.hide();
				}
			});

			final Button buttonSubmit = new Button("Opslaan", new ClickHandler()
			{
				@Override
				public void onClick(ClickEvent event)
				{
					boolean valid = validate(txt10a, txt10b, txt20a, txt20b);
					String username = correctName(txt10a.getText());
					String familyname = correctName(txt10b.getText());
					String loginname = txt20a.getText();
					String passw = txt20b.getText();

					// id will be attached in the db
					if (valid)
					{
						User newuser = new User(0, emailsuperuser, correctName(username), correctName(familyname),
								loginname, passw, rb0.getValue(), mypackages.get(1).getId().intValue(), mypackages
										.get(1).getId().intValue());
						main.client.addNewUser(newuser);
						
						WpRepresentation w = null;
						if(mygroup.size()==0)
						{
						w = new WpRepresentation(0, correctName(username) + " " + correctName(familyname), mypackages.get(0)
								.getIdentifier(), mypackages.get(0).getIdentifier());
						}
						else
						{
							w = new WpRepresentation(0, correctName(username) +" "+ correctName(familyname),list.get(0).getDefaultWp(),
									list.get(0).getExtraWp());
						}
						mygroup.add(newuser);
						box.hide();
						flag = 2;
						fieldupdatercol1.update(1, w, username);
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
			box.center();
		}
	}

	public void updateLeerling(final int index, final int id, final String emailsuperuser, WpRepresentation object)
	{
		for (User u : mygroup)
		{
			if (u.getId() == id)
			{
				currentuser = u;
				break;
			}
		}
		defaultpassword = "";
		if (!mygroup.isEmpty())
		{
			defaultpassword = mygroup.get(0).getPassword();
		}
		final DialogBox box = new DialogBox();
		box.setStyleName("popup2");
		final VerticalPanel panel = new VerticalPanel();
		panel.setBorderWidth(0);
		panel.setSpacing(20);
		HTML htmlheading = new HTML("<h1>Aanpassen</h1>");
		htmlheading.setStyleName("topkader");
		panel.add(htmlheading);

		HorizontalPanel hpanel10 = new HorizontalPanel();
		HTML html10a = new HTML("Voornaam: ");
		html10a.setWidth("140px");
		html10a.setStyleName("html");
		html10a.setWordWrap(false);
		final TextBox txt10a = new TextBox();
		txt10a.setStyleName("page4invulbox");
		txt10a.setText(currentuser.getName());

		HTML html10b = new HTML("Achternaam: ");
		html10b.setWidth("140px");
		html10b.setStyleName("html");
		html10b.setWordWrap(false);
		final TextBox txt10b = new TextBox();
		txt10b.setStyleName("page4invulbox");
		txt10b.setText(currentuser.getFamilyname());

		new Label();
		hpanel10.add(html10a);
		hpanel10.add(txt10a);
		hpanel10.add(html10b);
		hpanel10.add(txt10b);
		panel.add(hpanel10);
		// derde rij

		HorizontalPanel hpanel20 = new HorizontalPanel();

		HTML html20a = new HTML("Gebruikersnaam: ");
		html20a.setWidth("140px");
		html20a.setStyleName("html");
		html20a.setWordWrap(false);
		final TextBox txt20a = new TextBox();
		txt20a.setStyleName("page4invulbox");
		txt20a.setText(currentuser.getLoginname());
		new Label("");

		HTML html20b = new HTML("Wachtwoord: ");
		html20b.setWidth("140px");
		html20b.setStyleName("html");
		html20b.setWordWrap(false);
		final TextBox txt20b = new TextBox();
		txt20b.setText(defaultpassword);
		txt20b.setStyleName("page4invulbox");
		txt20b.setText(currentuser.getPassword());

		hpanel20.add(html20a);
		hpanel20.add(txt20a);
		hpanel20.add(html20b);
		hpanel20.add(txt20b);

		panel.add(hpanel20);

		// radiobuttons
		VerticalPanel vpanel40 = new VerticalPanel();
		HTML html40 = new HTML("Hou me op de hoogte");
		html40.setStyleName("html_fout");
		final RadioButton rb0 = new RadioButton("Hou in de gaten", "nee ");
		final RadioButton rb1 = new RadioButton("Hou in de gaten", "doe maar");
		rb0.setStyleName("html");
		rb1.setStyleName("html");
		rb0.setValue(true);

		HorizontalPanel hpanelrbrow = new HorizontalPanel();
		vpanel40.add(html40);
		HorizontalPanel hpanelrbutton = new HorizontalPanel();
		hpanelrbutton.add(rb0);
		hpanelrbutton.add(rb1);
		vpanel40.add(hpanelrbutton);
		hpanelrbrow.add(vpanel40);

		final Anchor deleteuser = new Anchor(" X ", false);
		deleteuser.setStyleName("anchor_red");

		panel.add(hpanelrbrow);
		final Button buttonClose = new Button("Annuleren", new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				box.hide();
			}
		});

		Button buttonSubmit = new Button("Opslaan");
		buttonSubmit.addClickHandler(new btnSubmitClickHandler(txt10a, txt10b, txt20a, txt20b, rb0, box, object));
		buttonClose.setStyleName("standardbutton");
		buttonSubmit.setStyleName("standardbutton");
		buttonClose.setWidth("120px");
		buttonSubmit.setWidth("120px");
		Button buttondelete = new Button("Verwijderen");
		buttondelete.addClickHandler(new btnDeleteClickHandler(index, box));
		buttondelete.setStyleName("standardbutton");
		HorizontalPanel hpanelbuttons = new HorizontalPanel();
		hpanelbuttons.setSpacing(20);
		hpanelbuttons.add(buttonClose);
		hpanelbuttons.add(buttonSubmit);
		hpanelbuttons.add(buttondelete);
		panel.add(hpanelbuttons);
		box.add(panel);
		box.center();

	}

	private class btnDeleteClickHandler implements ClickHandler
	{
		int index;
		DialogBox box;

		public btnDeleteClickHandler(int index, DialogBox box)
		{
			this.index = index;
			this.box = box;
		}

		@Override
		public void onClick(ClickEvent event)
		{
			flag = 3;
			WpRepresentation w = new WpRepresentation(0, " ", mypackages.get(1).getIdentifier(), mypackages.get(1)
					.getIdentifier());
			box.hide();
			fieldupdatercol1.update(index, w, "");

		}
	}

	private class btnSubmitClickHandler implements ClickHandler
	{

		TextBox t1, t2, t3, t4;
		RadioButton r1;
		DialogBox box;
		WpRepresentation object;

		private btnSubmitClickHandler(TextBox t1, TextBox t2, TextBox t3, TextBox t4, RadioButton r1, DialogBox box,
				WpRepresentation object)
		{
			{
				this.t1 = t1;
				this.t2 = t2;
				this.t3 = t3;
				this.t4 = t4;
				this.r1 = r1;
				this.box = box;
				this.object = object;
			}
		}

		@Override
		public void onClick(ClickEvent event)
		{

			if (validate(t1, t2, t3, t4))
			{

				currentuser.setName(t1.getText());
				currentuser.setFamilyname(t2.getText());
				currentuser.setLoginname(t3.getText());
				currentuser.setPassword(t4.getText());
				currentuser.setHouindegaten(r1.getValue());
				main.client.updateUser(currentuser);
				object.setUsername(correctName(t1.getText() + " " + t2.getText()));
				celltable.redraw();
				box.hide();
				return;
			} else
			{
				return;
			}

		}

	}

	public String correctName(String name)
	{
		ArrayList<Integer> capitalindexes = new ArrayList<Integer>();
		capitalindexes.add(0);
		name.trim();
		StringBuilder str = new StringBuilder(name);
		for (int i = 0; i < str.length() - 1; i++)
		{
			if (str.charAt(i) == ' ')
			{
				capitalindexes.add(i + 1);
			} else
				str.setCharAt(i, Character.toLowerCase(str.charAt(i)));
		}
		for (int j : capitalindexes)

			str.setCharAt(j, Character.toUpperCase(str.charAt(j)));
		return str.toString();
	}

	private class anchor11ClickHandler implements ClickHandler
	{

		@Override
		public void onClick(ClickEvent event)
		{
			final DialogBox box = new DialogBox();
			// box.setPixelSize(350, 550);
			box.setStyleName("popup2");
			final VerticalPanel panel = new VerticalPanel();
			panel.setBorderWidth(0);
			panel.setSpacing(20);
			HTML htmlheading = new HTML("<h1>Gegevens Wijzigen</h1>");
			htmlheading.setStyleName("topkader");
			panel.add(htmlheading);

			HorizontalPanel hpanel10 = new HorizontalPanel();
			HTML html10 = new HTML("Uw naam: ");
			html10.setWidth("140px");
			html10.setStyleName("html");
			html10.setWordWrap(false);
			final TextBox txt10 = new TextBox();
			txt10.setStyleName("page4invulbox");
			txt10.setText(superuser.getName());
			final Label lbl10 = new Label();
			hpanel10.add(html10);
			hpanel10.add(txt10);
			hpanel10.add(lbl10);
			panel.add(hpanel10);
			// derde rij
			HorizontalPanel hpanel20 = new HorizontalPanel();
			HTML html20 = new HTML("Emailadres: ");
			html20.setWidth("140px");
			html20.setStyleName("html");
			html20.setWordWrap(false);
			final TextBox txt20 = new TextBox();
			txt20.setText(superuser.getEmail());
			txt20.setStyleName("page4invulbox");
			final Label lbl20 = new Label("");
			hpanel20.add(html20);
			hpanel20.add(txt20);
			hpanel20.add(lbl20);
			panel.add(hpanel20);
			// vierde rij
			HorizontalPanel hpanel30 = new HorizontalPanel();
			HTML html30a = new HTML("Wachtwoord: ");
			html30a.setWidth("140px");
			html30a.setStyleName("html");
			html30a.setWordWrap(false);
			final PasswordTextBox txt30a = new PasswordTextBox();
			txt30a.setText(superuser.getPassword());
			txt30a.setStyleName("page4invulbox");
			HTML html30b = new HTML("Ww herhaald: ");
			html30b.setWidth("100px");
			html30b.setStyleName("html");
			html30b.setWordWrap(false);
			final PasswordTextBox txt30b = new PasswordTextBox();
			txt30b.setText(superuser.getPassword());
			txt30b.setStyleName("page4invulbox");
			final Label lbl30a = new Label();
			lbl30a.setWordWrap(true);
			final Label lbl30b = new Label();
			lbl30b.setWordWrap(true);
			hpanel30.add(html30a);
			hpanel30.add(txt30a);
			hpanel30.add(lbl30a);
			hpanel30.add(html30b);
			hpanel30.add(txt30b);
			hpanel30.add(lbl30b);
			panel.add(hpanel30);

			// radiobuttons
			VerticalPanel vpanel40 = new VerticalPanel();
			HTML html40 = new HTML("Hou me op de hoogte");
			html40.setStyleName("html_fout");
			final RadioButton rb0 = new RadioButton("Hou me op de hoogte", "nee bedankt");
			final RadioButton rb1 = new RadioButton("Hou me op de hoogte", "wekelijks");
			final RadioButton rb2 = new RadioButton("Hou me op de hoogte", "tweewekelijks");
			rb0.setStyleName("html");
			rb1.setStyleName("html");
			rb2.setStyleName("html");
			if (superuser.getEmailfrequency() == 0)
				rb0.setValue(true);
			if (superuser.getEmailfrequency() == 1)
				rb1.setValue(true);
			if (superuser.getEmailfrequency() == 2)
				rb2.setValue(true);

			vpanel40.add(html40);
			HorizontalPanel hpanelrbutton = new HorizontalPanel();
			hpanelrbutton.add(rb0);
			hpanelrbutton.add(rb1);
			hpanelrbutton.add(rb2);
			vpanel40.add(hpanelrbutton);
			panel.add(vpanel40);

			final Button buttonClose = new Button("Annuleren", new ClickHandler()
			{
				@Override
				public void onClick(ClickEvent event)
				{
					box.hide();
				}
			});

			final Button buttonSubmit = new Button("Opslaan", new ClickHandler()
			{
				@Override
				public void onClick(ClickEvent event)
				{
					boolean valid = false;
					boolean validpw = false;
					boolean validusername = false;

					String username = txt10.getText();
					validusername = Validate.checkTextbox(username);
					if (!validusername)
					{
						lbl10.setVisible(true);
						lbl10.setStyleName("middellabels");
						lbl10.setText(NewSuperUser.infoString("naam"));
						txt10.setText("");
						txt10.setFocus(true);
						txt20.setStyleName("page4invulbox_fout");
					} else
					{
						lbl10.setVisible(true);
						lbl10.setStyleName("labelgoedgekeurd");
						lbl10.setText(" V");
					}

					String email = txt20.getText();
					valid = Validate.checkEmail(email);
					if (!valid)
					{
						lbl20.setVisible(true);
						lbl20.setStyleName("middellabels");
						lbl20.setText(NewSuperUser.infoString("email"));
						txt20.setText("");
						txt20.setFocus(true);
						txt20.setStyleName("page4invulbox_fout");
					} else
					{
						txt20.setStyleName("page4invulbox");
						lbl20.setStyleName("labelgoedgekeurd");
						lbl20.setText(" V");
					}

					String ww1 = txt30a.getText();
					String ww2 = txt30b.getText();
					if (!(ww1.equals(ww2)))
					{
						txt30a.setText("");
						txt30b.setText("");
						txt30a.setFocus(true);
						txt30a.setStyleName("page4invulbox_fout");
						txt30b.setStyleName("page4invulbox_fout");
						lbl30b.setVisible(true);
						lbl30b.setStyleName("middellabels");
						lbl30b.setText(NewSuperUser.infoString("unequalpasswords"));
					}
					if (ww1.equals(ww2))

					{
						validpw = Validate.checkPassword(ww1);

						if (!validpw)
						{
							txt30a.setText("");
							txt30b.setText("");
							txt30a.setFocus(true);
							lbl30b.setVisible(true);
							txt30a.setStyleName("page4invulbox_fout");
							txt30b.setStyleName("page4invulbox_fout");
							lbl30b.setStyleName("middellabels");
							lbl30b.setText(NewSuperUser.infoString("password"));
						} else
						{
							lbl30a.setStyleName("labelgoedgekeurd");
							lbl30a.setText(" V");
							lbl30b.setStyleName("labelgoedgekeurd");
							lbl30b.setText(" V");
							txt30a.setStyleName("page4invulbox");
							txt30b.setStyleName("page4invulbox");
						}
					}

					if (valid && validpw && validusername)
					{
						int frequency = 0;
						if (rb1.getValue() == true)
						{
							frequency = 1;
						}
						if (rb2.getValue() == true)
						{
							frequency = 2;
						}
						main.client.updateSuperUser(new SuperUser(username, email, ww1, frequency),
								superuser.getEmail());
						superuser.setName(username);
						superuser.setEmail(email);
						superuser.setPassword(ww1);
						superuser.setEmailfrequency(frequency);
						main.administratiepage.anchor11.setText(username);
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
			box.center();
		}

	}

	public void addGroupmember(User user)
	{
		celltable.redraw();
	}

	public boolean validate(TextBox txt10a, TextBox txt10b, TextBox txt20a, TextBox txt20b)
	{
		boolean valid = true;

		if (txt10a.getText().length() < 1 || txt10a.getText().length() > 15)
		{
			txt10a.setStyleName("admininvulbox_fouteinvoer");
			valid = false;
		} else
			txt10a.setStyleName("admininvulbox");

		if (txt10b.getText().length() < 1 || txt10b.getText().length() > 15)
		{
			txt10b.setStyleName("admininvulbox_fouteinvoer");
			valid = false;
		} else
			txt10b.setStyleName("admininvulbox");

		if (txt20a.getText().length() < 1 || txt20a.getText().length() > 15)
		{
			txt20a.setStyleName("admininvulbox_fouteinvoer");
			valid = false;
		} else
			txt20a.setStyleName("admininvulbox");

		if (txt20b.getText().length() < 1 || txt20b.getText().length() > 15)
		{
			txt20b.setStyleName("admininvulbox_fouteinvoer");
			valid = false;
		} else
			txt20b.setStyleName("admininvulbox");

		return valid;
	}

}
