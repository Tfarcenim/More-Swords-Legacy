package net.darkhax.msmlegacy;

import net.darkhax.msmlegacy.init.ModEnchantments;
import net.darkhax.msmlegacy.init.ModItems;
import net.darkhax.msmlegacy.item.ItemSwordRelic;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ExperienceBottleEntity;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent.SpecialSpawn;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickItem;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;


@EventBusSubscriber(modid = MSMLegacy.MODID)
public class EnchantmentEffectHandler {

    public static final Method calculateXP;

    static {
        calculateXP = ObfuscationReflectionHelper.findMethod(LivingEntity.class,"func_70693_a",PlayerEntity.class);
    }

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
        LivingEntity livingEntity = event.getEntityLiving();
        if ((event.getEntity() instanceof SkeletonEntity || event.getEntity() instanceof ZombieEntity) && Math.random() < ConfigurationHandler.getSpawnChance()) {

            final Item item = ModItems.items.get(livingEntity.getRNG().nextInt(ModItems.items.size()));

            if (item != ModItems.adminiumArk && (ConfigurationHandler.isAllowRelics() || !(item instanceof ItemSwordRelic))) {

                event.getEntityLiving().setHeldItem(Hand.MAIN_HAND, new ItemStack(item));
            }
        }
    }

    private static void handleExtinctionEffect (LivingEntity attacker, LivingEntity target, ItemStack item, int level, LivingHurtEvent event) {

        for (final Entity entity : ((ServerWorld)target.world).getEntities().collect(Collectors.toList())) {

            if (entity instanceof LivingEntity && entity != attacker && entity.getClass() == target.getClass()) {

                entity.remove();
            }
        }
    }

    private static void handleConsumingShadowEffect (LivingEntity attacker, LivingEntity target, ItemStack item, int level, LivingHurtEvent event) {
        AxisAlignedBB bb = new AxisAlignedBB(target.getPosition().add(-level,-level,-level),target.getPosition().add(level,level,level));
        for (final LivingEntity entity : target.world.getEntitiesWithinAABB(LivingEntity.class,bb)) {

            if (entity != attacker) {
                entity.addPotionEffect(new EffectInstance(Effects.WITHER, 60 * level, level));
                target.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 60 * level, level));
            }
        }
    }

    private static void handleDecayEffect (LivingEntity attacker, LivingEntity target, ItemStack item, int level, LivingHurtEvent event) {

        target.addPotionEffect(new EffectInstance(Effects.WITHER, 60 * level));
        target.addPotionEffect(new EffectInstance(Effects.HUNGER, 60 * level, level));
    }

    private static void handleDescensionEffect (LivingEntity user, ItemStack item, int level) {

        if (user.isSneaking()) {
            Vector3d motion = user.getMotion();
            user.setMotion(motion.x,motion.y * .6,motion.z);
            user.fallDistance = 0;
        }
    }

    private static void handleFrostWaveEffect (LivingEntity attacker, LivingEntity target, ItemStack item, int level, LivingHurtEvent event) {
        AxisAlignedBB bb = new AxisAlignedBB(target.getPosition().add(-level,-level,-level),target.getPosition().add(level,level,level));
        for (final LivingEntity entity : target.world.getEntitiesWithinAABB(LivingEntity.class,bb)) {

            if (entity != attacker) {

                entity.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 100 * level, level));
            }
        }
    }

    private static void handleFrozenAspectEffect (LivingEntity attacker, LivingEntity target, ItemStack item, int level, LivingHurtEvent event) {

        target.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 200 * level, level));
    }

    private static void handleAscensionEffect (LivingEntity attacker, LivingEntity target, ItemStack item, int level, LivingHurtEvent event) {

        target.addPotionEffect(new EffectInstance(Effects.LEVITATION, 30, 4 * level));
    }

    private static void handleEnderAura (LivingEntity user, ItemStack item, int level, LivingHurtEvent event) {

        for (int i = 0; i < 3; i++) {

            if (Math.random() < 0.30d) {

                final AxisAlignedBB bounds = user.getBoundingBox().grow(30d);
                final List<Entity> entities = user.world.getEntitiesInAABBexcluding(user, bounds, entity -> entity instanceof LivingEntity);

                if (!entities.isEmpty()) {

                    final Entity randomEntity = entities.get(user.world.rand.nextInt(entities.size()));

                    if (randomEntity instanceof LivingEntity) {

                        final LivingEntity living = (LivingEntity) randomEntity;

                        user.setPositionAndUpdate(living.getPosX(), living.getPosY(), living.getPosZ());
                    }
                }
            }
        }
    }

    private static void handleEnderPulseEffect (LivingEntity user, ItemStack item, int level, RightClickItem event) {

        final RayTraceResult results = user.pick( 16d * level,0,false);

        if (results instanceof BlockRayTraceResult) {
            BlockRayTraceResult blockRayTraceResult = (BlockRayTraceResult)results;
            final BlockPos tpPos = blockRayTraceResult.getPos().offset(blockRayTraceResult.getFace());
            user.setPositionAndUpdate(tpPos.getX() + 0.5, tpPos.getY(), tpPos.getZ() + 0.5f);
            item.damageItem(50, user,livingEntity -> livingEntity.sendBreakAnimation(event.getHand()));
            user.attackEntityFrom(DamageSource.FALL, 1);
        }
    }

    private static void handleGreedEffect (LivingEntity attacker, LivingEntity target, ItemStack item, int level, LivingHurtEvent event) {

        if (target.isNonBoss() && Math.random() < 0.07 * level && attacker instanceof PlayerEntity) {

            final PlayerEntity player = (PlayerEntity) attacker;
            int exp = 0; //target.getExperiencePoints(attacker); //EntityUtils.getExperiencePoints(target, player);
            try {
                exp = (int) calculateXP.invoke(target,player);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            exp = net.minecraftforge.event.ForgeEventFactory.getExperienceDrop(target, player, exp);

            attacker.world.addEntity(new ExperienceOrbEntity(target.world, target.getPosX(), target.getPosY(), target.getPosZ(), exp));
        }
    }

    private static void handleWisdomEffect (LivingEntity attacker, LivingEntity target, ItemStack item, int level, LivingHurtEvent event) {

        if (attacker instanceof PlayerEntity) {

            event.setAmount(event.getAmount() + Math.min(0.625f * level * (((PlayerEntity) attacker).experienceLevel / 25f), 5f * level));
        }
    }

    private static void handleScornEffect (LivingEntity attacker, LivingEntity target, ItemStack item, int level, LivingHurtEvent event) {

        if (target.world.getDimensionKey() != World.OVERWORLD) {

            event.setAmount(event.getAmount() * (level + 1));
        }
    }

    private static void handleAbsorbEffect (LivingEntity attacker, LivingEntity target, ItemStack item, int level, LivingHurtEvent event) {

        if (attacker instanceof PlayerEntity && Math.random() < 0.05 * level) {

            final PlayerEntity player = (PlayerEntity) attacker;
            final int foodAmount = player.getRNG().nextInt(3);
            player.getFoodStats().addStats(foodAmount, foodAmount);
        }
    }

    private static void handleVenomousAspect (LivingEntity attacker, LivingEntity target, ItemStack item, int level, LivingHurtEvent event) {

        target.addPotionEffect(new EffectInstance(Effects.POISON, 120 * level, level));
    }

    private static void handleStealthEffect (LivingEntity user, ItemStack item, int level, RightClickItem event) {

        user.setInvisible(!user.isInvisible());
    }

    private static void handleVitalityEffect (LivingEntity attacker, ItemStack item, int level, RightClickItem event) {

        item.damageItem(128, attacker,livingEntity -> livingEntity.sendBreakAnimation(event.getHand()));
        attacker.addPotionEffect(new EffectInstance(Effects.ABSORPTION, 600, level - 1));
        attacker.addPotionEffect(new EffectInstance(Effects.INSTANT_HEALTH, 10, level - 1));
        attacker.addPotionEffect(new EffectInstance(Effects.REGENERATION, 500, level - 1));
    }

    private static void handleFeastEffect (LivingEntity attacker, LivingEntity target, ItemStack item, int level, LivingHurtEvent event) {

        if (item.getDamage() > 0 && Math.random() < 0.10 * level) {

            item.setDamage(Math.max(0, item.getDamage() - attacker.getRNG().nextInt( 2 * level + 1) + level));
        }
    }

    private static void handleSparksEffect (LivingEntity attacker, LivingEntity target, ItemStack item, int level, LivingHurtEvent event) {
        AxisAlignedBB bb = new AxisAlignedBB(target.getPosition().add(-level,-level,-level),target.getPosition().add(level,level,level));
        for (final LivingEntity entity : target.world.getEntitiesWithinAABB(LivingEntity.class,bb)) {

            if (entity != attacker && entity != target && !entity.isImmuneToFire() && !entity.isPotionActive(Effects.FIRE_RESISTANCE)) {

                entity.setFire(4 + level);
                target.attackEntityFrom(DamageSource.ON_FIRE, level);
            }
        }
    }

    private static void handleIgniteEffect (LivingEntity attacker, LivingEntity target, ItemStack item, int level, LivingHurtEvent event) {

        if (!target.isImmuneToFire() && !target.isPotionActive(Effects.FIRE_RESISTANCE)) {

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