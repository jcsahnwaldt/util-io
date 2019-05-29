package jcsahnwaldt.util.io.plist;

import java.io.IOException;

class PlistNullHandler
extends PlistItemHandler
{
  @Override
  public void writeItem(Object item)
  throws IOException {
    if (item != null) throw new IllegalArgumentException(item.getClass().getName());
    out.val("$null");
  }

  @Override
  public Object readItem()
  throws IOException {
    throw new UnsupportedOperationException();
    // return null;
  }

  @Override
  public boolean canHandle(Object item) {
    return item == null;
  }
  
  @Override
  public Class<?> itemType() {
    return null;
  }
  
}