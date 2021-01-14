
public class AI {
    private int Guess;
    //Purpose: To calculate a guess spot for the ball
    AI() {
        



    }
    public int getGuess(int xD, int yD, int bX, int bY) {
        bY += 251;
        for(int i = 0; i <= 740; i++)
            {
                bY+=yD;
                
                
                if (bY <= 0 || bY >= 540) {
                    yD = -yD;
            }
        }
            Guess =  (540 - bY);
        return Guess;
        
    }

}
