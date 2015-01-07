package afr.tafeltrainer3.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Woordpakket implements IsSerializable
{
	//The internal id which is used in the db; 
	Long id;
	//The external identifier and how it is represented to the user
	String identifier;
	//Overall pattern of the wordpackage
	String description;
	//The actual words in the package with which the user will practice and interact
	String contents;

	public Woordpakket()
	{
	}

	public Woordpakket(Long id)
	{
		super();
		this.id = id;
	}

	public Woordpakket(Long id, String description, String contents)
	{
		super();
		this.id = id;
		this.description = description;
		this.contents = contents;
	}

	public Woordpakket(Long id, String identifier,String description, String contents)
	{
		super();
		this.id = id;
		this.identifier = identifier;
		this.description = description;
		this.contents = contents;
	}
	
	public Woordpakket(String identifier,String description, String contents)
	{
		super();
		this.identifier = identifier;
		this.description = description;
		this.contents = contents;
	}
	
	
	
	
	
	public String getIdentifier()
	{
		return identifier;
	}

	public void setIdentifier(String identifier)
	{
		this.identifier = identifier;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getContents()
	{
		return contents;
	}

	public void setContents(String contents)
	{
		this.contents = contents;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Woordpakket other = (Woordpakket) obj;
		if (id == null)
		{
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "Woordpakket [id=" + id + " ,code=" + identifier + ", description=" + description + ", contents=" + contents + "]";
	}

}
