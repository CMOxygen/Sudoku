import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class SudokuMain {

    JFrame frameMain;
    JPanel panelCentral;
    Box bottomBox;

    JLabel labelCentral;

    //    ArrayList<JButton> listButtons;
    ArrayList<ArrayList<JButton>> listButtons;
    ArrayList<JButton> listNumberButtons;

    public static void main(String[] args) {
        new SudokuMain().buildGui();
    }

    public void buildGui() {

        GridLayout layoutGrid = new GridLayout(9, 9, 2, 2);
        frameMain = new JFrame();
        panelCentral = new JPanel(layoutGrid);

        labelCentral = new JLabel("LOL");

        listButtons = new ArrayList<ArrayList<JButton>>();
        listNumberButtons = new ArrayList<JButton>();

        bottomBox = new Box(BoxLayout.X_AXIS);

        bottomBox.setPreferredSize(new Dimension(600, 100));
        bottomBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        bottomBox.setAlignmentY(Component.CENTER_ALIGNMENT);

//        for (int i = 0; i < 81; i++) {
//            JButton btn = new JButton(Integer.toString(i + 1));
//            btn.setBorderPainted(false);
//            btn.setBackground(Color.WHITE);
//            btn.setFocusPainted(false);
//            btn.setSelected(true);
//            btn.addActionListener(new ButtonCentralListener());
//            listButtons.add(btn);
//            panelCentral.add(btn);
//        }

        for (int i = 0; i < 9; i++) {
            ArrayList<JButton> list = new ArrayList<JButton>();

            for (int j = 0; j < 9; j++) {
                JButton btn = new JButton(Integer.toString(j + 1));
                btn.setBorderPainted(false);
                btn.setBackground(Color.WHITE);
                btn.setFocusPainted(false);
                btn.addActionListener(new ButtonCentralListener());
                list.add(btn);
                panelCentral.add(btn);
            }
            listButtons.add(list);
        }

        for (int i = 0; i < 9; i++) {
            JButton btn = new JButton(Integer.toString(i + 1));
            btn.setBorderPainted(false);
            btn.setBackground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.setSelected(true);
            btn.setSize(50, 200);
            listNumberButtons.add(btn);
            bottomBox.add(btn);
        }

        panelCentral.setBackground(Color.GRAY);

        frameMain.setResizable(false);
        frameMain.add(BorderLayout.CENTER, panelCentral);
        frameMain.add(BorderLayout.SOUTH, bottomBox);
        frameMain.setSize(600, 600);
        frameMain.setVisible(true);
        frameMain.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public class ButtonCentralListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            int x = 0;
            int y = 0;

            JButton buttonAction;
            String buttonValue = "";

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {

                    if (e.getSource() == listButtons.get(i).get(j)) {
                        buttonAction = listButtons.get(i).get(j);
                        buttonValue = listButtons.get(i).get(j).getText();
                        listButtons.get(i).get(j).setBackground(Color.MAGENTA);
                        x = j;
                        y = i;
                    } else {
                        listButtons.get(i).get(j).setBackground(Color.WHITE);
                    }
                }
            }

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {

                    if (listButtons.get(i).get(j).getText() == buttonValue)
                        listButtons.get(i).get(j).setBackground(Color.BLUE);

                    if (i == y)
                        listButtons.get(i).get(j).setBackground(Color.GRAY);
                    if (j == x)
                        listButtons.get(i).get(j).setBackground(Color.GRAY);
                }
            }
//            for (int i = 0; i < listButtons.size(); i++) {
//                int counter = 1;
//
//                if (e.getSource() == listButtons.get(i)) {
//
//                    for (int j = 1; j < 9; j++) {
//
//                        if (9 * j < i) {
//                            listButtons.get(i - 9 * j).setBackground(Color.MAGENTA);
//                            System.out.println(Integer.toString(i - 9 * j));
//                        }
//                        if (i + 9 * j <= listButtons.size() - 1) {
//                            listButtons.get(i + 9 * j).setBackground(Color.MAGENTA);
//                            System.out.println(Integer.toString(i + 9 * j));
//                        }
//                    }
//                    listButtons.get(i).setBackground(Color.ORANGE);
//                } else {
//                    listButtons.get(i).setBackground(Color.WHITE);
//                }
//            }
        }
    }

    public class LabelCentralListener implements MouseListener {
        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseClicked(MouseEvent e) {
            labelCentral.setText("NOT LOL");
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
