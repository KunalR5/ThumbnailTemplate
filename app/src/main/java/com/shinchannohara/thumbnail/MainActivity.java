package com.shinchannohara.thumbnail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import top.defaults.colorpicker.ColorPickerPopup;

public class MainActivity extends AppCompatActivity {

    int SELECT_PICTURE1 = 200;
    int SELECT_PICTURE2 = 300;
    ImageView imageview,imageview2;
    Button colourbtn;
    SeekBar tsize,rotate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText one = (EditText)findViewById(R.id.one);
        final EditText two = (EditText)findViewById(R.id.two);
        final EditText three = (EditText)findViewById(R.id.three);
        imageview = (ImageView)findViewById(R.id.imageView);
        imageview2 = (ImageView)findViewById(R.id.imageView2);
        Button button = (Button)findViewById(R.id.button);
        colourbtn = (Button)findViewById(R.id.change);
        tsize = (SeekBar)findViewById(R.id.tsize);
        rotate = (SeekBar)findViewById(R.id.rotate);

        final ConstraintLayout constraintLayout = (ConstraintLayout)findViewById(R.id.constraintLayout);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Bitmap bitmap = getbitmapfromview(constraintLayout);

                    FileOutputStream outStream = null;
                    File path = Environment.getExternalStorageDirectory();
                    File dir = new File(path.getAbsolutePath() + "/YourFolderName");
                    dir.mkdirs();
                    String fileName = String.format("%d.jpg", System.currentTimeMillis());
                    File outFile = new File(dir, fileName);
                    outStream = new FileOutputStream(outFile);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
                    outStream.flush();
                    outStream.close();
                    Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    intent.setData(Uri.fromFile(outFile));
                    sendBroadcast(intent);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE1);
            }
        });

        imageview2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE2);
            }
        });

        one.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    // Offsets are for centering the TextView on the touch location
                    v.setX(event.getRawX() - v.getWidth() / 2.0f);
                    v.setY(event.getRawY() - v.getHeight() / 2.0f);
                }
                int a = one.getCurrentTextColor();
                colourbtn.setBackgroundColor(a);
                colourbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new ColorPickerPopup.Builder(MainActivity.this).initialColor(Color.RED)
                                .enableBrightness(true)
                                .enableAlpha(true)
                                .okTitle("Choose")
                                .cancelTitle("Cancel")
                                .showIndicator(true)
                                .showValue(true)
                                .build()
                                .show(v, new ColorPickerPopup.ColorPickerObserver() {
                                            @Override
                                            public void
                                            onColorPicked(int color) {
                                                one.setTextColor(color);
                                                colourbtn.setBackgroundColor(color);
                                            }
                                        });
                    }
                });
                tsize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        tsize.setProgress(progress);
                        float temp = (float) ((float) (progress)*2);
                        one.setTextSize(temp);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });
                rotate.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        rotate.setProgress(progress);
                        float temp = (float) ((float) (progress)*3.6);
                        one.setRotation(temp);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });
                return false;
            }
        });
        two.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    // Offsets are for centering the TextView on the touch location
                    v.setX(event.getRawX() - v.getWidth() / 2.0f);
                    v.setY(event.getRawY() - v.getHeight() / 2.0f);
                }
                int a = two.getCurrentTextColor();
                colourbtn.setBackgroundColor(a);
                colourbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new ColorPickerPopup.Builder(MainActivity.this).initialColor(Color.RED)
                                .enableBrightness(true)
                                .enableAlpha(true)
                                .okTitle("Choose")
                                .cancelTitle("Cancel")
                                .showIndicator(true)
                                .showValue(true)
                                .build()
                                .show(v, new ColorPickerPopup.ColorPickerObserver() {
                                    @Override
                                    public void
                                    onColorPicked(int color) {
                                        two.setTextColor(color);
                                        colourbtn.setBackgroundColor(color);
                                    }
                                });
                    }
                });
                tsize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        tsize.setProgress(progress);
                        float temp = (float) ((float) (progress)*2);
                        two.setTextSize(temp);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });
                rotate.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        rotate.setProgress(progress);
                        float temp = (float) ((float) (progress)*3.6);
                        two.setRotation(temp);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });
                return false;
            }
        });
        three.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    // Offsets are for centering the TextView on the touch location
                    v.setX(event.getRawX() - v.getWidth() / 2.0f);
                    v.setY(event.getRawY() - v.getHeight() / 2.0f);
                }
                int a = three.getCurrentTextColor();
                colourbtn.setBackgroundColor(a);
                colourbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new ColorPickerPopup.Builder(MainActivity.this).initialColor(Color.RED)
                                .enableBrightness(true)
                                .enableAlpha(true)
                                .okTitle("Choose")
                                .cancelTitle("Cancel")
                                .showIndicator(true)
                                .showValue(true)
                                .build()
                                .show(v, new ColorPickerPopup.ColorPickerObserver() {
                                    @Override
                                    public void
                                    onColorPicked(int color) {
                                        three.setTextColor(color);
                                        colourbtn.setBackgroundColor(color);
                                    }
                                });
                    }
                });
                tsize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        tsize.setProgress(progress);
                        float temp = (float) ((float) (progress)*2);
                        three.setTextSize(temp);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });
                rotate.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        rotate.setProgress(progress);
                        float temp = (float) ((float) (progress)*3.6);
                        three.setRotation(temp);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });
                return false;
            }
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE1) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    imageview.setImageURI(selectedImageUri);
                }
            }
            if (requestCode == SELECT_PICTURE2) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    imageview2.setImageURI(selectedImageUri);
                }
            }
        }
    }

    private Bitmap getbitmapfromview(View view)
    {
        Bitmap returnedbitmap = Bitmap.createBitmap(view.getWidth(),view.getHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedbitmap);
        Drawable bgdrawable = view.getBackground();
        if(bgdrawable!=null)
            bgdrawable.draw(canvas);
        view.draw(canvas);
        return returnedbitmap;
    }

}
