package net.shirojr.pathly.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.shirojr.pathly.Pathly;
import net.shirojr.pathly.init.PathlyTags;

import java.util.concurrent.CompletableFuture;

public class PathlyTagGenerators {
    public static class BlockTagProvider extends FabricTagProvider.BlockTagProvider {

        public BlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
            super(output, registriesFuture);
        }

        @Override
        protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
            getOrCreateTagBuilder(PathlyTags.BlockTags.PATH_TOP_REPLACABLES)
                    .addOptionalTag(BlockTags.FLOWERS)

                    .addOptional(Identifier.of(Pathly.COMPAT_MOD_ID_TWIGS, "twig"))
                    .addOptional(Identifier.of(Pathly.COMPAT_MOD_ID_TWIGS, "pebble"))
                    .addOptional(Identifier.of(Pathly.COMPAT_MOD_ID_TWIGS, "opaline_seashell"))
                    .addOptional(Identifier.of(Pathly.COMPAT_MOD_ID_TWIGS, "bronzed_seashell"))
                    .addOptional(Identifier.of(Pathly.COMPAT_MOD_ID_TWIGS, "roseate_seashell"))
                    .addOptional(Identifier.of(Pathly.COMPAT_MOD_ID_TWIGS, "tangerine_seashell"))
                    .addOptional(Identifier.of(Pathly.COMPAT_MOD_ID_TWIGS, "bamboo_leaves"))

                    .addOptional(Identifier.of(Pathly.COMPAT_MOD_ID_THIS_ROCKS, "rock"))
                    .addOptional(Identifier.of(Pathly.COMPAT_MOD_ID_THIS_ROCKS, "granite_rock"))
                    .addOptional(Identifier.of(Pathly.COMPAT_MOD_ID_THIS_ROCKS, "diorite_rock"))
                    .addOptional(Identifier.of(Pathly.COMPAT_MOD_ID_THIS_ROCKS, "andesite_rock"))
                    .addOptional(Identifier.of(Pathly.COMPAT_MOD_ID_THIS_ROCKS, "sand_rock"))
                    .addOptional(Identifier.of(Pathly.COMPAT_MOD_ID_THIS_ROCKS, "red_sand_rock"))
                    .addOptional(Identifier.of(Pathly.COMPAT_MOD_ID_THIS_ROCKS, "gravel_rock"))
                    .addOptional(Identifier.of(Pathly.COMPAT_MOD_ID_THIS_ROCKS, "end_stone_rock"))
                    .addOptional(Identifier.of(Pathly.COMPAT_MOD_ID_THIS_ROCKS, "netherrack_rock"))
                    .addOptional(Identifier.of(Pathly.COMPAT_MOD_ID_THIS_ROCKS, "soul_soil_rock"))

                    .addOptional(Identifier.of(Pathly.COMPAT_MOD_ID_THIS_ROCKS, "oak_stick"))
                    .addOptional(Identifier.of(Pathly.COMPAT_MOD_ID_THIS_ROCKS, "spruce_stick"))
                    .addOptional(Identifier.of(Pathly.COMPAT_MOD_ID_THIS_ROCKS, "birch_stick"))
                    .addOptional(Identifier.of(Pathly.COMPAT_MOD_ID_THIS_ROCKS, "acacia_stick"))
                    .addOptional(Identifier.of(Pathly.COMPAT_MOD_ID_THIS_ROCKS, "jungle_stick"))
                    .addOptional(Identifier.of(Pathly.COMPAT_MOD_ID_THIS_ROCKS, "dark_oak_stick"))
                    .addOptional(Identifier.of(Pathly.COMPAT_MOD_ID_THIS_ROCKS, "mangrove_stick"))
                    .addOptional(Identifier.of(Pathly.COMPAT_MOD_ID_THIS_ROCKS, "cherry_stick"))
                    .addOptional(Identifier.of(Pathly.COMPAT_MOD_ID_THIS_ROCKS, "bamboo_stick"))
                    .addOptional(Identifier.of(Pathly.COMPAT_MOD_ID_THIS_ROCKS, "crimson_stick"))
                    .addOptional(Identifier.of(Pathly.COMPAT_MOD_ID_THIS_ROCKS, "warped_stick"))
                    .addOptional(Identifier.of(Pathly.COMPAT_MOD_ID_THIS_ROCKS, "pinecone"))
                    .addOptional(Identifier.of(Pathly.COMPAT_MOD_ID_THIS_ROCKS, "seashell"))
                    .addOptional(Identifier.of(Pathly.COMPAT_MOD_ID_THIS_ROCKS, "starfish"))

            ;
        }
    }

    public static void registerAll(FabricDataGenerator.Pack generator) {
        generator.addProvider(BlockTagProvider::new);
    }
}
