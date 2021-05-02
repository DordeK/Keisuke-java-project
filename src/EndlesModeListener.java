import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

class EndlesModeListener implements ActionListener {
    GuiCreator endlesModeGui;

    public EndlesModeListener(GuiCreator endlesModeGui) {
        this.endlesModeGui = endlesModeGui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CreateLogic endlessModeGameLogic = new CreateLogic();
        JButton endelsModeBtn = (JButton) e.getSource();
        if (endelsModeBtn.getText().equals("Endless mode:off")) {
            endelsModeBtn.setText("Endless mode:on");
            endlesModeGui.CheckGameListener.setEndlesModeBoll(true);
            endlesModeGui.SaveBtnListener.setEndlesMode(true);
            endlesModeGui.endlessModeBoll = true;
        } else if (endelsModeBtn.getText().equals("Endless mode:on")) {
            endelsModeBtn.setText("Endless mode:off");
            endlesModeGui.CheckGameListener.setEndlesModeBoll(false);
            endlesModeGui.SaveBtnListener.setEndlesMode(false);
            endlesModeGui.endlessModeBoll = false;
        }
        LinkedList<Object> newGameMatrika = endlessModeGameLogic.newGamelogicGenerator(5, 5);
        endlesModeGui.NewGameGui(newGameMatrika);
    }
}