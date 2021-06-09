public class SpellAndTrap extends Card{

    private String icon;
    public SpellAndTrap(int price, String name, String type, String description, String icon) {
        super(name);

        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }
}
