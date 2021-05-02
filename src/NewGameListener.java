import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

class NewGameListener implements ActionListener {
    GuiCreator guiCreator;

    public NewGameListener(GuiCreator guiCreator) {
        this.guiCreator = guiCreator;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        CreateLogic newGameObject = new CreateLogic();

        int stolpci = ThreadLocalRandom.current().nextInt(1, 11);
        int vrstice = ThreadLocalRandom.current().nextInt(1, 11);

        LinkedList<Object> newGameMatrika = newGameObject.newGamelogicGenerator(vrstice, stolpci);
        this.guiCreator.endlessModeBoll = false;
        guiCreator.CheckGameListener.setEndlesModeBoll(false);
        guiCreator.SaveBtnListener.setEndlesMode(false);

        this.guiCreator.NewGameGui(newGameMatrika);
    }
}