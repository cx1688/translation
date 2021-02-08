package com.bluesky.util;

import com.bluesky.interfaces.KeyMethod;
import com.tulskiy.keymaster.common.HotKey;
import com.tulskiy.keymaster.common.HotKeyListener;
import com.tulskiy.keymaster.common.Provider;

import javax.swing.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RegisterKeyUtils {
    private static final RegisterKeyUtils INSTANCE = new RegisterKeyUtils();
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    boolean flag = true;
    Provider provider = Provider.getCurrentProvider(false);
    private RegisterKeyUtils() {
    }

    public static RegisterKeyUtils getINSTANCE() {
        return INSTANCE;
    }

    public void registerKeys(KeyMethod keyMethod,String keys) {
        provider.register(KeyStroke.getKeyStroke(keys), new HotKeyListener() {
            @Override
            public void onHotKey(HotKey hotKey) {
                executorService.execute(() -> {
                    keyMethod.execute();
                });
            }
        });
    }
    public void registerKeys(String keys,HotKeyListener hotKeyListener){
        provider.register(KeyStroke.getKeyStroke(keys),hotKeyListener);
    }
}
