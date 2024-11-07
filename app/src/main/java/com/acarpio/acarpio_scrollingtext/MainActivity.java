package com.acarpio.acarpio_scrollingtext;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    private Button addComment;
    private Button publishComment;
    private Button editArticle;
    private Button publishArticle;
    private LinearLayout commentContainer;
    private AddComentFragment addComentFragment;
    private TextView articleText;
    String currentArticleText;
    EditText articleEditable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initializing views
        publishComment = findViewById(R.id.publishComment);
        addComment = findViewById(R.id.addComment);
        editArticle = findViewById(R.id.editArticle);
        commentContainer = findViewById(R.id.commentContainer);
        articleText = findViewById(R.id.article);
        publishArticle = findViewById(R.id.publishArticle);


        // Listeners
        addComment.setOnClickListener(v -> {
            loadFragment();
            publishComment.setVisibility(View.VISIBLE);
        });

        publishComment.setOnClickListener(v -> publishComment());

        editArticle.setOnClickListener(v -> editArticle());

        publishArticle.setOnClickListener(v -> publishArticle());






    }

    private void loadFragment() {
        AddComentFragment fragment = new AddComentFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        addComentFragment = fragment;
        fragmentTransaction.replace(R.id.articleContainer, fragment);
        fragmentTransaction.commit();
    }

    private String getFragmentText() {
        return addComentFragment.getTextoComentario();
    }

    private String getFragmentUserName() {
        return addComentFragment.getNombreUsuario();
    }

    private String getFragmentTitle() {
        return addComentFragment.getTituloComentario();
    }

    private void publishComment() {

        String usuario = getFragmentUserName();
        String titulo = getFragmentTitle();
        String comentario = getFragmentText();


        commentContainer.setVisibility(View.VISIBLE);


        TextView userNameView = new TextView(this);
        userNameView.setText("User: " + usuario);
        userNameView.setTextAppearance(Typeface.BOLD);

        TextView titleView = new TextView(this);
        titleView.setText("Title: " + titulo);
        titleView.setTextAppearance(Typeface.ITALIC);

        TextView commentView = new TextView(this);
        commentView.setText(comentario);


        commentContainer.addView(userNameView);
        commentContainer.addView(titleView);
        commentContainer.addView(commentView);

        // Removing the fragment

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.remove(addComentFragment);
        fragmentTransaction.commit();


        publishComment.setVisibility(View.INVISIBLE);

    }

    private void editArticle() {
        currentArticleText = articleText.getText().toString();
        articleEditable = new EditText(this);
        ViewGroup parentLayout = (ViewGroup) articleText.getParent();

        articleText.setVisibility(View.GONE);

        articleEditable.setLayoutParams(articleText.getLayoutParams());

        articleEditable.setText(currentArticleText);
        parentLayout.addView(articleEditable);
        articleEditable.requestFocus();
        publishArticle.setVisibility(View.VISIBLE);



    }

    private void publishArticle() {
        String newText = articleEditable.getText().toString();
        articleText.setText(newText);
        articleEditable.setVisibility(View.GONE);
        articleText.setVisibility(View.VISIBLE);
    }


}
