package afr.tafeltrainer3.client;


import afr.tafeltrainer3.shared.SuperUser;
import afr.tafeltrainer3.shared.User;
import afr.tafeltrainer3.shared.Woordpakket;


public interface ClientImpInterface {

	public void sendPw(String emailadress);
	
	public void sendAnotherVerificationMail(SuperUser superuser);
	
	public void verifyMailadress(String parameter);
	
	public void sendMail(SuperUser superuser);
	
	public void superuserUpdatesUser(User user);
	
	public void updateSuperUser(SuperUser superUser, String oldemail);
	
	public void getSuperUser(String loginname, String passw);
	
	public void addNewSuperUser(SuperUser superuser);
	
	public void getGroup(SuperUser superuser);
	
	public void updateUser(User user);
	
	public void deleteUser(User user);
	
	public void addNewUser(User user);
	
	public void retrieveUser(String loginname,String passw);
	
	public void getUser(String loginname, String passw);
	
	public void getWps(SuperUser superuser);
	
	public void updateWoordpakket(Woordpakket wp, SuperUser su);
	
	
	


}
