public class Ball {
    private static int ballPosX;
    private static int ballPosY;
    private static int ballDirX;
    private static int ballDirY; 
    private static int Guess;

    public void setX(int x) {
    ballPosX = x;
    }
    
    public int getX(){
    return ballPosX;
    }

    public void setY(int y) {
        ballPosY = y;
    }

    public int getY() {
        return ballPosY;
    }

    public int getGuess(int xD, int yD, int bX, int bY, int forloop) {
        for(int i = 0; i <= forloop; i++)
            {
                bX+=xD;
                bY+=yD;
                
                if (bX <= 0 || bX >= 760) {
                    xD = -xD;
                }
                if (bY <= 0 || bY >= 540) {
                    yD = -yD;
            }
        }
            Guess = bY;
        return Guess;
        
    }


}
