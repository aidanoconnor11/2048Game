package cs;
public class Driver {
    public static void main(String[] args)
    {
        Game g = new Game();   //BACK END game logic and state
        GUI myGUI= new GUI(g); //FRONT END game GUI
    }
}
