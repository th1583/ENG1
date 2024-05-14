package com.eng1.game.assets.images;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.eng1.game.assets.Assets;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Supplier;

public enum ImageAssets implements Assets<Texture> {
    MAIN_MENU_TITLE(() -> new Texture(Gdx.files.internal("images/main_menu_title.png")));

    private final List<Texture> loadedTextures = new ArrayList<>();
    private final Supplier<Texture> texture;

    ImageAssets(Supplier<Texture> texture) {
        this.texture = texture;
    }

    @Override
    public Texture get() {
        Texture texture = this.texture.get();
        loadedTextures.add(texture);
        return texture;
    }

    @Override
    public void dispose() {
        for (Texture texture : loadedTextures) {
            texture.dispose();
        }
        loadedTextures.clear();
    }

    @Override
    public void dispose(@NotNull Texture asset) {
        asset.dispose();
        loadedTextures.remove(asset);
    }

    public static void disposeAll() {
        for (ImageAssets asset : values()) {
            asset.dispose();
        }
    }
}