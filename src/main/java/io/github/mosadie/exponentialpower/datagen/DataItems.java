package io.github.mosadie.exponentialpower.datagen;

import io.github.mosadie.exponentialpower.ExponentialPower;
import io.github.mosadie.exponentialpower.setup.Registration;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class DataItems extends ItemModelProvider {

    public DataItems(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ExponentialPower.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        singleTexture(Registration.ENDER_CELL.get().toString(), new ResourceLocation("item/handheld"), "layer0", new ResourceLocation(ExponentialPower.MODID, "item/ender_cell"));
        withExistingParent(Registration.ENDER_GENERATOR_ITEM.get().toString(), new ResourceLocation(ExponentialPower.MODID, "block/ender_generator"));
        withExistingParent(Registration.ADV_ENDER_GENERATOR_ITEM.get().toString(), new ResourceLocation(ExponentialPower.MODID, "block/advanced_ender_generator"));
        withExistingParent(Registration.ENDER_STORAGE_ITEM.get().toString(), new ResourceLocation(ExponentialPower.MODID, "block/ender_storage"));
        withExistingParent(Registration.ADV_ENDER_STORAGE_ITEM.get().toString(), new ResourceLocation(ExponentialPower.MODID, "block/advanced_ender_storage"));
    }
}
