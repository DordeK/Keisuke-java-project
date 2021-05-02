import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.LinkedList;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

public class GuiCreator {
    boolean endlessModeBoll = false;

    NewGameListener NewGameListener = new NewGameListener(this);
    GridBtnListener GridBtnListener = new GridBtnListener(this);
    GridBtnListener.CheckGameListener CheckGameListener = new GridBtnListener.CheckGameListener(this.endlessModeBoll, this);
    SolutionListener SolutionListener = new SolutionListener();
    NewSizeBtnListener NewSizeBtnListener = new NewSizeBtnListener(this);
    EndlesModeListener EndlesModeListener = new EndlesModeListener(this);
    GridBtnListener.SaveBtnListener SaveBtnListener = new GridBtnListener.SaveBtnListener(endlessModeBoll);
    LoadBtnListener LoadBtnListener = new LoadBtnListener(this);

    JFrame okno = new JFrame("keisaki");
    JPanel centralFrameGrid = new JPanel(new GridLayout(1, 2));
    JPanel leviGrid = new JPanel(new GridLayout(5, 5));//levi grid moramo se dodelati
    JPanel desniGrid = new JPanel(new GridLayout(7, 1));
    JPanel desniGridCheckGame = new JPanel(new GridLayout(2, 1));
    JPanel desniGridNavpicniInputi = new JPanel(new GridLayout(2, 1));
    JPanel desniGridVodoravniInputi = new JPanel(new GridLayout(2, 1));
    JPanel desniGridNewSize = new JPanel(new BorderLayout());
    JPanel desniGridNewSizeCenterGrid = new JPanel(new GridLayout(2, 2));
    JButton desniGridSolutionBtn = new JButton("solution");
    JButton desniGridNewGameBtn = new JButton("New game");
    JButton desniGridEndlessModeBtn = new JButton("Endless mode:off");
    JButton saveBtn = new JButton("save");
    JButton loadBtn = new JButton("load from a file");
    JButton desniZgornjiGridBtn = new JButton("---->check the game<----");
    JButton desniSpodnjiGridBtn = new JButton("New size:");
    JLabel desniGridCheckGameJLABELtext = new JLabel("Check the game");
    JLabel desniGridNavpicniInputiJLABELname = new JLabel("Navpicno ⬇:");
    JLabel getDesniGridNavpicniInputiJLABEstevilke = new JLabel("............");
    JLabel desniGridVodoravniInputiJLABELname = new JLabel("Vodoravno ->:");
    JLabel getDesniGridVodoravniInputiJLABELstevilke = new JLabel("..........");
    JLabel newSizeCenter1 = new JLabel("Vrstica:");
    JLabel newSizeCenter2 = new JLabel("Stolpci:");
    JTextField newSizeCenter3 = new JTextField(".......");
    JTextField newSizeCenter4 = new JTextField(".......");


    public GuiCreator() {
        desniGridNewGameBtn.addActionListener(NewGameListener);
        desniZgornjiGridBtn.addActionListener(CheckGameListener);
        desniGridSolutionBtn.addActionListener(SolutionListener);
        desniSpodnjiGridBtn.addActionListener(NewSizeBtnListener);
        desniGridEndlessModeBtn.addActionListener(EndlesModeListener);
        saveBtn.addActionListener(SaveBtnListener);
        loadBtn.addActionListener(LoadBtnListener);


        desniGridEndlessModeBtn.setText("Endless mode:off");


        desniGridCheckGameJLABELtext.setFont(new Font("Serif", Font.PLAIN, 24));
        desniGridCheckGameJLABELtext.setHorizontalAlignment(SwingConstants.CENTER);
        desniGridCheckGameJLABELtext.setForeground(Color.pink);

        newSizeCenter3.setHorizontalAlignment(SwingConstants.CENTER);
        newSizeCenter4.setHorizontalAlignment(SwingConstants.CENTER);



        okno.add(saveBtn, BorderLayout.SOUTH);
        okno.add(loadBtn,BorderLayout.NORTH);
        okno.add(centralFrameGrid, BorderLayout.CENTER);
        centralFrameGrid.add(leviGrid);
        centralFrameGrid.add(desniGrid);
        desniGrid.add(desniGridCheckGame);
        desniGrid.add(desniGridSolutionBtn);
        desniGrid.add(desniGridNewGameBtn);
        desniGrid.add(desniGridEndlessModeBtn);
        desniGrid.add(desniGridNavpicniInputi);
        desniGrid.add(desniGridVodoravniInputi);
        desniGrid.add(desniGridNewSize);
        desniGridCheckGame.add(desniGridCheckGameJLABELtext);
        desniGridCheckGame.add(desniZgornjiGridBtn);
        desniGridNavpicniInputi.add(desniGridNavpicniInputiJLABELname);
        desniGridNavpicniInputi.add(getDesniGridNavpicniInputiJLABEstevilke);
        desniGridVodoravniInputi.add(desniGridVodoravniInputiJLABELname);
        desniGridVodoravniInputi.add(getDesniGridVodoravniInputiJLABELstevilke);
        desniGridNewSize.add(desniSpodnjiGridBtn, BorderLayout.NORTH);
        desniGridNewSize.add(desniGridNewSizeCenterGrid, BorderLayout.CENTER);
        desniGridNewSizeCenterGrid.add(newSizeCenter1);
        desniGridNewSizeCenterGrid.add(newSizeCenter2);
        desniGridNewSizeCenterGrid.add(newSizeCenter3);
        desniGridNewSizeCenterGrid.add(newSizeCenter4);

        okno.setSize(1000, 700);
        okno.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        okno.setLocationRelativeTo(null);
        okno.setVisible(true);
    }



    public void loadGame(LinkedList newGameLinkedList) {
        int newGameMatrika[][] = (int[][]) newGameLinkedList.get(0);
        int napisanaMatrika[][]= (int[][]) newGameLinkedList.get(1);
        String vodoravniString = (String) newGameLinkedList.get(2);
        String navpicniString = (String) newGameLinkedList.get(3);
        boolean EndlessMode = (boolean) newGameLinkedList.get(4);

        leviGrid.setLayout(new GridLayout(newGameMatrika.length, +newGameMatrika[0].length));
        leviGrid.removeAll();
        okno.setVisible(false);


        if (EndlessMode == false) {
            desniGridEndlessModeBtn.setText("Endless mode:off");
        }else {
            desniGridEndlessModeBtn.setText("Endless mode:on");
        }


        desniZgornjiGridBtn.setText("---->check the game<----");


        getDesniGridNavpicniInputiJLABEstevilke.setText(navpicniString);
        getDesniGridVodoravniInputiJLABELstevilke.setText(vodoravniString);

        for (int i = 0; i < napisanaMatrika.length; i++) {
            for (int j = 0; j < napisanaMatrika[0].length; j++) {
                if (napisanaMatrika[i][j] == 99) {
                    JButton GreyBtn = new JButton();
                    GreyBtn.setBackground(Color.gray);
                    GreyBtn.setEnabled(false);
                    leviGrid.add(GreyBtn);
                } else {
                    JButton normalenGumb = new JButton(String.valueOf(napisanaMatrika[i][j]));
                    normalenGumb.setName(i + "," + j);
                    normalenGumb.addActionListener(GridBtnListener);
                    leviGrid.add(normalenGumb);
                }
            }
        }
        CheckGameListener.endlesModeBoll=EndlessMode;
        GridBtnListener.pravilnaMatrika = newGameMatrika;
        GridBtnListener.vstavljanje9vTestMatrika();
        SolutionListener.setMatrika(newGameMatrika);
        GridBtnListener.endlesModeMatrikaSetter();
        GridBtnListener.setTestMatrika(napisanaMatrika);

        newSizeCenter3.setText(String.valueOf(newGameMatrika.length));
        newSizeCenter4.setText(String.valueOf(newGameMatrika[0].length));

        okno.setVisible(true);
    }



    public void EndlesModeGui(LinkedList newGameLinkedList) {
        int newGameMatrika[][] = (int[][]) newGameLinkedList.get(0);
        String vodoravniString = (String) newGameLinkedList.get(1);
        String navpicniString = (String) newGameLinkedList.get(2);

        leviGrid.setLayout(new GridLayout(newGameMatrika.length, +newGameMatrika[0].length));
        leviGrid.removeAll();
        leviGrid.setVisible(false);


        desniZgornjiGridBtn.setText("---->check the game<----");


        getDesniGridNavpicniInputiJLABEstevilke.setText(navpicniString);
        getDesniGridVodoravniInputiJLABELstevilke.setText(vodoravniString);

        for (int i = 0; i < newGameMatrika.length; i++) {
            for (int j = 0; j < newGameMatrika[0].length; j++) {
                if (i < newGameMatrika.length - 1 && j < newGameMatrika[0].length - 1) {
                    if (newGameMatrika[i][j] == 99) {
                        JButton GreyBtn = new JButton();
                        GreyBtn.setBackground(Color.gray);
                        GreyBtn.setEnabled(false);
                        leviGrid.add(GreyBtn);
                    } else {
                        JButton normalenGumb = new JButton(String.valueOf(newGameMatrika[i][j]));
                        normalenGumb.setName(i + "," + j);
                        normalenGumb.addActionListener(GridBtnListener);
                        leviGrid.add(normalenGumb);
                    }
                } else {
                    if (newGameMatrika[i][j] == 99) {
                        JButton GreyBtn = new JButton();
                        GreyBtn.setBackground(Color.gray);
                        GreyBtn.setEnabled(false);
                        leviGrid.add(GreyBtn);
                    } else {
                        JButton normalenGumb = new JButton("0");
                        normalenGumb.setName(i + "," + j);
                        normalenGumb.addActionListener(GridBtnListener);
                        leviGrid.add(normalenGumb);
                    }
                }
            }
        }

        GridBtnListener.pravilnaMatrika = newGameMatrika;
        GridBtnListener.vstavljanje9vTestMatrika();
        SolutionListener.setMatrika(newGameMatrika);
        GridBtnListener.endlesModeMatrikaSetter();

        newSizeCenter3.setText(String.valueOf(newGameMatrika.length));
        newSizeCenter4.setText(String.valueOf(newGameMatrika[0].length));


        leviGrid.setVisible(true);
    }


    public void NewGameGui(LinkedList newGameLinkedList) {

        int newGameMatrika[][] = (int[][]) newGameLinkedList.get(0);
        String vodoravniString = (String) newGameLinkedList.get(1);
        String navpicniString = (String) newGameLinkedList.get(2);

        leviGrid.setLayout(new GridLayout(newGameMatrika.length, +newGameMatrika[0].length));
        leviGrid.removeAll();
        leviGrid.setVisible(false);


        desniZgornjiGridBtn.setText("---->check the game<----");


        getDesniGridNavpicniInputiJLABEstevilke.setText(navpicniString);
        getDesniGridVodoravniInputiJLABELstevilke.setText(vodoravniString);

        if (endlessModeBoll == false) {
            desniGridEndlessModeBtn.setText("Endless mode:off");
        }


        for (int i = 0; i < newGameMatrika.length; i++) {
            for (int j = 0; j < newGameMatrika[0].length; j++) {
                if (newGameMatrika[i][j] == 99) {
                    JButton GreyBtn = new JButton();
                    GreyBtn.setBackground(Color.gray);
                    GreyBtn.setEnabled(false);
                    leviGrid.add(GreyBtn);
                } else {
                    JButton normalenGumb = new JButton("0");
                    normalenGumb.setName(i + "," + j);
                    normalenGumb.addActionListener(GridBtnListener);
                    leviGrid.add(normalenGumb);
                }
            }
        }
        GridBtnListener.pravilnaMatrika = newGameMatrika;
        GridBtnListener.vstavljanje9vTestMatrika();
        SolutionListener.setMatrika(newGameMatrika);
        newSizeCenter3.setText(String.valueOf(newGameMatrika.length));
        newSizeCenter4.setText(String.valueOf(newGameMatrika[0].length));

        leviGrid.setVisible(true);
    }

}

