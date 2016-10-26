package headfirst.news.decorate;

import java.io.IOException;

public class FilterInputStream extends InputStream {
    protected volatile InputStream in;

    protected FilterInputStream(InputStream in) {
        this.in = in;
    }

    public int read() throws IOException {
        return in.read();
    }

    public int read(byte b[]) throws IOException {
        return read(b, 0, b.length);
    }

    public int read(byte b[], int off, int len) throws IOException {
        return in.read(b, off, len);
    }

    @Override
    public void close() throws IOException {
        in.close();
    }

}
