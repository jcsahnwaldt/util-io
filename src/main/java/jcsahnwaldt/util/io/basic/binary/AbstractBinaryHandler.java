package jcsahnwaldt.util.io.basic.binary;

import java.io.InputStream;
import java.io.OutputStream;

import jcsahnwaldt.util.io.DataHandler;
import jcsahnwaldt.util.io.DataReader;
import jcsahnwaldt.util.io.DataWriter;

public abstract class AbstractBinaryHandler
implements DataHandler
{
  protected OutputStream output = null;
  protected InputStream input = null;

  @Override
  public void setWriter(DataWriter output)
  {
    this.output = ((BinaryDataWriter)output).output;
  }

  @Override
  public void setReader(DataReader input)
  {
    this.input = ((BinaryDataReader)input).input;
  }

}