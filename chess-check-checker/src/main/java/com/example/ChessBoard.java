package com.example;

public class ChessBoard {
	private final ChessMan[][] chessManPosition = new ChessMan[8][8]; 
	private static final char[][] POS_INIT  = { 	{ 'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R' },
													{ 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P' },
												    { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
												    { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
												    { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
												    { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
												    { 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p' }, 
												    { 'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r' } };
	 
	
	public ChessBoard (ChessMan[]  whitePlayer, ChessMan[]  blackPlayer){
		
		{
			byte chessManCounter = 0;
			for (int y =0; y<2; y++){
				for (int x =0; x<8; x++){
					char chessManRole = POS_INIT[y][x];
					whitePlayer[chessManCounter].setColor("White");
					whitePlayer[chessManCounter].setRole(chessManRole);
					this.chessManPosition[y][x] = whitePlayer[chessManCounter];
					
					chessManCounter ++;
				}
			}
		}
		{
			byte chessManCounter = 0;
			for (int y =6; y<8; y++){
				for (int x =0; x<8; x++){
					char chessManRole = POS_INIT[y][x];
					blackPlayer[chessManCounter].setColor("Black");
					blackPlayer[chessManCounter].setRole(chessManRole);
					this.chessManPosition[y][x] = blackPlayer[chessManCounter];
					
					chessManCounter ++;
				}
			}
		}
		
		displayBoard();
	}
	
	public void move(byte fx, byte fy, byte tx, byte ty){
		
		if (isOutofBoard(fx, fy) || isOutofBoard(tx, ty))
			return;
		
		ChessMan chessManToMove = this.chessManPosition[fy][fx];
		if (chessManToMove == null)
			return;
		
		this.chessManPosition[fy][fx] = null;
		this.chessManPosition[ty][tx] = chessManToMove;
		
		
		System.out.println("==========================================");
		for (byte y=0;y<7;y++){
			for (byte x=0;x<7;x++){
				
				if (this.chessManPosition[y][x] != null){
					char chessManRole = this.chessManPosition[y][x].getRole();
					
					if (isCheck(chessManRole,x,y,this.chessManPosition))
						System.out.println("Role: "+ chessManRole +" Check !!");
				}
			}
		}
		
		displayBoard();
	}
	
	public void displayBoard(){
		
		for (byte y=0;y<8;y++){
			for (byte x=0;x<8;x++){
				char chessManRole = this.chessManPosition[y][x] != null ? this.chessManPosition[y][x].getRole() : ' ';
				System.out.print(chessManRole + " ");
			}
			System.out.println("");
		}
	}
	
	public static boolean isCheck(char figure, byte xFigure, byte yFigure, ChessMan[][] chessmanPosition) {

		if (isOutofBoard(xFigure, yFigure))
			return false;
		
		boolean isWhite = figure - 'a' < 1; //capital is white
		char oppKing = isWhite ? 'k' : 'K';
		
		// ========= rook ===========
		if (figure == 'r' || figure == 'R') {
			for (int i = 0; i < 4; i++) {
				byte x = xFigure;
				byte y = yFigure;

				while (!isOutofBoard(x, y)) {
					switch (i) {
					case 0:
						x += 1;
						break;
					case 1:
						x -= 1;
						break;
					case 2:
						y += 1;
						break;
					case 3:
						y -= 1;
						break;
					}

					if (isOutofBoard(x, y))
						break;
					
					
					if (chessmanPosition[y][x] == null)
						continue;
					else if (chessmanPosition[y][x].getRole() == oppKing)
						return true;
					else if (chessmanPosition[y][x] != null)
						break;
					

				}
			}
		}
		// ========= bishop ===========
		else if (figure == 'b' || figure == 'B') {
			for (int i = 0; i < 4; i++) {
				byte x = xFigure;
				byte y = yFigure;

				while (!isOutofBoard(x, y)) {
					switch (i) {
					case 0:
						x += 1;
						y -= 1;
						break;
					case 1:
						x += 1;
						y += 1;
						break;
					case 2:
						x -= 1;
						y -= 1;
						break;
					case 3:
						x -= 1;
						y += 1;
						break;
					}

					if (isOutofBoard(x, y))
						break;

					if (chessmanPosition[y][x] == null)
						continue;
					else if (chessmanPosition[y][x].getRole() == oppKing)
						return true;
					else if (chessmanPosition[y][x] != null)
						break;

				}
			}
		}
		// ========= queen ===========
		else if (figure == 'q' || figure == 'Q') {
			for (int i = 0; i < 8; i++) {
				byte x = xFigure;
				byte y = yFigure;

				while (!isOutofBoard(x, y)) {
					switch (i) {
					case 0:
						x += 1;
						break;
					case 1:
						x -= 1;
						break;
					case 2:
						y += 1;
						break;
					case 3:
						y -= 1;
						break;
					case 4:
						x += 1;
						y -= 1;
						break;
					case 5:
						x += 1;
						y += 1;
						break;
					case 6:
						x -= 1;
						y -= 1;
						break;
					case 7:
						x -= 1;
						y += 1;
						break;
					}

					if (isOutofBoard(x, y))
						break;

					if (chessmanPosition[y][x] == null)
						continue;
					else if (chessmanPosition[y][x].getRole() == oppKing)
						return true;
					else if (chessmanPosition[y][x] != null)
						break;

				}
			}
		}
		// ========= king ===========
		else if (figure == 'k' || figure == 'K') {
			for (int i = 0; i < 8; i++) {
				byte x = xFigure;
				byte y = yFigure;

				switch (i) {
				case 0:
					x += 1;
					break;
				case 1:
					x -= 1;
					break;
				case 2:
					y += 1;
					break;
				case 3:
					y -= 1;
					break;
				case 4:
					x += 1;
					y -= 1;
					break;
				case 5:
					x += 1;
					y += 1;
					break;
				case 6:
					x -= 1;
					y -= 1;
					break;
				case 7:
					x -= 1;
					y += 1;
					break;
				}

				if (isOutofBoard(x, y))
					continue;

				if (chessmanPosition[y][x] == null)
					continue;
				else if (chessmanPosition[y][x].getRole() == oppKing)
					return true;

			}
		}
		// ========= pawn ===========
		else if (figure == 'p' || figure == 'P') {
			for (int i = 0; i < 2; i++) {
				byte x = xFigure;
				byte y = yFigure;

				y += figure == 'p' ? -1 : 1;

				switch (i) {
				case 0:
					x -= 1;
					break;
				case 1:
					x += 1;
					break;
				}

				if (isOutofBoard(x, y))
					continue;

				if (chessmanPosition[y][x] == null)
					continue;
				else if (chessmanPosition[y][x].getRole() == oppKing)
					return true;

			}
		}

		// ========= knight ===========
		else if (figure == 'n' || figure == 'N') {
			for (int i = 0; i < 8; i++) {
				byte x = xFigure;
				byte y = yFigure;

				switch (i) {
				case 0:
					x += 2;
					y -= 1;
					break;
				case 1:
					x += 2;
					y += 1;
					break;
				case 2:
					x -= 2;
					y -= 1;
					break;
				case 3:
					x -= 2;
					y += 1;
					break;
				case 4:
					x += 1;
					y -= 2;
					break;
				case 5:
					x += 1;
					y += 2;
					break;
				case 6:
					x -= 1;
					y -= 2;
					break;
				case 7:
					x -= 1;
					y += 2;
					break;
				}

				if (isOutofBoard(x, y))
					continue;

				if (chessmanPosition[y][x] == null)
					continue;
				else if (chessmanPosition[y][x].getRole() == oppKing)
					return true;

			}
		}

		return false;

	}
	
	private static boolean isOutofBoard(byte x, byte y) {
		return !((x >= 0 && x <= 7) && (y >= 0 && y <= 7));
	}
	
	public static void main(String[] args) {
		
		ChessMan whitePlayer[] = new ChessMan[16];
		for (int i=0;i<16;i++){
			whitePlayer[i] = new ChessMan();
		}
		
		ChessMan blackPlayer[] = new ChessMan[16];
		for (int i=0;i<16;i++){
			blackPlayer[i] = new ChessMan();
		}
		
		ChessBoard chessBoard = new ChessBoard(whitePlayer, blackPlayer);
		
		chessBoard.move((byte)0, (byte)1, (byte)0, (byte)2);
		chessBoard.move((byte)6, (byte)0, (byte)5, (byte)5);
		chessBoard.move((byte)6, (byte)7, (byte)5, (byte)2);
		chessBoard.move((byte)7, (byte)0, (byte)7, (byte)3);
		chessBoard.move((byte)4, (byte)7, (byte)5, (byte)3);
	}
}
