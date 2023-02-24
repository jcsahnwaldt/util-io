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
    out.defClass(this, "NSDictionary");
  }

  @Override
  public void writeItem(Object item)
  throws IOException {
    Map<?,?> map = (Map<?, ?>) item;
    List<?> keys = out.writeItems(map.keySet());
    List<?> vals = out.writeItems(map.values());
    out.dict(true);
    out.refClass(this);
    out.writeRefs("NS.keys", keys);
    out.writeRefs("NS.objects", vals);
    out.dict(false);
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
