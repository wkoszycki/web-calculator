package com.cinemacity.calculator;

/**
 *
 * @author Wojciech Koszycki <wojciech.koszycki@gmail.com>
 */
public class TestUtil {

    public static final String[] positiveCases = new String[]{"1+1*{69/1}-1", "1+1*(69/1)-1", "1+1*[69/1]-1", "123-54", "98*5-421"};
    public static final String[] negativeCases = new String[]{"cos1+1", "12a3-54", " 98[*5", "23-4*(50/\\n8)+[43+{5]"};
}
