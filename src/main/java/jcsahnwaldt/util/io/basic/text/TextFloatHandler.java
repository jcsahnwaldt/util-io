package jcsahnwaldt.util.io.basic.text;

import java.io.EOFException;
import java.io.IOException;

import jcsahnwaldt.util.io.FloatHandler;
import jcsahnwaldt.util.io.ItemHandler;
import jcsahnwaldt.util.io.basic.CompactHandler;

public class TextFloatHandler
extends AbstractTextHandler
implements ItemHandler, FloatHandler, CompactHandler
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
  public boolean canHandle(Object item) {
    return item instanceof Float;
  }

  @Override
  public Class<?> itemType() {
    return float.class;
  }

  @Override
  public void writeFloat(float item)
  throws IOException {
    output.write(Float.toString(item));
    output.write('\n');
  }

  @Override
  public float readFloat()
  throws IOException {
    String item = input.readLine();
    if (item == null) throw new EOFException();
    return Float.parseFloat(item);
  }

}