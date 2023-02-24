package jcsahnwaldt.util.io.basic.text;

import java.io.Writer;

import jcsahnwaldt.util.io.MapMode;
import jcsahnwaldt.util.io.basic.BasicDataWriter;

public class TextDataWriter
extends BasicDataWriter
{
  public static TextDataWriter intIds(Writer out, MapMode mode) {
    return new TextDataWriter(out, TextHandlers.intIds(), mode);
  }

  public static TextDataWriter stringIds(Writer out, MapMode mode) {
    return new TextDataWriter(out, TextHandlers.stringIds(), mode);
  }

  /*package*/ final Writer out;

  public TextDataWriter(Writer out, TextHandlers handlers, MapMode mode) {
    super(handlers, mode);
    this.out = out;
    register(new TextBoolHandler());
    register(new TextIntHandler());
    register(new TextFloatHandler());
    register(new TextStringHandler());
  }

}