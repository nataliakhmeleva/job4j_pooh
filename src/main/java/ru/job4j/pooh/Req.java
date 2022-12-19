package ru.job4j.pooh;

public class Req {

    private final String httpRequestType;
    private final String poohMode;
    private final String sourceName;
    private final String param;

    public Req(String httpRequestType, String poohMode, String sourceName, String param) {
        this.httpRequestType = httpRequestType;
        this.poohMode = poohMode;
        this.sourceName = sourceName;
        this.param = param;
    }

    public static Req of(String content) {
        String[] strings = content.split(System.lineSeparator());
        String[] lines = strings[0].split("/");
        String httpRequestType = lines[0].trim();
        String poohMode = lines[1];
        String sourceName = lines[2].split(" ")[0];
        String param;
        if ("GET".equals(httpRequestType)) {
            param = lines.length > 4 ? lines[3].split(" ")[0] : "";
        } else {
            param = strings[strings.length - 1];
        }
        return new Req(httpRequestType, poohMode, sourceName, param);
    }

    public String httpRequestType() {
        return httpRequestType;
    }

    public String getPoohMode() {
        return poohMode;
    }

    public String getSourceName() {
        return sourceName;
    }

    public String getParam() {
        return param;
    }
}