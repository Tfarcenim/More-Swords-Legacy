package net.darkhax.msmlegacy;

import java.util.List;

import net.darkhax.msmlegacy.init.ModEnchantments;
import net.darkhax.msmlegacy.init.ModItems;
import net.darkhax.msmlegacy.item.ItemSwordRelic;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent.SpecialSpawn;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickItem;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;


@EventBusSubscriber(modid = "msmlegacy")
public class EnchantmentEffectHandler {

    @Instance("msmlegacy")
    public static MSMLegacy msm;

    @SubscribeEvent
    public static void onDamageMob (LivingHurtEvent event) {

        if (event.getSource().getTrueSource() instanceof LivingEntity) {

            final LivingEntity target = event.getEntityLiving();
            final LivingEntity attacker = (LivingEntity) event.getSource().getTrueSource();
            final ItemStack heldItem = attacker.getHeldItemMainhand();

            if (!heldItem.isEmpty()) {

                checkAndApplyEffect(ModEnchantments.ignite, target, attacker, heldItem, EnchantmentEffectHandler::handleIgniteEffect, event);
                checkAndApplyEffect(ModEnchantments.sparks, target, attacker, heldItem, EnchantmentEffectHandler::handleSparksEffect, event);
                checkAndApplyEffect(ModEnchantments.feast, target, attacker, heldItem, EnchantmentEffectHandler::handleFeastEffect, event);
                checkAndApplyEffect(ModEnchantments.venomousAspect, target, attacker, heldItem, EnchantmentEffectHandler::handleVenomousAspect, event);
                checkAndApplyEffect(ModEnchantments.absorb, target, attacker, heldItem, EnchantmentEffectHandler::handleAbsorbEffect, event);
                checkAndApplyEffect(ModEnchantments.scorn, target, attacker, heldItem, EnchantmentEffectHandler::handleScornEffect, event);
                checkAndApplyEffect(ModEnchantments.greed, target, attacker, heldItem, EnchantmentEffectHandler::handleGreedEffect, event);
                checkAndApplyEffect(ModEnchantments.wisdom, target, attacker, heldItem, EnchantmentEffectHandler::handleWisdomEffect, event);
                checkAndApplyEffect(ModEnchantments.frozenAspect, target, attacker, heldItem, EnchantmentEffectHandler::handleFrozenAspectEffect, event);
                checkAndApplyEffect(ModEnchantments.frostWave, target, attacker, heldItem, EnchantmentEffectHandler::handleFrostWaveEffect, event);
                checkAndApplyEffect(ModEnchantments.ascension, target, attacker, heldItem, EnchantmentEffectHandler::handleAscensionEffect, event);
                checkAndApplyEffect(ModEnchantments.decay, target, attacker, heldItem, EnchantmentEffectHandler::handleDecayEffect, event);
                checkAndApplyEffect(ModEnchantments.consumingShadows, target, attacker, heldItem, EnchantmentEffectHandler::handleConsumingShadowEffect, event);
                checkAndApplyEffect(ModEnchantments.extinction, target, attacker, heldItem, EnchantmentEffectHandler::handleExtinctionEffect, event);
            }
        }

        final LivingEntity user = event.getEntityLiving();
        final ItemStack userItem = user.getHeldItemMainhand();

        if (!userItem.isEmpty()) {

            checkAndApplyEffect(ModEnchantments.enderAura, user, userItem, EnchantmentEffectHandler::handleEnderAura, event);
        }
    }

    @SubscribeEvent
    public static void onItemUsedEvent (PlayerInteractEvent.RightClickItem event) {

        if (!event.getItemStack().isEmpty() && event.getHand() == Hand.MAIN_HAND) {

            checkAndApplyEffect(ModEnchantments.vitality, event.getPlayer(), event.getItemStack(), EnchantmentEffectHandler::handleVitalityEffect, event);
            checkAndApplyEffect(ModEnchantments.enderPulse, event.getPlayer(), event.getItemStack(), EnchantmentEffectHandler::handleEnderPulseEffect, event);
            checkAndApplyEffect(ModEnchantments.stealth, event.getPlayer(), event.getItemStack(), EnchantmentEffectHandler::handleStealthEffect, event);
        }
    }

    @SubscribeEvent
    public static void onPlayerTick (TickEvent.PlayerTickEvent event) {

        final PlayerEntity player = event.player;
        final ItemStack heldItem = player.getHeldItemMainhand();

        if (!heldItem.isEmpty()) {

            checkAndApplyEffect(ModEnchantments.descension, player, heldItem, EnchantmentEffectHandler::handleDescensionEffect);
        }
    }

    @SubscribeEvent
    public static void onSpecialSpawn (SpecialSpawn event) {

        if ((event.getEntity() instanceof EntitySkeleton || event.getEntity() instanceof EntityZombie) && MathsUtils.tryPercentage(MSMLegacy.config.getSpawnChance())) {

            final Item item = msm.registry.getItems().get(event.getWorld().rand.nextInt(msm.registry.getItems().size()));

            if (item != ModItems.adminiumArk && (MSMLegacy.config.isAllowRelics() || !(item instanceof ItemSwordRelic))) {

                event.getEntityLiving().setHeldItem(Hand.MAIN_HAND, new ItemStack(item));
            }
        }
    }

    private static void handleExtinctionEffect (LivingEntity attacker, LivingEntity target, ItemStack item, int level, LivingHurtEvent event) {

        for (final Entity entity : target.world.loadedEntityList) {

            if (entity instanceof EntityLiving && entity != attacker && entity.getClass() == target.getClass()) {

                entity.setDead();
            }
        }
    }

    private static void handleConsumingShadowEffect (LivingEntity attacker, LivingEntity target, ItemStack item, int level, LivingHurtEvent event) {

        for (final LivingEntity entity : EntityUtils.<LivingEntity> getEntitiesInArea(LivingEntity.class, target.getEntityWorld(), target.getPosition(), level)) {

            if (entity != attacker) {

                entity.addPotionEffect(new PotionEffect(MobEffects.WITHER, 60 * level, level));
                target.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 60 * level, level));
            }
        }
    }

    private static void handleDecayEffect (LivingEntity attacker, LivingEntity target, ItemStack item, int level, LivingHurtEvent event) {

        target.addPotionEffect(new PotionEffect(MobEffects.WITHER, 60 * level));
        target.addPotionEffect(new PotionEffect(MobEffects.HUNGER, 60 * level, level));
    }

    private static void handleDescensionEffect (LivingEntity user, ItemStack item, int level) {

        if (user.isSneaking()) {

            user.motionY *= 0.6;
            user.fallDistance = 0;
        }
    }

    private static void handleFrostWaveEffect (LivingEntity attacker, LivingEntity target, ItemStack item, int level, LivingHurtEvent event) {

        for (final LivingEntity entity : EntityUtils.<LivingEntity> getEntitiesInArea(LivingEntity.class, target.getEntityWorld(), target.getPosition(), level)) {

            if (entity != attacker) {

                entity.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 100 * level, level));
            }
        }
    }

    private static void handleFrozenAspectEffect (LivingEntity attacker, LivingEntity target, ItemStack item, int level, LivingHurtEvent event) {

        target.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 200 * level, level));
    }

    private static void handleAscensionEffect (LivingEntity attacker, LivingEntity target, ItemStack item, int level, LivingHurtEvent event) {

        target.addPotionEffect(new PotionEffect(MobEffects.LEVITATION, 30, 4 * level));
    }

    private static void handleEnderAura (LivingEntity user, ItemStack item, int level, LivingHurtEvent event) {

        for (int i = 0; i < 3; i++) {

            if (MathsUtils.tryPercentage(0.30d)) {

                final AxisAlignedBB bounds = user.getEntityBoundingBox().grow(30d);
                final List<Entity> entities = user.world.getEntitiesInAABBexcluding(user, bounds, entity -> entity instanceof LivingEntity);

                if (!entities.isEmpty()) {

                    final Entity randomEntity = entities.get(user.world.rand.nextInt(entities.size()));

                    if (randomEntity instanceof LivingEntity) {

                        final LivingEntity living = (LivingEntity) randomEntity;

                        user.setPositionAndUpdate(living.posX, living.posY, living.posZ);
                    }
                }
            }
        }
    }

    private static void handleEnderPulseEffect (LivingEntity user, ItemStack item, int level, RightClickItem event) {

        final RayTraceResult results = MathsUtils.rayTrace(user, 16d * level);

        if (results != null && results.typeOfHit == Type.BLOCK) {

            final BlockPos tpPos = results.getBlockPos().offset(results.sideHit);
            user.setPositionAndUpdate(tpPos.getX() + 0.5, tpPos.getY(), tpPos.getZ() + 0.5f);
            item.damageItem(50, user);
            user.attackEntityFrom(DamageSource.FALL, 1);
        }
    }

    private static void handleGreedEffect (LivingEntity attacker, LivingEntity target, ItemStack item, int level, LivingHurtEvent event) {

        if (target.isNonBoss() && MathsUtils.tryPercentage(0.07 * level) && attacker instanceof PlayerEntity) {

            final PlayerEntity player = (PlayerEntity) attacker;
            int exp = EntityUtils.getExperiencePoints(target, player);
            exp = net.minecraftforge.event.ForgeEventFactory.getExperienceDrop(target, player, exp);

            attacker.world.spawnEntity(new EntityXPOrb(target.world, target.posX, target.posY, target.posZ, exp));
        }
    }

    private static void handleWisdomEffect (LivingEntity attacker, LivingEntity target, ItemStack item, int level, LivingHurtEvent event) {

        if (attacker instanceof PlayerEntity) {

            event.setAmount(event.getAmount() + Math.min(0.625f * level * (((PlayerEntity) attacker).experienceLevel / 25f), 5f * level));
        }
    }

    private static void handleScornEffect (LivingEntity attacker, LivingEntity target, ItemStack item, int level, LivingHurtEvent event) {

        if (target.dimension != 0) {

            event.setAmount(event.getAmount() * (level + 1));
        }
    }

    private static void handleAbsorbEffect (LivingEntity attacker, LivingEntity target, ItemStack item, int level, LivingHurtEvent event) {

        if (attacker instanceof PlayerEntity && MathsUtils.tryPercentage(0.05 * level)) {

            final PlayerEntity player = (PlayerEntity) attacker;
            final int foodAmount = MathsUtils.nextIntInclusive(0, 2);
            player.getFoodStats().addStats(foodAmount, foodAmount);
        }
    }

    private static void handleVenomousAspect (LivingEntity attacker, LivingEntity target, ItemStack item, int level, LivingHurtEvent event) {

        target.addPotionEffect(new PotionEffect(MobEffects.POISON, 120 * level, level));
    }

    private static void handleStealthEffect (LivingEntity user, ItemStack item, int level, RightClickItem event) {

        user.setInvisible(!user.isInvisible());
    }

    private static void handleVitalityEffect (LivingEntity attacker, ItemStack item, int level, RightClickItem event) {

        item.damageItem(128, attacker);
        attacker.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 600, level - 1));
        attacker.addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 10, level - 1));
        attacker.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 500, level - 1));
    }

    private static void handleFeastEffect (LivingEntity attacker, LivingEntity target, ItemStack item, int level, LivingHurtEvent event) {

        if (item.getItemDamage() > 0 && MathsUtils.tryPercentage(0.10 * level)) {

            item.setItemDamage(Math.max(0, item.getItemDamage() - MathsUtils.nextIntInclusive(1 * level, 3 * level)));
        }
    }

    private static void handleSparksEffect (LivingEntity attacker, LivingEntity target, ItemStack item, int level, LivingHurtEvent event) {

        for (final LivingEntity entity : EntityUtils.<LivingEntity> getEntitiesInArea(LivingEntity.class, target.getEntityWorld(), target.getPosition(), level)) {

            if (entity != attacker && entity != target && !entity.isImmuneToFire() && !entity.isPotionActive(MobEffects.FIRE_RESISTANCE)) {

                entity.setFire(4 + level);
                target.attackEntityFrom(DamageSource.ON_FIRE, level);
            }
        }
    }

    private static void handleIgniteEffect (LivingEntity attacker, LivingEntity target, ItemStack item, int level, LivingHurtEvent event) {

        if (!target.isImmuneToFire() && !target.isPotionActive(MobEffects.FIRE_RESISTANCE)) {

            target.attackEntityFrom(DamageSource.ON_FIRE, level * 2f);
            target.setFire(1);
        }
    }

    private static void checkAndApplyEffect (Enchantment enchant, LivingEntity target, LivingEntity attacker, ItemStack heldItem, EnchantmentEffectAttack effect, LivingHurtEvent event) {

        final int level = EnchantmentHelper.getEnchantmentLevel(enchant, heldItem);

        if (level > 0) {

            effect.apply(attacker, target, heldItem, level, event);
        }
    }

    private static void checkAndApplyEffect (Enchantment enchant, LivingEntity attacker, ItemStack heldItem, EnchantmentEffectItemUse effect, RightClickItem event) {

        final int level = EnchantmentHelper.getEnchantmentLevel(enchant, heldItem);

        if (level > 0) {

            effect.apply(attacker, heldItem, level, event);
        }
    }

    private static void checkAndApplyEffect (Enchantment enchant, LivingEntity user, ItemStack heldItem, EnchantmentEffectAttacked effect, LivingHurtEvent event) {

        final int level = EnchantmentHelper.getEnchantmentLevel(enchant, heldItem);

        if (level > 0) {

            effect.apply(user, heldItem, level, event);
        }
    }

    private static void checkAndApplyEffect (Enchantment enchantment, LivingEntity user, ItemStack heldItem, EnchantmentEffectTick effect) {

        final int level = EnchantmentHelper.getEnchantmentLevel(enchantment, heldItem);

        if (level > 0) {

            effect.apply(user, heldItem, level);
        }
    }

    private interface EnchantmentEffectAttack {

        void apply (LivingEntity attacker, LivingEntity target, ItemStack item, int level, LivingHurtEvent event);
    }

    private interface EnchantmentEffectItemUse {

        void apply (LivingEntity attacker, ItemStack item, int level, RightClickItem event);
    }

    private interface EnchantmentEffectAttacked {

        void apply (LivingEntity user, ItemStack item, int level, LivingHurtEvent event);
    }

    private interface EnchantmentEffectTick {

        void apply (LivingEntity user, ItemStack item, int level);
    }
}