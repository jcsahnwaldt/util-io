package jcsahnwaldt.util.io;

public interface IdDataHandler<I>
extends TagHandler, IdHandler<I>
{
  public Items<I> items(MapMode mode);

  public <H extends ItemHandler> Types<I, H> types();
}