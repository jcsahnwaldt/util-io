package jcsahnwaldt.util.io;

import java.io.IOException;

public interface StringHandler
extends DataHandler
{
  public void writeString(String val) throws IOException;

  public String readString() throws IOException;
}