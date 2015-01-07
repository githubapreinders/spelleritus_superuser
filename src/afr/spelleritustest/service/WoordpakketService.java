package afr.spelleritustest.service;

import java.util.ArrayList;
import java.util.List;

import afr.spelleritustest.entity.Woordpakket;

public class WoordpakketService {

	public static List<Woordpakket> woordpakketten = new ArrayList<Woordpakket>();

	public Woordpakket addWoordpakket(Integer id, String description, String contents) throws Exception {
		//Check for already exists
		int index = woordpakketten.indexOf(new Woordpakket((long)id));
		if (index != -1) throw new Exception("Woordpakket Record already exists");
		Woordpakket wp = new Woordpakket((long)id, description, contents);
		woordpakketten.add(wp);
		return wp;
	}

	public Woordpakket updateWoordpakket(Woordpakket wp) throws Exception {
		int index = woordpakketten.indexOf(wp);
		if (index == -1) throw new Exception("Woordpakket Record does not exist");
		Woordpakket currentWoordpakket = woordpakketten.get(index);
		currentWoordpakket.setDescription(wp.getDescription());
		currentWoordpakket.setContents(wp.getContents());
		return wp;
	}

	public List<Woordpakket> searchWoordpakket(String searchterm)throws Exception
	{
		if(searchterm.length()<3)throw new Exception("Zoekterm is te kort");
		
		ArrayList<Woordpakket> results = new ArrayList<Woordpakket>();
		for(Woordpakket wp : woordpakketten)
		{
			String string = wp.getDescription()+wp.getContents();
			if(string.toLowerCase().contains(searchterm))
			{
				results.add(wp);
			}
		}
		
		
		return results;
	}
	
	public void removeWoordpakket(Integer id) throws Exception {
		int index = woordpakketten.indexOf(new Woordpakket((long)id));
		if (index == -1)
			throw new Exception("Woordpakket Record does not exist");
		woordpakketten.remove(index);
	}

	public List<Woordpakket> getWoordpakkets() {
		return woordpakketten;
	}

	public List<Woordpakket> getWoordpakketsBydescription(String description) {
		List<Woordpakket> results = new ArrayList<Woordpakket>();
		for (Woordpakket Woordpakket : woordpakketten) {
			if (Woordpakket.getDescription().indexOf(description) != -1) {
				results.add(Woordpakket);
			}
		}
		return results;
	}

	public Woordpakket getWoordpakket(Integer id) throws Exception {
		int index = woordpakketten.indexOf(new Woordpakket((long)id));
		if (index == -1)
			throw new Exception("Woordpakket Record does not exist");
		return woordpakketten.get(index);
	}

}
