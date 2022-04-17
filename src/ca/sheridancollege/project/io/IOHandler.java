package ca.sheridancollege.project.io;

/**
 * This class handles everything related to input/output operations in the game.
 *
 * @author Jayson Evans
 */
public abstract class IOHandler
{
        protected static final String SAVE_FILE_NAME = "save.dat";
        
        public static String getSaveFileName()
        {
            return SAVE_FILE_NAME;
        }
}
