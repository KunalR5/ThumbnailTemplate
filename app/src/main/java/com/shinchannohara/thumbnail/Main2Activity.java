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
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import top.defaults.colorpicker.ColorPickerPopup;

public class Main2Activity extends AppCompatActivity {

    int SELECT_PICTURE1 = 200;
    int SELECT_PICTURE2 = 300;
    ImageView back,icon;
    Button colourbtn;
    SeekBar tsize,rotate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        back = (ImageView)findViewById(R.id.imageView6);
        icon = (ImageView)findViewById(R.id.imageView5);

        final EditText fv,htc,tbg,bar,grill;
        fv = (EditText)findViewById(R.id.foodvlog);
        htc = (EditText)findViewById(R.id.htc);
        tbg = (EditText)findViewById(R.id.tbg);
        bar = (EditText)findViewById(R.id.bar);
        grill = (EditText)findViewById(R.id.grill);
        colourbtn = (Button)findViewById(R.id.change);
        tsize = (SeekBar)findViewById(R.id.tsize);
        rotate = (SeekBar)findViewById(R.id.rotate);

        Button button = (Button)findViewById(R.id.button);
        final ConstraintLayout constraintLayout = (ConstraintLayout)findViewById(R.id.constraintLayout2);

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

        fv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    // Offsets are for centering the TextView on the touch location
                    v.setX(event.getRawX() - v.getWidth() / 2.0f);
                    v.setY(event.getRawY() - v.getHeight() / 2.0f);
                }
                int a = fv.getCurrentTextColor();
                colourbtn.setBackgroundColor(a);
                colourbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new ColorPickerPopup.Builder(Main2Activity.this).initialColor(Color.RED)
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
                                        fv.setTextColor(color);
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
                        fv.setTextSize(temp);
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
                        fv.setRotation(temp);
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
        htc.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    // Offsets are for centering the TextView on the touch location
                    v.setX(event.getRawX() - v.getWidth() / 2.0f);
                    v.setY(event.getRawY() - v.getHeight() / 2.0f);
                }
                colourbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new ColorPickerPopup.Builder(Main2Activity.this).initialColor(Color.RED)
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
                                        htc.setTextColor(color);
                                        colourbtn.setBackgroundColor(color);
                                    }
                                });
                    }
                });
                int a = htc.getCurrentTextColor();
                colourbtn.setBackgroundColor(a);
                tsize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        tsize.setProgress(progress);
                        float temp = (float) ((float) (progress)*2);
                        htc.setTextSize(temp);
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
                        htc.setRotation(temp);
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
        tbg.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    // Offsets are for centering the TextView on the touch location
                    v.setX(event.getRawX() - v.getWidth() / 2.0f);
                    v.setY(event.getRawY() - v.getHeight() / 2.0f);
                }
                int a = tbg.getCurrentTextColor();
                colourbtn.setBackgroundColor(a);
                colourbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new ColorPickerPopup.Builder(Main2Activity.this).initialColor(Color.RED)
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
                                        tbg.setTextColor(color);
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
                        tbg.setTextSize(temp);
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
                        tbg.setRotation(temp);
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
        bar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    // Offsets are for centering the TextView on the touch location
                    v.setX(event.getRawX() - v.getWidth() / 2.0f);
                    v.setY(event.getRawY() - v.getHeight() / 2.0f);
                }
                int a = bar.getCurrentTextColor();
                colourbtn.setBackgroundColor(a);
                colourbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new ColorPickerPopup.Builder(Main2Activity.this).initialColor(Color.RED)
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
                                        bar.setTextColor(color);
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
                        bar.setTextSize(temp);
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
                        bar.setRotation(temp);
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
        grill.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    // Offsets are for centering the TextView on the touch location
                    v.setX(event.getRawX() - v.getWidth() / 2.0f);
                    v.setY(event.getRawY() - v.getHeight() / 2.0f);
                }
                int a = grill.getCurrentTextColor();
                colourbtn.setBackgroundColor(a);
                colourbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new ColorPickerPopup.Builder(Main2Activity.this).initialColor(Color.RED)
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
                                        grill.setTextColor(color);
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
                        grill.setTextSize(temp);
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
                        grill.setRotation(temp);
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

        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE2);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE1);
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
                    back.setImageURI(selectedImageUri);
                }
            }
            if (requestCode == SELECT_PICTURE2) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    icon.setImageURI(selectedImageUri);
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
