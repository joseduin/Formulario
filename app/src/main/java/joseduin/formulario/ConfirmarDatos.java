package joseduin.formulario;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ConfirmarDatos extends AppCompatActivity implements View.OnClickListener,
                                        NavigationView.OnNavigationItemSelectedListener {

    private TextView confNombre, confFechaNacimiento, confTelefono, confEmail, confDescripcion;
    private Button botonEditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_datos);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        enlazarControladorConVista();
        setListener();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            getExtras(bundle);
        }
    }

    private void getExtras(Bundle bundle) {
        Usuario user = (Usuario) bundle.getSerializable(getResources().getString(R.string.KEY_USUARIO));

        confNombre.setText(user.getNombre());
        confFechaNacimiento.setText(user.getFecha_nac());
        confTelefono.setText(user.getTelefono());
        confEmail.setText(user.getEmail());
        confDescripcion.setText(user.getDescripcion());
    }

    private void enlazarControladorConVista() {
        // TextView
        confNombre = (TextView) findViewById(R.id.confNombre);
        confFechaNacimiento = (TextView) findViewById(R.id.confFechaNacimiento);
        confTelefono = (TextView) findViewById(R.id.confTelefono);
        confEmail = (TextView) findViewById(R.id.confEmail);
        confDescripcion = (TextView) findViewById(R.id.confDescripcion);

        // Button
        botonEditar = (Button) findViewById(R.id.botonEditar);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK) {
            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void setListener() {
        botonEditar.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
