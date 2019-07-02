package de.uniwue.gdp.labyrinth.model;

public interface Maze {
    enum Direction {
        LEFT, AHEAD, RIGHT, BACK
    }

    /**
     * @return the total width of this maze.
     */
    int width();

    /**
     * @return the total height of this maze.
     */
    int height();

    /**
     * Checks whether there is a wall in the given direction or not.
     * @param direction the direction to check.
     * @return true if and only if there is a wall in the given direction.
     */
    boolean isWall(Direction direction);

    /**
     * Marks this field of the maze in the given direction. Every field can have multiple marks in arbitrary directions.
     * @param direction the direction to mark.
     */
    void mark(Direction direction);

    /**
     * Gets the number of marks on the current field in the given direction.
     * @param direction the direction the marks are oriented.
     * @return the number of marks.
     */
    int marks(Direction direction);

    /**
     * Walks in the given direction.
     * @param direction the direction to walk.
     */
    void walk(Direction direction);
}
