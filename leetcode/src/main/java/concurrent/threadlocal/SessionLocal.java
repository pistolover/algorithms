package concurrent.threadlocal;

public final class SessionLocal {

    
    private static final ThreadLocal<String> SESSION = new ThreadLocal<>();
    
    
    public static final String get() {
        return SESSION.get();
    }
    
    
    public static final void set(String token) {
        SESSION.set(token);
    }
    
    
    public static final void clear() {
        SESSION.set(null);
    }
}
