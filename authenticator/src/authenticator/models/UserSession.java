package authenticator.models;

import javax.persistence.Entity;

@Entity
public class UserSession
{
	public Long sessionID;

	public Long UserID;

	public String FLI;

	public String SLI;

	public Long expiryTime;

}
