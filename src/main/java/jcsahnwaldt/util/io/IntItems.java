package jcsahnwaldt.util.io;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class IntItems
extends DelegatingIdHandler<Integer>
implements Items<Integer>
{
  private final List<Object> objects = new ArrayList<Object>();
  private final Map<Object, Integer> ids;
  
  public IntItems(IdHandler<Integer> handler, MapMode mode) {
    super(handler);
    ids = mode.map();
  }

  @Override
  public void add(Integer id, Object item) {
    if (id != null) throw new IllegalArgumentException("expected null id, got ["+id+"]");
    if (ids != null) ids.put(item, Integer.valueOf(objects.size()));
    objects.add(item);
  }

  @Override
  public Object item(Integer id) {
    return objects.get(id.intValue());
  }

  @Override
  public Integer id(Object item) {
    if (ids == null) throw new UnsupportedOperationException("no item map configured");
    return ids.get(item);
  }

  @Override
  public boolean isNew(Integer id) {
    return id == null;
  }

}
