import java.util.Scanner;
import java.util.Random;


public class Tic {
	public static char[][] board = new char [3][3];
	public static void main(String[] args)
	{
		boolean computer = false;
		Scanner in = new Scanner(System.in);
	    System.out.print("How many Player? [1/2] ");
		int num = in.nextInt();
		if(num == 2)
		{
			computer = false;
		}
		else
		{
			computer = true;
		}
		innitialize();
		printBoard();
		play(computer);
	}
	
	public static void playerMove(char player)
	{
	    int r, c;
	    System.out.print(player + " turn!\n");
	    System.out.print("Please choose the row: ");
		Scanner in = new Scanner(System.in);
	    r = in.nextInt();
	    System.out.print("Please choose the column: ");
	    c = in.nextInt();
	    while( r < 1 || r > 3 || c < 1 || c > 3 || board[r-1][c-1] != '-')
	    {
		    System.out.print("Not Available! Please choose another place!\n");	
		    System.out.print("Please choose the row: ");
		    r = in.nextInt();
		    System.out.print("Please choose the column: ");
		    c = in.nextInt();
	    }
	    board[r-1][c-1] = player;
	    printBoard();
	}
	
	public static void play (boolean comp)
	{
		int depth = 0;
		while(avail())
		{
			playerMove('X');
			if(hasWon('X'))
			{
			    System.out.print("X Wins!");
			    return;
			}
			if(!comp)
			{
				playerMove('O');
			}
			else
			{
				compMove();
			}
			if(hasWon('O'))
			{
			    System.out.print("O Wins!");
			    return;
			}

		}
	}
	
	public static void compMove()
	{
	    int r = 0,c = 0;
	    int bestScore = -1000;
	    int score;
	    int t = 0;
	    for(int i = 0; i < 3; i++)
	    {
	        for(int j = 0; j < 3; j++)
	        {
	            if(board[i][j] == '-')
	            {
	                board[i][j] = 'O';
	                score = scores(0,false);
	                if(score >= bestScore)
	                {
	                    bestScore = score;
	                    r = i;
	                    c = j;
	                }
	                board[i][j] = '-';
	            }
	        }
	    }
	    board[r][c] = 'O';
	    printBoard();

	}
	
	private static int max(int a, int b)
	{
		if (a >= b)
			return a;
		return b;
	}

	private static int min(int a, int b)
	{
		if (a <= b)
			return a;
		return b;
	}
	
	public static int scores(int depth, boolean comp )
	{
	    if(hasWon('O'))
	    {
	        return 10-depth;
	    }
	    if(hasWon('X'))
	    {
	        return depth-10;
	    }
	    if(!avail())
	    {
	        return 0;
	    }
	    int bestScore;
	    if(comp)
	    {
	        bestScore = -1000;
	    }
	    else
	    {
	        bestScore = 1000;
	    }
	    for(int i = 0; i < 3; i++)
	    {
	        for (int j = 0; j < 3; j++)
	        {
	            if(board[i][j] == '-')
	            {
	                if(comp)
	                {
	                    board[i][j] = 'O';
	                    bestScore = max(bestScore,scores(depth+1, false));
	                    board[i][j] = '-';
	                }
	                else
	                {
	                    board[i][j] = 'X';
	                    bestScore = min(bestScore,scores(depth+1, true));
	                    board[i][j] = '-';
	                }
	            }
	        }
	    }
	    return bestScore;
	}

	
	public static boolean avail()
	{
    	for (int i = 0; i < 3; i++)
    	{
    		for (int j = 0; j < 3; j++)
    		{
    			if (board[i][j]=='-')
    				return true;
    		}
    	}
    	return false;
	}


    public static void innitialize()
    {
    	for (int i = 0; i < 3; i++)
    	{
    		for (int j = 0; j < 3; j++)
    		{
    			board[i][j] = '-';
    		}
    	}
    }
    

    public static boolean hasWon(char c)
    {
        if ((board [0][0] == board[1][1]) && (board[1][1] == board[2][2])
                && (board[2][2] == c))
            return true;
        if ((board [0][2] == board[1][1]) && (board[1][1] == board[2][0])
                && (board[0][2] == c))
            return true;
        for (int i = 0; i < 3; i++)
        {
            if ((board [i][0] == board[i][1]) && (board[i][1] == board[i][2])
                    && (board[i][2] == c))
                return true;
            if ((board [0][i] == board[1][i]) && (board[1][i] == board[2][i])
                    && (board[2][i] == c))
                return true;
        }
        return false;
    }
    
    public static void printBoard() {
		System.out.print("   1   2  3\n");
    	for (int i = 0; i < 3; i++)
    	{
    		System.out.print("  -----------\n");
    		System.out.print(i+1);
    		for (int j = 0; j < 3; j++)
    		{
    			System.out.print("| ");
    			System.out.print(board[i][j]);
    			System.out.print(" ");
    		}
			System.out.print("|\n");
    	}
		System.out.print("  -----------\n");
    }
}

