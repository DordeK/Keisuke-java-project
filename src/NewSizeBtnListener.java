import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class NewSizeBtnListener implements ActionListener {
    GuiCreator newSizeGui;

    public NewSizeBtnListener(GuiCreator guiCreator) {
        this.newSizeGui = guiCreator;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        CreateLogic newGameWithInputSize = new CreateLogic();
        newSizeGui.endlessModeBoll = false;
        try {
            int noveVrstice = Integer.parseInt(newSizeGui.newSizeCenter3.getText());
            int noviStolpci = Integer.parseInt(newSizeGui.newSizeCenter4.getText());
            if (noveVrstice == 0 || noviStolpci == 0) {
                JOptionPane.showMessageDialog(null, "Neveljaven vnos, vrstice in stolpci morajo biti stevilke vecje od 0", "test", JOptionPane.INFORMATION_MESSAGE);
            } else {
                LinkedList<Object> newGameMatrika = newGameWithInputSize.newGamelogicGenerator(noveVrstice, noviStolpci);
                newSizeGui.NewGameGui(newGameMatrika);
                newSizeGui.CheckGameListener.setEndlesModeBoll(false);
                newSizeGui.SaveBtnListener.setEndlesMode(false);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Neveljaven vnos, vrstice in stolpci morajo biti stevilke", "test", JOptionPane.INFORMATION_MESSAGE);

        }


    }
}