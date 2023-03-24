package jcsahnwaldt.util.io.plist;

import java.io.IOException;

import jcsahnwaldt.util.io.DataReader;
import jcsahnwaldt.util.io.DataWriter;
import jcsahnwaldt.util.io.ItemHandler;

public abstract class PlistItemHandler
implements ItemHandler
{
  protected PlistDataWriter output = null;

  @Override
  public void setWriter(DataWriter output) {
    this.output = (PlistDataWriter) output;
  }

  @Override
  public void setReader(DataReader input) {
    throw new UnsupportedOperationException();
  }

  public void init()
  throws IOException {
  }
}