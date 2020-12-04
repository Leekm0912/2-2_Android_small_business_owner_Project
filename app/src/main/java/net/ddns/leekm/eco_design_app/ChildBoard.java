package net.ddns.leekm.eco_design_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import org.xmlpull.v1.XmlPullParser;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.regex.Pattern;

public class ChildBoard extends AppCompatActivity {
    XmlPullParser parser; // 파서
    ArrayList<MyItem> arrayList; // 파싱해온 값을 저장해줄 리스트
    String xml; // xml의 url
    MyAdapter myAdapter; // 어댑터
    //ImageView imageView; // 이미지
    //Bitmap bitmap; // 이미지 가져올때 저장할곳
    TextView title; // 제목
    TextView desc; // 자기소개
    TextView num; // 글 번호
    TextView date; //글 작성일
    TextView writer; // 글 작성자
    TextView mainText; // 글 본문
    TextInputEditText c_text; // 댓글내용
    //Spinner spinner2;
    String board_num;
    MyItem item;
    ListView listView;

    public void init(){
        String url = AppData.SERVER_FULL_URL+"/eco_design/eco_design/getDetailBoard.jsp";
        ContentValues contentValues = new ContentValues();
        Intent intent = getIntent();
        contentValues.put("분류",intent.getStringExtra("board"));
        board_num = intent.getStringExtra("board_num");
        contentValues.put("게시글번호",board_num);
        String parse_data = null;

        NetworkTask networkTask = new NetworkTask(this, url, contentValues, (AppData)getApplication());
        try {
            parse_data =  networkTask.execute().get(); // get()함수를 이용해 작업결과를 불러올 수 있음.
            Log.i("1",parse_data);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        Parse p = new Parse((AppData)getApplication() ,parse_data);
        if(p.getNotice().equals("success")){
            item = p.getMyItem();
        }

        title = findViewById(R.id.board_title);
        num = findViewById(R.id.board_num);
        date = findViewById(R.id.board_date);
        writer = findViewById(R.id.board_writer);
        mainText = findViewById(R.id.mainText);
        //imageView = findViewById(R.id.childBoardImage);

        title.setText(item.getTitle());
        num.setText(item.getPostNumber());
        date.setText(item.getDate());
        writer.setText(item.getUserName());
        mainText.setText(item.getMainText());
        /* 이미지 부분
        Thread uThread = new Thread() {
            @Override
            public void run(){
                try{
                    //서버에 올려둔 이미지 URL
                    URL url = new URL(item.getImagePath());
                    //Web에서 이미지 가져온 후 ImageView에 지정할 Bitmap 만들기
                    //URLConnection 생성자가 protected로 선언되어 있으므로
                    //개발자가 직접 HttpURLConnection 객체 생성 불가

                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();

                        //openConnection()메서드가 리턴하는 urlConnection 객체는

                        //HttpURLConnection의 인스턴스가 될 수 있으므로 캐스팅해서 사용한다

                    conn.setDoInput(true); //Server 통신에서 입력 가능한 상태로 만듦
                    conn.connect(); //연결된 곳에 접속할 때 (connect() 호출해야 실제 통신 가능함)

                    InputStream is = conn.getInputStream(); //inputStream 값 가져오기
                    bitmap = BitmapFactory.decodeStream(is); // Bitmap으로 반환
                    Log.i("======================bitmap======================",bitmap.toString());

                }catch (MalformedURLException e){
                    e.printStackTrace();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        };
        uThread.start(); // 작업 Thread 실행
        try{

            //메인 Thread는 별도의 작업을 완료할 때까지 대기한다!
            //join() 호출하여 별도의 작업 Thread가 종료될 때까지 메인 Thread가 기다림
            //join() 메서드는 InterruptedException을 발생시킨다.
            uThread.join();

            //작업 Thread에서 이미지를 불러오는 작업을 완료한 뒤
            //UI 작업을 할 수 있는 메인 Thread에서 ImageView에 이미지 지정
            imageView.setImageBitmap(bitmap);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        이미지 부분 */

        // ListView 작업
        listView = findViewById(R.id.comment);
        myAdapter = new MyAdapter(this,R.layout.comment_layout, item.getComment());
        listView.setAdapter(myAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_board);

        init();

        listView.setOnItemClickListener((parent, view, position, l_position)->{
            // 작업안함.
        });

        listView.setOnItemLongClickListener((parent, view, position, id)->{
            Intent new_intent = new Intent(this, Popup.class);
            TextView c_writer = view.findViewById(R.id.c_writer);
            String comment_num = item.getComment().get(position).getComment_num();
            String comment_writer = c_writer.getText().toString();
            AppData appData = (AppData)getApplication();
            if(comment_writer.equals(appData.getUser().get이름()) || appData.getUser().get이름().equals("admin")){ // 현재 접속중인 계정의 이름과 댓글의 이름이 같다면
                new_intent.putExtra("data","작업을 선택해 주세요.");
                new_intent.putExtra("type","Comment");
                new_intent.putExtra("pos",comment_num);
                startActivityForResult(new_intent,0);
            }
            return true;
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        init();
    }

    public void onClick(View v){
        String url = AppData.SERVER_FULL_URL+"/yonam-market/market/insertComment.jsp";
        String parse_data = null;

        // AsyncTask를 통해 HttpURLConnection 수행.
        ContentValues contentValues = new ContentValues();
        c_text = findViewById(R.id.comment_input);
        String comment_str =  c_text.getText().toString();
        Pattern pattern = Pattern.compile("[<>+%]");
        if(pattern.matcher(comment_str).find()){ // 특수문자 들어있으면 true 리턴
            Toast.makeText(this,"사용 불가능한 특수문자가 포함되어 있습니다.",Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            contentValues.put("내용", URLEncoder.encode(comment_str, "utf-8"));
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "댓글을 입력하세요",Toast.LENGTH_SHORT).show();
            return;
        }
        AppData appData = (AppData)getApplication();
        contentValues.put("작성자", appData.getUser().get이름());
        contentValues.put("게시글번호", board_num);

        NetworkTask networkTask = new NetworkTask(this, url, contentValues, (AppData)getApplication());
        try {
            parse_data =  networkTask.execute().get(); // get()함수를 이용해 작업결과를 불러올 수 있음.
            Log.i("1",parse_data);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        Parse p = new Parse((AppData)getApplication() ,parse_data);
        if(p.getNotice().equals("success")){
            //Intent intent = new Intent(this,MainMenu.class);
            //startActivityForResult(intent,0);//액티비티 띄우기
            Log.i("댓글삽입완료","삽입완료");
        }

        //화면 갱신
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    class MyAdapter extends BaseAdapter {
        Context context;
        LayoutInflater inflater;
        ArrayList<Comment> list;
        int layout;

        @SuppressLint("ServiceCast")
        public MyAdapter(Context context, int layout, ArrayList<Comment> item){
            this.context = context;
            this.layout = layout;
            this.list = item;
            this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) { return position; }

        /*
         * position : 생성할 항목의 순서값
         * parent : 생성되는 뷰의 부모(지금은 리스트뷰)
         * convertView : 이전에 생성되었던 차일드 뷰(지금은 Layout.xml) 첫 호출시에는 null
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = inflater.inflate(layout,parent,false);
            }
            TextView c_title = convertView.findViewById(R.id.c_text);
            TextView c_date = convertView.findViewById(R.id.c_date);
            TextView c_writer = convertView.findViewById(R.id.c_writer);
            c_title.setText(list.get(position).get내용());
            c_date.setText(list.get(position).get등록일());
            c_writer.setText(list.get(position).get작성자());

            return convertView;
        }
    }
}