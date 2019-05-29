package jcsahnwaldt.util.io;

import java.io.IOException;

public interface FloatHandler
extends DataHandler
{
  public void writeFloat(float val) throws IOException;
  
  public float readFloat() throws IOException;
}