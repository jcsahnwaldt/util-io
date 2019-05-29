package jcsahnwaldt.util.io.basic.text;

import jcsahnwaldt.util.io.IdDataHandler;
import jcsahnwaldt.util.io.basic.BasicHandlers;

public class TextHandlers
extends BasicHandlers
{
  public static TextHandlers intIds() {
    return new TextHandlers(new TextIntIdHandler(true), new TextIntIdHandler(false));
  }
  
  public static TextHandlers stringIds() {
    return new TextHandlers(new TextStringIdHandler(true), new TextStringIdHandler(false));
  }
  
  public TextHandlers(IdDataHandler<?> typeId, IdDataHandler<?> itemId) {
    super(
      new TextTagHandler(),
      new TextIntHandler(),
      new TextStringHandler(),
      typeId,
      itemId
    );
  }
}