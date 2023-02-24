package jcsahnwaldt.util.io;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

public enum MapMode
{
  NONE
  {
    @Override
    public <I> Map<Object, I> map() {
      return null;
    }

    @Override
    public int hashCode(Object item) {
      throw new UnsupportedOperationException();
    }
  },

  EQUALITY
  {
    @Override
    public <I> Map<Object, I> map() {
      return new HashMap<Object, I>();
    }

    @Override
    public int hashCode(Object item) {
      return item == null ? 0 : item.hashCode();
    }
  },

  IDENTITY
  {
    @Override
    public <I> Map<Object, I> map() {
      return new IdentityHashMap<Object, I>();
    }

    @Override
    public int hashCode(Object item) {
      return System.identityHashCode(item);
    }
  };

  public abstract <I> Map<Object, I> map();

  public abstract int hashCode(Object item);
}