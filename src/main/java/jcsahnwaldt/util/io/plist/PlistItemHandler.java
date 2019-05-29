package jcsahnwaldt.util.io.plist;

import java.io.IOException;

import jcsahnwaldt.util.io.DataReader;
import jcsahnwaldt.util.io.DataWriter;
import jcsahnwaldt.util.io.ItemHandler;

public abstract class PlistItemHandler
implements ItemHandler
{
  protected PlistDataWriter out = null;
  
  @Override
  public void setWriter(DataWriter out) {
    this.out = (PlistDataWriter)out;
  }

  @Override
  public void setReader(DataReader in) {
    throw new UnsupportedOperationException();
  }
  
  public void init()
  throws IOException {
  }
}