import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.BufferedReader;
import java.io.IOException;

public class ScreenShotUtil extends JFrame {
    private static final ScreenShotUtil INSTANCE = new ScreenShotUtil();
    private BufferedImage image, tempImg, saveImage;
    private ToolWindows toolWindows;
    private int orgY, orgX, endX, endY;


    private ScreenShotUtil() {

    }

    public static final ScreenShotUtil getINSTANCE() {
        return INSTANCE;
    }

    public static void main(String[] args) throws IOException, UnsupportedFlavorException {
        ScreenShotUtil screenShotUtil = ScreenShotUtil.getINSTANCE();
        screenShotUtil.init();
    }

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
                if (toolWindows != null) {
                    toolWindows.setVisible(false);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (toolWindows == null) {
                    toolWindows = new ToolWindows(ScreenShotUtil.this, e.getX(), e.getY());
                } else {
                    toolWindows.setLocation(e.getX(), e.getY());
                }
                toolWindows.setVisible(true);
                toolWindows.toFront();
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
}


class ToolWindows extends JFrame {
    private ScreenShotUtil screenShotUtil;
    private BufferedImage image;
    public ToolWindows(ScreenShotUtil screenShotUtil, int x, int y) {
        this.screenShotUtil = screenShotUtil;
        this.init();
        this.pack();
        this.setVisible(true);

    }

    private void init() {
        this.setLayout(new CardLayout());
        JLabel imageLabel = new JLabel();
        this.image = screenShotUtil.getSaveImage();
        int width = image.getWidth();
        int height =image.getHeight();
        imageLabel.setBounds(0,20,width,height);
        imageLabel.setIcon(new ImageIcon(image));
        this.setBounds(0,0,width,height+20);
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
        JToolBar toolBar = new JToolBar("Java 截图");
        //保存按钮
        JButton saveButton = new JButton("保存");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        toolBar.add(saveButton);
        //关闭按钮
        JButton literacy = new JButton("识字");



        literacy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
                ToolWindows.this.dispose();
                screenShotUtil.setVisible(true);
            }
        });
        toolBar.add(cancel);
        toolBar.setVisible(true);
        this.add(imageLabel);
        this.add(toolBar);

    }
//    @Override
//    public void paint(Graphics g) {
//        g.drawImage(image, 0, 0, this);
//    }
}


