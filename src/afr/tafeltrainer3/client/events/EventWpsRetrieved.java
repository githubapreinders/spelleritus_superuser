package afr.tafeltrainer3.client.events;

import java.io.Serializable;
import java.util.ArrayList;

import afr.tafeltrainer3.shared.Woordpakket;

public class EventWpsRetrieved extends DataEvent implements Serializable

{

	private static final long serialVersionUID = 1L;
	public ArrayList<Woordpakket> wp;

	public ArrayList<Woordpakket> getWp()
	{
		return wp;
	}

	public void setWp(ArrayList<Woordpakket> wp)
	{
		this.wp = wp;
	}

}
