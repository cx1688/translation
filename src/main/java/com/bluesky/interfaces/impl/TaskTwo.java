package com.bluesky.interfaces.impl;

import com.bluesky.config.ApiConfig;
import com.bluesky.interfaces.KeyMethod;
import com.bluesky.util.HttpUtils;
import com.bluesky.util.JsonUtils;

import javax.swing.*;
import java.util.Map;

/**
 * 这个是按钮被点击后翻译的方法
 */
public class TaskTwo implements KeyMethod {
    private JTextArea textArea1;
    private JTextArea textArea2;

    public TaskTwo(JTextArea textArea1, JTextArea textArea2) {
        this.textArea1 = textArea1;
        this.textArea2 = textArea2;
    }
    @Override
    public void execute() {
        try {
            String result = HttpUtils.get(ApiConfig.YOUDAO_TRANSLATION_API.getUrl(), textArea1.getText());
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
}
