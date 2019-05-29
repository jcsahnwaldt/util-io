package jcsahnwaldt.util.io.basic.binary;

import java.io.InputStream;

import jcsahnwaldt.util.io.basic.BasicDataReader;

public class BinaryDataReader
extends BasicDataReader
{
  /*package*/ final InputStream in;
  
  public BinaryDataReader(InputStream in) {
    super(new BinaryHandlers());
    this.in = in;
  }
}