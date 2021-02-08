package com.bluesky.util;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class StringUtils {
    /**
     * 取得选中的文本内容
     * @return
     * @throws IOException
     * @throws UnsupportedFlavorException
     */
    public static String getMouseSelectionString()
            throws IOException, UnsupportedFlavorException {
       return Toolkit.getDefaultToolkit()
               .getSystemSelection()
               .getData(DataFlavor.stringFlavor)
               .toString();
    }
}
