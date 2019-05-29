package jcsahnwaldt.util.io;

import java.io.IOException;

public interface DataReader
{
  public Object readItem() throws IOException;
}