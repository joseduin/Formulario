package joseduin.formulario;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
                                                    TextInputEditText.OnEditorActionListener {

    private TextInputLayout text_input_layout_nombre, text_input_layout_fecha,
            text_input_layout_telefono, text_input_layout_email, text_input_layout_descripcion;
    private TextInputEditText editTextNombre, editTextFecha, editTextTelefono, editTextEmail,
            editTextDescripcion;
    private Button botonSiguiente;
    private ImageButton butonFechaNacimiento;

    private boolean camposVacios = false;

    // Datapicker constants
    private int year_x, month_x, day_x;
    private static final int DIALOG_ID = 0;
    private String[] meses = new String[11];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enlazarControladorConVista();
        setListener();
        setDateToDataPicker();

        editTextFecha.setEnabled(false);
    }

    private void setDateToDataPicker() {
        meses = getResources().getStringArray(R.array.meses);

        Calendar cal = Calendar.getInstance();
        day_x = cal.get(Calendar.DAY_OF_MONTH);
        month_x = cal.get(Calendar.MONTH);
        year_x = 1990; // Suponiendo que todos son mayores de edad y en promedio de los 90's
    }

    private void enlazarControladorConVista() {
        // TextInputLayout
        text_input_layout_nombre = (TextInputLayout) findViewById(R.id.text_input_layout_nombre);
        text_input_layout_fecha = (TextInputLayout) findViewById(R.id.text_input_layout_fecha);
        text_input_layout_telefono = (TextInputLayout) findViewById(R.id.text_input_layout_telefono);
        text_input_layout_email = (TextInputLayout) findViewById(R.id.text_input_layout_email);
        text_input_layout_descripcion = (TextInputLayout) findViewById(R.id.text_input_layout_descripcion);

        // TextInputEditText
        editTextNombre = (TextInputEditText) findViewById(R.id.editTextNombre);
        editTextFecha = (TextInputEditText) findViewById(R.id.editTextFecha);
        editTextTelefono = (TextInputEditText) findViewById(R.id.editTextTelefono);
        editTextEmail = (TextInputEditText) findViewById(R.id.editTextEmail);
        editTextDescripcion = (TextInputEditText) findViewById(R.id.editTextDescripcion);

        // Button
        botonSiguiente = (Button) findViewById(R.id.botonSiguiente);

        // ImageButton
        butonFechaNacimiento = (ImageButton) findViewById(R.id.butonFechaNacimiento);
    }

    private void setListener() {
        botonSiguiente.setOnClickListener(this);
        butonFechaNacimiento.setOnClickListener(this);
        editTextNombre.setOnEditorActionListener(this);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_ID) {
            return new DatePickerDialog(this, dpickerListener, year_x, month_x, day_x);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener dpickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            year_x = year;
            month_x = month;
            day_x = dayOfMonth;
            editTextFecha.setText(day_x + "/" + (month_x + 1) + "/" +  year_x);
        }
    };

    private void validarCampos() {
        camposVacios = false;
        validarCampo(text_input_layout_nombre, editTextNombre);
        validarCampo(text_input_layout_fecha, editTextFecha);
        validarCampo(text_input_layout_telefono, editTextTelefono);
        validarCampo(text_input_layout_email, editTextEmail);
        validarCampo(text_input_layout_descripcion, editTextDescripcion);
        if (!camposVacios) {
            prepararDatos();
        }
    }

    private void validarCampo(TextInputLayout textInput, TextInputEditText editText) {
        String obligatorio = TextUtils.isEmpty(editText.getText()) ?
                                getResources().getString(R.string.campo_obligatorio) : null;
        toggleTextInputLayoutError(textInput, obligatorio);
    }

    private void toggleTextInputLayoutError(TextInputLayout textInputLayout, String msg) {
        textInputLayout.setError(msg);
        textInputLayout.setErrorEnabled((msg == null) ? false : true);
        if (msg != null) {
            camposVacios = true;
        }
    }

    private void prepararDatos() {
            Usuario user = new Usuario(editTextNombre.getText().toString(),
                    editTextFecha.getText().toString(),
                    editTextTelefono.getText().toString(),
                    editTextEmail.getText().toString(),
                    editTextDescripcion.getText().toString());

            irConfirmarDatos(user);
    }

    private void irConfirmarDatos(Usuario user) {
        Intent intent = new Intent(this, ConfirmarDatos.class);
        intent.putExtra(getResources().getString(R.string.KEY_USUARIO), user);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.botonSiguiente:
                validarCampos();
                break;
            case R.id.butonFechaNacimiento:
                showDialog(DIALOG_ID);
                break;
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        showDialog(DIALOG_ID);
        return false;
    }
}