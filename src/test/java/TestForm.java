import javax.swing.*;
import java.awt.*;

public class TestForm  {

    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setLayout(new GridBagLayout());
        JLabel jLabel = new JLabel("1111");
        jLabel.setBounds(10, 10, 80, 33);
        jFrame.setBounds(0,0,640,480);
        jFrame.setVisible(true);
        JPanel test = new JPanel();
        test.add(jLabel);
        jFrame.add(test);

    }
}
