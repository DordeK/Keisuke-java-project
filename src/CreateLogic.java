import java.text.DecimalFormat;
import java.util.*;

public class CreateLogic {


    public LinkedList<Object> endlesModeLogicGenerator(int [][]matrika){
           int[][] endlesModeMatrika = new int[matrika.length+1][matrika[0].length+1];
        for (int i = 0; i < endlesModeMatrika.length; i++) {
            for (int j = 0; j < endlesModeMatrika[0].length; j++) {
                        if (i <= matrika.length-1 && j <= matrika[0].length-1){
                            endlesModeMatrika[i][j] = matrika[i][j];
                        } else {
                            int[] arr = {0,1, 2, 3,4,5,6,7,8,9,99};
                            Random rn = new Random();
                            int ran = rn.nextInt(11);
                            endlesModeMatrika[i][j] = arr[ran];
                        }

            }
        }

        //definiranje stringa zaVstavit ki bo vstavljel stevilke ki so navpicno in vodoravno v matiki skupaj!
        String zaVstavit = "";

        //defioniranje vektorja za vstavljanje navpicnih stevil, ki so skupaj
        Vector<String> navpicno = new Vector<String>();

        //loopamo skozi matriko po stolpcih
        for (int i = 0; i < endlesModeMatrika[0].length; i++) {
            for (int j = 0; j < endlesModeMatrika.length; j++) {
                //ce je matrika[j][i]==99 potem dodamo zaVstavit v VEKTOR ce ni prazen, ce je prazen pa samo koncamo z iteracijo loopa
                if (endlesModeMatrika[j][i] == 99) {
                    if (zaVstavit != "") {
                        navpicno.add(zaVstavit);
                    }
                    zaVstavit = "";
                    continue;
                }

                //else matrika[j][i] != 99 zaVstavit konkatiniramo stevilo na matrika[j][i] ki ga pretvorimo v string
                else {
                    String st = String.valueOf(endlesModeMatrika[j][i]);
                    zaVstavit = zaVstavit + st;
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
        for (int i = 0; i < endlesModeMatrika.length; i++) {
            for (int j = 0; j < endlesModeMatrika[0].length; j++) {
                //ce je matrika[i][j]== 99 && zaVstavit ni prazen dodamo zaVstavit v VEKTOR, drugace samo damo zaVstavit="" in koncamo z iteracijo
                if (endlesModeMatrika[i][j] == 99) {
                    if (zaVstavit != "") {
                        vodoravno.add(zaVstavit);
                        zaVstavit = "";
                    }
                    continue;
                }
                //else if matrika[i][j]!=99 vrednost stevila matrika[i][j] pretvorimo v String in ga dodamo k zaVstavit
                else {
                    String st = String.valueOf(endlesModeMatrika[i][j]);
                    zaVstavit = zaVstavit + st;
                }
            }
            //na koncu iteracij notranjega loopa dodamo zaVstavit v VEKOTR-vodoravno in vrednost zaVstavit=""
            if (zaVstavit!="") {
                vodoravno.add(zaVstavit);
                zaVstavit = "";
            }
        }
        Collections.sort(vodoravno);
        Collections.sort(navpicno);


        String vodoravnoString="";
        String navpicnoString="";

        for (int i = 0; i < vodoravno.size(); i++) {
            vodoravnoString= vodoravnoString+ vodoravno.get(i)+ ", ";
        }

        for (int i = 0; i < navpicno.size(); i++) {
            navpicnoString= navpicnoString+  navpicno.get(i)+", ";
        }






        LinkedList<Object> returnLinkedList=new LinkedList<>();
        returnLinkedList.add(endlesModeMatrika);
        returnLinkedList.add( vodoravnoString);
        returnLinkedList.add(navpicnoString);

        return returnLinkedList;
    }




    public LinkedList<Object> newGamelogicGenerator(int vrstica, int stolpec ) {
        int [][]matrika= new int[vrstica][stolpec];

        //zapoljenjevanje matrike z stevilo med 0-3
        for (int i = 0; i < vrstica; i++) {
            for (int j = 0; j < stolpec; j++) {
                //array iz katerega bomo dobivali vrednosti na podlagi random indexov
                int[] arr = {0,1, 2, 3,4,5,6,7,8,9,99};
                //dobivanje random indeksov od 0-3 in jih shranjevati v int ran
                Random rn = new Random();
                int ran = rn.nextInt(11);
                //matrika na poziciji [i][j] ima vrednost(ran==random index med 0-3)arr[ran]
                matrika[i][j] = arr[ran];
            }
        }


        //definiranje stringa zaVstavit ki bo vstavljel stevilke ki so navpicno in vodoravno v matiki skupaj!
        String zaVstavit = "";

        //defioniranje vektorja za vstavljanje navpicnih stevil, ki so skupaj
        Vector<String> navpicno = new Vector<String>();

        //loopamo skozi matriko po stolpcih
        for (int i = 0; i < matrika[0].length; i++) {
            for (int j = 0; j < matrika.length; j++) {
                //ce je matrika[j][i]==99 potem dodamo zaVstavit v VEKTOR ce ni prazen, ce je prazen pa samo koncamo z iteracijo loopa
                if (matrika[j][i] == 99) {
                    if (zaVstavit != "") {
                        navpicno.add(zaVstavit);
                        zaVstavit = "";
                    }
                    continue;
                }
                //else matrika[j][i] != 99 zaVstavit konkatiniramo stevilo na matrika[j][i] ki ga pretvorimo v string
                else {
                    String st = String.valueOf(matrika[j][i]);
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
        for (int i = 0; i < matrika.length; i++) {
            for (int j = 0; j < matrika[0].length; j++) {
                //ce je matrika[i][j]== 99 && zaVstavit ni prazen dodamo zaVstavit v VEKTOR, drugace samo damo zaVstavit="" in koncamo z iteracijo
                if (matrika[i][j] == 99) {
                    if (zaVstavit != "") {
                        vodoravno.add(zaVstavit);
                        zaVstavit = "";
                    }
                    continue;
                }
                //else if matrika[i][j]!=99 vrednost stevila matrika[i][j] pretvorimo v String in ga dodamo k zaVstavit
                else {
                    String st = String.valueOf(matrika[i][j]);
                    zaVstavit = zaVstavit + st;
                }
            }
            //na koncu iteracij notranjega loopa dodamo zaVstavit v VEKOTR-vodoravno in vrednost zaVstavit=""
            if (zaVstavit!="") {
                vodoravno.add(zaVstavit);
                zaVstavit = "";
            }
        }
        Collections.sort(vodoravno);
        Collections.sort(navpicno);

        String vodoravnoString="";
        String navpicnoString="";

        for (int i = 0; i < vodoravno.size(); i++) {
            vodoravnoString+= vodoravno.get(i)+ ", ";
        }

        for (int i = 0; i < navpicno.size(); i++) {
            navpicnoString+=  navpicno.get(i) + ", ";
        }

        LinkedList<Object> returnLinkedList=new LinkedList<>();
        returnLinkedList.add(matrika);
        returnLinkedList.add(vodoravnoString);
        returnLinkedList.add(navpicnoString);

            return returnLinkedList;
    }
}

