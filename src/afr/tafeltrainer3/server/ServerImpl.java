package afr.tafeltrainer3.server;

import afr.tafeltrainer3.client.events.DataEvent;
import afr.tafeltrainer3.client.events.EventAddSuperUser;
import afr.tafeltrainer3.client.events.EventGetGroup;
import afr.tafeltrainer3.client.events.EventSuperUserRetrieved;
import afr.tafeltrainer3.client.events.EventUserNew;
import afr.tafeltrainer3.client.events.EventUserRetrieved;
import afr.tafeltrainer3.client.events.EventVerifyMail;
import afr.tafeltrainer3.client.events.EventWpsRetrieved;
import afr.tafeltrainer3.shared.SimpleService;
import afr.tafeltrainer3.shared.SuperUser;
import afr.tafeltrainer3.shared.User;
import afr.tafeltrainer3.shared.Woordpakket;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class ServerImpl extends RemoteServiceServlet implements SimpleService
{

	private static final long serialVersionUID = -8441122045657762850L;

	private MySQLAccess mySQLAccess = new MySQLAccess();

	public ServerImpl()
	{

	}

	@Override
	public void sendPw(String emailadress)
	{
		SuperUser su = mySQLAccess.findPw(emailadress);
		@SuppressWarnings("unused")
		SendMessage me = new SendMessage(su.getName(), su.getEmail(), su.getPassword());
	}

	@Override
	public void sendAnotherVerificationMail(SuperUser superuser)
	{
		Encrypter encrypter = Encrypter.getInstance();
		String verificationcode = encrypter.encrypt(superuser.getName());
		String passw = encrypter.encrypt(superuser.getPassword());
		superuser.setVerificationcode(verificationcode);
		superuser.setPassword(passw);
		sendVerificationMail mail = new sendVerificationMail(superuser);
		mail.sendMail("apreinders74@gmail.com", superuser.getEmail(), "apreinders74@gmail.com",
				"Verificatie van uw e-mailadres", mail.createMessage());
	}

	@Override
	public DataEvent verifyMailadress(String parameter)
	{
		String returnstring = "Helaas, verificatie was niet mogelijk...";
		EventVerifyMail evm = new EventVerifyMail();
		try
		{
			Boolean verified = mySQLAccess.verifyMailadress(parameter);
			if (verified)
				returnstring = "succes";
			evm.setParameter(returnstring);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return evm;
	}

	@Override
	public void sendMail(SuperUser superuser)
	{
		@SuppressWarnings("unused")
		SendMessage m = new SendMessage(superuser);
	}

	@Override
	public void superuserUpdatesUser(User user)
	{
		try
		{
			mySQLAccess.superuserUpdatesUser(user);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void updateUser(User user)
	{
		try
		{
			mySQLAccess.updateUser(user);
		} catch (Exception e)
		{

		}
	}

	@Override
	public void updateSuperUser(SuperUser superuser, String oldemail)
	{
		try
		{
			mySQLAccess.updateSuperUser(superuser, oldemail);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return;
	}

	// checkt validiteit van de hoofdgebruiker
	@Override
	public DataEvent retrieveSuperUser(String loginname, String passw)
	{
		EventSuperUserRetrieved ersu = new EventSuperUserRetrieved();
		try
		{
			SuperUser su = mySQLAccess.retrieveSuperUser(loginname, passw);
			ersu.setSuperuser(su);
			return ersu;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return ersu;
	}

	// maakt een nieuwe hoofdgebruiker/superuser aan
	@Override
	public DataEvent addNewSuperUser(SuperUser superuser)
	{
		EventAddSuperUser easu = new EventAddSuperUser();
		Encrypter encrypter = Encrypter.getInstance();
		String verificationcode = encrypter.encrypt(superuser.getName());
		String passw = encrypter.encrypt(superuser.getPassword());
		superuser.setVerificationcode(verificationcode);
		superuser.setPassword(passw);
		sendVerificationMail mail = new sendVerificationMail(superuser);
		mail.sendMail("apreinders74@gmail.com", superuser.getEmail(), "apreinders74@gmail.com",
				"Verificatie van uw e-mailadres", mail.createMessage());

		try
		{
			SuperUser su = mySQLAccess.addNewSuperUser(superuser);
			easu.setSuperuser(su);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return easu;
	}

	@Override
	public DataEvent getWps(SuperUser superuser)
	{
		EventWpsRetrieved ewp = new EventWpsRetrieved();
		try
		{
			ewp.setWp(mySQLAccess.retrieveWps(superuser));
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return ewp;
	}

	// haalt de naw groepsgegevens uit de db
	@Override
	public DataEvent getGroup(SuperUser superuser)
	{

		EventGetGroup egg = new EventGetGroup();
		try
		{
			egg.setUsers(mySQLAccess.getGroup(superuser));
			return egg;
		} catch (Exception e)
		{

		}
		return egg;
	}

	// voegt een nieuwe user toe in de database;
	@Override
	public DataEvent addNewUser(User user)
	{
		EventUserNew eun = new EventUserNew();
		try
		{
			User newuser = mySQLAccess.addNewUser(user);
			eun.setUser(newuser);
			return eun;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	// zoekt een user op bij een inlogpoging
	@Override
	public DataEvent retrieveUser(String loginname, String passw)
	{

		EventUserRetrieved eur = new EventUserRetrieved();
		try
		{
			mySQLAccess = new MySQLAccess();
			User user = mySQLAccess.getUser(loginname, passw);
			eur.setUser(user);
			return eur;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return eur;
	}

	// haalt een user uit de db en deleted alle bijbehorende records in de db
	@Override
	public void deleteUser(User user)
	{
		try
		{

			if (user != null)
			{
				mySQLAccess.deleteUser(user);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	// checkt de user bij login en geeft onthouden gegevens mee terug
	@Override
	public User getUser(String loginname, String passw)
	{
		try
		{
			User user = mySQLAccess.getUser(loginname, passw);
			return user;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateWoordpakket(Woordpakket wp, SuperUser su)
	{
		try
		{
			@SuppressWarnings("unused")
			boolean succes = mySQLAccess.updateWoordpakket(wp, su);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

}
