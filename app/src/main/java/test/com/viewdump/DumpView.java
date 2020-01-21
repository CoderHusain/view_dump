package test.com.viewdump;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class DumpView extends AppCompatActivity {

    TextView mainTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dump_view);
        mainTextView = (TextView) findViewById(R.id.tv1);
        try {
            onSharedIntent();
        }catch (Exception e){
            e.printStackTrace();
        }
    }



//    private void onSharedIntent() {
//        Intent receiverdIntent = getIntent();
//        String receivedAction = receiverdIntent.getAction();
//        String receivedType = receiverdIntent.getType();
//
//            // check mime type
//            if (receivedType.startsWith("text/")) {
//
//                String receivedText = receiverdIntent
//                        .getStringExtra(Intent.EXTRA_TEXT);
//                if (receivedText != null) {
//                    System.out.println("--------------------------------------------------------------");
//                    mainTextView.setText(receivedText);
//                }
//                else{
//                    mainTextView.setText("Im an Ass");
//                }
//            }
//
//
//        else{
//            Toast.makeText(this, "onSharedIntent: nothing shared", Toast.LENGTH_SHORT).show();
//            System.out.println("onSharedIntent: nothing shared");
//
//        }
//    }

    public void onSharedIntent()throws IOException {

        Intent intent = getIntent();
        String receivedAction = intent.getAction();
        String receivedType = intent.getType();

        InputStream inputstream;
            Uri uri = intent.getClipData().getItemAt(0).getUri();
            inputstream = getContentResolver().openInputStream(uri);
        byte[] data = new byte[1024];
        int bytesRead = inputstream.read(data);

        StringBuilder chatContent = new StringBuilder();
        while (bytesRead != -1) {
                chatContent.append(new String(data));
            bytesRead = inputstream.read(data);
        }
    System.out.print("Hello");
        // TODO - Here we can do whatever we want with the chat content chatContent.toString()
        if (mainTextView != null){
            mainTextView.setText(chatContent.toString());
        }
    }
}
