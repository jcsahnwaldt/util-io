package jcsahnwaldt.util.io.basic.text;

import java.io.BufferedReader;
import java.io.Writer;

import jcsahnwaldt.util.io.DataHandler;
import jcsahnwaldt.util.io.DataReader;
import jcsahnwaldt.util.io.DataWriter;

public abstract class AbstractTextHandler
implements DataHandler
{
  protected Writer output = null;
  protected BufferedReader input = null;

  @Override
  public void setWriter(DataWriter output) {
    this.output = ((TextDataWriter) output).output;
  }

  @Override
  public void setReader(DataReader input) {
    this.input = ((TextDataReader) input).input;
  }

}