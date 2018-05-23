package com.hb.fatsecret.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hb.fatsecret.R;
import com.hb.fatsecret.activity.MainActivity;
import com.hb.fatsecret.activity.QuestionActivity;
import com.hb.fatsecret.preference.PreferenceManager;
import com.hb.fatsecret.utils.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Collection;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import butterknife.OnTextChanged;
import butterknife.Unbinder;

public class SignUpFragment extends Fragment {
    private static final String TAG = SignUpFragment.class.getSimpleName();

    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @BindView(R.id.edtPassword)
    EditText edtPassword;
    @BindView(R.id.btnNext)
    ImageButton btnNext;
    @BindView(R.id.layout)
    View layout;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private FirebaseAuth mAuth;

    Unbinder unbinder;
    private CallbackManager callbackManager;

    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.ivClose)
    void closeFragment(View view) {
        getFragmentManager().popBackStack();
    }

    @OnFocusChange(R.id.edtEmail)
    void focusEmail(View view, boolean focus) {
        if (focus) edtEmail.setAlpha(1);
        else edtEmail.setAlpha(Constants.ALPHA_HALF);
    }

    @OnFocusChange(R.id.edtPassword)
    void focusPassword(View view, boolean focus) {
        if (focus) edtPassword.setAlpha(1);
        else edtPassword.setAlpha(Constants.ALPHA_HALF);
    }

    @OnTextChanged(R.id.edtEmail)
    void textEmailChange(CharSequence text) {
//        Log.i(TAG, "Email change: " + text);
        if (TextUtils.isEmpty(text)) btnNext.setAlpha(Constants.ALPHA_HALF);
        else {
            if (edtPassword.getText().length() < Constants.MIN_PASSWORD_LENGTH)
                btnNext.setAlpha(Constants.ALPHA_HALF);
            else btnNext.setAlpha(Float.valueOf(1));
        }
    }

    @OnTextChanged(R.id.edtPassword)
    void textPasswordChange(CharSequence text) {
//        Log.i(TAG, "Password change: " + text);
        if (text.length() < Constants.MIN_PASSWORD_LENGTH)
            btnNext.setAlpha(Constants.ALPHA_HALF);
        else {
            if (TextUtils.isEmpty(edtEmail.getText())) btnNext.setAlpha(Constants.ALPHA_HALF);
            else btnNext.setAlpha(Float.valueOf(1));
        }
    }

    @OnClick(R.id.btnNext)
    void clickNext(View view) {
        if (btnNext.getAlpha() < 1) return;
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();
        QuestionActivity.userObject.setEmail(email);
        QuestionActivity.userObject.setPassword(password);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.i(TAG, "createUserWithEmail: success");
                            goToMainActivity();
                        } else {
                            Exception e = task.getException();
                            Log.e(TAG, "Exception " + e.getMessage());
                            e.printStackTrace();
                            Toast.makeText(getContext(),
                                    getResources().getString(R.string.error) + e.getMessage(),
                                    Toast.LENGTH_SHORT)
                                    .show();
                        }
                    }
                });
    }

    private void saveUserToFirebase() {
        FirebaseUser firebaseUser= mAuth.getCurrentUser();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference()
                .child(Constants.FIELD_USER_FIREBASE).child(firebaseUser.getUid());
        ref.child(Constants.FIELD_USER_INFOR_FIREBASE).setValue(QuestionActivity.userObject);
    }

    @OnClick(R.id.llSignUpFacebook)
    void signUpFacebook(View view) {
        Log.i(TAG, "Click login facebook");
        callbackManager = CallbackManager.Factory.create();
        LoginManager loginManager = LoginManager.getInstance();
        loginManager.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookAccessToken(loginResult.getAccessToken());
                Log.i(TAG, "Login facebook success");
            }

            @Override
            public void onCancel() {
                Toast.makeText(getContext(), getResources().getString(R.string.fb_cancel), Toast.LENGTH_SHORT)
                        .show();
                Log.i(TAG, "Login facebook success");
            }

            @Override
            public void onError(FacebookException error) {
                Log.e(TAG, "Exception " + error.toString());
                error.printStackTrace();
                Toast.makeText(getContext(), getResources().getString(R.string.fb_error), Toast.LENGTH_SHORT)
                        .show();
            }
        });

        Collection<String> permissions = Arrays.asList("email", "user_gender");

        loginManager.logInWithReadPermissions(this, permissions);
    }

    @OnClick(R.id.llSignupGoogle)
    void signUpGoogle(View view) {
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(getContext(), gso);
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, Constants.RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        layout.setAlpha(Constants.ALPHA_HALF);
        progressBar.setVisibility(View.VISIBLE);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == Constants.RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        } else {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void handleFacebookAccessToken(final AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);
        layout.setAlpha(Constants.ALPHA_HALF);
        progressBar.setVisibility(View.VISIBLE);

        GraphRequest request = GraphRequest.newMeRequest(token, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                Log.i("LoginActivity", response.toString());
                // Get facebook data from login
                Bundle bFacebookData = getFacebookData(object);
                String email = bFacebookData.getString("email");
                if (TextUtils.isEmpty(email)){
                    Toast.makeText(getContext(),
                            getResources().getString(R.string.email_not_found),
                            Toast.LENGTH_SHORT).show();
                    layout.setAlpha(Float.valueOf(1));
                    progressBar.setVisibility(View.INVISIBLE);
                    return;
                }

                AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
                mAuth.signInWithCredential(credential)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.i(TAG, "signInWithCredential:success");
                                    goToMainActivity();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.e(TAG, "signInWithCredential:failure", task.getException());
                                    Toast.makeText(getContext(),
                                            getResources().getString(R.string.authentication_failed),
                                            Toast.LENGTH_SHORT)
                                            .show();
                                }
                            }
                        });
                layout.setAlpha(Float.valueOf(1));
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "email, gender");
        request.setParameters(parameters);
        request.executeAsync();
    }

    private Bundle getFacebookData(JSONObject object) {
        try {
            Bundle bundle = new Bundle();
            String id = object.getString("id");

            bundle.putString("idFacebook", id);
            if (object.has("email"))
                bundle.putString("email", object.getString("email"));
            if (object.has("gender"))
                bundle.putString("gender", object.getString("gender"));
            return bundle;
        }
        catch(JSONException e) {
            Log.d(TAG,"Error parsing JSON");
        }
        return null;
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            firebaseAuthWithGoogle(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.e(TAG, "signInResult:failed code = " + e.getStatusCode());
            e.printStackTrace();
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.i(TAG, "firebaseAuthWithGoogle: " + acct.getId());
        if (TextUtils.isEmpty(acct.getEmail())){
            Toast.makeText(getContext(),
                    getResources().getString(R.string.email_not_found),
                    Toast.LENGTH_SHORT).show();
            return;
        }

        layout.setAlpha(Constants.ALPHA_HALF);
        progressBar.setVisibility(View.VISIBLE);

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.i(TAG, "signInWithCredential:success");
                            goToMainActivity();
                        } else {
                            Exception e = task.getException();
                            e.printStackTrace();
                            // If sign in fails, display a message to the user.
                            Log.e(TAG, "signInWithCredential:failure", e);
                            Toast.makeText(getContext(),
                                    getResources().getString(R.string.error) + e,
                                    Toast.LENGTH_SHORT)
                                    .show();
                        }
                    }
                });

        layout.setAlpha(Float.valueOf(1));
        progressBar.setVisibility(View.INVISIBLE);
    }

    private void goToMainActivity() {
        saveUserToFirebase();
        PreferenceManager pre = new PreferenceManager(getContext());
        pre.setFirstTimeLaunch(false);

        Intent intent = new Intent(getContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
