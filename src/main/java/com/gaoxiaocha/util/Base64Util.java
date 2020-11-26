package com.gaoxiaocha.util;

import net.iharder.Base64;

import java.io.*;
import java.util.UUID;

public class Base64Util {
    public Base64Util() throws FileNotFoundException {
    }

    public static String encodeBase64(byte[] buf) {
        return Base64.encodeBytes(buf);
    }

    public static void decodeBase64(String image, String filename) throws IOException {
        Base64.decodeToFile(image.replaceAll(" ","+"), filename);
    }
}
