import java.util.ArrayList;

public class SpellAndTrap extends Card {
    private String state;
    private boolean isTrap;
    private String name;
    private ArrayList<SpellAndTrap> spellAndTrapCards;

    public SpellAndTrap() {
        super();
    }

    public void effect() {

    }

    public static SpellAndTrap getCartByName(String name) {
        for (Card card : Card.getAllCards()) {
            if (card.getName().equals(name)) return (SpellAndTrap) card;
        }
        return null;
    }


    private void MirrorForceEffect() {

    }


    private void UmirukaEffect() {

    }


    private void MagicCylinderEffect() {

    }


    private void BlackPendantEffect() {

    }


    private void MessengerOfPeaceEffect() {

    }


    private void MysticaalSpaceTyphoonEffect() {

    }


    private void TwinTwistersEffect() {

    }


    private void SwordOfDarkDestructionEffect() {

    }


    private void CallOfTheHauntedEffect() {

    }


    private void MonsterRebornEffect() {

    }


    private void SordsOfLightEffect() {

    }


    private void PotOfGreedEffect() {

    }


    private void TorrentialTributeEffect() {

    }


    private void RingOfDeffenseEffect() {

    }


    private void UnitedweStandEffect() {

    }


    private void NegateAttackEffect() {

    }


    private void solemnWarningEffect() {

    }


    private void AdvancedRitualArtEffect() {

    }


    private void YamiEffect() {

    }


    private void RaigekiEffect() {

    }


    private void ChangeOfHeartEffect() {

    }


    private void HarpiesFeatherDusterEffect() {

    }


    private void MagicJammerEffect() {

    }


    private void closedForestEffect() {

    }


    private void ForestEffect() {

    }


    private void MindCrushEffect() {

    }


    private void DarkHoleEffect() {

    }


    private void TrapHoleEffect() {

    }


    private void SuplySquadEffect() {

    }


    private void TerraformingEffect() {

    }


    private void SpellAbsorptionEffect() {

    }


    private void timeSealEffect() {

    }
}
