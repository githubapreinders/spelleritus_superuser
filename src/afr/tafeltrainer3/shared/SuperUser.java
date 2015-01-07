package afr.tafeltrainer3.shared;

import java.io.Serializable;
import java.util.ArrayList;

public class SuperUser implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	String name;
	String email;
	String password;
	int emailfrequency;
	String verificationcode;
	String packages;
	String sendaddress;
	Boolean verified;
	ArrayList <String> groups;
	
	public SuperUser()
	{
		
	}
	
	
	

	public SuperUser(String email, String password)
	
	{
		this.email = email;
		this.password = password;
		groups = new ArrayList<String>();
	}

	public SuperUser(String name,String email, String password)
	
	{
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
public SuperUser(String name,String email, String password, int emailfrequency)
	
	{
		this.name = name;
		this.email = email;
		this.password = password;
		this.emailfrequency = emailfrequency;
	}

public SuperUser(String name,String email, String password, int emailfrequency,boolean verified)

{
	this.name = name;
	this.email = email;
	this.password = password;
	this.emailfrequency = emailfrequency;
	this.verified = verified;
}
	
public SuperUser(String name,String email, String password, int emailfrequency,String packages,boolean verified)

{
	this.name = name;
	this.email = email;
	this.password = password;
	this.emailfrequency = emailfrequency;
	this.packages = packages;
	this.verified = verified;
}

	public String toString()
	{
		String returnstring = this.getName() + " ," + this.email + " ," + this.password ;
		return returnstring;
	}
	
	public String getVerificationcode() 
	{
		return verificationcode;
	}


	public void setVerificationcode(String verificationcode) 
	{
		this.verificationcode = verificationcode;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getEmailfrequency() {
		return emailfrequency;
	}

	public void setEmailfrequency(int emailfrequency) 
	{
		this.emailfrequency = emailfrequency;
	}

	public ArrayList<String> getGroups() {
		return groups;
	}

	public void setGroups(String groep) {
		this.groups.add(groep);
	}


	public Boolean getVerified()
	{
		return verified;
	}


	public void setVerified(Boolean verified)
	{
		this.verified = verified;
	}

	public String getPackages()
	{
		return packages;
	}


	public void setPackages(String packages)
	{
		this.packages = packages;
	}


	public String getSendaddress()
	{
		return sendaddress;
	}


	public void setSendaddress(String sendaddress)
	{
		this.sendaddress = sendaddress;
	}


	
}
