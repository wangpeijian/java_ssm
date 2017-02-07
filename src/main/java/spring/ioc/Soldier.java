package spring.ioc;

/**
 * Created by dx on 17-1-20.
 */
public class Soldier {
	
	private Saber saber;
	
	public void setSaber(Saber saber){
		this.saber = saber;
	}
	
	public void say(){
		System.out.println("i have " + saber.getName());
	}
	
}
