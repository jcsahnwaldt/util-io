package jcsahnwaldt.util.io.basic.binary;

import java.io.EOFException;
import java.io.IOException;

import jcsahnwaldt.util.io.IntHandler;
import jcsahnwaldt.util.io.ItemHandler;
import jcsahnwaldt.util.io.basic.CompactHandler;

public class BinaryIntHandler
extends AbstractBinaryHandler
implements ItemHandler, IntHandler, CompactHandler
{
  @Override
  public void writeItem(Object item)
  throws IOException {
    int value = ((Integer)item).intValue();
    writeInt(value);
  }

  @Override
  public Object readItem()
  throws IOException {
    int value = readInt();
    return Integer.valueOf(value);
  }

  @Override
  public boolean canHandle(Object item) {
    return item instanceof Integer;
  }

  @Override
  public Class<?> itemType() {
    return int.class;
  }

  @Override
  public void writeInt(int item)
  throws IOException {
    output.write((item >>> 24) & 0xFF);
    output.write((item >>> 16) & 0xFF);
    output.write((item >>>  8) & 0xFF);
    output.write((item >>>  0) & 0xFF);
  }

  @Override
  public int readInt()
  throws IOException {
    int b1 = input.read();
    int b2 = input.read();
    int b3 = input.read();
    int b4 = input.read();
    if ((b1 | b2 | b3 | b4) == -1) throw new EOFException();
    int item = ((b1 << 24) | (b2 << 16) | (b3 << 8) | (b4 << 0));
    return item;
  }

}