package jcsahnwaldt.util.io;

import java.io.IOException;

public interface BoolHandler
extends DataHandler
{
  public void writeBool(boolean val) throws IOException;

  public boolean readBool() throws IOException;
}