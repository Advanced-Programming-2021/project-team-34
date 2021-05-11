public class SpellAndTrap extends Card{

    private String icon;
    public SpellAndTrap(int price, String name, String type, String description, String icon) {
        super(price, name, type, description);
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }
}
