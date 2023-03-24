package jcsahnwaldt.util.io.basic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jcsahnwaldt.util.io.ItemHandler;

public class BasicListHandler
extends AbstractBasicHandler
implements ItemHandler
{
  @Override
  public void writeItem(Object item)
  throws IOException {
    List<?> list = (List<?>) item;
    output.handlers.size.writeInt(list.size());
    for (Object child: list) {
      output.writeItem(child);
    }
  }

  @Override
  public Object readItem()
  throws IOException {
    int count = input.handlers.size.readInt();
    List<Object> list = new ArrayList<Object>(count);
    while (count-- > 0) {
      list.add(input.readItem());
    }
    return list;
  }

  @Override
  public boolean canHandle(Object item) {
    return item instanceof List;
  }

  @Override
  public Class<?> itemType() {
    return List.class;
  }

}
