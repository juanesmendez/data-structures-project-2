package model.vo;


public class Company implements Comparable<Company>{
	private String name;

	public Company(String name) {
		this.name = name;
	}

	public String getName() {
		return name;

	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(Company comp) {
		// TODO Auto-generated method stub
		if(this.name.compareTo(comp.getName()) == 0) {
			return 0;
		}else if(this.name.compareTo(comp.getName()) < 0) {
			return -1;
		}else if(this.name.compareTo(comp.getName()) > 0) {
			return 1;
		}

		return 0;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Company Name: "+this.name;
	}

}
