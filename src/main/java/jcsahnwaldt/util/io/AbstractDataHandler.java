package jcsahnwaldt.util.io;

public abstract class AbstractDataHandler
implements DataHandler
{
  protected DataWriter output = null;
  protected DataReader input = null;

  @Override
  public void setWriter(DataWriter output) {
    this.output = output;
  }

  @Override
  public void setReader(DataReader input) {
    this.input = input;
  }
}