package jcsahnwaldt.util.io.basic;

import jcsahnwaldt.util.io.ItemHandler;

public class BasicNullHandler
extends AbstractBasicHandler
implements ItemHandler, CompactHandler
{
  @Override
  public void writeItem(Object item) {
    if (item != null) throw new IllegalArgumentException(item.getClass().getName());
  }

  @Override
  public Object readItem() {
    return null;
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
