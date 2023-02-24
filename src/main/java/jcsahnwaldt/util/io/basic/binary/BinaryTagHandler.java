package jcsahnwaldt.util.io.basic.binary;

public class BinaryTagHandler
extends AbstractBinaryTagHandler
{
  // compact binary data format version 1.2
  private static final int TAG = 0xCBDF0102;

  @Override
  protected int tag() {
    return TAG;
  }

}