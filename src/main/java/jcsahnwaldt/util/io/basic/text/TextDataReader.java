package jcsahnwaldt.util.io.basic.text;

import java.io.BufferedReader;
import java.io.Reader;

import jcsahnwaldt.util.io.basic.BasicDataReader;

public class TextDataReader
extends BasicDataReader
{
  public static TextDataReader intIds(Reader input) {
    return new TextDataReader(input, TextHandlers.intIds());
  }

  public static TextDataReader stringIds(Reader input) {
    return new TextDataReader(input, TextHandlers.stringIds());
  }

  /*package*/ final BufferedReader input;

  public TextDataReader(Reader input, TextHandlers handlers) {
    super(handlers);
    this.input = input instanceof BufferedReader ? (BufferedReader) input : new BufferedReader(input);
  }
}