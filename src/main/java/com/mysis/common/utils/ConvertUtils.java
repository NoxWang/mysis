package com.mysis.common.utils;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 类型转换工具类
 */
public class ConvertUtils {

    public static String utf8Str(Object obj) {
        return str(obj, StandardCharsets.UTF_8);
    }

    public static String str(Object obj, Charset charset) {
        if (null == obj) {
            return null;
        }

        if (obj instanceof String) {
            return (String) obj;
        } else if (obj instanceof byte[] || obj instanceof Byte[]) {
            return str((Byte[]) obj, charset);
        } else if (obj instanceof ByteBuffer) {
            return str((ByteBuffer) obj, charset);
        }
        return obj.toString();
    }
}
