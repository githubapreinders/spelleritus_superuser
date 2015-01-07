package afr.tafeltrainer3.client;

import afr.tafeltrainer3.client.MainView;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class Tafeltrainer3 implements EntryPoint
{
	
	public void onModuleLoad() 
	{
		//Devicetype d = (Devicetype)GWT.create(Devicetype.class);
		MainView page = new MainView();
		RootPanel.get().add(page);
	}

}
