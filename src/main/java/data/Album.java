package data;

public class Album {
	public String name;
	public int approvalLevel;

	public Album() {
		name = "";
		approvalLevel = 1;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getApprovalLevel()
	{
		return approvalLevel;
	}

	public void setApprovalLevel(int approvalLevel)
	{
		this.approvalLevel = approvalLevel;
	}
	
	
}