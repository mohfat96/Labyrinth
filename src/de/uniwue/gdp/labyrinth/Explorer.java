package de.uniwue.gdp.labyrinth;
import de.uniwue.gdp.labyrinth.examples.Examples;
import de.uniwue.gdp.labyrinth.model.Maze;
import de.uniwue.gdp.labyrinth.model.Maze.Direction;

public class Explorer {

	static int x = 1;
	static int y = 1;
	static int angle = 270;
	static String s;

	static void changePosition(Maze.Direction direction, String[][] maze) {

		switch (direction) {
		case RIGHT:
			angle -= 90;
			break;
		case LEFT:
			angle += 90;
			break;
		case AHEAD:
			break;
		case BACK:
			angle -= 180;
			break;
			
		}

		switch (angle) {
		case 360:
			angle = 0;
			break;
		case -90:
			angle = 270;
			break;
		case -180:
			angle = 180;
			break;
		case -270:
			angle = 90;
			break;	
		}

		switch (angle) {
		case 0:
			x++;
			break;
		case 90:
			y--;
			break;
		case 180:
			x--;
			break;
		case 270:
			y++;
			break;	
		}

		maze[y][x] = " ";
	}
	
	static public void main(String args[]){
		System.out.println(exploreMaze(Examples.example02()));
	}



	public static String exploreMaze(Maze z) {
		x = 1;
		y = 1;
		angle = 270;
		String[][] maze = new String[z.height()][z.width()];
		maze[1][1] = " ";
		String result = "";
		Direction[] allDirection = { Direction.AHEAD, Direction.LEFT, Direction.RIGHT };
		Direction[] possible = new Direction[3];
		int step = 0;
		
		while (true) {
			int weg = 0, k = 0;
			for (int i = 0; i < allDirection.length; i++) {
				if (!z.isWall(allDirection[i])) {
					weg++;
				}

			}
			
			if (weg == 0){
				z.walk(Direction.BACK);
				changePosition(Direction.BACK, maze);
				continue;
			}
			
			possible = new Direction[weg];

			for (int i = 0; i < allDirection.length; i++) {
				if (!z.isWall(allDirection[i])) {
					possible[k] = allDirection[i];
					k++;	
				}

			}

			if (weg > 1) {
				
				z.mark(Direction.BACK);
				if (wasHere(possible, z)) {
					Direction dir = littleMarks(possible, z);

					if (dir == null) {
						break;
					}

					else {
						z.mark(dir);
						z.walk(dir);
						changePosition(dir ,maze);

					}

				} else {
					z.mark(possible[1]);
					z.walk(possible[1]);
					changePosition(possible[1], maze);
				}
			} else if (weg == 1) {
				z.walk(possible[0]);
				changePosition(possible[0], maze);
			}
		

		}
		
		
	
		

		for (int i = 0; i < z.height(); i++) // save the maze as String result
												// and fill it with #
		{

			for (int j = 0; j < z.width(); j++) {
				if (maze[i][j] == null)
					maze[i][j] = "#";
				result = result + maze[i][j];
			}
			
			if (i != z.height() - 1){
				result = result + '\n';
			}

			
		}

		return result;

	}

	private static Direction littleMarks(Direction[] possible, Maze z) {
		// TODO Auto-generated method stub
		int smallest = z.marks(possible[0]);
		int index = 0;

		if (z.marks(Direction.BACK) < 2) {
			return Direction.BACK;
		} else {
			for (int i = 0; i < possible.length; i++) {
				if (z.marks(possible[i]) < smallest) {
					index = i;
					smallest = z.marks(possible[i]);
				}
			}
		}
		if (smallest == 2)
			return null;
		else
			return possible[index];
		

	}

	private static boolean wasHere(Direction[] possible, Maze z) {
		// TODO Auto-generated method stub
		int marks = 0;
		for (int i = 0; i < possible.length; i++) {
			if ((z.marks(possible[i]) != 0)) {
				marks++;
				
			}
		}
		if (marks == 0)
			return false;
		else
			return true;
	}

}


