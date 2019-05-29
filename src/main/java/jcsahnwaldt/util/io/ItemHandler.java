package jcsahnwaldt.util.io;

import java.io.IOException;

public interface ItemHandler
extends DataHandler
{
  public boolean canHandle(Object item);
  
  public Class<?> itemType();
  
  public void writeItem(Object item) throws IOException;
  
  public Object readItem() throws IOException;
}