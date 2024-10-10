package backend.test.client_storage.enumeration;

public enum ContactTypeEnum {
    EMAIL("email", "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"),
    PHONE_NUMBER("phoneNumber", "^\\+?[0-9]{7,15}$");

    private final String value;
    private final String pattern;

    ContactTypeEnum(String value, String pattern) {
        this.value = value;
        this.pattern = pattern;
    }

    public String getValue() {
        return value;
    }

    public String getPattern() {
        return pattern;
    }

    public static ContactTypeEnum fromValue(String value) {
        for (ContactTypeEnum type : values()) {
            if (type.getValue().equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid contact type: " + value);
    }

    public boolean isValid(String value) {
        return value != null && value.matches(this.pattern);
    }
}
