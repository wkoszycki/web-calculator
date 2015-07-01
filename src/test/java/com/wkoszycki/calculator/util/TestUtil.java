package com.wkoszycki.calculator.util;

//TODO: Find a better name ...
public class TestUtil {

  public static final String[]
      positiveCases =
      new String[]{"1+1*{69/1}-1", "1+1*(69/1)-1", "1+1*[69/1]-1", "123-54", "98*5-421",
                   "98.20*5-421-1"};
  public static final String[]
      negativeCases =
      new String[]{"some1+1", "12a3-54", " 98[*5", "23-4*(50/\\n8)+[43+{5]", ":98[*5"};
}
