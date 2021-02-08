/*
 * Created by JFormDesigner on Sun Feb 07 22:33:35 CST 2021
 */

package com.bluesky.windows;

import com.bluesky.config.ApiConfig;
import com.bluesky.util.HttpUtils;
import com.bluesky.util.JsonUtils;
import com.bluesky.util.StringUtils;
import com.tulskiy.keymaster.common.HotKey;
import com.tulskiy.keymaster.common.HotKeyListener;
import com.tulskiy.keymaster.common.Provider;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author unknown
 */
public class MainApp extends JFrame {
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    boolean flag = true;
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
    public MainApp() {
        initComponents();
    }

    public static void main(String[] args) {
        MainApp mainApp = new MainApp();
        mainApp.setResizable(false);
        mainApp.registerKeys();
        mainApp.setVisible(true);
        mainApp.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    }

    private void registerKeys() {
        Provider provider = Provider.getCurrentProvider(false);
        provider.register(KeyStroke.getKeyStroke("alt 1"), new HotKeyListener() {
            @Override
            public void onHotKey(HotKey hotKey) {
                executorService.execute(() -> {
                    String content = null;
                    try {
                        content = StringUtils.getMouseSelectionString();
                        textArea1.setText(content);
                        String result = HttpUtils.get(ApiConfig.YOUDAO_TRANSLATION_API.getUrl(), content);
                        System.out.println(result);
                        Map<String, Object> dataMap = JsonUtils.parseObejct(result, Map.class);
                        java.util.List<Object> list = (java.util.List<Object>) dataMap.get("translateResult");
                        java.util.List<Object> o = (java.util.List<Object>) list.get(0);
                        StringBuilder sb = new StringBuilder();
                        o.stream().forEach(t -> {
                            Map<String, Object> param = (Map<String, Object>) t;
                            sb.append(param.get("tgt")).append("\n");
                        });
                        textArea2.setText(sb.toString());
                        MainApp.this.setVisible(true);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (UnsupportedFlavorException e) {
                        e.printStackTrace();
                    }
                });
            }
        });
    }

    private void okButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        try {
            String result = HttpUtils.get(ApiConfig.YOUDAO_TRANSLATION_API.getUrl(), textArea1.getText());
            System.out.println(result);
            Map<String, Object> dataMap = JsonUtils.parseObejct(result, Map.class);
            java.util.List<Object> list = (java.util.List<Object>) dataMap.get("translateResult");
            java.util.List<Object> o = (java.util.List<Object>) list.get(0);
            StringBuilder sb = new StringBuilder();
            o.stream().forEach(t -> {
                Map<String, Object> param = (Map<String, Object>) t;
                sb.append(param.get("tgt")).append("\n");
            });
            textArea2.setText(sb.toString());
        } catch (Exception exception) {
        }
    }
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    private void initComponents() {
        try {
            BeautyEyeLNFHelper.launchBeautyEyeLNF();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.
                    swing.border.EmptyBorder(0, 0, 0, 0), "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax.swing.border
                    .TitledBorder.CENTER, javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dia\u006cog"
                    , java.awt.Font.BOLD, 12), java.awt.Color.red), dialogPane.getBorder
                    ()));
            dialogPane.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
                @Override
                public void propertyChange(java
                                                   .beans.PropertyChangeEvent e) {
                    if ("\u0062ord\u0065r".equals(e.getPropertyName())) throw new RuntimeException
                            ();
                }
            });
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(null);

                //======== scrollPane1 ========
                {

                    //---- textArea1 ----
                    textArea1.setLineWrap(true);
                    textArea1.setWrapStyleWord(true);
                    scrollPane1.setViewportView(textArea1);
                }
                contentPanel.add(scrollPane1);
                scrollPane1.setBounds(0, 0, 930, 245);

                //======== scrollPane2 ========
                {

                    //---- textArea2 ----
                    textArea2.setWrapStyleWord(true);
                    textArea2.setLineWrap(true);
                    scrollPane2.setViewportView(textArea2);
                }
                contentPanel.add(scrollPane2);
                scrollPane2.setBounds(0, 250, 930, 235);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for (int i = 0; i < contentPanel.getComponentCount(); i++) {
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
                ((GridBagLayout) buttonBar.getLayout()).columnWidths = new int[]{0, 80};
                ((GridBagLayout) buttonBar.getLayout()).columnWeights = new double[]{1.0, 0.0};

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
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        systemTray();
    }

    private void systemTray() {
        if (SystemTray.isSupported()) { // 判断系统是否支持托盘功能.
            // 创建托盘右击弹出菜单
            PopupMenu popupMenu = new PopupMenu();

            //创建弹出菜单中的退出项
            MenuItem itemDisplay = new MenuItem("显示/隐藏");
            MenuItem itemExit = new MenuItem("退出");

            itemDisplay.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (flag) {
                        MainApp.this.setVisible(false);
                        flag = false;
                    } else {
                        MainApp.this.setVisible(true);
                        flag = true;
                    }
                }
            });

            itemExit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
            popupMenu.add(itemDisplay);
            popupMenu.add(itemExit);
//            System.out.println(getClass().getClassLoader().getResource("image/resizeApi12.png"));
            //创建托盘图标
            ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("image/TrayIcon.ico"));
            // 创建图片对象
            TrayIcon trayIcon = new TrayIcon(icon.getImage(), "Scan Upload",
                    popupMenu);

            //这句话很重要，不然托盘图标不显示！！！
            trayIcon.setImageAutoSize(true);
            trayIcon.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MainApp.this.setVisible(true);
                }

            });

            //把托盘图标添加到系统托盘
            //这个可以点击关闭之后再放到托盘里面，在此是打开程序直接显示托盘图标了
            try {
                SystemTray.getSystemTray().add(trayIcon);
            } catch (AWTException e1) {
                e1.printStackTrace();
            }
        }
    }
}
