package com.ruptech.ai;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ruptech.ai.loading.LoadingHelloActivity;
import com.ruptech.ai.model.User;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


/**
 * A login screen that offers login via username/password.
 */
public class LoginActivity extends ActionBarActivity {
    private static final String TAG = LoginActivity.class.getName();

    // UI references.
    @InjectView(R.id.login_username)
    EditText mUsernameView;

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

    private void gotoMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void gotoLoadingHelloActivity() {
        Intent intent = new Intent(this, LoadingHelloActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);

        if (App.readUser() != null) {
            finish();
            gotoMainActivity();
            return;
        }

    }

    @OnClick(R.id.login_sign_in_button)
    public void doSign() {
        attemptLogin();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid username, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    public void attemptLogin() {

        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mUsernameView.setError(null);

        // Store values at the time of the login attempt.
        String username = mUsernameView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid username address.
        if (TextUtils.isEmpty(username)) {
            mUsernameView.setError(getString(R.string.error_field_required));
            focusView = mUsernameView;
            cancel = true;
        } else if (!isUsernameValid(username)) {
            mUsernameView.setError(getString(R.string.error_invalid_username));
            focusView = mUsernameView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            mAuthTask = new UserLoginTask(username, "");
            mAuthTask.execute((Void) null);
        }
    }

    private void afterLogin(Boolean login) {
        mAuthTask = null;

        if(null == login) {
            Toast.makeText(LoginActivity.this, getString(R.string.message_login_error), Toast.LENGTH_LONG).show();
        } else if (login) {
            gotoLoadingHelloActivity();
            finish();
        } else {
            mUsernameView.setError(getString(R.string.error_invalid_username));
            mUsernameView.requestFocus();
        }
    }

    private boolean isUsernameValid(String username) {
        return username.length() > 4;
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mUsername;
        private final String mPassword;
        private User user;
        private ProgressDialog progressDialog;

        UserLoginTask(String username, String password) {
            mUsername = username;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                user = new User(mUsername, mPassword);
                App.saveUser(user);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage(), e);
                return false;
            }

            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            afterLogin(success);
            if (progressDialog != null) {
                progressDialog.dismiss();
            }
        }

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(LoginActivity.this, LoginActivity.this.getString(R.string.progress_title), LoginActivity.this.getString(R.string.progress_message), true, false);
        }
    }
}



