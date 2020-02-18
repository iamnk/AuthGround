package authenticator.core;

import Ulang.Password;

public class UserAuthData
{
	public String userName;

	public long userID;

	public Password passWord;

	public String email;

	public String FLI;

	public String SLI;

	public Integer authSource;

	public String appSalt;



	public void fillSessionData(String FLI, String SLI)
	{
		this.FLI = FLI;
		this.SLI = SLI;
	}

	public void fillSignUpData(String userName, Password password, String email, String appSalt)
	{
		this.passWord = password;
		this.email = email;
		this.userName = userName;
		this.appSalt = appSalt;
	}

	public void fillLoginData(String email, Password password, String appSalt)
	{
		this.email = email;
		this.passWord = password;
		this.appSalt = appSalt;
	}

	public void flushSensitiveData()
	{
		this.passWord = null;
		this.appSalt = null;
	}


}
