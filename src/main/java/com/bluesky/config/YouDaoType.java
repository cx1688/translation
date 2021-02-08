package com.bluesky.config;

public enum YouDaoType {
    ZH_CN2EN("中文　»　英语", 1),
    ZH_CN2JA("中文　»　日语", 2),
    ZH_CN2KR("中文　»　韩语", 3),
    ZH_CN2FR("中文　»　法语", 4),
    ZH_CN2RU("中文　»　俄语", 5),
    ZH_CN2SP("中文　»　西语", 6),
    EN2ZH_CN("英语　»　中文", 7),
    JA2ZH_CN("日语　»　中文", 8),
    KR2ZH_CN("韩语　»　中文", 9),
    FR2ZH_CN("法语　»　中文", 10),
    RU2ZH_CN("俄语　»　中文", 11),
    SP2ZH_CN("西语　»　中文", 12);

    private String name;
    private int index;

    YouDaoType(String name, int index) {
        this.name = name;
        this.index = index;
    }
}
