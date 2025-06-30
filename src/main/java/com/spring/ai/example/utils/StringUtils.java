package com.spring.ai.example.utils;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.regex.Pattern;

public final class StringUtils {
    private static final Pattern pattern = Pattern.compile("^-?\\d+(\\.\\d+)?$");
    public static String EMPTY = "";

    private static final int INDEX_NOT_FOUND = -1;
    private StringUtils() {
    }

    public static String toString(final Object src) {
        return src == null ? EMPTY : src.toString();
    }

    public static String defaultString(final String src) {
        return isBlank(src) ? EMPTY : src;
    }

    public static String defaultIfBlank(final String src, final String defaultVal) {
        return isBlank(src) ? defaultVal : src;
    }

    public static String trimString(final String src) {
        return isBlank(src) ? EMPTY : src.trim();
    }

    public static String trimIfBlank(final String src, final String defaultVal) {
        return isBlank(src) ? defaultVal : src.trim();
    }

    public static boolean isBlank(final String src) {
        int strLen;
        if (src == null || (strLen = src.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(src.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static String leftPad(final String src, final int size, char pad) {
        String lpS = defaultString(src);
        int lpLen = lpS.length();
        if (lpLen == size) {
            return lpS;
        }
        if (lpS.length() > size) {
            return lpS.substring(lpS.length() - size);
        }
        int padLen = size - src.toCharArray().length;
        char[] chars = new char[padLen];
        for (int i = 0; i < padLen; i++) {
            chars[i] = pad;
        }
        return new String(chars) + src;
    }

    public static int length(final CharSequence cs) {
        return cs == null ? 0 : cs.length();
    }

    public static String substringBetween(final String src, final String open, final String close) {
        if (src == null || open == null || close == null) {
            return null;
        }
        final int start = src.indexOf(open);
        if (start != INDEX_NOT_FOUND) {
            final int end = src.indexOf(close, start + open.length());
            if (end != INDEX_NOT_FOUND) {
                return src.substring(start + open.length(), end);
            }
        }
        return EMPTY;
    }

    public static boolean isNumeric(String src) {
        return !isBlank(src) && pattern.matcher(src).matches();
    }

    public static List<String> list(String splitter, String src) {
        if(EMPTY.equals(splitter) && !isBlank(src)) {
            return Lists.newArrayList(src.split(splitter));
        }
        return Splitter.on(splitter).splitToList(defaultString(src));
    }


}
