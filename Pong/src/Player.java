
public class Player {
	
	
	private int playerXPos;
	private int playerYPos;
	
	public int move(String d) {
		
		if(d.equalsIgnoreCase("up"))
		{
			if(!(playerYPos<0))
			playerYPos -= 80;
		
			return playerYPos;
		}
		if(d.equalsIgnoreCase("down"))
		{
			if(!(playerYPos>400))
			playerYPos+= 80;
		
		}
		return playerYPos;
	}//for AI
	public void setXPos(int pos) {
	pos = playerXPos;
	}
	
	public int getXPos() {
		return playerXPos;
	}
	public int getYPos() {
		return playerYPos;
	}
	
	public void setYPos(int pos) {
	pos = playerYPos;
	}
}