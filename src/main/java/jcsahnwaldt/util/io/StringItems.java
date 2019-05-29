package jcsahnwaldt.util.io;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringItems
extends DelegatingIdHandler<String>
implements Items<String>
{
  private static final String HASH_PREFIX = "@";
  
  private final MapMode mode;
  private final Map<Object, String> ids;
  private final Map<String, Object> objects = new HashMap<String, Object>();
  
  public StringItems(IdHandler<String> handler, MapMode mode) {
    super(handler);
    this.mode = mode;
    this.ids = mode.map();
  }

  @Override
  public void add(String id, Object item) {
    if (id == null || !isNew(id)) throw new IllegalArgumentException("expected new id, got ["+id+"]");
    
    Class<?> type = item.getClass();
    String typeName =
    Map.class.isAssignableFrom(type) ? "Map" :
    List.class.isAssignableFrom(type) ? "List" :
    type.getSimpleName();
    
    id = typeName + id;
    
    if (objects.put(id, item) != null) throw new IllegalArgumentException("duplicate id ["+id+"]");
    if (ids != null) ids.put(item, id);
  }
  
  @Override
  public Object item(String id) {
    return objects.get(id);
  }

  @Override
  public String id(Object item) {
    if (ids == null) throw new UnsupportedOperationException("no item map configured");
    String id = ids.get(item);
    if (id != null) return id;
    return HASH_PREFIX + Integer.toHexString(mode.hashCode(item));
  }

  @Override
  public boolean isNew(String id) {
    return id.startsWith(HASH_PREFIX);
  }

}
