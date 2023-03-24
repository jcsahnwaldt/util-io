package jcsahnwaldt.util.io.plist;

import java.io.IOException;

import jcsahnwaldt.util.io.BoolHandler;

class PlistBoolHandler
extends PlistItemHandler
implements BoolHandler
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
  public void writeBool(boolean val)
  throws IOException {
    output.val(val);
  }

  @Override
  public boolean readBool()
  throws IOException {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean canHandle(Object item) {
    return item instanceof Boolean;
  }

  @Override
  public Class<?> itemType() {
    return boolean.class;
  }

}