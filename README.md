
# Pathly

Pathly is a Fabric Minecraft mod that makes creating path blocks
smoother and more intuitive as well as providing other QoL features.

Paths can be created by directly targeting e.g. dirt blocks or by
targeting "replaceable" blocks on top of them.

This makes path-making feel much more natural—no more needing to clear vegetation before using a shovel!

In addition, sword and axe items will avoid hitting foliage.

## ✨ Features

- Automatically shifts the shovel’s path-creation check downward when the aimed block is replaceable.
- Fully vanilla-friendly behavior—does not change the properties of any block.
- Works with any Shovel Item
- Swords and Axes will ignore foliage from being targetable, helping out players when fighting

## 🔧 Configuration

- Additional replaceable blocks can be specified with the newly added [pathly:path_top_replacables](src/main/generated/data/pathly/tags/blocks/path_top_replacables.json) block tag
- Using the `"enablePathTopReplacables"` Gamerule, the replaceable feature can be enabled and disabled
- The Block Tag [pathly:raycast_pass_through](src/main/generated/data/pathly/tags/blocks/raycast_pass_through.json) defines additional blocks, which swords and axes will ignore. All blocks without collision are automatically included
- The Item Tag [pathly:modified_raycast](src/main/generated/data/pathly/tags/items/modified_raycast.json) defines Items, which can ignore hitting specific blocks. Swords and Axes are included by default

<div style="text-align: center;">
<br>
<a href="https://fabricmc.net/"><img
    src="https://raw.githubusercontent.com/fabricated-atelier/.github/a021bde84febcb68adc69fc7ae60114e8c0902db/assets/badges/bc25/supported_on_fabric_loader.svg"
    alt="Supported on Fabric"
    width="200"
></a>
</div>