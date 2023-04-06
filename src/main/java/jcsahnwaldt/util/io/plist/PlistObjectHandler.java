package jcsahnwaldt.util.io.plist;

import java.io.IOException;
import java.util.List;

import jcsahnwaldt.util.io.DataReader;
import jcsahnwaldt.util.io.DataWriter;
import jcsahnwaldt.util.io.ItemHandler;

public class PlistObjectHandler
extends PlistItemHandler
{
  private final ItemHandler handler;

  private final String type;

  public PlistObjectHandler(ItemHandler handler, String type) {
    if (handler == null) throw new NullPointerException("handler");
    if (type == null) throw new NullPointerException("type");
    this.handler = handler;
    this.type = type;
  }

  @Override
  public void setWriter(DataWriter output) {
    super.setWriter(output);
    handler.setWriter(output);
  }

  @Override
  public void setReader(DataReader input) {
    super.setReader(input);
    handler.setReader(input);
  }

  @Override
  public void init()
  throws IOException {
    output.defClass(this, type);
  }

  @Override
  public void writeItem(Object item)
  throws IOException {
    output.push();
    handler.writeItem(item);
    List<?> children = output.pop();

    output.dict(true);
    output.refClass(this);
    int n = children.size();
    for (int i = 0; i < n; i++) {
      output.ref("$"+i, children.get(i));
    }
    output.dict(false);
  }

  @Override
  public Object readItem()
  throws IOException {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean canHandle(Object item) {
    return handler.canHandle(item);
  }

  @Override
  public Class<?> itemType() {
    return handler.itemType();
  }

}
