package authenticator.core;

public class Credential
{
	private final int authSource;
	private final String username;

	public Credential(int authSource, String username)
	{
		this.authSource = authSource;
		this.username = username;
	}

}
