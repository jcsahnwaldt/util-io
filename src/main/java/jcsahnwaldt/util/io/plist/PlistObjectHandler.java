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
  public void setWriter(DataWriter out) {
    super.setWriter(out);
    handler.setWriter(out);
  }

  @Override
  public void setReader(DataReader in) {
    super.setReader(in);
    handler.setReader(in);
  }

  @Override
  public void init()
  throws IOException {
    out.defClass(this, type);
  }

  @Override
  public void writeItem(Object item)
  throws IOException {
    out.push();
    handler.writeItem(item);
    List<?> children = out.pop();
    
    out.dict(true);
    out.refClass(this);
    for (int i = 0; i < children.size(); i++) {
      out.ref("$"+i, children.get(i));
    }
    out.dict(false);
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
