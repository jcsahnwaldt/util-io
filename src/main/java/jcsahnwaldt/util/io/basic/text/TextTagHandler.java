package jcsahnwaldt.util.io.basic.text;

class TextTagHandler
extends AbstractTextTagHandler
{
  private static final String TAG = "TextData/1.2";

  @Override
  protected String tag(String prefix) {
    return prefix+TAG;
  }
}