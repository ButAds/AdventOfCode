package com.dissi.adventofcode.version2021.day16;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PacketUtils {

    public String toBinaryStringNotation(String hex) {
        hex = hex.replace("0", "0000");
        hex = hex.replace("1", "0001");
        hex = hex.replace("2", "0010");
        hex = hex.replace("3", "0011");
        hex = hex.replace("4", "0100");
        hex = hex.replace("5", "0101");
        hex = hex.replace("6", "0110");
        hex = hex.replace("7", "0111");
        hex = hex.replace("8", "1000");
        hex = hex.replace("9", "1001");
        hex = hex.replace("A", "1010");
        hex = hex.replace("B", "1011");
        hex = hex.replace("C", "1100");
        hex = hex.replace("D", "1101");
        hex = hex.replace("E", "1110");
        hex = hex.replace("F", "1111");
        return hex;
    }


    public static boolean binToBool(String s, int start) {
        return s.charAt(start) == '1';
    }

    public static int binToInt(String s, int start, int length) {
        return Integer.parseInt(s.substring(start, start + length), 2);
    }

    public static long binToLong(String s, int start, int length) {
        return Long.parseLong(s.substring(start, start + length), 2);
    }

}
