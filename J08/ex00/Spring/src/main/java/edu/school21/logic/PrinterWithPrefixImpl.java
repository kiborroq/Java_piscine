package edu.school21.logic;

public class PrinterWithPrefixImpl implements Printer {
    Renderer render;
    String prefix;

    public PrinterWithPrefixImpl(Renderer render) {
        this.render = render;
        this.prefix = "";
    }

    @Override
    public void print(String message) {
        render.render(prefix + " " + message);
    }

    public Renderer getRender() {
        return render;
    }

    public void setRender(Renderer render) {
        this.render = render;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
