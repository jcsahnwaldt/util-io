package jcsahnwaldt.util.io;

import java.io.IOException;

public interface TagHandler
extends DataHandler
{
  public void writeTag(String prefix) throws IOException;

  public void readTag(String prefix) throws IOException;
}