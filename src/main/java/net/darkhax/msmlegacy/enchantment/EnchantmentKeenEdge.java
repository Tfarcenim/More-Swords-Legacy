package net.darkhax.msmlegacy.enchantment;

import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.item.Item;

public class EnchantmentKeenEdge extends EnchantmentSwordLegacy {

	public EnchantmentKeenEdge(Rarity rarityIn, Item item, EnchantmentType typeIn, int min, int max, boolean isVanillaAllowed) {
		
		super(rarityIn, item, typeIn, min, max, isVanillaAllowed);
	}
	
	@Override
    public float calcDamageByCreature(int level, CreatureAttribute creatureType) {
    	
        return level * 1.5f;
    }
}