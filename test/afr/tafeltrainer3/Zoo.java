package afr.tafeltrainer3;

import java.util.ArrayList;

public class Zoo
{
	public  ArrayList<Animal> oldlist = new ArrayList<Animal>();;
	public  ArrayList<Animal> newlist = new ArrayList<Animal>();
	int goats, wolves, lions;

	public void init(int goats, int wolves, int lions)
	{
		
		this.goats = goats;
		this.wolves = wolves;
		this.lions = lions;
		
		for (int i = 0; i <goats; i++)
		{
			oldlist.add(new Goat());
		}
		for (int i = 0; i < wolves; i++)
		{
			oldlist.add(new Wolf());
		}
		for (int i = 0; i < lions; i++)
		{
			oldlist.add(new Lion());
		}
		

		while ((goats > 0 && wolves > 0) || (goats > 0 && lions > 0))
		{
			for (Animal a : oldlist)
			{
				System.out.println("geiten: " + goats + " wolven: " + wolves + " leeuwen:" + lions);
				if (a instanceof Wolf && goats > 0)
				{
					newlist.add(new Lion());
					goats--;
				}
				if (a instanceof Lion && goats == 0 && wolves > 0)
				{
					newlist.add(new Goat());
					wolves--;
				}
				if (a instanceof Lion && goats > 0)
				{
					newlist.add(new Wolf());
					goats--;
				}
			}

			oldlist.clear();
			for (Animal a : newlist)
			{
				oldlist.add(a);
			}
			newlist.clear();
		}

	}
	
}
