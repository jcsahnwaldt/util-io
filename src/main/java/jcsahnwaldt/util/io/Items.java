package jcsahnwaldt.util.io;

public interface Items<I>
extends IdHandler<I>
{
  public void add(I id, Object item);

  public Object item(I id);

  public I id(Object item);

  public boolean isNew(I id);

}
