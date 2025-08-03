package com.automationexercices.utils;

import com.automationexercices.utils.dataReader.PropertyReader;

public class OSUtils {
    public enum OS { WINDOWS, MAC, LINUX, OTHER }

    public static OS getCurrentOS() {
        String os = PropertyReader.getProperty("os.name").toLowerCase();
        if (os.contains("win")) return OS.WINDOWS;
        if (os.contains("mac")) return OS.MAC;
        if (os.contains("nix") || os.contains("nux")) return OS.LINUX;
        return OS.OTHER;
    }
}
