package payload;

public class BasketPayload {
    private BasketPayload() {}

    public static String addProduct(int productId) {
        return """
                {"productId":%d}
                """.formatted(productId);
    }
}

