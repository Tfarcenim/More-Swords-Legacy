package net.darkhax.msmlegacy;

import net.darkhax.msmlegacy.init.ModEnchantments;
import net.darkhax.msmlegacy.init.ModItems;
import net.darkhax.msmlegacy.item.ItemSwordRelic;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.commons.lang3.tuple.Pair;

@Mod(MSMLegacy.MODID)
public class MSMLegacy {

    public static final String MODID = "msmlegacy";
    public static final ItemGroup creativeTab = new CreativeTabMSMLegacy();
    private final String[] relicNames = { "aqueous", "candy", "infinity", "keyblade", "master", "molten", "pie" };

    public MSMLegacy() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, SERVER_SPEC);
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addGenericListener(Item.class,this::registerItems);
        bus.addGenericListener(Enchantment.class,this::registerEnchants);
    }

    public static final ConfigurationHandler SERVER;
    public static final ForgeConfigSpec SERVER_SPEC;

    static {
        final Pair<ConfigurationHandler, ForgeConfigSpec> specPair2 = new ForgeConfigSpec.Builder().configure(ConfigurationHandler::new);
        SERVER_SPEC = specPair2.getRight();
        SERVER = specPair2.getLeft();
    }


    private void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(ModItems.items.toArray(new Item[0]));
        for (String name : relicNames) {
            Item relic = new ItemSwordRelic(new Item.Properties().group(MSMLegacy.creativeTab)).setRegistryName("relic_"+name);
            event.getRegistry().register(relic);
            ModItems.items.add(relic);
        }
    }

    private void registerEnchants(RegistryEvent.Register<Enchantment> event) {
        event.getRegistry().registerAll(ModEnchantments.getEnchantments().toArray(new Enchantment[0]));
    }

}