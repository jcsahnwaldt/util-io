package jcsahnwaldt.util.io.plist;

import java.io.IOException;

import jcsahnwaldt.util.io.FloatHandler;

final class PlistFloatHandler
extends PlistItemHandler
implements FloatHandler
{
  @Override
  public void writeItem(Object item)
  throws IOException {
    float value = ((Float)item).floatValue();
    writeFloat(value);
  }

  @Override
  public Object readItem()
  throws IOException {
    float value = readFloat();
    return Float.valueOf(value);
  }

  @Override
  public void writeFloat(float val)
  throws IOException {
    output.val(val);
  }

  @Override
  public float readFloat()
  throws IOException {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean canHandle(Object item) {
    return item instanceof Float;
  }

  @Override
  public Class<?> itemType() {
    return float.class;
  }

}