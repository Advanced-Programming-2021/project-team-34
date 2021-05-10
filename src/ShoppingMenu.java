public class ShoppingMenu {
    private Player currentUser;
    private static ShoppingMenu shopMenu = null;

    private ShoppingMenu(Player newUser) {
        currentUser = newUser;
    }

    public static ShoppingMenu getInstance(Player newUser) {
        if (shopMenu == null) shopMenu = new ShoppingMenu(newUser);
        return shopMenu;
    }

    public void runShoppingMenu(Player currentUser) {
    }
}
