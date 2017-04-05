package core.dao;

public class DuplicateException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DuplicateException(String id) {
        super(id);
    }

}
