package jcsahnwaldt.util.io.plist;

import java.io.IOException;
import java.util.List;

public class PlistListHandler
extends PlistItemHandler
{
  @Override
  public void init()
  throws IOException {
    output.defClass(this, "NSArray");
  }

  @Override
  public void writeItem(Object item)
  throws IOException {
    List<?> list = (List<?>) item;
    List<?> children = output.writeItems(list);
    output.dict(true);
    output.refClass(this);
    output.writeRefs("NS.objects", children);
    output.dict(false);
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
