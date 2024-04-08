package tasks.familyhub.dto;

public class ImageResponse {
    protected String id;

    protected String name;
    protected byte[] imageData;

    protected String type;

    public ImageResponse(String id, String name, byte[] imageData, String type) {
        this.id = id;
        this.name = name;
        this.imageData = imageData;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
