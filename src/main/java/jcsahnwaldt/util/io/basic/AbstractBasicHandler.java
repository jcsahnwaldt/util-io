package jcsahnwaldt.util.io.basic;

import jcsahnwaldt.util.io.DataHandler;
import jcsahnwaldt.util.io.DataReader;
import jcsahnwaldt.util.io.DataWriter;

public abstract class AbstractBasicHandler
implements DataHandler
{
  protected BasicDataWriter out;
  protected BasicDataReader in;

  @Override
  public void setWriter(DataWriter out) {
    this.out = (BasicDataWriter) out;
  }

  @Override
  public void setReader(DataReader in) {
    this.in = (BasicDataReader) in;
  }
}