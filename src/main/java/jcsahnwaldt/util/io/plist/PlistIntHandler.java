package jcsahnwaldt.util.io.plist;

import java.io.IOException;

import jcsahnwaldt.util.io.IntHandler;

final class PlistIntHandler
extends PlistItemHandler
implements IntHandler
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
  public void writeInt(int val)
  throws IOException {
    out.val(val);
  }
  
  @Override
  public int readInt()
  throws IOException {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean canHandle(Object item) {
    return item instanceof Integer;
  }
  
  @Override
  public Class<?> itemType() {
    return int.class;
  }
  
}