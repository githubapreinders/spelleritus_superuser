package afr.tafeltrainer3.shared;



import com.google.gwt.user.client.rpc.AsyncCallback;

public interface SimpleServiceAsync  {
	
	public void sendPw(String emailadress,@SuppressWarnings("rawtypes") AsyncCallback callback);
	
	public void sendAnotherVerificationMail(SuperUser superuser,@SuppressWarnings("rawtypes") AsyncCallback callback);

	public void verifyMailadress(String parameter,@SuppressWarnings("rawtypes") AsyncCallback callback);
	
	void sendMail(SuperUser superuser, @SuppressWarnings("rawtypes") AsyncCallback callback);
	
	public void superuserUpdatesUser(User user, @SuppressWarnings("rawtypes") AsyncCallback callback);
	
	public void updateSuperUser(SuperUser superuser, String oldemail,	@SuppressWarnings("rawtypes") AsyncCallback callback);
	
	public void retrieveSuperUser(String loginname, String passw,	@SuppressWarnings("rawtypes") AsyncCallback callback);
	
	public void addNewSuperUser(SuperUser superuser,@SuppressWarnings("rawtypes") AsyncCallback callback);
	
	public void getGroup(SuperUser superuser,@SuppressWarnings("rawtypes") AsyncCallback callback);
	
	public void addNewUser(User user, @SuppressWarnings("rawtypes") AsyncCallback callback);
	
	public void retrieveUser(String loginname,String passw, @SuppressWarnings("rawtypes") AsyncCallback callback);
	
	public void getUser(String loginname,String passw, @SuppressWarnings("rawtypes") AsyncCallback callback);
	
	public void deleteUser(User user,@SuppressWarnings("rawtypes")AsyncCallback callback);

	public void getWps(SuperUser superuser,@SuppressWarnings("rawtypes")AsyncCallback callback);

	public void updateUser(User user, @SuppressWarnings("rawtypes")AsyncCallback callback);
	
	public void updateWoordpakket(Woordpakket wp, SuperUser su,@SuppressWarnings("rawtypes")AsyncCallback callback);
	



	



	



}
