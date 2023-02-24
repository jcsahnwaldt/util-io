package jcsahnwaldt.util.io.basic.text;

import java.io.BufferedReader;
import java.io.Writer;

import jcsahnwaldt.util.io.DataHandler;
import jcsahnwaldt.util.io.DataReader;
import jcsahnwaldt.util.io.DataWriter;

public abstract class AbstractTextHandler
implements DataHandler
{
  protected Writer out = null;

  protected BufferedReader in = null;

  @Override
  public void setWriter(DataWriter out) {
    this.out = ((TextDataWriter)out).out;
  }

  @Override
  public void setReader(DataReader in) {
    this.in = ((TextDataReader)in).in;
  }

}