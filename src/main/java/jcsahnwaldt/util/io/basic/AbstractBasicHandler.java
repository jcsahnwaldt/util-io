package jcsahnwaldt.util.io.basic;

import jcsahnwaldt.util.io.DataHandler;
import jcsahnwaldt.util.io.DataReader;
import jcsahnwaldt.util.io.DataWriter;

public abstract class AbstractBasicHandler
implements DataHandler
{
  protected BasicDataWriter output;
  protected BasicDataReader input;

  @Override
  public void setWriter(DataWriter output) {
    this.output = (BasicDataWriter) output;
  }

  @Override
  public void setReader(DataReader input) {
    this.input = (BasicDataReader) input;
  }
}