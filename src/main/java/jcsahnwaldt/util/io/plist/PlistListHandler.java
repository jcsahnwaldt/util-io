package jcsahnwaldt.util.io.plist;

import java.io.IOException;
import java.util.List;

public class PlistListHandler
extends PlistItemHandler
{
  @Override
  public void init()
  throws IOException {
    out.defClass(this, "NSArray");
  }

  @Override
  public void writeItem(Object item)
  throws IOException {
    List<?> list = (List<?>) item;
    List<?> children = out.writeItems(list);
    out.dict(true);
    out.refClass(this);
    out.writeRefs("NS.objects", children);
    out.dict(false);
  }

  @Override
  public Object readItem()
  throws IOException {
    throw new UnsupportedOperationException();
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
