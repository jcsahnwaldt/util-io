package jcsahnwaldt.util.io;

import java.io.IOException;

public interface DataWriter
{
  public void writeItem(Object item) throws IOException;
}