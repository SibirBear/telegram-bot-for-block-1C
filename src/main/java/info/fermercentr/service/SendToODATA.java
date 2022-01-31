package info.fermercentr.service;

import info.fermercentr.store.SessionData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Base64;

public final class SendToODATA {

    private static final Logger log = LogManager.getLogger(SendToODATA.class);
    private static final String REGISTER_TYPE_ODATA = "/InformationRegister";
    private static final String REGISTER_ODATA = "_ум_БлокировкаВходаВПрограмму";

    public static boolean send(final long userId, final SessionData sd) {
        final String url = sd.getOrder(userId).getOdata().getUrl();
        final String user = sd.getOrder(userId).getOdata().getUser();
        final String pass = sd.getOrder(userId).getOdata().getPass();
        final boolean action = sd.getOrder(userId).isActionBlock();
        final String date = sd.getOrder(userId).getDate();
        final String time = sd.getOrder(userId).getTime();

        boolean result = false;

        final String logBase64 = "Basic " + Base64.getEncoder().encodeToString((user + ":" + pass).getBytes());

        String dateTime = null;

        if (date != null && time != null) {
            dateTime = date + "T" + time + ":00";
        }

        log.info("[ODATA] - Trying to sent record to 1C ODATA...");

        try {
            URL connectUrl = new URL(url + REGISTER_TYPE_ODATA
                    + URLEncoder.encode(REGISTER_ODATA, "utf-8"));
            HttpURLConnection con = (HttpURLConnection) connectUrl.openConnection();
            con.setDoOutput(true);

            con.setRequestMethod("POST");
            con.setRequestProperty("Authorization", logBase64);
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Content-type", "application/json");
            con.setRequestProperty("Content-type", "odata.metadata=minimal");
            con.setRequestProperty("Accept-Charset", String.valueOf(StandardCharsets.UTF_8));

            StringBuilder sb = getJSONString(action, dateTime);

            String s = sb.toString();
            byte[] out = s.getBytes(StandardCharsets.UTF_8);
            OutputStream os = con.getOutputStream();
            os.write(out);

            result = con.getResponseCode() == 200;

            if (!result) {
                InputStream er = con.getErrorStream();
                String err;
                BufferedReader br = new BufferedReader(new InputStreamReader(er));

                while((err = br.readLine()) != null) {
                    log.error("[ODATA] " + err);
                }
            }

            con.disconnect();

            log.info("[ODATA] - Sending record to 1C ODATA successful.");

        } catch (IOException e) {
            log.error("[ODATA] - Error sending record. " + e.getMessage());
            e.printStackTrace();
        }

        return result;

    }

    private static StringBuilder getJSONString(boolean pAction, String pDateTime) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{\"Period\":\"")
                .append(LocalDateTime.now().withNano(0))
                .append("\",\"Запрет\":\"")
                .append(pAction).append("\"");
        if (pDateTime != null) {
            stringBuilder.append(",\"ДатаВремяЗапрета\":\"").append(pDateTime).append("\"");
        }
        stringBuilder.append("}");

        return stringBuilder;
    }

}
