package cricket;

public class Player {
     
	private int playerId;
	private String playerName;
	private String state;
	private String phone;
	private Roles roles;
	
	Player(int id,String name, String states, String phoneNo,Roles role){
		
		playerId = id;
		playerName = name;
		state = states;
		phone = phoneNo;
		setRoles(role);
		
	}
	

	public Player(){}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	

	public Roles getRoles() {
		return roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}
	
	public String toString() {
		if(state.length()<10 || playerName.length()<10) {
			state += "\s\s\s";
			playerName += "\s\s\s";
		}
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------\n");
		return "PlayerId : "+playerId+"\tPlayer Name : "+playerName+"\tState : "+state+"\tPhone No : "+phone+"\t\tRole : "+roles+"\n";
	}
}
