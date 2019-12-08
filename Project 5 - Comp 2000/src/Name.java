
public class Name implements Comparable<Name>{
	private String firstName;
	private String lastName;
	
	public Name(String firstName, String lastName) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
	}
	
	public Name(String name) {
		int splitPoint = 0;
		for(int i = 0; i < name.length(); i++) {
			if(name.charAt(i) == ' ') {
				splitPoint = i;
				break;
			}
		}
		if(splitPoint != 0) {
			setFirstName(name.substring(0, splitPoint));
			setLastName(name.substring(splitPoint+1));
		}else {
			setFirstName(name);
		}
	}

	@Override
	public int compareTo(Name n) {
		return firstName.compareTo(n.getFirstName());
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String toString() {
		return String.format("%s %s", firstName, lastName);
	}
	
	
}
