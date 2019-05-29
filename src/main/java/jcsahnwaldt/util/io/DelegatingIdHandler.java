package jcsahnwaldt.util.io;

import java.io.IOException;

public class DelegatingIdHandler<I>
implements IdHandler<I>
{
  private final IdHandler<I> handler;

  public DelegatingIdHandler(IdHandler<I> handler) {
    this.handler = handler;
  }

  @Override
  public void writeId(I id)
  throws IOException {
    if (handler == null) throw new UnsupportedOperationException();
    handler.writeId(id);
  }

  @Override
  public I readId()
  throws IOException {
    if (handler == null) throw new UnsupportedOperationException();
    return handler.readId();
  }
  
}
