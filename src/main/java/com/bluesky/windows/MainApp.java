/*
 * Created by JFormDesigner on Sun Feb 07 22:33:35 CST 2021
 */

package com.bluesky.windows;

import com.bluesky.glabol.GlobalConfig;
import com.bluesky.interfaces.impl.TaskOne;
import com.bluesky.interfaces.impl.TaskThree;
import com.bluesky.interfaces.impl.TaskTwo;
import com.bluesky.util.RegisterKeyUtils;
import com.bluesky.util.ScreenShotUtil;
import com.bluesky.util.TraySettings;
import com.tulskiy.keymaster.common.HotKey;
import com.tulskiy.keymaster.common.HotKeyListener;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

/**
 * @author unknown
 */
public class MainApp extends JFrame {
    private final RegisterKeyUtils registerKeyUtils = RegisterKeyUtils.getINSTANCE();
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JScrollPane scrollPane1;
    private JTextArea textArea1;
    private JScrollPane scrollPane2;
    private JTextArea textArea2;
    private JPanel buttonBar;
    private JLabel label1;
    private JButton okButton;
    private JLabel label2;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
//    ScreenShotUtil screenShotUtil = ScreenShotUtil.getINSTANCE();
    private void screenshotTranslation(ActionEvent e) {
            new ScreenShotUtil(this).init();
        // TODO add your code here
    }

    public MainApp() throws HeadlessException {
        initComponents();
    }

    private void initComponents() {
        try {
            BeautyEyeLNFHelper.launchBeautyEyeLNF();
        } catch (Exception e) {
            e.printStackTrace();
        }

//        registerKeyUtils.registerKeys(taskOne,"alt 1");
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        scrollPane1 = new JScrollPane();
        textArea1 = new JTextArea();
        scrollPane2 = new JScrollPane();
        textArea2 = new JTextArea();
        buttonBar = new JPanel();
        label1 = new JLabel();
        okButton = new JButton();
        label2 = new JLabel();
        button1 = new JButton();

        //======== this ========
        setTitle("\u9886\u822a\u5458\u53f7\u7ffb\u8bd1\u5de5\u5177");
        setMinimumSize(new Dimension(1000, 600));
        setResizable(false);
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax
            . swing. border. EmptyBorder( 0, 0, 0, 0) , "JFor\u006dDesi\u0067ner \u0045valu\u0061tion", javax. swing
            . border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .
            Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ), java. awt. Color. red
            ) ,dialogPane. getBorder( )) ); dialogPane. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override
            public void propertyChange (java .beans .PropertyChangeEvent e) {if ("bord\u0065r" .equals (e .getPropertyName (
            ) )) throw new RuntimeException( ); }} );
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(null);

                //======== scrollPane1 ========
                {

                    //---- textArea1 ----
                    textArea1.setLineWrap(true);
                    textArea1.setWrapStyleWord(true);
                    textArea1.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
                    scrollPane1.setViewportView(textArea1);
                }
                contentPanel.add(scrollPane1);
                scrollPane1.setBounds(0, 0, 980, 245);

                //======== scrollPane2 ========
                {

                    //---- textArea2 ----
                    textArea2.setWrapStyleWord(true);
                    textArea2.setLineWrap(true);
                    textArea2.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
                    scrollPane2.setViewportView(textArea2);
                }
                contentPanel.add(scrollPane2);
                scrollPane2.setBounds(0, 250, 980, 225);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < contentPanel.getComponentCount(); i++) {
                        Rectangle bounds = contentPanel.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = contentPanel.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    contentPanel.setMinimumSize(preferredSize);
                    contentPanel.setPreferredSize(preferredSize);
                }
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0};

                //---- label1 ----
                label1.setText("Alt + 1 \u83b7\u53d6\u9f20\u6807\u9009\u4e2d\u6587\u672c\u5e76\u81ea\u52a8\u7ffb\u8bd1");
                buttonBar.add(label1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- okButton ----
                okButton.setText("\u7ffb\u8bd1");
                okButton.addActionListener(e -> okButtonActionPerformed(e));
                buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- label2 ----
                label2.setText("Alt + 2 \u622a\u56fe\u5e76\u7ffb\u8bd1");
                buttonBar.add(label2, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- button1 ----
                button1.setText("\u622a\u56fe\u7ffb\u8bd1");
                button1.addActionListener(e -> screenshotTranslation(e));
                buttonBar.add(button1, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
//        systemTray();
        initMyCode();
    }

    private void okButtonActionPerformed(ActionEvent e) {
            this.setVisible(true);
            new TaskTwo(textArea1,textArea2).execute();
    }

    public JTextArea getTextArea1() {
        return textArea1;
    }

    public void setTextArea1(JTextArea textArea1) {
        this.textArea1 = textArea1;
    }

    public JTextArea getTextArea2() {
        return textArea2;
    }

    public void setTextArea2(JTextArea textArea2) {
        this.textArea2 = textArea2;
    }

    private void initMyCode() {
        TraySettings.getINSTANCE().systemTray(this);
        TaskOne taskOne = new TaskOne(this, textArea1, textArea2);

        registerKeyUtils.registerKeys(taskOne, "alt 1");
        registerKeyUtils.registerKeys("alt 2", new HotKeyListener() {
            @Override
            public void onHotKey(HotKey hotKey) {
                button1.doClick();
            }
        });
        this.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                System.out.println("2");
            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                MainApp.this.setVisible(false);
            }
        });
        setTitle("领航员号翻译工具");
        setResizable(false);
        try {
            setIconImage(ImageIO.read(this.getClass().getClassLoader().getResource("image/resizeApi.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
