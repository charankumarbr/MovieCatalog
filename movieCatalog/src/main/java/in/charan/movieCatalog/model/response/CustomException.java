package in.charan.ratingsData.model;

public class CustomException {

    private String exceptionMessage;

    private int code;

    public CustomException(String exceptionMessage, int code) {
        this.exceptionMessage = exceptionMessage;
        this.code = code;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
