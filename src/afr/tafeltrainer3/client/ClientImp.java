package afr.tafeltrainer3.client;

import afr.tafeltrainer3.client.events.DataEvent;
import afr.tafeltrainer3.shared.SimpleService;
import afr.tafeltrainer3.shared.SimpleServiceAsync;
import afr.tafeltrainer3.shared.SuperUser;
import afr.tafeltrainer3.shared.User;
import afr.tafeltrainer3.shared.Woordpakket;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Composite;

public class ClientImp extends Composite implements ClientImpInterface
{

	private SimpleServiceAsync service;
	private MainView main;

	public ClientImp()
	{

	}

	public ClientImp(String url, MainView main)
	{
		// creeert een instantie van de server/client verbinding
		this.service = GWT.create(SimpleService.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) this.service;
		endpoint.setServiceEntryPoint(url);
		this.main = main;

	}

	@Override
	public void sendPw(String emailadress)
	{
		this.service.sendPw(emailadress, new DefaultCallBack());
	}

	@Override
	public void sendAnotherVerificationMail(SuperUser superuser)
	{
		this.service.sendAnotherVerificationMail(superuser, new DefaultCallBack());
	}

	@Override
	public void verifyMailadress(String parameter)
	{
		this.service.verifyMailadress(parameter, new DefaultCallBack());
	}

	
	@Override
	public void sendMail(SuperUser superuser)
	{
		this.service.sendMail(superuser, new DefaultCallBack());

	}

	public void superuserUpdatesUser(User user)
	{
		this.service.superuserUpdatesUser(user, new DefaultCallBack());
	}

	@Override
	public void updateSuperUser(SuperUser superuser, String oldemail)
	{
		this.service.updateSuperUser(superuser, oldemail, new DefaultCallBack());
	}

	@Override
	public void getSuperUser(String loginname, String passw)
	{
		this.service.retrieveSuperUser(loginname, passw, new DefaultCallBack());
	}

	@Override
	public void addNewSuperUser(SuperUser superuser)
	{
		this.service.addNewSuperUser(superuser, new DefaultCallBack());
	}

	@Override
	public void getGroup(SuperUser superuser)
	{
		this.service.getGroup(superuser, new DefaultCallBack());
	}

	@Override
	public void deleteUser(User user)
	{
		this.service.deleteUser(user, new DefaultCallBack());
	}

	@Override
	public void addNewUser(User user)
	{
		this.service.addNewUser(user, new DefaultCallBack());
	}

	@Override
	public void retrieveUser(String loginname, String passw)
	{
		this.service.retrieveUser(loginname, passw, new DefaultCallBack());
	}

	
	// haalt de user uit de database
	@Override
	public void getUser(String loginname, String passw)
	{
		this.service.getUser(loginname, passw, new DefaultCallBack());
	}

	@Override
	public void getWps(SuperUser superuser)
	{
		this.service.getWps(superuser,new DefaultCallBack());
	}

	@Override
	public void updateUser(User user)
	{
		this.service.updateUser(user , new DefaultCallBack());
		
	}

	@Override
	public void updateWoordpakket(Woordpakket wp, SuperUser su)
	{
		this.service.updateWoordpakket(wp, su, new DefaultCallBack());
		
	}

	
	// sluist de antwoorden van de server door naar MainView
	@SuppressWarnings("rawtypes")
	private class DefaultCallBack implements AsyncCallback
	{

		@Override
		public void onFailure(Throwable caught)
		{
			System.out.print("foutje bedankt");
		}

		@Override
		public void onSuccess(Object result)
		{
			if (result instanceof DataEvent)
			{
				main.handleEvent((DataEvent) result);
			}

		}
	}



	


	

	
	

}
