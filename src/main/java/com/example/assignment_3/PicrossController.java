package com.example.assignment_3;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

/**
 * Controller between the GUI and rest of code
 * @author Dylan Gomez, edited by CPR
 */
public class PicrossController implements Initializable {

    @FXML
    private Label label_2, label_3, label_4, label_5, label_6, label_a, label_b, label_c, label_d, label_e, nameofFile;

    @FXML
    private Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16, b17, b18, b19, b20, b21, b22, b23, b24, b25;

    final String ON_COLOR = "-fx-background-color: black";
    final String OFF_COLOR = "-fx-background-color: white";

    @FXML
    private GridPane grid;

    @FXML
    /**
     * Selects a puzzle and shows the solution, also prints the title
     */
    protected void showSolution() {
        //creates objects
        PicrossPuzzlePool pool1 = new PicrossPuzzlePool(Paths.get(".\\Data"));
        PicrossPuzzle puzzle1 = pool1.getRandomPuzzle();
        //prints file name in gui
        String currentfile = pool1.getFile();
        nameofFile.setText(currentfile.substring(0,currentfile.length()-4));

        String[] rowClues = puzzle1.getRowClues();
        String[] colClues = puzzle1.getColumnClues();
        System.out.println();
        System.out.println(puzzle1);

        //Prints the puzzle's solution to standard output for debugging purposes
        System.out.println("ROW CLUES: ");
        for(int i=0;i< rowClues.length;i++)
            System.out.print("\""+rowClues[i]+"\" ");
        System.out.println("\nCOLUMN CLUES: ");
        for(int i=0;i< colClues.length;i++)
            System.out.print("\""+colClues[i]+"\" ");
        System.out.println();

        //Place column clues on GUI
        label_a.setText(colClues[0]+" ");
        label_b.setText(colClues[1]+" ");
        label_c.setText(colClues[2]+" ");
        label_d.setText(colClues[3]+" ");
        label_e.setText(colClues[4]+" ");

        //Place row clues on GUI
        label_2.setText(rowClues[0]+" ");
        label_3.setText(rowClues[1]+" ");
        label_4.setText(rowClues[2]+" ");
        label_5.setText(rowClues[3]+" ");
        label_6.setText(rowClues[4]+" ");

        //Set buttons on the gridpane to either black or white
        //depending on the puzzle's solution array
        setButton(b1, puzzle1.getValue(0, 0));
        setButton(b2, puzzle1.getValue(0, 1));
        setButton(b3, puzzle1.getValue(0, 2));
        setButton(b4, puzzle1.getValue(0, 3));
        setButton(b5, puzzle1.getValue(0, 4));
        setButton(b6, puzzle1.getValue(1, 0));
        setButton(b7, puzzle1.getValue(1, 1));
        setButton(b8, puzzle1.getValue(1, 2));
        setButton(b9, puzzle1.getValue(1, 3));
        setButton(b10, puzzle1.getValue(1, 4));
        setButton(b11, puzzle1.getValue(2, 0));
        setButton(b12, puzzle1.getValue(2, 1));
        setButton(b13, puzzle1.getValue(2, 2));
        setButton(b14, puzzle1.getValue(2, 3));
        setButton(b15, puzzle1.getValue(2, 4));
        setButton(b16, puzzle1.getValue(3, 0));
        setButton(b17, puzzle1.getValue(3, 1));
        setButton(b18, puzzle1.getValue(3, 2));
        setButton(b19, puzzle1.getValue(3, 3));
        setButton(b20, puzzle1.getValue(3, 4));
        setButton(b21, puzzle1.getValue(4, 0));
        setButton(b22, puzzle1.getValue(4, 1));
        setButton(b23, puzzle1.getValue(4, 2));
        setButton(b24, puzzle1.getValue(4, 3));
        setButton(b25, puzzle1.getValue(4, 4));
    }

    /**
     * Quits/stops the program
     */
    public void quit(){
        System.exit(0);
    }

    /**
     * Changes the background color of a button (i.e., pixel on the puzzle)
     * @param b the button to change
     * @param value 0 == OFF, 1 == ON
     */
    public void setButton (Button b, int value) {
        b.setStyle(value == 0 ? OFF_COLOR : ON_COLOR);
    }

    @Override
    /**
     * Acts as the start point for the Controller and shows a puzzle's solution
     */
    public void initialize (URL url, ResourceBundle rb) {
       // showSolution();
        Button b;
        EventHandler<ActionEvent> event;


        for (int col = 1; col < 6; col++) {
            for (int row = 1; row < 6; row++) {
                int i = col*6 + row;
                b = (Button)(grid.getChildren().get(i));

                b.setText("");

                event = new CellEventHandler(b);

                b.setOnAction(event);

            }
        }

    }



    private class CellEventHandler implements EventHandler<ActionEvent> {

        private final Button B;
        public CellEventHandler(Button b) {
            B = b;
        }

        @Override
        public void handle(ActionEvent actionEvent) {
        int row = (grid.getRowIndex(B)==null) ? 0 : grid.getRowIndex(B);
        int col = (grid.getColumnIndex(B)==null) ? 0 : grid.getColumnIndex(B);


        System.out.println("You clicked row " + row + " and columnn " + col);
        }
    }

}