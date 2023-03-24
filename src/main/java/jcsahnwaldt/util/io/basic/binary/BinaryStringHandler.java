package jcsahnwaldt.util.io.basic.binary;

import java.io.EOFException;
import java.io.IOException;

import jcsahnwaldt.util.io.DataReader;
import jcsahnwaldt.util.io.DataWriter;
import jcsahnwaldt.util.io.ItemHandler;
import jcsahnwaldt.util.io.StringHandler;

public class BinaryStringHandler
extends AbstractBinaryHandler
implements ItemHandler, StringHandler
{
  private final BinaryIntHandler intHandler = new BinaryIntHandler();

  @Override
  public void setWriter(DataWriter output) {
    super.setWriter(output);
    intHandler.setWriter(output);
  }

  @Override
  public void setReader(DataReader input) {
    super.setReader(input);
    intHandler.setReader(input);
  }

  @Override
  public void writeItem(Object item)
  throws IOException {
    writeString((String)item);
  }

  @Override
  public Object readItem()
  throws IOException {
    return readString();
  }

  @Override
  public boolean canHandle(Object item) {
    return item instanceof String;
  }

  @Override
  public Class<?> itemType() {
    return String.class;
  }

  @Override
  public void writeString(String item)
  throws IOException {
    byte[] bytes = item.getBytes("UTF-8");
    intHandler.writeInt(bytes.length);
    output.write(bytes);
  }

  @Override
  public String readString()
  throws IOException {
    int length = intHandler.readInt();
    byte[] bytes = new byte[length];
    int offset = 0;
    for (;;) {
      if (length == 0) break;
      int found = input.read(bytes, offset, length);
      if (found == -1) throw new EOFException();
      offset += found;
      length -= found;
    }
    return new String(bytes, "UTF-8");
  }

}