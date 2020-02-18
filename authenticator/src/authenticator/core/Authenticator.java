package authenticator.core;

import java.util.List;

import com.google.common.collect.ImmutableList;

public class Authenticator
{
	private static final List<Auth> authenticators = new ImmutableList.Builder<Auth>().
		add(new SessionAuth()).build();


	public Authenticator()
	{

	}

	public static UserAuthData authenticate(UserAuthData userAuthData) throws Exception
	{
		Credential credential = null;
		boolean authenticated = false;

		for(Auth auth : authenticators)
		{
			auth.authenticate(userAuthData);

			if(userAuthData.authSource != null)
			{
				auth.afterAuth(userAuthData);
				break;
			}
		}

		return userAuthData;
	}

	public static void authenticate(UserAuthData userAuthData, Integer authID) throws Exception
	{

		Auth auth = findAuth(authID);

		if(auth == null)
		{
			throw new Exception("invalid Auth ID");
		}

		auth.authenticate(userAuthData);
		auth.afterAuth(userAuthData);
	}

	public static void signUp(UserAuthData userAuthData, Integer authID) throws Exception
	{

		StarAuth starAuth = getStarAuth(authID);
		starAuth.signUp(userAuthData);

		starAuth.afterAuth(userAuthData);

	}

	public static void resetPassword(UserAuthData authData, Integer authID) throws Exception
	{
		StarAuth  starAuth = getStarAuth(authID);

	}

	public static Auth findAuth(int authID)
	{
		return authenticators.stream().filter(auth1 -> auth1.getId() == authID).findFirst().get();
	}

	public static boolean isStarAuth(Auth auth)
	{
		return auth instanceof StarAuth;
	}

	public static StarAuth getStarAuth(int authID) throws Exception
	{
		Auth auth = findAuth(authID);

		if(auth == null)
		{
			throw new Exception("Invalid auth ID");
		}

		if(isStarAuth(auth) )
		{
			throw new Exception("This auth not perform signUp service");
		}

		return (StarAuth) auth;

	}


}
