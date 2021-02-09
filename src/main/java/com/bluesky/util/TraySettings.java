package com.bluesky.util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

public class TraySettings {
    private static final TraySettings INSTANCE = new TraySettings();
    boolean flag = true;

    private TraySettings() {
    }

    public static TraySettings getINSTANCE() {
        return INSTANCE;
    }

    public void systemTray(JFrame jFrame) {
        if (SystemTray.isSupported()) { // 判断系统是否支持托盘功能.
            // 创建托盘右击弹出菜单
            PopupMenu popupMenu = new PopupMenu();

            //创建弹出菜单中的退出项
            MenuItem itemDisplay = new MenuItem("显示/隐藏");
            MenuItem itemExit = new MenuItem("退出");

            itemDisplay.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (jFrame.isVisible()) {
                        jFrame.setVisible(false);
                    } else {
                        jFrame.setVisible(true);
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
//            ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("image/resizeApi.png"));
            URL url = this.getClass().getClassLoader().getResource("image/resizeApi.png");
            BufferedImage image = null;
            try {
                image = ImageIO.read(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
//            Image image = Toolkit.getDefaultToolkit().getImage(read.get);
            // 创建图片对象
            TrayIcon trayIcon = new TrayIcon(image, "这是我的托盘程序",
                    popupMenu);

            //这句话很重要，不然托盘图标不显示！！！
            trayIcon.setImageAutoSize(true);
            trayIcon.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    jFrame.setVisible(true);
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
