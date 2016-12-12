package elevator.queue.gachon.queue;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity implements Button.OnClickListener {
    public Context context;
    int deptF=1, arriveF=1;
    String id = null;
    EditText idET;
    private Socket socket;

    private BufferedReader in;
    private PrintWriter out;

    private int port = 9999;
    private String ip = "110.9.105.26";

    @Override
    protected void onStop() {
        super.onStop();
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        idET = (EditText)findViewById(R.id.idEdit);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    port = 9999;
                    setSocket(ip, port);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }).start();

        Spinner departS = (Spinner) findViewById(R.id.depart);
        Spinner arriveS = (Spinner) findViewById(R.id.arrive);
        Button reqBtn = (Button) findViewById(R.id.reqeustBtn);

        departS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                deptF = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        arriveS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                arriveF = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        reqBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.reqeustBtn:
                try {
                    id = idET.getText().toString();
                    if(deptF == arriveF){
                        Toast.makeText(getApplicationContext(),
                                "Depart Floor == Arrive Floor Error!",Toast.LENGTH_SHORT).show();
                        Log.d("same","deptF == arriveF !! Error");
                        return;
                    }
                    sendReqeust();
                    Toast.makeText(getApplicationContext(),
                            "Request complete!",Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    public void sendReqeust() throws IOException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result;
                String request;

                request = id + ":" + (deptF) + ":" + (arriveF) + ":5";

                out.write(request+"\n");
                out.flush();
            }
        }).start();
    }

    public void setSocket(String ip, int port) throws IOException {

        try {
            socket = new Socket(ip, port);
            out = new PrintWriter(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

}
