package com.pingvini.mobilecooking;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseObject;

public class RecipeAddActivity extends AppCompatActivity {

    private String ingredients;
    private EditText ingredientsEdit;
    private EditText nameEdit;
    private EditText descEdit;
    private Button btnAddImage;
    private Button btnSave;
    private ImageView imgRecipe;
    private static int RESULT_LOAD_IMAGE = 1;
    private Bitmap bitmapRecipe;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_add);

        ingredientsEdit = (EditText)findViewById(R.id.recipe_ingredients_edit);
        nameEdit = (EditText)findViewById(R.id.recipe_name_edit);
        descEdit = (EditText)findViewById(R.id.recipe_desc_edit);
        btnAddImage = (Button)findViewById(R.id.btn_add_image);
        imgRecipe = (ImageView)findViewById(R.id.recipe_add_image);
        btnSave = (Button)findViewById(R.id.btn_save_recipe);
        ingredients = new String();

        final SpannableStringBuilder sb = new SpannableStringBuilder();
        ingredientsEdit = createIngredientsTextView(ingredients);
        Bitmap bitmap = (Bitmap) convertViewToDrawable(ingredientsEdit);
        BitmapDrawable bd = new BitmapDrawable(bitmap);
        bd.setBounds(0,0,bitmap.getWidth(), bitmap.getHeight());

        sb.append(ingredients + ",");
        sb.setSpan(new ImageSpan(bd), sb.length()-(ingredients.length()+1), sb.length()-1,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ingredientsEdit.setText(sb);

        btnAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseObject recipeObj = new ParseObject("Recipes");
                recipeObj.put("name", nameEdit.getText());
                recipeObj.put("description", descEdit.getText());
                recipeObj.put("ingredients", ingredientsEdit.getText());
                recipeObj.saveInBackground();
            }
        });
    }

    public EditText createIngredientsTextView(String text){
        //creating textview dynamically
        EditText tv = new EditText(this);
        tv.setText(text);
        tv.setTextSize(20);
        tv.setBackgroundResource(R.drawable.oval);
        tv.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_clear_search_api_holo_light, 0);
        return tv;
    }

    public static Bitmap convertViewToDrawable(View view) {
        int spec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(spec, spec);
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        Bitmap b = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        c.translate(-view.getScrollX(), -view.getScrollY());
        view.draw(c);
        view.setDrawingCacheEnabled(true);
        Bitmap cacheBmp = view.getDrawingCache();
        Bitmap viewBmp = cacheBmp.copy(Bitmap.Config.ARGB_8888, true);
        view.destroyDrawingCache();
        return viewBmp;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            ImageView imageView = (ImageView) findViewById(R.id.recipe_add_image);
            bitmapRecipe = BitmapFactory.decodeFile(picturePath);
            imageView.setImageBitmap(bitmapRecipe);
        }
    }
}
