package four;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ConnectFour extends JFrame {
    final int numRows = 6;
    final int numColumns = 7;
    String currentTurn = "X";
    String[]ColumnNames = {"A","B","C","D","E","F","G"};
    Button[][] cell = new Button[numRows][numColumns];
    boolean win = false;


    // Create a method when someone wins
    public void GameWon(){
        if (win) {
            DisableAllButtons();
            System.out.println("Congratulations player " + currentTurn + " you won the game!");
            JOptionPane.showMessageDialog(null, "Congratulations! " + currentTurn + " wins!");
        }
    }


    // create function that disables all buttons
    public void DisableAllButtons() {
        for (int i = 0; i < this.numRows; i++) {
            for (int j = 0; j < this.numColumns; j++) {
                this.cell[i][j].setEnabled(false);
            }
        }
    }


    public void EnableAllButtons() {
        for (int i = 0; i < this.numRows; i++) {
            for (int j = 0; j < this.numColumns; j++) {
                this.cell[i][j].setEnabled(true);
            }
        }
    }


    // create method that checks if there is a winner, with the use of methods that check all possible directions
    public void CheckWinner(int row, int column){
        CheckSelectedColumn(column);
        CheckSelectedRow(row);
        CheckDiagonal1();
        CheckDiagonal2();
        if (win) {
            GameWon();
            return;
        }
    }


    // create method that checks if there is a cell filled with X or O, when it finds one, it automatically checks for 4 in a row
    public void FindFilledCell(){
        for (int i = 0; i < numRows && !win; i++) {
            for (int j = 0; j < numColumns && !win; j++) {
                if (cell[i][j].getText().equals(currentTurn)) {
                    CheckWinner(i,j);
                }
            }
        }
    }


    // create method that checks the selected column for 4 X or O in a row vertically
    public void CheckSelectedColumn(int column) {
        for (int i = 0; i < numRows - 3; i++){
            if (cell[i][column].getText().equals(currentTurn)
                    && cell[i + 1][column].getText().equals(this.cell[i][column].getText())
                    && cell[i + 2][column].getText().equals(this.cell[i][column].getText())
                    && cell[i + 3][column].getText().equals(this.cell[i][column].getText()) ){

                cell[i][column].setBackground(Color.green);
                cell[i + 1][column].setBackground(Color.green);
                cell[i + 2][column].setBackground(Color.green);
                cell[i + 3][column].setBackground(Color.green);
                win = true;
            }
        }
    }


    // create method that checks the selected row for 4 X or O in a row horizontally
    public void CheckSelectedRow(int row) {
        for (int i = 0; i < numColumns - 3; i++){
            if (cell[row][i].getText().equals(currentTurn)
                    && cell[row][i + 1].getText().equals(this.cell[row][i].getText())
                    && cell[row][i + 2].getText().equals(this.cell[row][i].getText())
                    && cell[row][i + 3].getText().equals(this.cell[row][i].getText()) ){

                cell[row][i].setBackground(Color.green);
                cell[row][i + 1].setBackground(Color.green);
                cell[row][i + 2].setBackground(Color.green);
                cell[row][i + 3].setBackground(Color.green);
                win = true;
            }
        }
    }


    // Checks diagonal from top left to down right starts at (5,0) ends at (3,3)
    public void CheckDiagonal1() {
        for (int i = numRows - 1; i > 2; i--){
            for (int j = 0; j < numColumns - 3; j++){
                if (cell[i][j].getText().equals(currentTurn)
                        && cell[i - 1][j + 1].getText().equals(this.cell[i][j].getText())
                        && cell[i - 2][j + 2].getText().equals(this.cell[i][j].getText())
                        && cell[i - 3][j + 3].getText().equals(this.cell[i][j].getText())) {

                    cell[i][j].setBackground(Color.green);
                    cell[i - 1][j + 1].setBackground(Color.green);
                    cell[i - 2][j + 2].setBackground(Color.green);
                    cell[i - 3][j + 3].setBackground(Color.green);
                    win = true;
                }
            }
        }
    }


    // checks diagonal from bottom left (0,0) to top left ends at (2,3)
    public void CheckDiagonal2() {
        for (int i = 0; i < numRows - 3; i++) {
            for (int j = 0; j < numColumns - 3; j++) {
                if (cell[i][j].getText().equals(currentTurn)
                        && cell[i + 1][j + 1].getText().equals(this.cell[i][j].getText())
                        && cell[i + 2][j + 2].getText().equals(this.cell[i][j].getText())
                        && cell[i + 3][j + 3].getText().equals(this.cell[i][j].getText())) {

                    cell[i][j].setBackground(Color.green);
                    cell[i + 1][j + 1].setBackground(Color.green);
                    cell[i + 2][j + 2].setBackground(Color.green);
                    cell[i + 3][j + 3].setBackground(Color.green);
                    win = true;
                }
            }
        }
    }


    // Create method that clears all cells
    public void ResetGame() {
        for (int i = numRows -1; i >= 0; i--) {
            for (int j = 0; j < numColumns; j++) {
                cell[i][j].setText(" ");
                cell[i][j].setBackground(Color.white);
            }
        }
        currentTurn = "X";
        win = false;
        EnableAllButtons();
    }


    // Create constructor
    public ConnectFour() {

        //Create JPanel with a grid layout
        GridLayout gridLayout = new GridLayout(numRows, numColumns, 20, 20);
        JPanel ButtonPanel = new JPanel(gridLayout);
        ButtonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        EmptyChecker emptyChecker = new EmptyChecker(cell);

        // Create grid of empty JButtons
        for (int i = numRows - 1; i >= 0; i--) {
            for (int j = 0; j < numColumns; j++) {
                final int finalI = i;
                final int finalJ = j;
                cell[i][j] = new Button();
                cell[i][j].setName("Button" + ColumnNames[j] + (i + 1));
                cell[i][j].setText(" ");
                cell[i][j].setFont(new Font(cell[i][j].getFont().getName(), Font.PLAIN, 60));
                cell[i][j].setFocusPainted(false);
                ButtonPanel.setBackground(Color.blue);
                ButtonPanel.setOpaque(true);
                ButtonPanel.add(cell[i][j]);
                cell[i][j].setBackground(Color.white);
                cell[i][j].setOpaque(true);
                cell[i][j].setBorderPainted(true);
                cell[i][j].setBorder(BorderFactory.createLineBorder(Color.black, 5, false));


                // add action listener to the JButtons that alternates turns X and O
                cell[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        int firstEmptyRow = emptyChecker.CheckEmpty(finalI, finalJ);
                        if (firstEmptyRow == cell.length){
                            return;
                        }

                        if (currentTurn.equals("X") && cell[firstEmptyRow][finalJ].getText().equals(" ")) {
                            cell[firstEmptyRow][finalJ].setForeground(Color.red);
                            cell[firstEmptyRow][finalJ].setText(currentTurn);
                            FindFilledCell();
                            currentTurn = "O";

                        } else if (currentTurn.equals("O") && cell[firstEmptyRow][finalJ].getText().equals(" ")){
                            cell[firstEmptyRow][finalJ].setForeground(Color.yellow);
                            cell[firstEmptyRow][finalJ].setText(currentTurn);
                            FindFilledCell();
                            currentTurn = "X";
                        }
                    }
                });
            }
        }
        // printing the button names in console
        for (int i = numRows - 1; i >= 0; i--) {
            for (int j = 0; j < numColumns; j++) {
                System.out.print(cell[i][j].getName() + " ");
            }
            System.out.println();
        }


        // Create a new JPanel to add ResetButton without messing up the grid layout
        JPanel ResetButtonPanel = new JPanel();
        ResetButtonPanel.setBackground(Color.blue);
        ResetButtonPanel.setOpaque(true);
        ResetButtonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));


        // create a reset button
        Button resetButton = new Button();
        resetButton.setName("ButtonReset");
        resetButton.setText("RESET GAME");
        resetButton.setBackground(Color.white);
        resetButton.setOpaque(true);
        resetButton.setBorderPainted(true);
        resetButton.setPreferredSize(new Dimension(800,100));
        resetButton.setBorder(BorderFactory.createLineBorder(Color.black, 5, false));


        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                ResetGame();
            }
        });
        ResetButtonPanel.add(resetButton);


        // settings of the ConnectFour JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setTitle("Connect Four");
        add(ButtonPanel, BorderLayout.CENTER);
        add(ResetButtonPanel, BorderLayout.SOUTH);
        setVisible(true);
        setSize(830,950);
    }
}
