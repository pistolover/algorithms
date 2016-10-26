package headfirst.news.adapter;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class FileInputStream extends InputStream {
    private final FileDescriptor fd;
    private final String path;
    private FileChannel channel = null;
    private final Object closeLock = new Object();
    private volatile boolean closed = false;

    public FileInputStream(String name) throws FileNotFoundException {
        this(name != null ? new File(name) : null);
    }

    public FileInputStream(File file) throws FileNotFoundException {
        String name = (file != null ? file.getPath() : null);
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkRead(name);
        }
        if (name == null) {
            throw new NullPointerException();
        }

        fd = new FileDescriptor();
        path = name;
        open(name);
    }

    public FileInputStream(FileDescriptor fdObj) {
        SecurityManager security = System.getSecurityManager();
        if (fdObj == null) {
            throw new NullPointerException();
        }
        if (security != null) {
            security.checkRead(fdObj);
        }
        fd = fdObj;
        path = null;
    }

    private native void open0(String name) throws FileNotFoundException;

    private void open(String name) throws FileNotFoundException {
        open0(name);
    }

    public int read() throws IOException {
        return read0();
    }

    private native int read0() throws IOException;

    private native int readBytes(byte b[], int off, int len) throws IOException;

    public int read(byte b[]) throws IOException {
        return readBytes(b, 0, b.length);
    }

    public int read(byte b[], int off, int len) throws IOException {
        return readBytes(b, off, len);
    }

    public native long skip(long n) throws IOException;

    public native int available() throws IOException;

    public void close() throws IOException {

    }

    public final FileDescriptor getFD() throws IOException {
        if (fd != null) {
            return fd;
        }
        throw new IOException();
    }

    private static native void initIDs();

    private native void close0() throws IOException;

    static {
        initIDs();
    }

    protected void finalize() throws IOException {
        if ((fd != null) && (fd != FileDescriptor.in)) {
            close();
        }
    }
}
