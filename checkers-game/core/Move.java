package core;

/**
 * Record class representing a move on a checkers board.
 * Used for edge case with generating random moves for computer.
 *
 * @param startX Starting row.
 * @param startY Starting column.
 * @param endX   Ending row.
 * @param endY   Ending column.
 * @author Muaaz Wahid
 * @version 9/5/2023
 */
public record Move(int startX, int startY, int endX, int endY) {}
