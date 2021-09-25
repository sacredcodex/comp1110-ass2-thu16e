package comp1110.ass2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;


import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.junit.jupiter.api.Assertions.*;

@Timeout(value = 1000, unit = MILLISECONDS)
public class LocationTest {

    public void testGetNext(int column,int row,int direction,int expColumn,int expRow){
        Location loc=new Location(column,row);
        Location out= loc.getNext(direction);
        int a=out.getColumn();
        int b=out.getRow();
        assertEquals(a,expColumn);
        assertEquals(b,expRow);
    }
    @Test
    public void getNextTest(){
        testGetNext(4,3,1,5,4);
        testGetNext(2,2,3,1,2);
        testGetNext(1,2,2,0,3);
        testGetNext(3,2,4,2,1);
        testGetNext(5,1,5,6,0);
        testGetNext(1,1,0,2,1);
        testGetNext(3,2,1,3,3);
        testGetNext(6,0,2,5,1);
        testGetNext(5,2,3,4,2);
        testGetNext(2,3,4,2,2);
        testGetNext(3,3,5,4,2);
    }



    public void testOnBoard(int row,int column,boolean expected){
        Location loc=new Location(column,row);
        boolean out= loc.onBoard();
        assertEquals(out,expected);
    }
    @Test
    public void onBoardTest(){
        testOnBoard(1,6,false);
        testOnBoard(2,3,true);
        testOnBoard(2,8,false);
        testOnBoard(3,5,true);
        testOnBoard(3,6,false);
    }



    public void testOnBoard2(String loc,boolean expected){
        Location a=new Location();
        boolean out=a.onBoard(loc);
        assertEquals(out,expected);
     }
     @Test
    public void onBoardTest2(){
        testOnBoard2("43",true);
        testOnBoard2("38",false);
        testOnBoard2("60",true);
        testOnBoard2("61",false);
        testOnBoard2("04",false);
        testOnBoard2("33",true);
        testOnBoard2("72",false);
    }
}
