package jcsahnwaldt.util.io;

public abstract class AbstractDataHandler
implements DataHandler
{
  protected DataWriter out = null;
  protected DataReader in = null;
  
  @Override
  public void setWriter(DataWriter out) {
    this.out = out;
  }

  @Override
  public void setReader(DataReader in) {
    this.in = in;
  }
}