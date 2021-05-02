import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Vector;

public class  LoadBtnListener implements ActionListener {
    boolean endlessBoll;
    GuiCreator gui;
    public LoadBtnListener(GuiCreator a) {
        this.gui=a;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser izbira = new JFileChooser();
        FileNameExtensionFilter podaljsek = new FileNameExtensionFilter("text datoteke", "txt");
        izbira.setFileFilter(podaljsek);

        int ret = izbira.showSaveDialog(null);

        if (ret == JFileChooser.APPROVE_OPTION) {

            File datoteka = izbira.getSelectedFile();

            try {
                BufferedReader bralec = new BufferedReader(new FileReader(datoteka));
                String besediliIzFila="";
                String s;
                while ((s = bralec.readLine())!= null){
                    besediliIzFila+=s+"\n";
                }

                String []deliBesedila= besediliIzFila.split("-----");



                String[] pravilnaMatrikaVrstice = deliBesedila[0].split("\\|");
                String[] stevilke = pravilnaMatrikaVrstice[0].split(",");

                int pravilaMatrika[][] = new int[pravilnaMatrikaVrstice.length][stevilke.length];


                for (int i = 0; i < pravilaMatrika.length; i++) {
                    String[] stevilke1 = pravilnaMatrikaVrstice[i].split(",");
                    for (int j = 0; j < pravilaMatrika[0].length; j++) {
                        if (stevilke1[j].equals("?")) {
                            pravilaMatrika[i][j] = 99;
                        } else {
                            pravilaMatrika[i][j] = Integer.parseInt(stevilke1[j]);
                        }
                    }
                }


                String[] napisanaMatrikaVrstice = deliBesedila[1].split("\\|");
                String[] stevilke2 = napisanaMatrikaVrstice[0].split(",");

                int napisaMatrika[][] = new int[napisanaMatrikaVrstice.length][stevilke2.length];


                for (int i = 0; i < napisaMatrika.length; i++) {
                    String[] stevilke3 = napisanaMatrikaVrstice[i].split(",");
                    if (stevilke3[0].matches("^\n.+$")){
                        stevilke3[0]=stevilke3[0].strip();
                    }
                    for (int j = 0; j < napisaMatrika[0].length; j++) {
                        if (stevilke3[j].equals("?")) {
                            napisaMatrika[i][j] = 99;
                        } else {
                            napisaMatrika[i][j] = Integer.parseInt(stevilke3[j]);
                        }
                    }
                }


                String[] endlessMode = deliBesedila[2].split(" ");

                endlessMode[2]=endlessMode[2].strip();


                if (endlessMode[2].equals("on")){
                    endlessBoll=true;
                }else {
                    endlessBoll=false;
                }

                String zaVstavit = "";

                //defioniranje vektorja za vstavljanje navpicnih stevil, ki so skupaj
                Vector<String> navpicno = new Vector<String>();

                //loopamo skozi matriko po stolpcih
                for (int i = 0; i < pravilaMatrika[0].length; i++) {
                    for (int j = 0; j < pravilaMatrika.length; j++) {
                        //ce je matrika[j][i]==99 potem dodamo zaVstavit v VEKTOR ce ni prazen, ce je prazen pa samo koncamo z iteracijo loopa
                        if (pravilaMatrika[j][i] == 99) {
                            if (zaVstavit != "") {
                                navpicno.add(zaVstavit);
                                zaVstavit = "";
                            }
                            continue;
                        }

                        //else matrika[j][i] != 99 zaVstavit konkatiniramo stevilo na matrika[j][i] ki ga pretvorimo v string
                        else {
                            String st = String.valueOf(pravilaMatrika[j][i]);
                            zaVstavit+= st;
                        }
                    }
                    //na koancu notranjega loopa dodamo zaVstavit v VEKTOR in ga damo v ""
                    if (zaVstavit!="") {
                        navpicno.add(zaVstavit);
                        zaVstavit = "";
                    }
                }

                //definiramo VEKTOR vodoravno ki bo vseboval vodoravna stevila ki jih bomo morali vstaviti
                Vector<String> vodoravno = new Vector<String>();

                //loopamo skozi matriko po vrsticah
                for (int i = 0; i < pravilaMatrika.length; i++) {
                    for (int j = 0; j < pravilaMatrika[0].length; j++) {
                        //ce je matrika[i][j]== 99 && zaVstavit ni prazen dodamo zaVstavit v VEKTOR, drugace samo damo zaVstavit="" in koncamo z iteracijo
                        if (pravilaMatrika[i][j] == 99) {
                            if (zaVstavit != "") {
                                vodoravno.add(zaVstavit);
                                zaVstavit = "";
                            }
                            continue;
                        }
                        //else if matrika[i][j]!=99 vrednost stevila matrika[i][j] pretvorimo v String in ga dodamo k zaVstavit
                        else {
                            String st = String.valueOf(pravilaMatrika[i][j]);
                            zaVstavit = zaVstavit + st;
                        }
                    }
                    //na koncu iteracij notranjega loopa dodamo zaVstavit v VEKOTR-vodoravno in vrednost zaVstavit=""
                    if (zaVstavit!="") {
                        vodoravno.add(zaVstavit);
                        zaVstavit = "";
                    }
                }

                String vodoravnoString="";
                String navpicnoString="";

                for (int i = 0; i < vodoravno.size(); i++) {
                    vodoravnoString+= vodoravno.get(i)+ ", ";
                }

                for (int i = 0; i < navpicno.size(); i++) {
                    navpicnoString+=  navpicno.get(i) + ", ";
                }
                LinkedList<Object> returnLinkedList=new LinkedList<>();
                returnLinkedList.add(pravilaMatrika);
                returnLinkedList.add(napisaMatrika);
                returnLinkedList.add(vodoravnoString);
                returnLinkedList.add(navpicnoString);
                returnLinkedList.add(endlessBoll);


                gui.loadGame(returnLinkedList);
                bralec.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Ups something went wrong!", "test", JOptionPane.INFORMATION_MESSAGE);
                ex.printStackTrace();
            }
        }
    }

}