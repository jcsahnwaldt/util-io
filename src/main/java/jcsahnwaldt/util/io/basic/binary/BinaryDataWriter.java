package jcsahnwaldt.util.io.basic.binary;

import java.io.OutputStream;

import jcsahnwaldt.util.io.MapMode;
import jcsahnwaldt.util.io.basic.BasicDataWriter;

public class BinaryDataWriter
extends BasicDataWriter
{
  /*package*/ final OutputStream out;
  
  public BinaryDataWriter(OutputStream out, MapMode mode) {
    super(new BinaryHandlers(), mode);
    this.out = out;
    register(new BinaryBoolHandler());
    register(new BinaryIntHandler());
    register(new BinaryFloatHandler());
    register(new BinaryStringHandler());
  }
  
}