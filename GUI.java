package cs;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUI extends JFrame implements KeyListener, ActionListener {
    /** The 2048 Game BACK END */
    private Game myGame;
    /** The size of the square board */
    private final int SIZE= 4;
    /** The main panel containing the game components. */
    private JPanel panel;
    /** The game board displaying point values. */
    private JLabel[][] board;
    /** Displays the points and number of moves */
    private JLabel info;
    /** The title or "game over". */
    private JLabel title;
    /** Button to start over. */
    private JButton startOver;

    private int buttonLength= 100;
    private int buttonWidth= 100;

    public GUI(Game g) {
        super("Java 2048");
        myGame= g;
        initAndBuildGUI();
    }

    public void initAndBuildGUI() {
        // Program will end if frame is closed
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setSize(600, 700);

        panel= new JPanel();
        panel.setLayout(null);

        panel.setBackground(Color.DARK_GRAY);

        title= new JLabel();

        // create the panel for the game title
        panel.add(title);
        title.setBounds(150, 20, 300, 80);
        title.setOpaque(true);
        title.setBackground(Color.GRAY);
        title.setHorizontalAlignment(info.CENTER);
        title.setVerticalAlignment(info.CENTER);
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 40));
        title.setText("APCS 2048");

        // create the 2-dimensional board array to display the board values
        board= new JLabel[SIZE][SIZE];
        for (int r= 0; r < board.length; r++ ) {
            for (int c= 0; c < board[0].length; c++ ) {
                board[r][c]= new JLabel();
                panel.add(board[r][c]);
                board[r][c].setBounds(buttonWidth * c + 100, buttonLength * r + 100, buttonWidth,
                    buttonLength);
                board[r][c].setFont(new Font(board[r][c].getFont().getName(), Font.PLAIN, 20));
                board[r][c].setOpaque(true);
                board[r][c].setHorizontalAlignment(board[r][c].CENTER);
                board[r][c].setVerticalAlignment(board[r][c].CENTER);
                board[r][c].setBackground(Color.GRAY);
                board[r][c].setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
                int val= myGame.getTile(r, c);
                if (val == 0)
                    board[r][c].setText("");
                else
                    board[r][c].setText("" + val);
            }
        }

        // create the label to display score and turns left
        info= new JLabel();

        panel.add(info);
        info.setBounds(100, 500, 400, 50);
        info.setOpaque(true);
        info.setBackground(Color.GRAY);
        info.setHorizontalAlignment(info.CENTER);
        info.setVerticalAlignment(info.CENTER);
        info.setFont(new Font(info.getFont().getName(), Font.BOLD, 20));
        info.setText("POINTS: " + myGame.getPoints() + "       MOVES: " + myGame.getMoves());

        // create the button to restart the game
        startOver= new JButton();
        panel.add(startOver);
        startOver.setBounds(225, 575, 150, 50);
        startOver.setBackground(Color.gray);
        startOver.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
        startOver.setFont(new Font("startOver", Font.BOLD, 20));
        startOver.setText("START OVER");
        startOver.addActionListener(this);

        panel.setFocusable(true);
        panel.requestFocusInWindow();
        panel.addKeyListener(this);

        this.add(panel);
        setVisible(true);
        setResizable(false);

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode= e.getKeyCode();
        switch (keyCode) {
        case KeyEvent.VK_UP:
            myGame.moveUp();
            break;
        case KeyEvent.VK_DOWN:
            myGame.moveDown();
            break;
        case KeyEvent.VK_LEFT:
            myGame.moveLeft();
            break;
        case KeyEvent.VK_RIGHT:
            myGame.moveRight();
            break;
        }
        redraw();
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // reset for new game
        myGame.initBoard();
        title.setText("2048");

        // redraw board labels with new values
        redraw();
        panel.requestFocusInWindow();
    }

    private void redraw() {
        // updates board vals
        for (int r= 0; r < board.length; r++ ) {
            for (int c= 0; c < board[0].length; c++ ) {
                int val= myGame.getTile(r, c);
                if (val == 0)
                    board[r][c].setText("");
                else
                    board[r][c].setText("" + val);
            }
        }

        // updates title if game is lost
        if (!myGame.canMove())
            title.setText("GAME OVER");

        if (myGame.hasWon())
            title.setText("Nice.");

        // updates score and moves labels
        info.setText("POINTS: " + myGame.getPoints() + "       MOVES: " + myGame.getMoves());
    }
}
