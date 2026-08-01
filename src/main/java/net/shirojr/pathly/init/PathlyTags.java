package net.shirojr.pathly.init;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.shirojr.pathly.Pathly;

import java.util.ArrayList;
import java.util.List;

public class PathlyTags {
    public static final List<TagKey<?>> ALL_TAGS = new ArrayList<>();

    public static class BlockTags {
        public static final List<TagKey<Block>> ALL_BLOCK_TAGS = new ArrayList<>();

        public static final TagKey<Block> PATH_TOP_REPLACABLES = createTag("path_top_replacables");
        public static final TagKey<Block> RAYCAST_PASS_THROUGH = createTag("raycast_pass_through");

        @SuppressWarnings("SameParameterValue")
        private static TagKey<Block> createTag(String name) {
            TagKey<Block> tagKey = TagKey.of(RegistryKeys.BLOCK, Pathly.getId(name));
            ALL_BLOCK_TAGS.add(tagKey);
            ALL_TAGS.add(tagKey);
            return tagKey;
        }

        public static void initialize() {
            // static initialisation
        }
    }

    public static class ItemTags {
        public static final List<TagKey<Item>> ALL_ITEM_TAGS = new ArrayList<>();

        public static final TagKey<Item> MODIFIED_RAYCAST = createTag("modified_raycast");

        @SuppressWarnings("SameParameterValue")
        private static TagKey<Item> createTag(String name) {
            TagKey<Item> tagKey = TagKey.of(RegistryKeys.ITEM, Pathly.getId(name));
            ALL_ITEM_TAGS.add(tagKey);
            ALL_TAGS.add(tagKey);
            return tagKey;
        }

        public static void initialize() {
            // static initialisation
        }
    }

    public static void initialize() {
        BlockTags.initialize();
        ItemTags.initialize();
    }
}
