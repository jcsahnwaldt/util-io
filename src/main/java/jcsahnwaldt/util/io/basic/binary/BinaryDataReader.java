package jcsahnwaldt.util.io.basic.binary;

import java.io.InputStream;

import jcsahnwaldt.util.io.basic.BasicDataReader;

public class BinaryDataReader
extends BasicDataReader
{
  /*package*/ final InputStream input;

  public BinaryDataReader(InputStream input) {
    super(new BinaryHandlers());
    this.input = input;
  }
}