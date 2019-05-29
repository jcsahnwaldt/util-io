package jcsahnwaldt.util.io;

import java.io.IOException;

public interface IdHandler<I>
{
  public void writeId(I id) throws IOException;
  
  public I readId() throws IOException;
}