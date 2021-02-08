package com.bluesky.interfaces.impl;

import com.bluesky.config.PictureRecognitionApi;
import com.bluesky.glabol.GlobalConfig;
import com.bluesky.interfaces.KeyMethod;
import com.bluesky.util.HttpUtils;
import com.bluesky.util.JsonUtils;
import com.bluesky.windows.MainApp;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.Map;

public class TaskThree implements KeyMethod {

    private byte[] bytes;
    private MainApp mainApp;

    public TaskThree(byte[] bytes, MainApp mainApp) {
        this.bytes =bytes;
        this.mainApp = mainApp;
    }
    @Override
    public void execute() {
        String mypic = HttpUtils.post(PictureRecognitionApi.T.getUrl(), bytes, "mypic", LocalDateTime.now() + ".png");
        try {
            Map<String, String> o = JsonUtils.parseObejct(mypic, Map.class);
            mainApp.getTextArea1().setText(o.get("text"));
            System.out.println(mainApp.isShowing());
            System.out.println(mainApp.isActive());
            if (!mainApp.isShowing()) {
                mainApp.setVisible(true);
            }else{
                mainApp.setVisible(true);
            }
            new TaskTwo(mainApp.getTextArea1(), mainApp.getTextArea2()).execute();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
