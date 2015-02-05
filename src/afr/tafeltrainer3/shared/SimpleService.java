package afr.tafeltrainer3.shared;
import afr.tafeltrainer3.client.events.DataEvent;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("simpleservice")

public interface SimpleService extends RemoteService {

	public void sendPw(String emailadress);
	
	public void sendAnotherVerificationMail(SuperUser superuser);
	
	public void updateUser(User user);
	
	DataEvent verifyMailadress(String parameter);
	
	public void sendMail(SuperUser superuser);
	
	void superuserUpdatesUser(User user);
	
	void updateSuperUser(SuperUser superuser, String oldemail);
	
	DataEvent getWps(SuperUser superuser);
	
	DataEvent retrieveSuperUser(String loginname, String passw);
	
	DataEvent addNewSuperUser(SuperUser superuser);
	
	DataEvent getGroup(SuperUser superuser);
	
	void deleteUser(User user);
	
	DataEvent addNewUser(User user);
	
	DataEvent retrieveUser(String loginname,String passw);
	
	void updateWoordpakket(Woordpakket wp, SuperUser su);
	
	User getUser(String loginname,String passw);
	
	
	

	

}
