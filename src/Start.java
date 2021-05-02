import java.util.LinkedList;

public class Start {
    public static void main(String[] args) {
        CreateLogic prviTest= new CreateLogic();
         LinkedList list = prviTest.newGamelogicGenerator(5,5);
         GuiCreator gui = new GuiCreator();
         gui.NewGameGui(list);
    }
}
