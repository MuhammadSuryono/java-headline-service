package silampari.headline.koran.constant;

public enum PaymentStatus {
    PAID("paid"),
    UNPAID("unpaid"),
    ORDER("order");

    private String value;

    private PaymentStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


}
