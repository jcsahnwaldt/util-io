package jcsahnwaldt.util.io.basic.binary;

import jcsahnwaldt.util.io.basic.BasicHandlers;

public class BinaryHandlers
extends BasicHandlers
{
  public BinaryHandlers() {
    super(
      new BinaryTagHandler(),
      new BinaryIntHandler(),
      new BinaryStringHandler(),
      new BinaryByteIdHandler(),
      new BinaryIntIdHandler()
    );
  }
}