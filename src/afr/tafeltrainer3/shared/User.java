package afr.tafeltrainer3.shared;


import com.google.gwt.user.client.rpc.IsSerializable;

public class User implements IsSerializable
{	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2767767209234983481L;
	int id;
	String emailsuperuser;
	String name;
	String familyname;
	String groupname;
	String loginname;
	String password;
	int currentpackage;
	int extrapackage;
	int money;
	boolean houindegaten;
	
	
	public User()
	{
		this.id =1;
		this.emailsuperuser = "default@nothing.com";
		this.name = "guestuser";
		this.familyname = "deffamname";
		this.groupname = "defgroupname";
		this.loginname = "deflogin";
		this.password = "defpassw";
		this.money = 0;
		this.houindegaten = false;
		this.currentpackage = 0;
		this.extrapackage = 0;
		
		
	}

	public User(int id, String emailsuperuser, String name, String familyname,
			 String loginname, String password,  boolean houindegaten) 
	{
		this.id = id;
		this.emailsuperuser = emailsuperuser;
		this.name = name;
		this.familyname = familyname;
		this.loginname = loginname;
		this.password = password;
		this.houindegaten=houindegaten;
	}
	
	public User(int id, String emailsuperuser, String name, String familyname,
			 String loginname, String password,  boolean houindegaten,int currentwp,int extrawp) 
	{
		this.id = id;
		this.emailsuperuser = emailsuperuser;
		this.name = name;
		this.familyname = familyname;
		this.loginname = loginname;
		this.password = password;
		this.houindegaten=houindegaten;
		this.currentpackage = currentwp;
		this.extrapackage = extrawp;
		
	}

	
	
	
	
	
	public int getCurrentwp()
	{
		return currentpackage;
	}

	public int getExtrawp()
	{
		return extrapackage;
	}

	public void setCurrentwp(int currentwp)
	{
		this.currentpackage = currentwp;
	}


	public void setExtrawp(int extrawp)
	{
		this.extrapackage = extrawp;
	}

	public void addMoney(int money)
	{
		this.money+=money;
	}

	public String toString()
	{
		String returnstring = this.name + " " + this.familyname + ", "+
						" loginnaam: " + this.loginname + " ww: " + this.password ;
		return returnstring;
	}
	
	public boolean getHouindegaten() 
	{
		return houindegaten;
	}

	public void setHouindegaten(boolean houindegaten) 
	{
		this.houindegaten = houindegaten;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFamilyname() {
		return familyname;
	}

	public void setFamilyname(String familyname) {
		this.familyname = familyname;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}


	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
		
	public String getEmailsuperuser() {
		return emailsuperuser;
	}
	
	public void setEmailsuperuser(String emailsuperuser) {
		this.emailsuperuser = emailsuperuser;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

}

