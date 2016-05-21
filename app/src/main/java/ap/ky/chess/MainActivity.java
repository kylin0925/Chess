package ap.ky.chess;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "CHESS";
    ImageView boardView;
    TextView txtDebug;
    Bitmap gameView;
    final int general = 0;
    final int advisor = 1;
    final int elephaant = 2;
    final int chariot = 3;
    final int horse = 4;
    final int cannon = 5;
    final int soldier = 6;
    Bitmap[] chessBlack = new Bitmap[7];
    Bitmap[] chessRed = new Bitmap[7];
    boolean selected = false;
    final char[][] chessBoard = new char[][]{
        {  0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 },
        {  0 ,'C','H','E','A','G','A','E','H','C'},
        {  0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 },
        {  0 , 0 ,'N', 0 , 0 , 0 , 0 , 0 ,'N', 0 },
        {  0 ,'S', 0 ,'S', 0 ,'S', 0 ,'S', 0 ,'S'},
        {  0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 },

        {  0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 },
        {  0 ,'s', 0 ,'s', 0 ,'s', 0 ,'s', 0 ,'s'},
        {  0 , 0 ,'n', 0 , 0 , 0 , 0 , 0 ,'n', 0 },
        {  0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 },
        {  0 ,'c','h','e','a','g','a','e','h','c'},
    };
    final char[][] resetchessBoard = new char[][]{
            {  0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 },
            {  0 ,'C','H','E','A','G','A','E','H','C'},
            {  0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 },
            {  0 , 0 ,'N', 0 , 0 , 0 , 0 , 0 ,'N', 0 },
            {  0 ,'S', 0 ,'S', 0 ,'S', 0 ,'S', 0 ,'S'},
            {  0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 },

            {  0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 },
            {  0 ,'s', 0 ,'s', 0 ,'s', 0 ,'s', 0 ,'s'},
            {  0 , 0 ,'n', 0 , 0 , 0 , 0 , 0 ,'n', 0 },
            {  0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 },
            {  0 ,'c','h','e','a','g','a','e','h','c'},
    };
    final int[] chessImgIds = {
            R.mipmap.general,
            R.mipmap.advisor,
            R.mipmap.elephant,
            R.mipmap.chariot,
            R.mipmap.horse,
            R.mipmap.cannon,
            R.mipmap.soldier};
    int chessWidth =0;
    int chessHeight =0;
    final int BLACK = 0;
    final int RED = 1;
    int gameTurn = BLACK;
    String[] chessName={"gaehcns","GAEHCNS"};
    void loadImage(){
        Bitmap tmp;
        Canvas canvas;
        int w,h;
        for(int i =0 ;i<7;i++) {
            //red
            tmp = BitmapFactory.decodeResource(getResources(), chessImgIds[i]);
            w = tmp.getWidth();
            h = tmp.getHeight();
            chessWidth = w/2;
            chessHeight = h;
            Log.e(TAG,"board w " + tmp.getWidth() + " " + tmp.getHeight());
            chessRed[i] = Bitmap.createBitmap(w/2, h, Bitmap.Config.ARGB_8888);
            canvas = new Canvas(chessRed[i]);
            canvas.drawBitmap(tmp, new Rect(0, 0, w/2, h), new Rect(0, 0, w/2, h), null);
            //black
            tmp = BitmapFactory.decodeResource(getResources(), chessImgIds[i]);
            chessBlack[i] = Bitmap.createBitmap(w/2, h, Bitmap.Config.ARGB_8888);
            canvas = new Canvas(chessBlack[i]);
            canvas.drawBitmap(tmp, new Rect(w/2, 0, w, h), new Rect(0, 0, w/2, h), null);
        }
    }
    void draw(){
        //draw canvas
        Bitmap board = BitmapFactory.decodeResource(getResources(),R.mipmap.board1);
        Bitmap bm = Bitmap.createBitmap(board.getWidth(), board.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bm);
        //board bitmap

        Log.e(TAG,"board w " + board.getWidth() + " " + board.getHeight());
        //chess
        //Bitmap bitadv = BitmapFactory.decodeResource(getResources(), R.mipmap.advisor);
        //Bitmap bittmp = Bitmap.createBitmap(bitadv, 0, 0, bitadv.getWidth() / 2, bitadv.getHeight());
        //Bitmap bitadvScal = Bitmap.createScaledBitmap(bittmp, 80, 80, false);

        canvas.drawBitmap(board,0,0,null);
        //canvas.drawBitmap(chessRed[advisor],0,0,null);
        //canvas.drawBitmap(chessBlack[advisor],0,100,null);
       // canvas.drawBitmap(chessBlack[general],0,160,null);
        //canvas.drawBitmap(bitadv,new Rect(0,0,60,65),new Rect(0,0,60,65),null);

        //canvas.drawBitmap(bitadvScal, 10, 0, null);
        drawBoard(canvas);
        boardView.setImageBitmap(bm);
    }
    void drawChess(Canvas canvas,Bitmap btChess,int x,int y){
        canvas.drawBitmap(btChess,x*chessWidth,y*chessHeight,null);
    }
    void drawBoard( Canvas canvas){
        for(int i = 1;i<11;i++){
            for(int j = 1;j<10;j++){
                if(chessBoard[i][j] == 'g'){
                    drawChess(canvas,chessBlack[general],j-1,i-1);
                }else if(chessBoard[i][j] == 'G'){
                    drawChess(canvas, chessRed[general], j - 1, i - 1);
                }else if(chessBoard[i][j] == 'a'){
                    drawChess(canvas,chessBlack[advisor],j-1,i-1);
                }else if(chessBoard[i][j] == 'A'){
                    drawChess(canvas, chessRed[advisor], j - 1, i - 1);
                }else if(chessBoard[i][j] == 'e'){
                    drawChess(canvas, chessBlack[elephaant], j - 1, i - 1);
                }else if(chessBoard[i][j] == 'E'){
                    drawChess(canvas, chessRed[elephaant], j - 1, i - 1);
                }else if(chessBoard[i][j] == 'c'){
                    drawChess(canvas, chessBlack[chariot], j - 1, i - 1);
                }else if(chessBoard[i][j] == 'C'){
                    drawChess(canvas, chessRed[chariot], j - 1, i - 1);
                }else if(chessBoard[i][j] == 'h'){
                    drawChess(canvas, chessBlack[horse], j - 1, i - 1);
                }else if(chessBoard[i][j] == 'H'){
                    drawChess(canvas, chessRed[horse], j - 1, i - 1);
                }else if(chessBoard[i][j] == 'n'){
                    drawChess(canvas, chessBlack[cannon], j - 1, i - 1);
                }else if(chessBoard[i][j] == 'N'){
                    drawChess(canvas, chessRed[cannon], j - 1, i - 1);
                }else if(chessBoard[i][j] == 's'){
                    drawChess(canvas, chessBlack[soldier], j - 1, i - 1);
                }else if(chessBoard[i][j] == 'S'){
                    canvas.drawBitmap(chessRed[soldier],(j-1)*chessWidth,(i-1)*chessHeight,null);
                }
            }
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boardView = (ImageView)findViewById(R.id.imageView);

        //boardView.setImageResource(R.mipmap.board1);


        boardView.setOnTouchListener(boarcTouch);
        loadImage();
        draw();
        txtDebug = (TextView)findViewById(R.id.txtDebug);
    }
    int fromX = -1;
    int fromY = -1;
    View.OnTouchListener boarcTouch = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                int x = (int)(motionEvent.getX()/chessWidth) +1;
                int y = (int)(motionEvent.getY()/chessHeight) + 1;


                Log.e(TAG, "click " + x + " " + y + " " + selected);
                //Log.e(TAG, "click " + motionEvent.getX() + " " + motionEvent.getY());
                if(selected == false){

                    if (chessBoard[y][x] != 0) {
                        //check trun
                        int foundChess = chessName[gameTurn].indexOf(chessBoard[y][x]);
                        if(foundChess >= 0) {
                            txtDebug.setText("click " + x + " " + y + " ");

                            fromX = x;
                            fromY = y;

                            selected = !selected; //set select
                            txtDebug.setText("select " + chessBoard[y][x] + " " + x + " " + y);
                        }
                    }
                }else {
                    //if can move
                    boolean r = checkCanMove(chessBoard[fromY][fromX],fromX,fromY,x,y);
                    if(r == true) {
                        if (chessBoard[y][x] != 0) {
                            chessBoard[y][x] = chessBoard[fromY][fromX];
                            chessBoard[fromY][fromX] = 0;
                        } else {
                            //do move
                            chessBoard[y][x] = chessBoard[fromY][fromX];
                            chessBoard[fromY][fromX] = 0;
                        }
                        selected = !selected; //set select
                        gameTurn = (gameTurn + 1) %2;
                        txtDebug.setText("move " + chessBoard[y][x] + " to " + x + " " + y);
                        draw();
                    }else {
                        //cannot move
                        txtDebug.setText("move " + chessBoard[y][x] + " " + x + " " + y + " error");
                        selected = !selected; //set select
                    }

                }
            }
            return false;
        }
    };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    boolean checkGernalMove(int fromX,int fromY,int toX,int toY){
        boolean ret = true;
        int dx = Math.abs(fromX-toX);
        int dy = Math.abs(fromY - toY);
        if(dx ==1 && dy == 1) {
            ret = false;
        }
        return ret;
    }
    boolean checkg(int fromX,int fromY,int toX,int toY){
        boolean ret = true;
        if(toX >6 || toX < 4 || toY >10 || toY <8){
            ret = false;
        }
        ret &= checkGernalMove(fromX,fromY,toX,toY);
        return ret;
    }
    boolean checkG(int fromX,int fromY,int toX,int toY){
        boolean ret = true;
        if(toX >6 || toX < 4 || toY >3 || toY <0){
            ret = false;
        }
        ret &= checkGernalMove(fromX,fromY,toX,toY);
        return ret;
    }
    boolean checkAMove(int fromX,int fromY,int toX,int toY){
        boolean ret = true;
        int dx = Math.abs(fromX-toX);
        int dy = Math.abs(fromY - toY);
        if(dx !=1 || dy != 1) {
            ret = false;
        }
        return ret;
    }
    boolean checka(int fromX,int fromY,int toX,int toY){
        boolean ret = true;
        if(toX >6 || toX < 4 || toY >10 || toY <8){
            ret = false;
        }
        ret &=checkAMove(fromX,fromY,toX,toY);

        return ret;
    }
    boolean checkA(int fromX,int fromY,int toX,int toY){
        boolean ret = true;
        if(toX >6 || toX < 4 || toY >3 || toY <0){
            ret = false;
        }
        ret &=checkAMove(fromX, fromY, toX, toY);
        return ret;
    }
    boolean check_bound(int bnd_arr[],int x,int y)
    {
        if( x>=bnd_arr[0]  && x<= bnd_arr[1] &&
                y>=bnd_arr[2]  && y<= bnd_arr[3]){
            return true;
        }else{
            return false;
        }
    }
    boolean checkEle(char c, int fromX, int fromY, int toX, int toY)
    {
        int x = Math.abs(fromX - toX);
        int y = Math.abs(fromY - toY);
        //char c=0;
        int uele_bound[] = {1,9,1,5};
        int bele_bound[] = {1,9,6,10};
        int bnt;
        boolean ret = true;
        if(c=='e')
        {
            ret = check_bound(bele_bound,toX,toY);
        }else{
            ret = check_bound(uele_bound,toX,toY);
        }
        //check not out of bound
        Log.e(TAG,"move " + toX + " " + toY);


        if(x!=2 || y!=2)
        {
            Log.e(TAG,"move error");
            ret = false;
        }
        else
        {
            x = toX > fromX ? fromX+1:toX+1;
            y = toY > fromY ? fromY+1:toY+1;
            if( chessBoard[y][x]!=0)
            {
                Log.e(TAG,"blocked\n");
                ret =false;
            }

        }

        return ret;
    }
    boolean checkHourse(int fromX,int fromY ,int toX,int toY)
    {
        int x = Math.abs(fromX - toX);
        int y = Math.abs(fromY - toY);
        int c=0;
        //check is_self

        if((x == 2 && y == 1) || (x == 1 && y==2))
        {
            if(y==1)
            {
                x = fromX<toX? fromX +1:toX+1;
                if(chessBoard[fromY][x]!=0)
                {
                    Log.e(TAG, "bloacked");
                    return false;
                }
            }
            else
            {
                y = fromY < toY ? fromY+1:toY+1;
                if(chessBoard[y][fromX]!=0)
                {
                    Log.e(TAG,"bloacked");
                    return false;
                }
            }
            //move
            //move_to(&board[from_y][from_x],&board[to_y][to_x]);
        }
        else
        {
            Log.e(TAG,"error");
            return false;
        }
        return true;
    }
    boolean checkCar(int from_x,int from_y ,int to_x,int to_y)
    {
        int x = Math.abs(from_x - to_x);
        int y = Math.abs(from_y - to_y);
        int c = 0;


        if(x>0 && y ==0 || x ==0 && y>0)
        {
            if(x>0)
            {
                int start = from_x < to_x ? from_x:to_x;
                int end = from_x > to_x ? from_x:to_x;
                for(start+=1;start<end;start++)
                {
                    if(chessBoard[from_y][start]!=0)
                    {
                        Log.e(TAG,"move c error " + start);
                        return false;
                    }
                }
            }
            else
            {
                int start = from_y < to_y ? from_y:to_y;
                int end = from_y > to_y ? from_y:to_y;
                for(start+=1;start<end;start++)
                {
                    if(chessBoard[start][from_x]!=0)
                    {
                        Log.e(TAG,"move c error " + start);
                        return false;
                    }
                }

            }
            //move_to(&board[from_y][from_x],&board[to_y][to_x]);
        }
        else
        {
            Log.e(TAG,"error\n");
            return false;
        }
        return true;
    }
    boolean checkBomb(int from_x,int from_y ,int to_x,int to_y)
    {
        int x = Math.abs(from_x - to_x);
        int y = Math.abs(from_y - to_y);

        if (x>0 && y >0)
        {
            Log.e(TAG,"error x " + x + " " + y);
            return false;
        }

        if(x>0 && chessBoard[to_y][to_x] == 0)
        {
            int start = Math.min(from_x, to_x);
            int end = Math.max(from_x, to_x);

            for(start +=1;start<end;start++)
            {
                if(chessBoard[from_y][start]!=0){
                    Log.e(TAG,"move bomb error " + start);
                    return false;
                }
            }

        }
        else if(x>0 && chessBoard[to_y][to_x] != 0)
        {
            int start = Math.min(from_x, to_x);
            int end = Math.max(from_x, to_x);
            int count = 0;
            for(start +=1;start<end;start++)
            {
                if(chessBoard[from_y][start]!=0){
                    count++;
                }
            }
            if(count > 1)
            {
                Log.e(TAG,"move bomb error " + count);
                return false;
            }
        }

        if(y>0 && chessBoard[to_y][to_x]==0)
        {
            int start = Math.min(from_y, to_y);
            int end = Math.max(from_y, to_y);
            for(start +=1;start<end;start++)
            {
                if(chessBoard[start][from_x]!=0)
                {
                    Log.e(TAG, "move bomb error " + start);
                    return false;
                }
            }

        }
        else if(y>0 && chessBoard[to_y][to_x]!=0)
        {
            int start = Math.min(from_y, to_y);
            int end = Math.max(from_y, to_y);
            int count = 0;
            for(start +=1;start<end;start++)
            {
                if(chessBoard[start][from_x]!=0){
                    count++;
                }
            }
            if(count > 1)
            {
                Log.e(TAG,"move bomb error " + count);
                return false;
            }
        }

        //move_to(&board[from_y][from_x],&board[to_y][to_x]);
        return true;
    }
    boolean checkSold(int from_x,int from_y ,int to_x,int to_y)
    {
        int x = Math.abs(from_x - to_x);
        int y = Math.abs(from_y - to_y);

        if ((x > 0 && y>0) || (x == 0 && y > 1) || (x > 1 && y ==0) )
        {
            Log.e(TAG,"move upper Sold error");
            return false;
        }
        if(x >0 && y == 0)
        {
            if(to_y <= 5)
            {
                Log.e(TAG,"move upper Sold error\n");
                return false;
            }
        }
        if(from_y > to_y)
        {
            Log.e(TAG,"move upper Sold error\n");
            return false;
        }
        //move_to(&board[from_y][from_x],&board[to_y][to_x]);
        return true;
    }
    boolean checksold(int from_x,int from_y ,int to_x,int to_y)
    {
        int x = Math.abs(from_x - to_x);
        int y = Math.abs(from_y - to_y);

        if((x > 0 && y>0) || (x == 0 && y > 1) || (x > 1 && y ==0) )
        {
            Log.e(TAG,"move bottom Sold error\n");
            return false;
        }
        if(x >0 && y == 0)
        {
            if(to_y > 5)
            {
                Log.e(TAG, "move bottom Sold error\n");
                return false;
            }
        }
        if(from_y < to_y)
        {
            Log.e(TAG,"move bottom Sold error\n");
            return false;
        }

        //move_to(&board[from_y][from_x],&board[to_y][to_x]);
        return true;
    }
    boolean checkCanMove(char c,int fromX,int fromY,int toX,int toY){
        //lower case bottom
        //upper case up
        boolean ret = true;
        Log.e(TAG,"move " + c);
        switch (c){
            case 'g':
                ret = checkg(fromX,fromY,toX,toY);
                break;
            case 'G':
                ret = checkG(fromX,fromY,toX,toY);
                break;
            case 'a':
                ret = checka(fromX, fromY, toX, toY);
                break;
            case 'A':
                ret = checkA(fromX,fromY,toX,toY);
                break;
            case 'e':
            case 'E':
                ret = checkEle(c, fromX, fromY, toX, toY);
                break;
            case 'h':
            case 'H':
                ret = checkHourse(fromX, fromY, toX, toY);
                break;
            case 'c':
            case 'C':
                ret = checkCar(fromX, fromY, toX, toY);
                break;
            case 'n':
            case 'N':
                ret = checkBomb(fromX,fromY,toX,toY);
                break;
            case 's':
                ret = checksold(fromX,fromY,toX,toY);
                break;
            case 'S':
                ret = checkSold(fromX,fromY,toX,toY);
                break;

        }
        return ret;
    }

}
