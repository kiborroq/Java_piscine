package edu.school21.logic;

import java.time.LocalDateTime;

public class PrinterWithDateTimeImpl implements Printer {
    Renderer render;

    public PrinterWithDateTimeImpl() { }

    public PrinterWithDateTimeImpl(Renderer render) {
        this.render = render;
    }

    @Override
    public void print(String message) {
        render.render(LocalDateTime.now() + " " + message);
    }

    public Renderer getRender() {
        return render;
    }

    public void setRender(Renderer render) {
        this.render = render;
    }
}
