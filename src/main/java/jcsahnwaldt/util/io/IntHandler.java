package jcsahnwaldt.util.io;

import java.io.IOException;

public interface IntHandler
extends DataHandler
{
  public void writeInt(int val) throws IOException;
  
  public int readInt() throws IOException;
}