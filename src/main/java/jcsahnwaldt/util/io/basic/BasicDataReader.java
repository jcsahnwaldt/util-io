package jcsahnwaldt.util.io.basic;

import java.io.IOException;

import jcsahnwaldt.util.io.DataHandler;
import jcsahnwaldt.util.io.DataReader;
import jcsahnwaldt.util.io.ItemHandler;
import jcsahnwaldt.util.io.Items;
import jcsahnwaldt.util.io.MapMode;
import jcsahnwaldt.util.io.Types;

public class BasicDataReader
extends BasicDataStream
implements DataReader
{
  public BasicDataReader(BasicHandlers handlers) {
    // use MapMode.NONE - we retrieve items by id, not ids by item
    super(handlers, MapMode.NONE);
  }
  
  @Override
  protected void inject(DataHandler handler) {
    handler.setReader(this);
  }

  private void register(ItemHandler handler) {
    handler.setReader(this);
    types.add(handler);
  }
  
  @Override
  public void init()
  throws IOException {
    super.init();
    handlers.tag.readTag(TAG_PREFIX);
    handlers.typeId.readTag(TYPE_TAG_PREFIX);
    handlers.itemId.readTag(ITEM_TAG_PREFIX);
    int count = handlers.size.readInt();
    while (count-- > 0) {
      String name = handlers.string.readString();
      ItemHandler handler;
      try {
        handler = (ItemHandler) Class.forName(name).getDeclaredConstructor().newInstance();
      }
      catch (Exception e) {
        throw new IllegalArgumentException("cannot load handler type "+name, e);
      }
      register(handler);
    }
  }

  @Override
  public Object readItem()
  throws IOException {
    return readItem(types, items);
  }

  private <T,I> Object readItem(Types<T,ItemHandler> types, Items<I> items)
  throws IOException {
    T typeId = types.readId();
    ItemHandler handler = types.get(typeId);
    if (handler instanceof CompactHandler) {
      // optimization: no ids for null and primitive types, save space and time
      return handler.readItem();
    }
    else {
      I itemId = items.readId();
      if (items.isNew(itemId)) {
        Object item = handler.readItem();
        items.add(itemId, item);
        return item;
      }
      else {
        return items.item(itemId);
      }
    }
  }

}