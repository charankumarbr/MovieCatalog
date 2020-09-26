package in.charan.movieCatalog.model.response;

public class CustomException {

    private String exceptionMessage;

    private int apiStatusCode;

    public CustomException() {
    }

    public CustomException(String exceptionMessage, int apiStatusCode) {
        this.exceptionMessage = exceptionMessage;
        this.apiStatusCode = apiStatusCode;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public int getApiStatusCode() {
        return apiStatusCode;
    }

    public void setApiStatusCode(int apiStatusCode) {
        this.apiStatusCode = apiStatusCode;
    }
}
