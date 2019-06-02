package jcsahnwaldt.util.io.basic;

import java.io.IOException;

import jcsahnwaldt.util.io.DataHandler;
import jcsahnwaldt.util.io.DataWriter;
import jcsahnwaldt.util.io.ItemHandler;
import jcsahnwaldt.util.io.Items;
import jcsahnwaldt.util.io.MapMode;
import jcsahnwaldt.util.io.Types;

public class BasicDataWriter
extends BasicDataStream
implements DataWriter
{
  public BasicDataWriter(BasicHandlers handlers, MapMode mode) {
    super(handlers, mode);
    register(new BasicNullHandler());
    register(new BasicListHandler());
    register(new BasicMapHandler());
  }
  
  @Override
  protected void inject(DataHandler handler) {
    handler.setWriter(this);
  }

  public void register(ItemHandler handler) {
    handler.setWriter(this);
    types.add(handler);
  }
  
  @Override
  public void init()
  throws IOException {
    super.init();
    handlers.tag.writeTag(TAG_PREFIX);
    handlers.typeId.writeTag(TYPE_TAG_PREFIX);
    handlers.itemId.writeTag(ITEM_TAG_PREFIX);
    handlers.size.writeInt(types.count());
    for (ItemHandler handler: types) {
      handlers.string.writeString(handler.getClass().getName());
    }
  }
  
  @Override
  public void writeItem(Object item)
  throws IOException {
    writeItem(item, types, items);
  }

  private <T,I> void writeItem(Object item, Types<T,ItemHandler> types, Items<I> items)
  throws IOException {
    T typeId = types.id(item);
    types.writeId(typeId);
    ItemHandler handler = types.get(typeId);
    if (handler instanceof CompactHandler) {
      // optimization: no ids for null and primitive types, save space and time
      handler.writeItem(item);
    }
    else {
      I itemId = items.id(item);
      items.writeId(itemId);
      if (items.isNew(itemId)) {
        handler.writeItem(item);
        items.add(itemId, item);
      }
    }
  }
  
}