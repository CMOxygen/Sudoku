import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class SudokuMain {

    int gameSize = 9;

    JFrame frameMain;
    JPanel panelGameField;
    Box bottomBox;

    JButton buttonNewGame;

    ArrayList<ArrayList<JButton>> listButtons;
    ArrayList<ArrayList<String>> listButtonNames;
    ArrayList<JButton> listNumberButtons;

    public static void main(String[] args) {

        SudokuMain gameManager = new SudokuMain();
        gameManager.buildGui();
        gameManager.generateSudoku();
    }

    public void buildGui() {

        GridLayout layoutGrid = new GridLayout(9, 9, 2, 2);
        frameMain = new JFrame();
        panelGameField = new JPanel(layoutGrid);

        buttonNewGame = new JButton("New Game");

        listButtons = new ArrayList<ArrayList<JButton>>();
        listNumberButtons = new ArrayList<JButton>();

        bottomBox = new Box(BoxLayout.X_AXIS);

        bottomBox.setPreferredSize(new Dimension(600, 100));
        bottomBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        bottomBox.setAlignmentY(Component.CENTER_ALIGNMENT);

        for (int i = 0; i < 9; i++) {
            ArrayList<JButton> list = new ArrayList<JButton>();

            for (int j = 0; j < 9; j++) {
                JButton btn = new JButton();
                btn.setBorderPainted(false);
                btn.setBackground(Color.WHITE);
                btn.setFocusPainted(false);
                btn.addActionListener(new ButtonCentralListener());
                list.add(btn);
                panelGameField.add(btn);
            }
            listButtons.add(list);
        }

        for (int i = 0; i < 9; i++) {
            JButton btn = new JButton(Integer.toString(i + 1));
            btn.setBorderPainted(false);
            btn.setBackground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.setSize(50, 200);
            listNumberButtons.add(btn);
            bottomBox.add(btn);
        }
        buttonNewGame.addActionListener(new ButtonNewGameListener());
        buttonNewGame.setBorderPainted(false);
        buttonNewGame.setBackground(Color.WHITE);
        buttonNewGame.setBorderPainted(false);
        bottomBox.add(buttonNewGame);

        panelGameField.setBackground(Color.GRAY);

        frameMain.setResizable(false);
        frameMain.add(BorderLayout.CENTER, panelGameField);
        frameMain.add(BorderLayout.SOUTH, bottomBox);
        frameMain.setSize(600, 600);
        frameMain.setVisible(true);
        frameMain.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void generateSudoku() {

        String randomValue;

        listButtonNames = new ArrayList<ArrayList<String>>();

        for (int i = 0; i < 9; i++) {
            ArrayList<String> stringArrayList = new ArrayList<String>();

            for (int j = 0; j < 9; j++) {

                randomValue = Integer.toString(j + 1/*(int) (Math.random() * 9) + 1*/);
                stringArrayList.add(randomValue);
            }
            listButtonNames.add(stringArrayList);
        }

//        for (int i = 0; i < 9; i++)
//            Collections.swap(listButtonNames.get(0), i, (int) (Math.random() * 9));

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Collections.swap(listButtonNames.get(i), j, (int) (Math.random() * 9));

                if (i != 0) {

                    for (int k = 0; k < i; k++) {
                        if (listButtonNames.get(k).indexOf(listButtonNames.get(i).get(j))
                                == listButtonNames.get(i).indexOf(listButtonNames.get(k).get(j))) {
                            j--;
                            break;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                listButtons.get(i).get(j).setText(listButtonNames.get(i).get(j));
            }
        }
    }

    public void paintBackgroundDefault() {
        for (ArrayList<JButton> list : listButtons) {
            list.forEach(jButton -> jButton.setBackground(Color.white));
        }
    }

    public class ButtonCentralListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            int x = 0;
            int y = 0;

            String buttonValue = "";

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {

                    if (e.getSource() == listButtons.get(i).get(j)) {
                        buttonValue = listButtons.get(i).get(j).getText();
                        x = j;
                        y = i;
                    } else {
                        listButtons.get(i).get(j).setBackground(Color.WHITE);
                    }
                }
            }

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {

                    if (i == y)
                        listButtons.get(i).get(j).setBackground(Color.lightGray);
                    if (j == x)
                        listButtons.get(i).get(j).setBackground(Color.lightGray);

                    if (listButtons.get(i).get(j).getText().equals(buttonValue))
                        listButtons.get(i).get(j).setBackground(Color.orange);
                }
            }
            listButtons.get(y).get(x).setBackground(Color.MAGENTA);
        }
    }

    public class ButtonNewGameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            generateSudoku();
            paintBackgroundDefault();
        }
    }
}
