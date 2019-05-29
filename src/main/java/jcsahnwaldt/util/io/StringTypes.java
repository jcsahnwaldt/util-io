package jcsahnwaldt.util.io;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class StringTypes<H extends ItemHandler>
extends DelegatingIdHandler<String>
implements Types<String, H>
{
  private final Map<String,H> map = new LinkedHashMap<String,H>();
  
  public StringTypes(IdHandler<String> handler) {
    super(handler);
  }

  @Override
  public int count() {
    return map.size();
  }
  
  @Override
  public void add(H handler) {
    Class<?> type = handler.itemType();
    String id = type == null ? "null" : type.getSimpleName();
    if (map.put(id, handler) != null) throw new IllegalArgumentException("duplicate key ["+id+"]");
  }

  @Override
  public H get(String id) {
    return map.get(id);
  }

  @Override
  public String id(Object item) {
    for (Map.Entry<String,H> e: map.entrySet()) {
      String id = e.getKey();
      H handler = e.getValue();
      if (handler.canHandle(item)) return id;
    }
    throw new IllegalArgumentException("no handler for type ["+(item == null ? null : item.getClass())+"]");
  }

  @Override
  public H find(Object item) {
    for (H handler: map.values()) {
      if (handler.canHandle(item)) return handler;
    }
    throw new IllegalArgumentException("no handler for type ["+(item == null ? null : item.getClass())+"]");
  }

  @Override
  public Iterator<H> iterator() {
    return map.values().iterator();
  }
}
