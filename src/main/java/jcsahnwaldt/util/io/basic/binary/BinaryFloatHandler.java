package jcsahnwaldt.util.io.basic.binary;

import java.io.EOFException;
import java.io.IOException;

import jcsahnwaldt.util.io.FloatHandler;
import jcsahnwaldt.util.io.ItemHandler;
import jcsahnwaldt.util.io.basic.CompactHandler;

public class BinaryFloatHandler
extends AbstractBinaryHandler
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
    int bits = Float.floatToIntBits(item);
    out.write((bits >>> 24) & 0xFF);
    out.write((bits >>> 16) & 0xFF);
    out.write((bits >>>  8) & 0xFF);
    out.write((bits >>>  0) & 0xFF);
  }
  
  @Override
  public float readFloat()
  throws IOException {
    int b1 = in.read();
    int b2 = in.read();
    int b3 = in.read();
    int b4 = in.read();
    if ((b1 | b2 | b3 | b4) == -1) throw new EOFException();
    int bits = ((b1 << 24) | (b2 << 16) | (b3 << 8) | (b4 << 0));
    return Float.intBitsToFloat(bits);
  }

}