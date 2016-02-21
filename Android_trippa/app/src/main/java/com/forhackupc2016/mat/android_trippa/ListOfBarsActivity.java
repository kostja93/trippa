package com.forhackupc2016.mat.android_trippa;

import android.app.Activity;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ListOfBarsActivity extends Activity{
    ListView list;
String[] itemname = {"peta","haha","gaay"};
    Integer[] imgID ={
            R.drawable.buildig,
            R.drawable.buildig,
            R.drawable.buildig
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listofbars);

//        ListAdapter adapter=new ListAdapter(this, itemname, imgID);
//        list = (ListView) findViewById(R.id.listOfBars);
//        list.setAdapter(adapter);

        SwipeMenuListView menuListView = (SwipeMenuListView) findViewById(R.id.listViewForMenu);
        menuListView.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT); // swipe left

        final List<Integer> list = new ArrayList<>();
        list.add(R.drawable.buildig);
        list.add(R.drawable.item001);
        list.add(R.drawable.buildig);
        list.add(R.drawable.item001);
        list.add(R.drawable.buildig);
        list.add(R.drawable.item001);
        list.add(R.drawable.buildig);

      final SwipeArrayAdapter adapter=new SwipeArrayAdapter(this, itemname, imgID);
//        final SwipeArrayAdapter<Integer> adapter = new SwipeArrayAdapter<Integer>(this, R.layout.list_row_item, list);
        menuListView.setAdapter(adapter);
        //Demo!

//        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {


//            @Override
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id) {
//                // TODO Auto-generated method stub
//                String selectedItem = itemName[+position];
//                Toast.makeText(getApplicationContext(), selectedItem, Toast.LENGTH_SHORT).show();

//            }
//        });
        //Demo end!
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {

                SwipeMenuItem openItem = new SwipeMenuItem(getApplicationContext());

                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9, 0xCE)));
                openItem.setWidth(400);
                openItem.setTitle("Open");
                openItem.setTitleSize(30);
                openItem.setTitleColor(Color.WHITE);
                menu.addMenuItem(openItem);


                SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());

                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9, 0x3F, 0x25)));
                deleteItem.setWidth(400);
                deleteItem.setIcon(R.drawable.ic_delete_forever_black_48dp);
                menu.addMenuItem(deleteItem);
            }
        };

        menuListView.setMenuCreator(creator);

        menuListView.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {

            @Override
            public void onSwipeStart(int position) {
                // swipe start
            }

            @Override
            public void onSwipeEnd(int position) {
                // swipe end
            }
        });
        menuListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                String value = adapter.getItem(position);
                switch (index) {
                    case 0:

                                Intent intent = new Intent(getBaseContext(), ChosenObjectActivity.class);
                                startActivityForResult(intent, 0);


                        break;
                    case 1:
                        Toast.makeText(getApplicationContext(), "deleting "+ value , Toast.LENGTH_SHORT).show();
                        //TODO  delete view
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });

    } // End of onCreate













    public Bitmap getBitmapFromUrl(){
        try{
            URL url = new URL(getHTTP().getPicture());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            return BitmapFactory.decodeStream(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }
    public ListedElement getHTTP() throws IOException {
        URL url = new URL(pseudoDB);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        BufferedInputStream bis = new BufferedInputStream(urlConnection.getInputStream());
        byte[] contents = new byte[1024];

        int bytesRead;
        String strFileContents="";
        while( (bytesRead = bis.read(contents)) != -1){
            strFileContents = new String(contents, 0, bytesRead);
        }
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        return gson.fromJson(strFileContents, ListedElement.class);

    }













    public String pseudoDB="[\n"+
            "{\n"+
            "id: \"51\",\n"+
            "name: \"Bar Casa dos Coimbras\",\n"+
            "picture: \"file:///home/mat/Documents/Android-Projects/trippa/Android_trippa/app/src/main/res/drawable/item003.png\",\n"+
            "lat: \"41.5499792\",\n"+
            "lon: \"-8.4247005\",\n"+
            "created_at: \"2016-02-20 14:47:40\",\n"+
            "updated_at: \"2016-02-20 14:47:40\"\n"+
            "},\n"+
            "{\n"+
            "id: \"52\",\n"+
            "name: \"Restaurante Cantinho Do Carmo\",\n"+
            "picture: null,\n"+
            "lat: \"41.5536775\",\n"+
            "lon: \"-8.4254458\",\n"+
            "created_at: \"2016-02-20 14:47:40\",\n"+
            "updated_at: \"2016-02-20 14:47:40\"\n"+
            "},\n"+
            "{\n"+
            "id: \"53\",\n"+
            "name: \"Restaurante Cozinha da Sé, Lda\",\n"+
            "picture: null,\n"+
            "lat: \"41.5500154\",\n"+
            "lon: \"-8.4284755\",\n"+
            "created_at: \"2016-02-20 14:47:40\",\n"+
            "updated_at: \"2016-02-20 14:47:40\"\n"+
            "},\n"+
            "{\n"+
            "id: \"54\",\n"+
            "name: \"Casa do Professor\",\n"+
            "picture: \"file:///home/mat/Documents/Android-Projects/trippa/Android_trippa/app/src/main/res/drawable/buildig.jpg\",\n"+
            "lat: \"41.551211\",\n"+
            "lon: \"-8.420412\",\n"+
            "created_at: \"2016-02-20 14:47:40\",\n"+
            "updated_at: \"2016-02-20 14:47:40\"\n"+
            "},\n"+
            "{\n"+
            "id: \"55\",\n"+
            "name: \"Spirito Cupcakes & Coffee\",\n"+
            "picture: \"file:///home/mat/Documents/Android-Projects/trippa/Android_trippa/app/src/main/res/drawable/item002.png\",\n"+
            "lat: \"41.5502559\",\n"+
            "lon: \"-8.4246157\",\n"+
            "created_at: \"2016-02-20 14:47:40\",\n"+
            "updated_at: \"2016-02-20 14:47:40\"\n"+
            "},\n"+
            "{\n"+
            "id: \"56\",\n"+
            "name: \"B.A. - Bar da Associação\",\n"+
            "picture: null,\n"+
            "lat: \"41.553219\",\n"+
            "lon: \"-8.4110672\",\n"+
            "created_at: \"2016-02-20 14:47:40\",\n"+
            "updated_at: \"2016-02-20 14:47:40\"\n"+
            "},\n"+
            "{\n"+
            "id: \"57\",\n"+
            "name: \"Tongobriga Restaurante Bar\",\n"+
            "picture: \"https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=CmRdAAAAiZnz-0dQFGiBIGeKmc6p91tbets1Ci05ui3jlfcIlff4fL15615mdB83i8ZqGKe1jHfCpzuFdOtyQ-yZPzRurAkPh5aTFx9j8Ul1w2Zp3MLukrhnKY2PO_uaf5pVcGb5EhDTIqJcQbgiEWPFBbeSFtrkGhQB0a8vcTInfjTOzhBvJuxRF8jnKw&key=AIzaSyBxw50IM6rmADRuFJaNRAXXU1puN103q2w\",\n"+
            "lat: \"41.161949\",\n"+
            "lon: \"-8.1460186\",\n"+
            "created_at: \"2016-02-20 14:47:40\",\n"+
            "updated_at: \"2016-02-20 14:47:40\"\n"+
            "},\n"+
            "{\n"+
            "id: \"58\",\n"+
            "name: \"INSÓLITO - BARES, LDA\",\n"+
            "picture: null,\n"+
            "lat: \"41.5519052\",\n"+
            "lon: \"-8.4219913\",\n"+
            "created_at: \"2016-02-20 14:47:40\",\n"+
            "updated_at: \"2016-02-20 14:47:40\"\n"+
            "},\n"+
            "{\n"+
            "id: \"59\",\n"+
            "name: \"T4 restaurante\",\n"+
            "picture: \"https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=CmRdAAAAukUNUo_gAS7Smda6ZoXH61rGRh7eY2qy35OLI6ERcM5oSQehF0l-3LA4LSKwVeNvTgJJ5iCIfrPcnJwlkJDQrY-wXjKGwwCt5dT_tgjckEtjL2Jz0RCHOZfh3lPWihQSEhDEZ7u2PJSgcjznxlvk5AoXGhQ3EOZLAyIL7yod4OgLe5AC1ZevXw&key=AIzaSyBxw50IM6rmADRuFJaNRAXXU1puN103q2w\",\n"+
            "lat: \"41.5357356\",\n"+
            "lon: \"-8.4361552\",\n"+
            "created_at: \"2016-02-20 14:47:40\",\n"+
            "updated_at: \"2016-02-20 14:47:40\"\n"+
            "},\n"+
            "{\n"+
            "id: \"60\",\n"+
            "name: \"Estúdio 22\",\n"+
            "picture: \"https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=CmRdAAAA4ARuno1lbJDPK-EUHpRAUGW1iS2Cc5CTjGORip2ySDHWzRv2YJM2Lks8m2-nt2iL2E1EH68abkUnElw0xpj5AF4KhGinjPwmhYh-pulv4yCLJ04MH4IkrhkyG4t8wnl7EhCiqLe2gDj0itTvzSYNwWaDGhTqka06Ie8iiwb0UJSPQaQoTXMjoQ&key=AIzaSyBxw50IM6rmADRuFJaNRAXXU1puN103q2w\",\n"+
            "lat: \"41.5496993\",\n"+
            "lon: \"-8.4280974\",\n"+
            "created_at: \"2016-02-20 14:47:40\",\n"+
            "updated_at: \"2016-02-20 14:47:40\"\n"+
            "},\n"+
            "{\n"+
            "id: \"61\",\n"+
            "name: \"Nova Bar- Esplanada Lda.\",\n"+
            "picture: \"https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=CmRdAAAAPNzyj3elKgjsYblyJk9__SVxRTRGNOxJFljZP7LK_n1sJ9mA51i9Fauomm7JhNLjeZChA-s80WjsLdk6Tr7heGSBTZf6H9dJ1h9_R4cnwSsjA6I-Xe_-M-dhuGqEqZAmEhBYJ-NqiLREWCX1v_lnppnQGhS_ilIj7Ps1EyEsf3VJkHBHxdBnZw&key=AIzaSyBxw50IM6rmADRuFJaNRAXXU1puN103q2w\",\n"+
            "lat: \"41.552549\",\n"+
            "lon: \"-8.426717\",\n"+
            "created_at: \"2016-02-20 14:47:40\",\n"+
            "updated_at: \"2016-02-20 14:47:40\"\n"+
            "},\n"+
            "{\n"+
            "id: \"62\",\n"+
            "name: \"Gare Caffé\",\n"+
            "picture: \"https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=CmRdAAAAaiWNzsPbagUiBZrLO_uY0O_mYbXX3aQx6ch41X2HXpH9LqFbF91wYitQxgHycYozDQ1-DfYi3mMkq9vX_DBGqQ7mwN2Wy_dpoXPe5JecEs-Uf9fII8VPBBl323lA9-2IEhChAGZkTZYX1CxfjT6KN-vAGhT8N0xHOiMg8itnX8kSDoKl-qMkfA&key=AIzaSyBxw50IM6rmADRuFJaNRAXXU1puN103q2w\",\n"+
            "lat: \"41.1736307\",\n"+
            "lon: \"-8.5472082\",\n"+
            "created_at: \"2016-02-20 14:47:40\",\n"+
            "updated_at: \"2016-02-20 14:47:40\"\n"+
            "},\n"+
            "{\n"+
            "id: \"63\",\n"+
            "name: \"Mirante Bar\",\n"+
            "picture: \"https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=CmRdAAAAJJAaaEd6sw43K_AeRjyfQaKe7y6vT-08DnKbb23flKHDXAwu2Y5WHxxFg7WJtmJvY2I020FBF0lVHQUvjh4gh7EjOJw0Scf7Zg966RTebbGLTxAVIGieZEWbWNGpiKVZEhCNY9KcLDn_XNAUoWYtGL6RGhRSjMAsSo2RHIdBOnuhHYOaoAAvmg&key=AIzaSyBxw50IM6rmADRuFJaNRAXXU1puN103q2w\",\n"+
            "lat: \"41.606153\",\n"+
            "lon: \"-8.433433\",\n"+
            "created_at: \"2016-02-20 14:47:40\",\n"+
            "updated_at: \"2016-02-20 14:47:40\"\n"+
            "},\n"+
            "{\n"+
            "id: \"64\",\n"+
            "name: \"LIFESTYLE club\",\n"+
            "picture: \"https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=CmRdAAAAkz1yl_z9Qc8I0jH8Cu9Va2WAJ5Mv06XpmQdVbVV3BTL2bCTaRDi3h7JAPQiUeja-A8eOsAVBQh1R_1yukGGe0F7mm3DvYfeFYS73sPR8bYaCY7MCrf7mf9ZFDJ3JeBgTEhCTylxpAVzYZM_aGKIxgaqfGhQzrYe0iES0p6cam6a68FUqYiD1AA&key=AIzaSyBxw50IM6rmADRuFJaNRAXXU1puN103q2w\",\n"+
            "lat: \"41.593318\",\n"+
            "lon: \"-8.43203\",\n"+
            "created_at: \"2016-02-20 14:47:40\",\n"+
            "updated_at: \"2016-02-20 14:47:40\"\n"+
            "},\n"+
            "{\n"+
            "id: \"65\",\n"+
            "name: \"Berber\",\n"+
            "picture: \"https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=CmRdAAAALPy7-8W2tvP7J9JfWhNspilVTSCOlSzAd1b-iU_fzeWs3kDIWLEN1lb7uhIzkRVeIOSuwB96u_sBmr37vBJ25jhUgXp7VSdC7SplftnLiDKojKS9SMPIgzkYouLb1s17EhBNBF1ala8ReKBXc9nQLe3EGhT2EOKwQZmbyYsEBBR68v002M65og&key=AIzaSyBxw50IM6rmADRuFJaNRAXXU1puN103q2w\",\n"+
            "lat: \"41.5559989\",\n"+
            "lon: \"-8.4248476\",\n"+
            "created_at: \"2016-02-20 14:47:40\",\n"+
            "updated_at: \"2016-02-20 14:47:40\"\n"+
            "},\n"+
            "{\n"+
            "id: \"66\",\n"+
            "name: \"Sardinha Biba\",\n"+
            "picture: \"https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=CmRdAAAAmSOoDxmcLmk77NK06BowegTWYs2FFDFCJV67V8QV8lwVoCUoB2Mpw3lP_5nS9LP1lcGnR96Fb_0W3CyE3inGcghfdE5k2eUbXNn1A4Y0pkjdMioIdrmneUD50vBwVZrdEhBukEe0wVmyT0jJyURtXshjGhQrRmSbG2MWzhxDHfDYsmpWd3nWaA&key=AIzaSyBxw50IM6rmADRuFJaNRAXXU1puN103q2w\",\n"+
            "lat: \"41.5458811\",\n"+
            "lon: \"-8.4155461\",\n"+
            "created_at: \"2016-02-20 14:47:40\",\n"+
            "updated_at: \"2016-02-20 14:47:40\"\n"+
            "},\n"+
            "{\n"+
            "id: \"67\",\n"+
            "name: \"RESTAURANTE MILHO REI\",\n"+
            "picture: \"https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=CmRdAAAAJ9Gmu_ikKS2f70NX-I-07ubEZv1PA6KjvyOWUoyV1IwmY_TO2EqzuC1Hb-JkYcDP7m_Sj8CkLv7wiOnAcGhb8eFgDnhEO6wsYBOS3CqSXGm__tigH70se3Q9TMxWdn9mEhBniI5VGsTC1ENtPu3bHhwLGhTJsLU6y3T3SZviHiGk22qZmF3L5A&key=AIzaSyBxw50IM6rmADRuFJaNRAXXU1puN103q2w\",\n"+
            "lat: \"41.6271853\",\n"+
            "lon: \"-8.367265\",\n"+
            "created_at: \"2016-02-20 14:47:40\",\n"+
            "updated_at: \"2016-02-20 14:47:40\"\n"+
            "},\n"+
            "{\n"+
            "id: \"68\",\n"+
            "name: \"Guimarães Studios Lounge\",\n"+
            "picture: \"https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=CmRdAAAAPxZ92zCGXea_FrXJuXYCoaalavZ-QyjA45fmwhjNgzB0z-eGPhqm_u5V2SSMX4_RMjEpaSyI6CT-w7-DfEAC5UkIO12uBdZLN_v9q-qCMjrzfPMQmzIAWO0FUpPIdIZpEhCWW0AnWs_X9X9Fs7iftNnfGhSdUKpsDbq2cHvf2-5D-H4l3fLGLQ&key=AIzaSyBxw50IM6rmADRuFJaNRAXXU1puN103q2w\",\n"+
            "lat: \"41.442601\",\n"+
            "lon: \"-8.294944\",\n"+
            "created_at: \"2016-02-20 14:47:40\",\n"+
            "updated_at: \"2016-02-20 14:47:40\"\n"+
            "},\n"+
            "{\n"+
            "id: \"69\",\n"+
            "name: \"Champanheria de Janes\",\n"+
            "picture: null,\n"+
            "lat: \"41.5502542\",\n"+
            "lon: \"-8.4248011\",\n"+
            "created_at: \"2016-02-20 14:47:40\",\n"+
            "updated_at: \"2016-02-20 14:47:40\"\n"+
            "},\n"+
            "{\n"+
            "id: \"70\",\n"+
            "name: \"Museu do Presunto Snack-bar\",\n"+
            "picture: \"https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=CmRdAAAA0bUkY2kVwpRwyhVqEDJ_UoTV7wR91lvJIKBd5FPnJ7dcXIyQ8KUv4YhnUamy_OndJlEVXsHZLb13kUnvQqRjHdiN_fpMrLcoZmPGpDyl5dGa7keeBXd-0vBsftSTsqD_EhD9KP2ikdzvmoQJWJAf5gK0GhRnjLFflsP5a9Y7H6JevV7YClH8AQ&key=AIzaSyBxw50IM6rmADRuFJaNRAXXU1puN103q2w\",\n"+
            "lat: \"41.444572\",\n"+
            "lon: \"-8.298228\",\n"+
            "created_at: \"2016-02-20 14:47:40\",\n"+
            "updated_at: \"2016-02-20 14:47:40\"\n"+
            "}\n"+
            "]";
} // End Of Class
