package backend.test.client_storage.exception;

public class ResourceAlreadyExistsException extends RuntimeException {
    public ResourceAlreadyExistsException(String message)
    {
        super(message);
    }
}
