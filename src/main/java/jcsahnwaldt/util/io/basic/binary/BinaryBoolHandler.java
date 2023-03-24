package jcsahnwaldt.util.io.basic.binary;

import java.io.EOFException;
import java.io.IOException;

import jcsahnwaldt.util.io.BoolHandler;
import jcsahnwaldt.util.io.ItemHandler;
import jcsahnwaldt.util.io.basic.CompactHandler;

public class BinaryBoolHandler
extends AbstractBinaryHandler
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
    output.write(item ? 1 : 0);
  }

  @Override
  public boolean readBool()
  throws IOException {
    int b = input.read();
    if (b == -1) throw new EOFException();
    return b != 0;
  }

}