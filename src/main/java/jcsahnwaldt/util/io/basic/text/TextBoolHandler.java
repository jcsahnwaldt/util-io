package jcsahnwaldt.util.io.basic.text;

import java.io.EOFException;
import java.io.IOException;

import jcsahnwaldt.util.io.BoolHandler;
import jcsahnwaldt.util.io.ItemHandler;
import jcsahnwaldt.util.io.basic.CompactHandler;

public class TextBoolHandler
extends AbstractTextHandler
implements ItemHandler, BoolHandler, CompactHandler
{
  @Override
  public void writeItem(Object item)
  throws IOException {
    boolean value = ((Boolean)item).booleanValue();
    writeBool(value);
  }
  
  @Override
  public Object readItem()
  throws IOException {
    boolean value = readBool();
    return Boolean.valueOf(value);
  }
  
  @Override
  public boolean canHandle(Object item) {
    return item instanceof Boolean;
  }
  
  @Override
  public Class<?> itemType() {
    return boolean.class;
  }
  
  @Override
  public void writeBool(boolean item)
  throws IOException {
    out.write(item ? "true" : "false");
    out.write('\n');
  }
  
  @Override
  public boolean readBool()
  throws IOException {
    String item = in.readLine();
    if (item == null) throw new EOFException();
    if (item.equals("true")) return true;
    if (item.equals("false")) return false;
    throw new IllegalArgumentException("unexpected boolean value ["+item+"]");
  }

}