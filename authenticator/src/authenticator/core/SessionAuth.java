package authenticator.core;

public class SessionAuth implements Auth
{

	public static final int ID = 1;

	@Override public int getId()
	{
		return ID;
	}

	@Override public boolean isAuthenticated()
	{
		return false;
	}

	@Override public boolean getCredentials()
	{
		return false;
	}

	/**
	 * UserAuthData mandatory fields : FLI, SLI
	 *
	 * @param userAuthData
	 * @return
	 * @throws Exception -> if userAuthData.FLI or userAuthData.SLI is null, empty or invalid
	 */
	@Override
	public void authenticate(UserAuthData userAuthData) throws Exception
	{

		if(userAuthData.FLI == null || userAuthData.SLI == null)
		{
			//Must be redirect to login page.
			throw new Exception("No session found");
		}


		//search for session from DB
		//if not found then also throw error.


	}
}
