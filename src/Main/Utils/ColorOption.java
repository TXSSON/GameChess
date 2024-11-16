package Main.Utils;

public class ColorOption {
    private String name;
    private String hexCode;

    public ColorOption(String name, String hexCode) {
        this.name = name;
        this.hexCode = hexCode;
    }

    public String getName() {
        return name;
    }

    public String getHexCode() {
        return hexCode;
    }

    @Override
    public String toString() {
        return name; // �?ể JComboBox hiển thị tên màu
    }
}