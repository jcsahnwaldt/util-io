package jcsahnwaldt.util.io.basic.binary;

import java.io.EOFException;
import java.io.IOException;

public class BinaryByteIdHandler
extends AbstractBinaryIdHandler
{
  @Override
  public void writeId(Integer id)
  throws IOException {
    int i = id.intValue();
    if (i < 0 || i > 255) throw new IllegalArgumentException("type id must be 0..255");
    out.write(i);
  }
  
  @Override
  public Integer readId()
  throws IOException {
    int i = in.read();
    if (i == -1) throw new EOFException();
    return Integer.valueOf(i);
  }

  @Override
  public int tag() {
    // Binary Byte ID 1.2
    return 0xBB1D0102;
  }
  
}