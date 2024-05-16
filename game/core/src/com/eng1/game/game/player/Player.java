package com.eng1.game.game.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Vector2;
import lombok.Getter;
import lombok.Setter;

/**
 * A class that represents the player character in the game.
 */
public class Player extends Sprite implements InputProcessor {
    private final Vector2 velocity = new Vector2();
    @Setter
    private float speed = 60 * 5;
    @Getter
    private final TiledMapTileLayer collisionLayer;

    /**
     * Constructs a new player with the given sprite and collision layer.
     * @param sprite The sprite representing the player character.
     * @param collisionLayer The collision layer for detecting collisions with tiles.
     */
    public Player(Sprite sprite, TiledMapTileLayer collisionLayer) {
        super(sprite);
        this.collisionLayer = collisionLayer;
        setScale(3);
    }

    @Override
    public void draw(Batch batch) {
        update(Gdx.graphics.getDeltaTime());
        super.draw(batch);
    }

    /**
     * Updates the player's position and checks for collisions.
     * @param delta The time passed since the last frame.
     */
    public void update(float delta) {

        //save old position
        float oldX = getX();
        float oldY = getY();

        boolean collisionX = false;
        boolean collisionY = false;

        setX(getX() + velocity.x * delta);

        if (velocity.x < 0) {// going left
            collisionX = collidesLeft();
        } else if (velocity.x > 0) {// going right
            collisionX = collidesRight();
        }

        //react to x collision
        if (collisionX) {
            setX(oldX);
            velocity.x = 0;
        }

        //move y
        setY(getY() + velocity.y * delta);
        if (velocity.y < 0) { // going down
            collisionY = collidesBottom();
        } else if (velocity.y > 0) { // going up
            collisionY = collidesTop();
        }
        //react to y collision
        if (collisionY) {
            setY(oldY);
            velocity.y = 0;
        }
    }

    /**
     * Called when a key is pressed.
     *
     * @param keycode The keycode of the key that was pressed (e.g., {@link com.badlogic.gdx.Input.Keys#W} for the 'W' key).
     * @return {@code true} if the input event was handled, {@code false} otherwise.
     */
    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Keys.W:
            case Keys.UP:
                velocity.y = speed;
                break;
            case Keys.A:
            case Keys.LEFT:
                velocity.x = -speed;
                break;
            case Keys.S:
            case Keys.DOWN:
                velocity.y = -speed;
                break;
            case Keys.D:
            case Keys.RIGHT:
                velocity.x = speed;
                break;

        }
        return true;
    }

    /**
     * Called when a key is pressed.
     *
     * @param keycode The keycode of the key that was pressed (e.g., {@link com.badlogic.gdx.Input.Keys#W} for the 'W' key).
     * @return {@code true} if the input event was handled, {@code false} otherwise.
     */
    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Keys.W:
            case Keys.S:
            case Keys.UP:
            case Keys.DOWN:
                velocity.y = 0;
                break;
            case Keys.A:
            case Keys.D:
            case Keys.LEFT:
            case Keys.RIGHT:
                velocity.x = 0;
                break;
        }
        return true;
    }
    /**
     * Checks whether the tile contains a property called blocked.
     * @param x which is the x coord of the player.
     * @param y which is the y coord of the player.
     * @return True if it does contain it and false if not.
     */
    private boolean isCellBlocked (float x, float y){
        Cell cell = collisionLayer.getCell((int) (x / collisionLayer.getTileWidth()), (int) (y / collisionLayer.getTileHeight()));
        String blockedKey = "blocked";
        if (cell == null) {
            return false;
        }
        TiledMapTile tile = cell.getTile();
        return tile != null && tile.getProperties().containsKey(blockedKey);
    }
    /**
     * Checks whether the tile contains a property called transition.
     * @param x which is the x coord of the player.
     * @param y which is the y coord of the player.
     * @return True if it does contain it and false if not.
     */
/*    private boolean isCellTransition (float x, float y) {
        Cell cell = collisionLayer.getCell((int) (x / collisionLayer.getTileWidth()), (int) (y / collisionLayer.getTileHeight()));
        return cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey(transitionKey);
    }*/
    /**
     * Checks whether the tile contains a property called activity.
     * @param x which is the x coord of the player.
     * @param y which is the y coord of the player.
     * @return True if it does contain it and false if not.
     */
/*    public boolean isCellActivity (float x, float y) {
        Cell cell = collisionLayer.getCell((int) (x / collisionLayer.getTileWidth()), (int) (y / collisionLayer.getTileHeight()));
        return cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey(activityKey);
    }*/
    /**
     * Gets the value of the property from the transition tile.
     * @param x which is the x coord of the player.
     * @param y which is the y coord of the player.
     */
/*    private void getTransition (float x, float y) {
        Cell cell = collisionLayer.getCell((int) (x / collisionLayer.getTileWidth()), (int) (y / collisionLayer.getTileHeight()));
        if (cell.getTile().getProperties().containsKey(transitionKey)) {
            Object value = cell.getTile().getProperties().get("transition");
            if (value != null) {
                transitionValue = value.toString();
            }
        }
    }*/
    /**
     * Gets the value of the property from the activity tile.
     * @param x which is the x coord of the player.
     * @param y which is the y coord of the player.
     */
/*    private void getActivity (float x, float y) {
        Cell cell = collisionLayer.getCell((int) (x / collisionLayer.getTileWidth()), (int) (y / collisionLayer.getTileHeight()));
        if (cell.getTile().getProperties().containsKey(activityKey)) {
            Object value = cell.getTile().getProperties().get("activity");
            if (value != null) {
                activityValue = value.toString();
            }
        }
    }*/

    /**
     * Checks if the cell to the right of the player is blocked.
     * @return True if the cell is blocked, false otherwise.
     */
    public boolean collidesRight() {
        for (float step = 0; step < getHeight(); step += (float) collisionLayer.getTileHeight() / 2) {
            if (isCellBlocked(getX() + getWidth(), getY() + step)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the cell to the right the player is a transition.
     * @return True if the cell is a transition, false otherwise.
     */
/*    public boolean transitionRight() {
        for (float step = 0; step < getHeight(); step += (float) collisionLayer.getTileHeight() / 2) {
            if (isCellTransition(getX() + getWidth(), getY() + step)) {
                getTransition(getX() + getWidth(), getY() + step);
                return true;
            }
        }
        return false;
    }*/
    /**
     * Checks if the cell to the right of the player is an activity.
     * @return True if the cell is an activity, false otherwise.
     */
/*    public boolean activityRight () {
        for (float step = 0; step < getHeight(); step += (float) collisionLayer.getTileHeight() / 2) {
            if (isCellActivity(getX() + getWidth(), getY() + step)) {
                getActivity(getX() + getWidth(), getY() + step);
                return true;
            }
        }
        return false;
    }*/

    /**
     * Checks if the cell to the left of the player is blocked.
     * @return True if the cell is blocked, false otherwise.
     */
    public boolean collidesLeft() {
        for (float step = 0; step < getHeight(); step += (float) collisionLayer.getTileHeight() / 2) {
            if (isCellBlocked(getX() + getWidth(), getY() + step)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the cell to the left of the player is a transition.
     * @return True if the cell is a transition, false otherwise.
     */
/*    public boolean transitionLeft () {
        for (float step = 0; step < getHeight(); step += (float) collisionLayer.getTileHeight() / 2) {
            if (isCellTransition(getX() + getWidth(), getY() + step)) {
                getTransition(getX() + getWidth(), getY() + step);
                return true;
            }
        }
        return false;
    }*/
    /**
     * Checks if the cell to the left of the player is an activity.
     * @return True if the cell is an activity, false otherwise.
     */
/*    public boolean activityLeft () {
        for (float step = 0; step < getHeight(); step += (float) collisionLayer.getTileHeight() / 2) {
            if (isCellActivity(getX() + getWidth(), getY() + step)) {
                getActivity(getX() + getWidth(), getY() + step);
                return true;
            }
        }
        return false;
    }*/

    /**
     * Checks if the cell above the player is blocked.
     * @return True if the cell is blocked, false otherwise.
     */
    public boolean collidesTop () {
        for (float step = 0; step < getWidth(); step += (float) collisionLayer.getTileWidth() / 2) {
            if (isCellBlocked(getX() + getWidth(), getY() + step)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the cell above the player is a transition.
     * @return True if the cell is a transition, false otherwise.
     */
/*    public boolean transitionTop() {
        for (float step = 0; step < getHeight(); step += (float) collisionLayer.getTileHeight() / 2) {
            if (isCellTransition(getX() + getWidth(), getY() + step)) {
                getTransition(getX() + getWidth(), getY() + step);
                return true;
            }
        }
        return false;
    }*/
    /**
     * Checks if the cell above of the player is an activity.
     * @return True if the cell is an activity, false otherwise.
     */
/*    public boolean activityTop() {
        for (float step = 0; step < getHeight(); step += (float) collisionLayer.getTileHeight() / 2) {
            if (isCellActivity(getX() + getWidth(), getY() + step)) {
                getActivity(getX() + getWidth(), getY() + step);
                return true;
            }
        }
        return false;
    }*/

    /**
     * Checks if the cell below the player is blocked.
     * @return True if the cell is blocked, false otherwise.
     */
    public boolean collidesBottom() {
        for(float step = 0; step < getWidth(); step += (float) collisionLayer.getTileWidth() / 2) {
            if (isCellBlocked(getX() + getWidth(), getY() + step)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the cell below the player is a transition.
     * @return True if the cell is a transition, false otherwise.
     */
/*    public boolean transitionBottom() {
        for (float step = 0; step < getHeight(); step += (float) collisionLayer.getTileHeight() / 2) {
            if (isCellTransition(getX() + getWidth(), getY() + step)) {
                getTransition(getX() + getWidth(), getY() + step);
                return true;
            }
        }
        return false;
    }*/
    /**
     * Checks if the cell below of the player is an activity.
     * @return True if the cell is an activity, false otherwise.
     */
/*    public boolean activityBottom() {
        for (float step = 0; step < getHeight(); step += (float) collisionLayer.getTileHeight() / 2) {
            if (isCellActivity(getX() + getWidth(), getY() + step)) {
                getActivity(getX() + getWidth(), getY() + step);
                return true;
            }
        }
        return false;
    }*/

    // Checks for inputs
    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

}