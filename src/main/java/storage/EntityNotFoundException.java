package storage;

public class EntityNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 449464452930630251L;

    public EntityNotFoundException(String message) {
        super(message);
    }
}
