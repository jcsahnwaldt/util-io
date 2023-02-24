package jcsahnwaldt.util.io.basic;

import java.io.IOException;

import jcsahnwaldt.util.io.DataHandler;
import jcsahnwaldt.util.io.ItemHandler;
import jcsahnwaldt.util.io.Items;
import jcsahnwaldt.util.io.MapMode;
import jcsahnwaldt.util.io.Types;

public abstract class BasicDataStream
{
  public static final String TAG_PREFIX = "";
  public static final String TYPE_TAG_PREFIX = "typeIds:";
  public static final String ITEM_TAG_PREFIX = "itemIds:";

  final Items<?> items;
  final Types<?,ItemHandler> types;
  final BasicHandlers handlers;

  protected BasicDataStream(BasicHandlers handlers, MapMode mode) {
    this.items = handlers.itemId.items(mode);
    this.types = handlers.typeId.types();
    this.handlers = handlers;
  }

  public void init()
  throws IOException {
    handlers.inject(this);
  }

  protected abstract void inject(DataHandler handler);
}