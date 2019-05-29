package jcsahnwaldt.util.io;

public interface Types<I, H extends ItemHandler>
extends Iterable<H>, IdHandler<I>
{
  public int count();
  
  public void add(H handler);

  public H get(I id);

  public I id(Object item);

  public H find(Object item);
}
