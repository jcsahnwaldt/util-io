package jcsahnwaldt.util.io.basic.binary;

import jcsahnwaldt.util.io.*;

public abstract class AbstractBinaryIdHandler
extends AbstractBinaryTagHandler
implements IdDataHandler<Integer>
{
  @Override
  public Items<Integer> items(MapMode mode) {
    return new IntItems(this, mode);
  }

  @Override
  public <H extends ItemHandler> Types<Integer, H> types() {
    return new IntTypes<H>(this);
  }

}