package pojo;

import java.io.Serializable;

public class Entity implements Serializable{

	private static final long serialVersionUID = 1L;
	private String name;
	private int status;
	
	public Entity() {
		
	}
	
	public Entity(String name, int status) {
		this.name = name;
		this.status = status;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
