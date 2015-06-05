public class Tile {

    private String type;
    private String image;
    private String item;
    private Baller character;

    public Tile() {
	type = "grass";
	image = "#";
	character = null;
    }

    public String getType() {
	return type;
    }

    public void setType(String data) {
	type = data;
    }

    public String getImage() {
	return image;
    }

    public void setImage(String data) {
	image = data;
    }

    public String getItem() {
	return item;
    }

    public void setItem(String data) {
	item = data;
    }

    public Baller getCharacter() {
	return character;
    }

    public void setCharacter(Baller data) {
	character = data;
    }

}
