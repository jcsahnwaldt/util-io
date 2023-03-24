package jcsahnwaldt.util.io.basic.text;

import java.io.Writer;

import jcsahnwaldt.util.io.MapMode;
import jcsahnwaldt.util.io.basic.BasicDataWriter;

public class TextDataWriter
extends BasicDataWriter
{
  public static TextDataWriter intIds(Writer output, MapMode mode) {
    return new TextDataWriter(output, TextHandlers.intIds(), mode);
  }

  public static TextDataWriter stringIds(Writer output, MapMode mode) {
    return new TextDataWriter(output, TextHandlers.stringIds(), mode);
  }

  /*package*/ final Writer output;

  public TextDataWriter(Writer output, TextHandlers handlers, MapMode mode) {
    super(handlers, mode);
    this.output = output;
    register(new TextBoolHandler());
    register(new TextIntHandler());
    register(new TextFloatHandler());
    register(new TextStringHandler());
  }

}