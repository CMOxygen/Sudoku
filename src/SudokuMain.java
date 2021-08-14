import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class SudokuMain {

    JFrame frameMain;
    JPanel panelGameField;
    Box bottomBox;

    JLabel labelCentral;

    JButton buttonNewGame;

    ArrayList<ArrayList<JButton>> listButtons;
    ArrayList<JButton> listNumberButtons;

    public static void main(String[] args) {

        SudokuMain gameManager = new SudokuMain();
        gameManager.buildGui();
        gameManager.buildSudoku();
    }

    public void buildGui() {

        GridLayout layoutGrid = new GridLayout(9, 9, 2, 2);
        frameMain = new JFrame();
        panelGameField = new JPanel(layoutGrid);

        buttonNewGame = new JButton("New Game");

        labelCentral = new JLabel("LOL");

        listButtons = new ArrayList<ArrayList<JButton>>();
        listNumberButtons = new ArrayList<JButton>();

        bottomBox = new Box(BoxLayout.X_AXIS);

        bottomBox.setPreferredSize(new Dimension(600, 100));
        bottomBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        bottomBox.setAlignmentY(Component.CENTER_ALIGNMENT);

        for (int i = 0; i < 9; i++) {
            ArrayList<JButton> list = new ArrayList<JButton>();

            for (int j = 0; j < 9; j++) {
                JButton btn = new JButton(/*Integer.toString((int) (Math.random() * 9) + 1)*/);
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
//            btn.setSelected(true);
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

    public void buildSudoku() {

        int counter = 1;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                listButtons.get(i).get(j).setText(Integer.toString((int) (Math.random() * 9) + 1));
            }
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
            buildSudoku();
        }
    }

    public class LabelCentralListener implements MouseListener {
        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
