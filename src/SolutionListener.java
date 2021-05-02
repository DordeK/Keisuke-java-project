import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SolutionListener implements ActionListener {
    int pravilnaMatrika[][];

    public void setMatrika(int pravilnaMatrika[][]){
        this.pravilnaMatrika= pravilnaMatrika;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JPanel matrika = new JPanel(new GridLayout(pravilnaMatrika.length, pravilnaMatrika[0].length));

        for (int i = 0; i < pravilnaMatrika.length; i++) {
            for (int j = 0; j < pravilnaMatrika[0].length; j++) {
                if (pravilnaMatrika[i][j]==99){
                    JButton GreyBtn =new JButton();
                    GreyBtn.setBackground(Color.gray);
                    GreyBtn.setEnabled(false);
                    matrika.add(GreyBtn);
                }else {
                    JButton resitevBtn = new JButton(String.valueOf(pravilnaMatrika[i][j]));
                    matrika.add(resitevBtn);
                    }
                }
             }

        JOptionPane.showMessageDialog(null,matrika, "Information",JOptionPane.INFORMATION_MESSAGE);
    }
}
