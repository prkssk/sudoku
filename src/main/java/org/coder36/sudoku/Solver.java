package org.coder36.sudoku;

import java.util.Stack;

/**
 */
public class Solver {

    int [][] a;  // row
    Stack<int[][]> stack = new Stack<>();

    public Solver(int [][] a ) {
        this.a = a;
    }

    public boolean recurse() {

        for ( int x=0; x<9; x++ ) {
            for ( int y=0; y<9; y++ ) {
                for ( int i=0; i<9; i++ ) {
                    if ( isAllowed( x, y, i ) ) {
                        a[y][x] = i;
                        stack.push( a.clone() );
                        if ( !recurse() ) {
                            a = stack.pop();
                        }
                    }
                }
            }
        }

    }

    protected boolean isAllowed( int x, int y, int num ) {
        return ! contains( getRow(x,y), num ) || contains( getCol(x,y), num ) || contains( getSquare(x, y), num );
    }

    protected int [] getRow( int x, int y  ) {
        return a[y];
    }

    protected int [] getCol( int x, int y  ) {
        int [] b = new int[9];
        for ( int i=0; i < 9; i++ ) {
            b[i] = a[i][x];
        }
        return b;
    }

    protected int [] getSquare( int x, int y ) {
        int [] b = new int[9];

        int z = 0;
        for ( int i=0; i < 3; i++ ) {
            for ( int j=0; j < 3; j++ ) {
                b[z++] = a[((y/3) * 3 )+i][ ((x/3) * 3) + j ];
            }
        }
        return b;
    }


    private boolean contains( int [] list, int num ) {
        for ( int i: list ) {
            if ( i == num ) return true;
        }
        return false;
    }

    public void print() {
        for ( int y=0; y<9; y++ ) {
            for ( int x=0; x<9; x++ ) {
                System.out.print( a[y][x] );
            }
            System.out.print( "\n" );
        }
        System.out.println( "================" );
    }
}