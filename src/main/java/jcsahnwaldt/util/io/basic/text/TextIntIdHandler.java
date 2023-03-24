package jcsahnwaldt.util.io.basic.text;

import java.io.EOFException;
import java.io.IOException;

import jcsahnwaldt.util.io.*;

public class TextIntIdHandler
extends AbstractTextTagHandler
implements IdDataHandler<Integer>
{
  private final boolean requireId;

  public TextIntIdHandler(boolean requireId) {
    this.requireId = requireId;
  }

  @Override
  public void writeId(Integer id)
  throws IOException {
    if (id != null) output.write(id.toString());
    else if (requireId) throw new IllegalArgumentException("null/empty id not allowed");
    output.write('\n');
  }

  @Override
  public Integer readId()
  throws IOException {
    String id = input.readLine();
    if (id == null) throw new EOFException();
    else if (! id.isEmpty()) return Integer.valueOf(id);
    else if (requireId) throw new IllegalArgumentException("null/empty id not allowed");
    else return null;
  }

  @Override
  public Items<Integer> items(MapMode mode) {
    return new IntItems(this, mode);
  }

  @Override
  public <H extends ItemHandler> Types<Integer, H> types() {
    return new IntTypes<H>(this);
  }

  @Override
  protected String tag(String prefix) {
    return prefix + (requireId ? "IntID/1.2;required" : "IntID/1.2;optional");
  }

}