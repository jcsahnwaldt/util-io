package jcsahnwaldt.util.io.plist;

import java.io.IOException;

import jcsahnwaldt.util.io.StringHandler;

class PlistStringHandler
extends PlistItemHandler
implements StringHandler
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
  public void writeString(String val)
  throws IOException {
    output.val(val);
  }

  @Override
  public String readString() {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean canHandle(Object item) {
    return item instanceof String;
  }

  @Override
  public Class<?> itemType() {
    return String.class;
  }

}