import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.LinkedList;


class GridBtnListener implements ActionListener {
    public   GuiCreator guiCreator;
    static int [][]TestMatrika;
    static int [][]pravilnaMatrika;

    public void setTestMatrika(int[][] testMatrika) {
        TestMatrika = testMatrika;
    }

    public void vstavljanje9vTestMatrika(){
        TestMatrika= new int[pravilnaMatrika.length][pravilnaMatrika[0].length];
        for (int i = 0; i < pravilnaMatrika.length; i++) {
            for (int j = 0; j < pravilnaMatrika[0].length; j++) {
                if (pravilnaMatrika[i][j] == 99){
                    TestMatrika[i][j]=99;
                }
            }
        }
    }
    

    public GridBtnListener(GuiCreator guiCreator){
        this.guiCreator= guiCreator;
    }

public void endlesModeMatrikaSetter(){
    for (int i = 0; i < TestMatrika.length; i++) {
        for (int j = 0; j < TestMatrika[0].length; j++) {
            if (i< TestMatrika.length-1 && j< TestMatrika[0].length-1){
                TestMatrika[i][j]=pravilnaMatrika[i][j];
            }
        }
    }
}

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton pritisnjenGum = (JButton) e.getSource();

        String besediloPritisnjenegaGumba = pritisnjenGum.getName();
        String []tockaVmrezi = besediloPritisnjenegaGumba.split(",");





        int xOs= Integer.parseInt(tockaVmrezi[0]);
        int yOs= Integer.parseInt(tockaVmrezi[1]);




        int vrednostPritisnjenegaGumba = Integer.parseInt(pritisnjenGum.getText());
        vrednostPritisnjenegaGumba++;
        vrednostPritisnjenegaGumba%=10;



        TestMatrika[xOs][yOs]= vrednostPritisnjenegaGumba;



        pritisnjenGum.setText(vrednostPritisnjenegaGumba+"");


    }









    public static class CheckGameListener implements ActionListener{
        boolean endlesModeBoll = false;
        GuiCreator gui;

        public CheckGameListener(boolean endlesModeBoll, GuiCreator gui){
           this.endlesModeBoll = endlesModeBoll;
           this.gui= gui;
       }


        public void setEndlesModeBoll(boolean endlesModeBoll) {
            this.endlesModeBoll = endlesModeBoll;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            CreateLogic logic = new CreateLogic();
            JButton checkBtn= (JButton) e.getSource();

             int breakCounter=0;




            for (int i = 0; i < TestMatrika.length; i++) {
                for (int j = 0; j < TestMatrika[0].length; j++) {
                    if (TestMatrika[i][j] != pravilnaMatrika[i][j]){
                        breakCounter++;
                            break;
                    }
                }
                if (breakCounter!=0){
                    break;
                }
            }

            if (breakCounter!=0){
                checkBtn.setText("Nepravilno");
                JOptionPane.showMessageDialog(null, "Nepravilno", "test", JOptionPane.INFORMATION_MESSAGE);
            }else {
                checkBtn.setText("Pravilno");
                if (endlesModeBoll){
                        LinkedList endlesMode = logic.endlesModeLogicGenerator(TestMatrika);
                    gui.EndlesModeGui(endlesMode);
                }
                JOptionPane.showMessageDialog(null, "Pravilno", "test", JOptionPane.INFORMATION_MESSAGE);
            }

        }


    }



    public static class SaveBtnListener implements ActionListener {
        boolean endlesMode=false;
        String endlesModeString;


        public SaveBtnListener(boolean endless) {
            this.endlesMode=endless;
        }

        public void setEndlesMode(boolean endlesMode) {
            this.endlesMode = endlesMode;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String zaSavePravilnaMatrika="";
            String zaSaveTestMatrika="-----\n";

            if (endlesMode== true ){
                endlesModeString="-----\nEndlesd mode: on";
            }else {
                endlesModeString="-----\nEndlesd mode: off";
            }



            for (int i = 0; i < pravilnaMatrika.length; i++) {
                for (int j = 0; j < pravilnaMatrika[0].length; j++) {
                    if (pravilnaMatrika[i][j] == 99){
                        zaSavePravilnaMatrika+="?,";
                    }else {
                        zaSavePravilnaMatrika += pravilnaMatrika[i][j] + ",";
                    }
                }
                zaSavePravilnaMatrika+="|";
            }

            for (int i = 0; i < TestMatrika.length; i++) {
                for (int j = 0; j < TestMatrika[0].length; j++) {
                    if (TestMatrika[i][j] == 99){
                        zaSaveTestMatrika+="?,";
                    }else {
                        zaSaveTestMatrika += TestMatrika[i][j] + ",";
                    }
                }
                zaSaveTestMatrika+="|";
            }


            JFileChooser izbira = new JFileChooser();
            FileNameExtensionFilter podaljsek = new FileNameExtensionFilter("text datoteke", "txt");
            izbira.setFileFilter(podaljsek);

            int ret = izbira.showSaveDialog(null);

            if (ret == JFileChooser.APPROVE_OPTION) {

                File datoteka = izbira.getSelectedFile();

                System.out.println(datoteka.getAbsolutePath());

                try {
                    BufferedWriter pisatelj = new BufferedWriter(new FileWriter(datoteka));
                    pisatelj.write( zaSavePravilnaMatrika);
                    pisatelj.write(zaSaveTestMatrika);
                    pisatelj.write(endlesModeString);
                    pisatelj.close();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Ups something went wrong!", "test", JOptionPane.INFORMATION_MESSAGE);
                    ex.printStackTrace();
                    }
                }
            }

        }


    }


