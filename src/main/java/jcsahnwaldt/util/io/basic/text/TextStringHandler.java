package jcsahnwaldt.util.io.basic.text;

import java.io.EOFException;
import java.io.IOException;

import jcsahnwaldt.util.io.ItemHandler;
import jcsahnwaldt.util.io.StringHandler;

public class TextStringHandler
extends AbstractTextHandler
implements ItemHandler, StringHandler
{
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
    // FIXME: escape line breaks
    output.write(item);
    output.write('\n');
  }

  @Override
  public String readString()
  throws IOException {
    String item = input.readLine();
    if (item == null) throw new EOFException();
    // FIXME: unescape line breaks
    return item;
  }
}