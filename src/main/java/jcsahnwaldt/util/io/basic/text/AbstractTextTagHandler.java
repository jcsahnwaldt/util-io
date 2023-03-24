package jcsahnwaldt.util.io.basic.text;

import java.io.IOException;

import jcsahnwaldt.util.io.DataReader;
import jcsahnwaldt.util.io.DataWriter;
import jcsahnwaldt.util.io.TagHandler;

public abstract class AbstractTextTagHandler
extends AbstractTextHandler
implements TagHandler
{
  private final TextStringHandler stringHandler = new TextStringHandler();

  @Override
  public void setWriter(DataWriter output) {
    super.setWriter(output);
    stringHandler.setWriter(output);
  }

  @Override
  public void setReader(DataReader input) {
    super.setReader(input);
    stringHandler.setReader(input);
  }

  protected abstract String tag(String prefix);

  @Override
  public void writeTag(String prefix)
  throws IOException {
    stringHandler.writeString(tag(prefix));
  }

  @Override
  public void readTag(String prefix)
  throws IOException {
    String tag = stringHandler.readString();
    if (! tag(prefix).equals(tag)) throw new IllegalArgumentException("unknown file format - expected tag ["+tag(prefix)+"], found ["+tag+"]");
  }
}