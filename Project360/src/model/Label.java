package model;

public class Label {
	
	/** TODO */
	private String myName;
	
	public Label(final String theLableName) {
		myName = theLableName;
	}

	/** TODO */
	public String getMyName() {
		return myName;
	}

	/** TODO */
	public void setMyName(String myName) {
		this.myName = myName;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Label {\"");
		sb.append(myName);
		sb.append("\"}");
		return sb.toString();
	}
}
