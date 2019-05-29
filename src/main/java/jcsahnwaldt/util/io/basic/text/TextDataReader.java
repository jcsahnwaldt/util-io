package jcsahnwaldt.util.io.basic.text;

import java.io.BufferedReader;
import java.io.Reader;

import jcsahnwaldt.util.io.basic.BasicDataReader;

public class TextDataReader
extends BasicDataReader
{
  public static TextDataReader intIds(Reader in) {
    return new TextDataReader(in, TextHandlers.intIds());
  }
  
  public static TextDataReader stringIds(Reader in) {
    return new TextDataReader(in, TextHandlers.stringIds());
  }
  
  /*package*/ final BufferedReader in;
  
  public TextDataReader(Reader in, TextHandlers handlers) {
    super(handlers);
    this.in = in instanceof BufferedReader ? (BufferedReader)in : new BufferedReader(in);
  }
}