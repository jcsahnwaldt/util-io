package jcsahnwaldt.util.io.basic.text;

import java.io.EOFException;
import java.io.IOException;

import jcsahnwaldt.util.io.IntHandler;
import jcsahnwaldt.util.io.ItemHandler;
import jcsahnwaldt.util.io.basic.CompactHandler;

public class TextIntHandler
extends AbstractTextHandler
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
    out.write(Integer.toString(item));
    out.write('\n');
  }
  
  @Override
  public int readInt()
  throws IOException {
    String item = in.readLine();
    if (item == null) throw new EOFException();
    return Integer.parseInt(item);
  }

}