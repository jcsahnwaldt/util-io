package jcsahnwaldt.util.io.basic;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import jcsahnwaldt.util.io.ItemHandler;

public class BasicMapHandler
extends AbstractBasicHandler
implements ItemHandler
{
  @Override
  public void writeItem(Object item)
  throws IOException {
    Map<?,?> map = (Map<?, ?>) item;
    output.handlers.size.writeInt(map.size());
    for (Entry<?,?> entry: map.entrySet()) {
      output.writeItem(entry.getKey());
      output.writeItem(entry.getValue());
    }
  }

  @Override
  public Object readItem()
  throws IOException {
    int count = input.handlers.size.readInt();
    Map<Object, Object> map = new LinkedHashMap<Object, Object>(count*4/3); // load factor is 0.75
    while (count-- > 0) {
      map.put(input.readItem(), input.readItem());
    }
    return map;
  }

  @Override
  public boolean canHandle(Object item) {
    return item instanceof Map;
  }

  @Override
  public Class<?> itemType() {
    return Map.class;
  }

}
