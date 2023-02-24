package jcsahnwaldt.util.io.basic.binary;

import java.io.InputStream;
import java.io.OutputStream;

import jcsahnwaldt.util.io.DataHandler;
import jcsahnwaldt.util.io.DataReader;
import jcsahnwaldt.util.io.DataWriter;

public abstract class AbstractBinaryHandler
implements DataHandler
{
  protected OutputStream out = null;

  protected InputStream in = null;

  @Override
  public void setWriter(DataWriter out)
  {
    this.out = ((BinaryDataWriter)out).out;
  }

  @Override
  public void setReader(DataReader in)
  {
    this.in = ((BinaryDataReader)in).in;
  }

}