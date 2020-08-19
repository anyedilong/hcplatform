package com.java.until;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

public class Base64Util {

    private final static Base64.Decoder decoder = Base64.getDecoder();
    private final static Base64.Encoder encoder = Base64.getEncoder();

    //base64解码
    public static InputStream encoder (String str) {
        if (StringUtils.isBlank(str))
            return null;
        str = str.replaceAll("data:image/png;base64,", "")
                .replaceAll("\n", "")
                .replaceAll("\\\\", "");
        byte[] bytes = decoder.decode(str);
        return new ByteArrayInputStream(bytes);
    }

    //base64编码
    public static String decoder (InputStream in) throws IOException {
        if (in == null)
            return null;
        int len = in.available();
        //定义数组
        byte [] bytes = new byte[len];
        //读取到数组里面
        in.read(bytes);
        //将字节码转化为base64的字符串
        return Base64.getEncoder().encodeToString(bytes);
    }
}
