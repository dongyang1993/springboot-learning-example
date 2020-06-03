package org.springboot.encrypt.jdk;

public class HexUtil {

    private static final char[] hexDigits = "0123456789abcdef".toCharArray();

    public static String byteToHex(byte[] data) {
        StringBuilder sb = new StringBuilder(2 * data.length);
        for (byte b : data) {
            /**
             * 效果等同
             * sb.append(Integer.toHexString(b & 0xff));
             */
            sb.append(hexDigits[(b >> 4) & 0xf]).append(hexDigits[b & 0xf]);
        }
        return sb.toString();
    }

    public static byte[] hexToByte(String data) {
        if (data.length() % 2 != 0) {
            throw new IllegalArgumentException("Expected a string of even length");
        }
        int size = data.length() / 2;
        byte[] bytes = new byte[size];
        for (int i = 0; i < size; i++) {
            int hi = Character.digit(data.charAt(2 * i), 16);
            int lo = Character.digit(data.charAt(2 * i + 1), 16);
            if ((hi == -1) || (lo == -1)) {
                throw new IllegalArgumentException("input is not hexadecimal");
            }
            bytes[i] = (byte) (16 * hi + lo);
        }
        return bytes;
    }

    public static void main(String[] args) {
        System.out.println(byteToHex("Hello".getBytes()));
        System.out.println(new String(hexToByte(byteToHex("Hello".getBytes()))));;
    }

}
