package jcsahnwaldt.util.io.basic;

import jcsahnwaldt.util.io.IdDataHandler;
import jcsahnwaldt.util.io.IntHandler;
import jcsahnwaldt.util.io.StringHandler;
import jcsahnwaldt.util.io.TagHandler;

public class BasicHandlers
{
  final TagHandler tag;
  final IntHandler size;
  final StringHandler string;
  final IdDataHandler<?> typeId;
  final IdDataHandler<?> itemId;
  
  protected BasicHandlers(
    TagHandler tag,
    IntHandler size,
    StringHandler string,
    IdDataHandler<?> typeId,
    IdDataHandler<?> itemId
  ) {
    this.tag = tag;
    this.size = size;
    this.string = string;
    this.typeId = typeId;
    this.itemId = itemId;    
  }
  
  public void inject(BasicDataStream stream) {
    stream.inject(tag);
    stream.inject(size);
    stream.inject(string);
    stream.inject(typeId);
    stream.inject(itemId);
  }

}
