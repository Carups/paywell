package ru.spbau.mit.hackathon.paywell.projectdescription

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.yandex.money.api.methods.payment.params.P2pTransferParams
import org.json.JSONObject
import ru.spbau.mit.hackathon.paywell.R
import ru.yandex.money.android.PaymentActivity
import java.math.BigDecimal

class ProjectDescriptionActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.project_description)

        val projectName: TextView = findViewById(R.id.projectName)
        val projectOwner: TextView = findViewById(R.id.projectOwner)
        val projectFullDescription: TextView = findViewById(R.id.fullProjectDescription)

        val paramsMap = JSONObject(intent.getStringExtra("project_info"))

        projectName.text = paramsMap.getString("name")
        projectOwner.text = paramsMap.getString("owner")
        projectFullDescription.text = paramsMap.getString("full_desc")

        val donate1Button: Button = findViewById(R.id.pay1)
        val donate2Button: Button = findViewById(R.id.pay2)
        val donate3Button: Button = findViewById(R.id.pay3)

        val manualPaymentText: EditText = findViewById(R.id.manualPaymentText)
        manualPaymentText.imeOptions = EditorInfo.IME_ACTION_GO
        manualPaymentText.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_GO) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://money.yandex.ru/to/410015482994105" + "/${manualPaymentText.text}"))

                /*val intent = PaymentActivity.getBuilder(this)
                        .setPaymentParams(P2pTransferParams.Builder("410015482994105")
                                .setAmount(BigDecimal("${manualPaymentText.text}"))
                                .create())
                        .setClientId("E21FFF181F50DF09EA1C79783DBA014D69A3F228BD2495FC47EC0A166E91FEC3")
                        .setHost("https://money.yandex.ru")
                        .build();*/
                    startActivityForResult(intent, 1)
                true
            }
            false
        }

        donate1Button.setOnClickListener {
             /*   val intent = PaymentActivity.getBuilder(this)
                .setPaymentParams(P2pTransferParams.Builder("410015482994105")
                        .setAmount(BigDecimal("${donate1Button.text}"))
                        .create())
                .setClientId("E21FFF181F50DF09EA1C79783DBA014D69A3F228BD2495FC47EC0A166E91FEC3")
                .setHost("https://money.yandex.ru")
                .build();*/
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://money.yandex.ru/to"
                    + "/410015482994105/"
                    + "${donate1Button.text}"))
            startActivityForResult(intent, 1)
            }
        donate2Button.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://money.yandex.ru/to"
                    + "/410015482994105/"
                    + "${donate1Button.text}"))
            startActivityForResult(intent, 1)
        }
        donate3Button.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://money.yandex.ru/to"
                    + "/410015482994105/"
                    + "${donate1Button.text}"))
            startActivityForResult(intent, 1) }
    }
}
