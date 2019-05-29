package jcsahnwaldt.util.io.basic.binary;

import java.io.IOException;

import jcsahnwaldt.util.io.DataReader;
import jcsahnwaldt.util.io.DataWriter;
import jcsahnwaldt.util.io.TagHandler;

public abstract class AbstractBinaryTagHandler
extends AbstractBinaryHandler
implements TagHandler
{
  private final BinaryIntHandler intHandler = new BinaryIntHandler();

  @Override
  public void setWriter(DataWriter out) {
    super.setWriter(out);
    intHandler.setWriter(out);
  }

  @Override
  public void setReader(DataReader in) {
    super.setReader(in);
    intHandler.setReader(in);
  }
  
  protected abstract int tag();

  @Override
  public void writeTag(String prefix)
  throws IOException {
    intHandler.writeInt(tag());
  }
  
  @Override
  public void readTag(String prefix)
  throws IOException {
    int tag = intHandler.readInt();
    if (tag != tag()) throw new IllegalArgumentException("unknown file format - expected tag ["+Integer.toHexString(tag())+"], found ["+Integer.toHexString(tag)+"]");
  }
}