package lotson.timkim.dto;

public class DecryptDTO {
    private String value;
    private String secretKey;
    private String algorithm;
    private String mode;
    private String padding;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getPadding() {
        return padding;
    }

    public void setPadding(String padding) {
        this.padding = padding;
    }

    public String getEncodingAlgorithm() {
        return this.algorithm + "/" + this.mode + "/" + this.padding;
    }

    @Override
    public String toString() {
        return "DecryptDTO [ " +
                "value = \"" + value + "\"" +
                ", secretKey = \"" + secretKey + "\"" +
                ", algorithm = \"" + algorithm + "\"" +
                ", mode = \"" + mode + "\"" +
                ", padding = \"" + padding + "\"" +
                " ]";
    }
}
