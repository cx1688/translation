package com.bluesky.interfaces.impl;

import com.bluesky.config.ApiConfig;
import com.bluesky.interfaces.KeyMethod;
import com.bluesky.util.HttpUtils;
import com.bluesky.util.JsonUtils;
import com.bluesky.util.StringUtils;
import com.bluesky.windows.MainApp;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.Map;

public class TaskOne implements KeyMethod {
    private JFrame jFrame;
    private JTextArea textArea1;
    private JTextArea textArea2;

    public TaskOne(JFrame jFrame, JTextArea textArea1, JTextArea textArea2) {
        this.jFrame = jFrame;
        this.textArea1 = textArea1;
        this.textArea2 = textArea2;
    }

    @Override
    public void execute() {
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
            jFrame.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedFlavorException e) {
            e.printStackTrace();
        }
    }
}
