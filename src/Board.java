/*
 * So what I'm thinking for this is to have a board object that holds the 
 * current state of the board. The only flaw with this method is that I did something 
 * similar for my Rubik's cube project, and found that it was actually extremely difficult 
 * to copy the board, without copying the reference as well. For example, if you 
 * perform changes to a copy, those changes will be applied to the original as well.
 *
 *
 * We could try making a 2-D array to represent the board. I'm not sure how that would play out,
 * but it's simple if we just assign pieces to the board. We could do actual move calculation on the
 * pieces themselves.
 */
public class Board {

}
