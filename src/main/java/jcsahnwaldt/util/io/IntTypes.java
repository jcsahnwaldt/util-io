package jcsahnwaldt.util.io;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IntTypes<H extends ItemHandler>
extends DelegatingIdHandler<Integer>
implements Types<Integer, H>
{
  private final List<H> list = new ArrayList<H>();
  
  public IntTypes(IdHandler<Integer> handler) {
    super(handler);
  }

  @Override
  public int count() {
    return list.size();
  }
  
  @Override
  public void add(H handler) {
    list.add(handler);
  }

  @Override
  public H get(Integer id) {
    return list.get(id.intValue());
  }

  @Override
  public Integer id(Object item) {
    int id = 0;
    for (H handler: list) {
      if (handler.canHandle(item)) return Integer.valueOf(id);
      id++;
    }
    throw new IllegalArgumentException("no handler for type "+(item == null ? null : item.getClass()));
  }

  @Override
  public H find(Object item) {
    for (H handler: list) {
      if (handler.canHandle(item)) return handler;
    }
    throw new IllegalArgumentException("no handler for type "+(item == null ? null : item.getClass()));
  }

  @Override
  public Iterator<H> iterator() {
    return list.iterator();
  }
}
