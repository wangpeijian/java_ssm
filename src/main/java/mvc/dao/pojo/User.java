package mvc.dao.pojo;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * Created by dx on 17-1-4.
 */
public class User implements Serializable {
	private int ID;
	
	private String Name;
	
	private String Age;
	
	private String Sex;
	
	public User(){
	
	}
	
	public User(Integer ID, String Name, String Age, String Sex){
		this.ID = ID;
		this.Name = Name;
		this.Age = Age;
		this.Sex = Sex;
	}
	
	public User(String Name, String Age, String Sex){
		this.Name = Name;
		this.Age = Age;
		this.Sex = Sex;
	}
	
	public int getID() {
		return ID;
	}
	
	public void setID(int ID) {
		this.ID = ID;
	}
	
	public String getName() {
		return Name;
	}
	
	public void setName(String name) {
		Name = name;
	}
	
	public String getAge() {
		return Age;
	}
	
	public void setAge(String age) {
		Age = age;
	}
	
	public String getSex() {
		return Sex;
	}
	
	public void setSex(String sex) {
		Sex = sex;
	}
	
	@Override
	public String toString()
	{
		return JSON.toJSONString(this);
	}
}
