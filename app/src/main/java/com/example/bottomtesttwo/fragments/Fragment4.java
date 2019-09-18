package com.example.bottomtesttwo.fragments;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bottomtesttwo.R;
import com.example.bottomtesttwo.activity.MainActivity;
import com.example.bottomtesttwo.fragments.fragment4.Frag4Adapter;
import com.example.bottomtesttwo.fragments.fragment4.Frag4Item;
import com.example.bottomtesttwo.fragments.fragment4.Frag4List_Email;
import com.example.bottomtesttwo.fragments.fragment4.Frag4List_Personal;
import com.example.bottomtesttwo.fragments.fragment4.Frag4List_Phone;
import com.example.bottomtesttwo.fragments.fragment4.Frag4List_Secret;
import com.example.bottomtesttwo.fragments.fragment4.Frag4List_Software;
import com.example.bottomtesttwo.serverd.DBOperator;
import com.example.bottomtesttwo.util.FastBlur;
import com.example.bottomtesttwo.util.ImageDispose;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

//我页
public class Fragment4 extends Fragment {

    private View view;
    private boolean firstDo = true;

    //RecyclerView
    List<Frag4Item> frag4ItemList = new ArrayList<>();
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    Frag4Adapter frag4Adapter;
    TextView frag4Out;
    TextView frag4UserName;
    public ImageView second_bg;
    public CircleImageView second_head;

    DBOperator dbOperator = DBOperator.getOperator();
    Cursor cursor;
    private int id ;

    public static final int CHOOSE_PHOTO = 2;


    public Fragment4() {
    }


    public static Fragment4 newInstance() {
        Fragment4 fragment = new Fragment4();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_4, container, false);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_4, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(firstDo){
            initDate();
            firstDo = false;
        }
        frag4Out = (TextView)view.findViewById(R.id.frag4_out);
        frag4Out.setOnClickListener((View.OnClickListener) getActivity());
        recyclerView = (RecyclerView)view.findViewById(R.id.frag4_recycle_view);
        layoutManager = new LinearLayoutManager(getActivity());//指定布局方式（线性布局）
        recyclerView.setLayoutManager(layoutManager);
        frag4Adapter = new Frag4Adapter(getActivity(),frag4ItemList);
        recyclerView.setAdapter(frag4Adapter);

        frag4UserName = (TextView)view.findViewById(R.id.frag4_user_name);

        second_bg = (ImageView)view.findViewById(R.id.second_bg);
        second_head = (CircleImageView)view.findViewById(R.id.second_head);

        second_head.setOnClickListener(MainActivity.instance);

        second_bg.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {

                    @Override
                    public boolean onPreDraw() {

                        second_bg.getViewTreeObserver().removeOnPreDrawListener(this);
                        second_bg.buildDrawingCache();

                        Bitmap bmp = second_bg.getDrawingCache();
                        blur(bmp, second_bg);
                        return true;
                    }
                });

        //添加点击事件
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                frag4Adapter.setOnclick(new Frag4Adapter.ClickInterface() {
                    @Override
                    public void onItemClick(View view, int position) {
                        //Log.d("ZXY",position+"");
                        switch (position){
                            case 0:
                                Intent intent0 = new Intent(getActivity(), Frag4List_Personal.class);
                                startActivity(intent0);
                                break;
                            case 1:
                                Intent intent1 = new Intent(getActivity(), Frag4List_Secret.class);
                                startActivity(intent1);
                                break;
                            case 2:
                                Intent intent2 = new Intent(getActivity(), Frag4List_Phone.class);
                                startActivity(intent2);
                                break;
                            case 3:
                                Intent intent3 = new Intent(getActivity(), Frag4List_Email.class);
                                startActivity(intent3);
                                break;
                            case 4:
                                Intent intent4 = new Intent(getActivity(),Frag4List_Software.class);
                                startActivity(intent4);
                                break;
                                default:
                                    break;
                        }
                    }
                });
            }
        });
        Log.d("LOADIMAGE", "loadImage: ");
        loadImage();

//        second_head.setOnClickListener(this);
//        {
//            @Override
//            public void onClick(View view) {
//                if(ContextCompat.checkSelfPermission(MainActivity.instance, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
//                    ActivityCompat.requestPermissions(MainActivity.instance,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
//                }else {
//                    openAlbum();
//                }
//            }
//        });


        cursor = dbOperator.Query( "select * from user_info");
        cursor.moveToFirst();
        if(cursor.getString(cursor.getColumnIndex("username")).equals("")){
        }else {

            frag4UserName.setText(cursor.getString(cursor.getColumnIndex("username")));

        }
        cursor.close();


    }



    private void initDate(){
        frag4ItemList.add(new Frag4Item("个人信息"));
        frag4ItemList.add(new Frag4Item("修改密码"));
        frag4ItemList.add(new Frag4Item("手机绑定"));
        frag4ItemList.add(new Frag4Item("邮箱绑定"));
        frag4ItemList.add(new Frag4Item("关于软件"));
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void blur(Bitmap bkg, View view) {
        long startMs = System.currentTimeMillis();
        float scaleFactor = 8;
        float radius = 2;

        Bitmap overlay = Bitmap.createBitmap(
                (int) (view.getMeasuredWidth() / scaleFactor),
                (int) (view.getMeasuredHeight() / scaleFactor),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(overlay);
        canvas.translate(-view.getLeft() / scaleFactor, -view.getTop()
                / scaleFactor);
        canvas.scale(1 / scaleFactor, 1 / scaleFactor);
        Paint paint = new Paint();
        paint.setFlags(Paint.FILTER_BITMAP_FLAG);
        canvas.drawBitmap(bkg, 0, 0, paint);

        overlay = FastBlur.doBlur(overlay, (int) radius, true);
        if (view instanceof ImageView){
            ((ImageView)view).setImageBitmap(overlay);
        }else {
            view.setBackground(new BitmapDrawable(getResources(), overlay));
        }
        System.out.println(System.currentTimeMillis() - startMs + "ms");
    }



    public void loadImage(){


        cursor = dbOperator.Query( "select * from user_info");
        cursor.moveToFirst();
        id = cursor.getInt(cursor.getColumnIndex("id"));
        String imageString = cursor.getString(cursor.getColumnIndex("avatar"));

        if(imageString == null){
            return;
        }else if(imageString.equals("")){
            return;
        }
        else switch (Integer.parseInt(imageString)) {
                case 0:
                    imageChange(R.mipmap.head1);
                    break;
                case 1:
                    imageChange(R.mipmap.head2);
                    break;
                case 2:
                    break;
                default:
            }

    }

    private void imageChange(int imageId){
        second_head.setImageResource(imageId);
        second_bg.setImageResource(imageId);
        second_bg.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {

                    @Override
                    public boolean onPreDraw() {

                        second_bg.getViewTreeObserver().removeOnPreDrawListener(this);
                        second_bg.buildDrawingCache();

                        Bitmap bmp = second_bg.getDrawingCache();
                        blur(bmp, second_bg);
                        return true;
                    }
                });
    }


}
