package jcsahnwaldt.util.io.plist;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class PlistMapHandler
extends PlistItemHandler
{
  @Override
  public void init()
  throws IOException {
    output.defClass(this, "NSDictionary");
  }

  @Override
  public void writeItem(Object item)
  throws IOException {
    Map<?,?> map = (Map<?, ?>) item;
    List<?> keys = output.writeItems(map.keySet());
    List<?> vals = output.writeItems(map.values());
    output.dict(true);
    output.refClass(this);
    output.writeRefs("NS.keys", keys);
    output.writeRefs("NS.objects", vals);
    output.dict(false);
  }

  @Override
  public Object readItem()
  throws IOException {
    throw new UnsupportedOperationException();
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
