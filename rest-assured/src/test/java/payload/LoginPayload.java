package payload;

public class LoginPayload {
    private LoginPayload() {}

    public static String validUser() {
        return """
                {"username":"user","password":"password"}
                """;
    }
}

