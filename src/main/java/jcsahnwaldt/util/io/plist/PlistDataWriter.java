package jcsahnwaldt.util.io.plist;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.List;

import jcsahnwaldt.util.io.DataWriter;
import jcsahnwaldt.util.io.IntItems;
import jcsahnwaldt.util.io.IntTypes;
import jcsahnwaldt.util.io.MapMode;

public class PlistDataWriter
implements DataWriter
{
  // "\n" for nicer output, "" for saving ~7% space
  private static final String LF = "\n";

  /*package*/ final Writer out;

  private final IntItems items;
  private final IntTypes<PlistItemHandler> types;

  /**
   * Stack of lists. Top list stores children of current item/array.
   */
  private final Deque<List<Object>> stack = new ArrayDeque<List<Object>>();

  public PlistDataWriter(Writer out, MapMode mode) {
    if (out == null) throw new NullPointerException("out");
    this.out = out;

    items = new IntItems(null, mode);
    types = new IntTypes<PlistItemHandler>(null);

    register(new PlistNullHandler());
    register(new PlistListHandler());
    register(new PlistMapHandler());
    register(new PlistBoolHandler());
    register(new PlistIntHandler());
    register(new PlistFloatHandler());
    register(new PlistStringHandler());
  }

  public void register(PlistItemHandler handler) {
    handler.setWriter(this);
    types.add(handler);
  }

  private void w(String string)
  throws IOException {
    out.write(string);
  }

  private boolean safe(String string) {
    for (int i = 0; i < string.length(); i++) {
      int c = string.charAt(i);
      if ((c < 64) && (c == '&' || c == '<' || c == '>')) return false;
    }
    return true;
  }

  private void esc(String string)
  throws IOException {
    if (! safe(string)) {
      out.write("<![CDATA[");
      out.write(string);
      out.write("]]>");
    }
    else {
      out.write(string);
    }
  }

  private void plist(boolean open)
  throws IOException {
    w(open ? "<plist version=\"1.0\">"+LF : "</plist>"+LF);
  }

  void dict(boolean open)
  throws IOException {
    w(open ? "<dict>"+LF : "</dict>"+LF);
  }

  void array(boolean open)
  throws IOException {
    w(open ? "<array>"+LF : "</array>"+LF);
  }

  void ref(String key, Object val)
  throws IOException {
    key(key);
    ref(val);
  }

  void ref(Object val)
  throws IOException {
    Integer id = items.id(val);
    if (id == null) throw new IllegalArgumentException("unknown item ["+val+"]");
    dict("CF$UID", id.intValue());
  }

  private void dict(String key, String val)
  throws IOException {
    dict(true);
    keyVal(key, val);
    dict(false);
  }

  private void dict(String key, int val)
  throws IOException {
    dict(true);
    keyVal(key, val);
    dict(false);
  }

  private void keyVal(String key, String val)
  throws IOException {
    key(key);
    val(val);
  }

  private void keyVal(String key, int val)
  throws IOException {
    key(key);
    val(val);
  }

  private void key(String key)
  throws IOException {
    w("<key>");
    esc(key);
    w("</key>"+LF);
  }

  void val(String val)
  throws IOException {
    w("<string>");
    esc(val);
    w("</string>"+LF);
  }

  void val(int val)
  throws IOException {
    w("<integer>");
    w(Integer.toString(val));
    w("</integer>"+LF);
  }

  void val(float val)
  throws IOException {
    w("<real>");
    w(Float.toString(val));
    w("</real>"+LF);
  }

  void val(boolean val)
  throws IOException {
    w(val ? "<true/>"+LF : "<false/>"+LF);
  }

  public void open()
  throws IOException {
    w("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+LF);
    w("<!DOCTYPE plist PUBLIC \"-//Apple//DTD PLIST 1.0//EN\" \"http://www.apple.com/DTDs/PropertyList-1.0.dtd\">"+LF);

    plist(true);
    dict(true);

    keyVal("$archiver", "NSKeyedArchiver");

    key("$objects");
    array(true);

    // insert null at index 0
    writeItem(null);

    for (PlistItemHandler handler: types) {
      handler.init();
    }
  }

  public void close(Object root)
  throws IOException {
    array(false);

    key("$top");
    dict(true);
    ref("root", root);
    dict(false);

    keyVal("$version", 100000);

    dict(false);
    plist(false);
  }

  void defClass(PlistItemHandler handler, String cls)
  throws IOException {
    items.add(null, handler);
    dict("$classname", cls);
  }

  void refClass(PlistItemHandler handler)
  throws IOException {
    ref("$class", handler);
  }


  /**
   * Put new list for children of current item on top of stack.
   */
  void push() {
    stack.push(new ArrayList<Object>());
  }

  /**
   * If there is a current child list, remember item.
   * @param item
   */
  void memo(Object item) {
    List<Object> children = stack.peek();
    if (children != null) children.add(item);
  }

  /**
   * Remove list for children of current item from top of stack.
   */
  List<?> pop() {
    return stack.pop();
  }

  List<?> writeItems(Collection<?> children)
  throws IOException {
    push();
    for (Object child: children) writeItem(child);
    return pop();
  }

  void writeRefs(String key, List<?> children)
  throws IOException {
    key(key);
    array(true);
    for (Object child: children) {
      ref(child);
    }
    array(false);
  }

  @Override
  public void writeItem(Object item)
  throws IOException {
    memo(item);
    if (items.id(item) != null) return;
    types.find(item).writeItem(item);
    items.add(null, item);
  }

}
