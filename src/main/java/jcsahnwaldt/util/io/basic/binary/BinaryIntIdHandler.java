package jcsahnwaldt.util.io.basic.binary;

import java.io.EOFException;
import java.io.IOException;

public class BinaryIntIdHandler
extends AbstractBinaryIdHandler
{
  // item ids can't be negative, so we can use any negative marker value for the top byte
  private static final int NEW_BYTE = 0xFF;
  
  @Override
  public void writeId(Integer id)
  throws IOException {
    if (id == null) {
      out.write(NEW_BYTE);
    }
    else {
      int i = id.intValue();
      out.write((i >>> 24) & 0xFF);
      out.write((i >>> 16) & 0xFF);
      out.write((i >>>  8) & 0xFF);
      out.write((i >>>  0) & 0xFF);
    }
  }
  
  @Override
  public Integer readId()
  throws IOException {
    int b1 = in.read();
    if (b1 == -1) {
      throw new EOFException();
    }
    else if (b1 == NEW_BYTE) {
      return null;
    }
    else {
      int b2 = in.read();
      int b3 = in.read();
      int b4 = in.read();
      if ((b2 | b3 | b4) == -1) throw new EOFException();
      int i = ((b1 << 24) | (b2 << 16) | (b3 << 8) | (b4 << 0));
      return Integer.valueOf(i);
    }
  }

  @Override
  public int tag() {
    // Binary Int ID 1.2
    return 0xB11D0102;
  }
  
}