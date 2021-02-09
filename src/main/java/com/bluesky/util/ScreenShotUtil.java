package com.bluesky.util;

import com.bluesky.interfaces.impl.TaskThree;
import com.bluesky.windows.MainApp;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

public class ScreenShotUtil extends JFrame {
    //    private static final ScreenShotUtil INSTANCE = new ScreenShotUtil();
    private BufferedImage image, tempImg, saveImage;
    private ImageWindows imageWindows;
    private int orgY, orgX, endX, endY;

    private MainApp mainApp;

    public ScreenShotUtil(MainApp mainApp) {
        this.mainApp = mainApp;
    }

//    public static final ScreenShotUtil getINSTANCE() {
//        return INSTANCE;
//    }


    @Override
    public void paint(Graphics g) {
        RescaleOp op = new RescaleOp(0.8f, 0f, null);
        tempImg = op.filter(image, image);
        g.drawImage(image, 0, 0, this);
    }

    public void init() {

        try {
            Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
            this.setBounds(0, 0, d.width, d.height);
            image = new Robot().createScreenCapture(new Rectangle(0, 0, d.width, d.height));
            this.setUndecorated(true);
            this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
            this.setVisible(true);
            //设置全屏
            this.getGraphicsConfiguration().getDevice().setFullScreenWindow(this);
            this.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentMoved(ComponentEvent e) {
                    setLocation(0, 0);
                }
            });
            this.getRootPane().registerKeyboardAction(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
        } catch (AWTException e) {
            e.printStackTrace();
        }

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                orgX = e.getX();
                orgY = e.getY();
                if (imageWindows != null) {
                    imageWindows.setVisible(false);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (imageWindows == null) {
                    imageWindows = new ImageWindows(ScreenShotUtil.this, e.getX(), e.getY());
                } else {
                    imageWindows.setLocation(e.getX(), e.getY());
                }
                imageWindows.setVisible(true);
                imageWindows.toFront();
                ScreenShotUtil.this.setVisible(false);
            }
        });
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                endX = e.getX();
                endY = e.getY();
                Image image2 = createImage(ScreenShotUtil.this.getWidth(), ScreenShotUtil.this.getHeight());

                Graphics g = image2.getGraphics();
                g.drawImage(tempImg, 0, 0, null);
                int x = Math.min(orgX, endX);
                int y = Math.min(orgY, endY);
                int width = Math.abs(endX - orgX) + 1;
                int height = Math.abs(endY - orgY) + 1;
                g.setColor(Color.RED);
                g.drawRect(x - 1, y - 1, width + 1, height + 1);
                saveImage = image.getSubimage(x, y, width, height);
                g.drawImage(saveImage, x, y, null);
                ScreenShotUtil.this.getGraphics().drawImage(image2, 0, 0, ScreenShotUtil.this);
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });
    }


    public BufferedImage getSaveImage() {
        return saveImage;
    }

    public void setSaveImage(BufferedImage saveImage) {
        this.saveImage = saveImage;
    }

    public MainApp getMainApp() {
        return mainApp;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}


class ImageWindows extends JFrame {
    private final ScreenShotUtil screenShotUtil;
    private BufferedImage image;
    private JFrame jFrame;

    public ImageWindows(ScreenShotUtil screenShotUtil, int x, int y) {
        this.screenShotUtil = screenShotUtil;
        this.init();
        this.pack();


    }

    private void init() {
        this.setLayout(new CardLayout());
//        this.setVisible(true);

//        this.setBounds(0,0,640,480);
        JLabel imageLabel = new JLabel();
        this.image = screenShotUtil.getSaveImage();
        JPanel jPanel = new JPanel();
        int width = image.getWidth();
        int height = image.getHeight();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = screenSize.width / 2 - width / 2;
        int centerY = screenSize.height / 2 - height / 2;
        imageLabel.setBounds(0, 0, width, height);
        imageLabel.setIcon(new ImageIcon(image));
        jPanel.setBounds(0, 0, width + 1, height + 1);
        jPanel.add(imageLabel);
        this.setBounds(0, 0, width, height);
//        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
        this.setBounds(centerX, centerY, jPanel.getWidth() + 2, image.getHeight());
        this.add(jPanel);

        this.setResizable(false);
        this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        this.setUndecorated(true);
        new ToolBarDto().init(screenShotUtil, this, image);
    }
//    @Override
//    public void paint(Graphics g) {
//        g.drawImage(image, 0, 0, this);
//    }
}

class ToolBarDto extends JWindow {

    public void init(ScreenShotUtil screenShotUtil, ImageWindows imageWindows, BufferedImage image) {
        this.setLayout(new CardLayout());
        this.setBounds(imageWindows.getX(), imageWindows.getY() - 50, 200, 33);
        this.setVisible(true);
        JToolBar toolBar = new JToolBar("截图工具");
        //保存按钮
        JButton saveButton = new JButton("保存");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (image != null) {
                    try {
                        FileOutputStream fileOutputStream = new FileOutputStream(new File(LocalDateTime.now() + ".png"));
                        ImageIO.write(image, "png", fileOutputStream);
                        imageWindows.dispose();
                        ToolBarDto.this.dispose();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        });
        toolBar.add(saveButton);
        //关闭按钮
        JButton literacy = new JButton("识字");


        literacy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                try {
                    ImageIO.write(image, "png", bos);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                new TaskThree(bos.toByteArray(), screenShotUtil.getMainApp()).execute();
                imageWindows.dispose();
                ToolBarDto.this.dispose();
            }
        });
        toolBar.add(literacy);

        JButton closeButton = new JButton("取消");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        toolBar.add(closeButton);

        JButton cancel = new JButton("返回");
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imageWindows.dispose();
                screenShotUtil.setVisible(true);
            }
        });
        toolBar.add(cancel);
//                this.setLocationRelativeTo(null);
//        toolBar.setBounds(0,jPanel.getHeight(),jPanel.getWidth(),30);
//        this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        this.add(toolBar);
    }
}
