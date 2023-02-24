package jcsahnwaldt.util.io.basic.text;

import java.io.EOFException;
import java.io.IOException;

import jcsahnwaldt.util.io.*;

public class TextStringIdHandler
extends AbstractTextTagHandler
implements IdDataHandler<String>
{
  private final boolean requireId;

  public TextStringIdHandler(boolean requireId) {
    this.requireId = requireId;
  }

  @Override
  public void writeId(String itemId)
  throws IOException {
    if (itemId != null) out.write(itemId);
    else if (requireId) throw new IllegalArgumentException("null/empty id not allowed");
    out.write('\n');
  }

  @Override
  public String readId()
  throws IOException {
    String itemId = in.readLine();
    if (itemId == null) throw new EOFException();
    else if (! itemId.isEmpty()) return itemId;
    else if (requireId) throw new IllegalArgumentException("null/empty id not allowed");
    else return null;
  }

  @Override
  public Items<String> items(MapMode mode) {
    return new StringItems(this, mode);
  }

  @Override
  public <H extends ItemHandler> Types<String, H> types() {
    return new StringTypes<H>(this);
  }

  @Override
  protected String tag(String prefix) {
    return prefix + (requireId ? "StringId/1.2;required" : "StringId/1.2;optional");
  }

}