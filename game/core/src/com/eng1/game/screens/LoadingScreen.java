package com.eng1.game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.eng1.game.HeslingtonHustle;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Represents the loading screen of the game.
 * This screen is displayed while the game is loading resources or preparing for the main menu.
 * ((redundant for now because loading takes a negligible amount of time))
 *
 * @since v2 -- the screen now uses the {@link HeslingtonHustle#getInstance()} method to access the orchestrator
 */
public class LoadingScreen implements Screen {
    private final HeslingtonHustle parent = HeslingtonHustle.getInstance(); // a field to store our orchestrator
    private final Stage stage;

    /**
     * Constructor for the LoadingScreen class.
     * Initializes the parent orchestrator and creates a new stage for UI rendering.
     */
    public LoadingScreen(){
        stage = new Stage(new ScreenViewport());

    }
    @Override
    public void show() {
    }

    /**
     * When the loading screen has finished loading, set the screen to the menu screen.
     */
    @Override
    public void render(float delta) {
        parent.changeScreen(Screens.MENU);

    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);

    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }
}